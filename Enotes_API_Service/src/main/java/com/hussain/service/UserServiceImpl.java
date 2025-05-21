package com.hussain.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hussain.dto.PasswordChngRequest;
import com.hussain.entity.User;
import com.hussain.service.Impl.UserRepository;
//import com.hussain.repository.UserRepository;
import com.hussain.service.UserService;
import com.hussain.util.CommonUtil;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepo;

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

}