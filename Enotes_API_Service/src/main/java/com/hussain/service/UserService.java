package com.hussain.service;

import com.hussain.dto.PasswordChngRequest;
import com.hussain.dto.PswdResetRequest;

import jakarta.servlet.http.HttpServletRequest;

public interface UserService {
	
	public void changePassword(PasswordChngRequest passwordRequest);
	
	public void sendEmailPasswordReset(String email,HttpServletRequest request) throws Exception;
	
	public void verifyPasswordLink(Integer uid, String code) throws Exception;
	
	public void resetPassword(PswdResetRequest pswdResetRequest) throws Exception;

}
