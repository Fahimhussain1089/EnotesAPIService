package com.hussain.service.Impl;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.hussain.config.security.CustomUserDetails;
import com.hussain.dto.EmailRequest;
import com.hussain.dto.LoginRequest;
import com.hussain.dto.LoginResponse;
import com.hussain.dto.UserRequest;
import com.hussain.dto.UserResponse;
import com.hussain.entity.AccountStatus;
import com.hussain.entity.Role;
import com.hussain.entity.User;
import com.hussain.repo.RoleRepository;
import com.hussain.service.EmailService;
///import com.hussain.repository.RoleRepository;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.authentication.AuthenticationManager;
import com.hussain.service.JwtService;
//import com.hussain.repository.UserRepository;
import com.hussain.service.AuthService;
import com.hussain.util.Validation;

//import jakarta.transaction.Transactional;
//import org.springframework.transaction.annotation.Transactional; // ← Correct import

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private RoleRepository roleRepo;

	@Autowired
	private Validation validation;

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtService jwtService;

	@Override
	public Boolean register(UserRequest userDto,String url) throws Exception {

		validation.userValidation(userDto);
		User user = mapper.map(userDto, User.class);

		setRole(userDto, user);
		
		//____________________________________________
//		AccountStatus status=AccountStatus.builder()
//				.isActive(false)
//				.verificationCode(UUID.randomUUID().toString())
//				.build();
//		user.setStatus(status);
		
		AccountStatus status = new AccountStatus();
		status.setIsActive(false);
		user.setPassword(passwordEncoder.encode(user.getPassword())); //that is save the password in Bcrpt format
		status.setVerificationCode(UUID.randomUUID().toString());

		user.setStatus(status);

		
		//____________________________________________

		User saveUser = userRepo.save(user);
		if (!ObjectUtils.isEmpty(saveUser)) {
			// send email
			emailSend(saveUser,url);
			
			return true;
		}
		return false;
	}
	
	/*
	 The @Transactional keeps the session open until the method completes.

	 Works with default FetchType.LAZY
	  
	 */
	//@Transactional 
	@Override
    public LoginResponse login(LoginRequest loginRequest) throws AuthenticationException {
        Authentication authenticate = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword()
            )
        );

        if (authenticate.isAuthenticated()) {
            CustomUserDetails customUserDetails = (CustomUserDetails) authenticate.getPrincipal();

//            String token = "safdghhfdssaghnggsdsgfvswaefqwaef";
			String token=jwtService.generateToken(customUserDetails.getUser());


//            return LoginResponse.Builder()
//                .user(mapper.map(customUserDetails.getUser(), UserDto.class))
//                .token(token)
//                .build();
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setUser(mapper.map(customUserDetails.getUser(), UserResponse.class));
            loginResponse.setToken(token);
            return loginResponse;

        }

        return null;
    }

	
	
	//**********************************here is the methode*****************************
	
	private void emailSend(User saveUser, String url) throws Exception {
	
		String message="Hi,<b>[[username]]</b> "
				+ "<br> Your account register sucessfully.<br>"
				+"<br> Click the below link verify & Active your account <br>"
				+"<a href='[[url]]'>Click Here</a> <br><br>"
				+"Thanks,<br>Enotes.com"
				;
		System.out.println("url yan pe hai check kro :: \n "+url);
		message=message.replace("[[username]]", saveUser.getFirstName());
		message=message.replace("[[url]]", url+"/api/v1/home/verify?uid="+saveUser.getId()+"&&code="+saveUser.getStatus().getVerificationCode());
		
			
//			EmailRequest emailRequest = EmailRequest.builder()
//					.to(saveUser.getEmail())
//					.title("Account Creating Confirmation")
//					.subject("Account Created Success")
//					.message(message)
//					.build();
			EmailRequest emailRequest = new EmailRequest();
			emailRequest.setTo(saveUser.getEmail());
			emailRequest.setTitle("Account Creating Confirmation");
			emailRequest.setSubject("Account Created Success");
			emailRequest.setMessage(message);

			emailService.sendEmail(emailRequest);
		}

//	@Override
//	public LoginResponse login(LoginRequest loginRequest) throws AuthenticationException {
//
//		Authentication authenticate = AuthenticationManager.authenticate(
//				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
//
//		if(authenticate.isAuthenticated())
//		{
//			CustomUserDetails customUserDetails= 
//					(CustomUserDetails)authenticate.getPrincipal();
//			
//			String token="safdghhfdssaghnggsdsgfvswaefqwaef";
//			
//			LoginResponse loginResponse=LoginResponse.builder()
//					.user(mapper.map(customUserDetails.getUser(), UserDto.class))
//					.token(token)
//					.build();
//			return loginResponse;
//		}
//		
//		return null;
//	}
	
	
	private void setRole(UserRequest userDto, User user) {
		List<Integer> reqRoleId = userDto.getRoles().stream().map(r -> r.getId()).toList();
		List<Role> roles = roleRepo.findAllById(reqRoleId);
		user.setRoles(roles);//setRoles
	}
	
	//__________________________________________________________________________________
	
//	private void setRole(UserDto userDto, User user) {
//	    List<Integer> reqRoleId = userDto.getRoles().stream()
//	        .map(r -> r.getId())
//	        .toList();
//	    List<Role> roles = roleRepo.findAllById(reqRoleId);
//	    user.setRoles(roles);
//	}


	
	

}