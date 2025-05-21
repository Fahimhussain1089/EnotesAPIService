package com.hussain.service;

import com.hussain.dto.LoginRequest;
import com.hussain.dto.LoginResponse;

//public class UserService {
//
//}

import com.hussain.dto.UserRequest;

public interface AuthService {

	public Boolean register(UserRequest userDto, String url) throws Exception;
	
	public LoginResponse login(LoginRequest loginRequest);

	
}