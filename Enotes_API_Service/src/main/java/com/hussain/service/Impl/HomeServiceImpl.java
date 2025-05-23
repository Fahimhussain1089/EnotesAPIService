package com.hussain.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hussain.entity.AccountStatus;
import com.hussain.entity.User;
import com.hussain.exception.ResourceNotFoundException;
import com.hussain.exception.SuccessException;
//import com.hussain.exception.SuccessException;
//import com.hussain.repository.UserRepository;
import com.hussain.service.HomeService;

@Component
public class HomeServiceImpl implements HomeService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public Boolean verifyAccount(Integer userId, String verificationCode) throws Exception {

		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("invalid user"));

		if(user.getStatus().getVerificationCode()==null)
		{
			throw new SuccessException("Account alreday verified");
		}
		
		if (user.getStatus().getVerificationCode().equals(verificationCode)) {
			AccountStatus status = user.getStatus();
			status.setIsActive(true);
			status.setVerificationCode(null);

			userRepo.save(user);

			return true;
		}

		return false;
	}

}