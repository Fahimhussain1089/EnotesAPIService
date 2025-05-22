package com.hussain.service;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.hussain.dto.EmailRequest;
import com.hussain.dto.PasswordChngRequest;
import com.hussain.dto.PswdResetRequest;
import com.hussain.entity.User;
import com.hussain.exception.ResourceNotFoundException;
import com.hussain.service.Impl.UserRepository;
//import com.hussain.repository.UserRepository;
import com.hussain.service.UserService;
import com.hussain.util.CommonUtil;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepo;
	
	@Autowired 
	private EmailService emailService;
	

	@Override
	public void changePassword(PasswordChngRequest passwordRequest) {

		User logedInUser = CommonUtil.getLoggedInUser();

		if (!passwordEncoder.matches(passwordRequest.getOldPassword(), logedInUser.getPassword())) {
			throw new IllegalArgumentException("Old Password is incorrect !!");
		}
		String encodePassword = passwordEncoder.encode(passwordRequest.getNewPassword());
		logedInUser.setPassword(encodePassword);
		userRepo.save(logedInUser);
	}

	@Override
	public void sendEmailPasswordReset(String email,HttpServletRequest request) throws Exception {
		User user = userRepo.findByEmail(email);
		if(ObjectUtils.isEmpty(user)){
		throw new ResourceNotFoundException("Invalid email");
		}
		//Generate unique password reset token
		String passwordResetToken  = UUID.randomUUID().toString();
		user.getStatus().setVerificationCode(passwordResetToken);
		User updateUser  = userRepo.save(user);

		//_______________________________
		
		String url = CommonUtil.getUrl(request);
		sendEmailRequest(updateUser,url);

		
	}
	
	@Override
	public void verifyPasswordLink(Integer uid, String code) throws Exception {
		
		User user = userRepo.findById(uid).orElseThrow(()-> new ResourceNotFoundException("invalid user"));
		verifyPasswordResetToken(user.getStatus().getPasswordResetToken(),code);

		
		
	}
	@Override
	public void resetPassword(PswdResetRequest pswdResetRequest) throws Exception {
		User user = userRepo.findById(pswdResetRequest.getUid()).orElseThrow(()->new ResourceNotFoundException("invalid user"));
		String encodePassword = passwordEncoder.encode(pswdResetRequest.getNewPassword());
		user.setPassword(encodePassword);
		user.getStatus().setPasswordResetToken(null);
		userRepo.save(user);
	}
	
	
	//_________________________here is the methode___________________________
	
	private void sendEmailRequest(User user,String url) throws Exception{
		
		
		

		String message="Hi,<b>[[username]]</b> "
				+ "<br> Your account register sucessfully.<br>"
				+"<br> Click the below link verify & Active your account <br>"
				+"<a href='[[url]]'>Click Here</a> <br><br>"
				+"Thanks,<br>Enotes.com"
				;
		System.out.println("url yan pe hai check kro :: \n "+url);
		System.out.println("url yan pe hai check kro :: \n "+url);
		message=message.replace("[[username]]", user.getFirstName());
//		message=message.replace("[[url]]", url+"/api/v1/home/verify-pswd-link?uid="+user.getId()+"&&code="+user.getStatus().getPasswordResetToken());
		message = message.replace("[[url]]", url + "/api/v1/home/verify-pswd-link?uid=" + user.getId() + "&&code="
				+ user.getStatus().getPasswordResetToken());

			
//			EmailRequest emailRequest = EmailRequest.builder()
//					.to(saveUser.getEmail())
//					.title("Account Creating Confirmation")
//					.subject("Account Created Success")
//					.message(message)
//					.build();
			EmailRequest emailRequest = new EmailRequest();
			emailRequest.setTo(user.getEmail());
			emailRequest.setTitle("Password Reset ");
			emailRequest.setSubject("Password Reset  Successfuly");
			emailRequest.setMessage(message);

		//return  emailRequest;
			emailService.sendEmail(emailRequest);
		}
	
	private void verifyPasswordResetToken(String existToken, String reqToken){
		// request token not null
				if(StringUtils.hasText(reqToken))
				{
					// password already reset
					if(!StringUtils.hasText(existToken))
					{
						throw new IllegalArgumentException("Already Password reset");
					}
					// user req token changes
					if(!existToken.equals(reqToken))
					{
						throw new IllegalArgumentException("invalid url");
					}
				}else {
					throw new IllegalArgumentException("invalid token");
				}
		
	}





	
 

}