package com.hussain.controller;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hussain.dto.PasswordChngRequest;
import com.hussain.dto.UserResponse;
import com.hussain.endpoint.UserEndpoint;
import com.hussain.entity.User;
import com.hussain.service.UserService;
import com.hussain.util.CommonUtil;

@RestController
@RequestMapping("/api/v1/user")
public class UserController  implements UserEndpoint {

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private UserService userService;
	
//	@GetMapping("/profile")
	@Override
	public ResponseEntity<?> getProfile()
	{
		User loggedInUser = CommonUtil.getLoggedInUser();
		UserResponse userResponse = mapper.map(loggedInUser, UserResponse.class);
		return CommonUtil.createBuildResponse(userResponse, HttpStatus.OK);
	}
	
	
//	@PostMapping("/chng-pswd")
	@Override
	public ResponseEntity<?> changePassword(@RequestBody PasswordChngRequest passwordRequest) {
		userService.changePassword(passwordRequest);
		return CommonUtil.createBuildResponseMessage("Password change success", HttpStatus.OK);
	}

	
}
