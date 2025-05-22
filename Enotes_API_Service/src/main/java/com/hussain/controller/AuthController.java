package com.hussain.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hussain.dto.LoginRequest;
import com.hussain.dto.LoginResponse;
import com.hussain.dto.UserRequest;
import com.hussain.endpoint.AuthEndpoint;
import com.hussain.endpoint.HomeEndpoint;
import com.hussain.service.AuthService;
import com.hussain.util.CommonUtil;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController implements AuthEndpoint{

	@Autowired
	private AuthService authService;

//	@PostMapping("/")
	@Override
	public ResponseEntity<?> registerUser(@RequestBody UserRequest userDto,HttpServletRequest request) throws Exception {
		String url=CommonUtil.getUrl(request);
		Boolean register = authService.register(userDto,url);
		if (register) {
			return CommonUtil.createBuildResponseMessage("Register success", HttpStatus.CREATED);
		}
		return CommonUtil.createErrorResponseMessage("Register failed", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
//	@PostMapping("/login")
	@Override
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws Exception {

		LoginResponse loginResponse = authService.login(loginRequest);
		if (ObjectUtils.isEmpty(loginResponse)) {
			return CommonUtil.createErrorResponseMessage("invalid credential", HttpStatus.BAD_REQUEST);
		}
		return CommonUtil.createBuildResponse(loginResponse,HttpStatus.OK);
	}


}