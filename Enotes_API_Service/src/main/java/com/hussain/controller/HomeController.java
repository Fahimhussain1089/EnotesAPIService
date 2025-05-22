package com.hussain.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hussain.dto.PswdResetRequest;
import com.hussain.endpoint.HomeEndpoint;
import com.hussain.service.HomeService;
import com.hussain.service.UserService;
import com.hussain.util.CommonUtil;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/home")
public class HomeController implements HomeEndpoint{

	@Autowired
	private HomeService homeService;
	
	@Autowired
	private UserService userService;

	//@GetMapping("/verify")
	@Override
	public ResponseEntity<?> verifyUserAccount(@RequestParam Integer uid, @RequestParam String code) throws Exception {
		Boolean verifyAccount = homeService.verifyAccount(uid, code);
		if (verifyAccount)
			return CommonUtil.createBuildResponseMessage("Account verification success", HttpStatus.OK);
		return CommonUtil.createErrorResponseMessage("Invalid Verification link", HttpStatus.BAD_REQUEST);
	}
	
	
	//@GetMapping("/send-emali-reset")
	@Override
	public ResponseEntity<?> sendEmailForPasswordReset(@RequestParam String email,HttpServletRequest request) throws Exception{
		
		userService.sendEmailPasswordReset(email,request);
		return CommonUtil.createBuildResponseMessage("Email send successFull !! Check Email Reset password",HttpStatus.OK);


	}

	
	@Override
	public ResponseEntity<?> verifyPasswordResetLink(Integer uid, String code) throws Exception {
		userService.verifyPasswordLink(uid,code);
		return CommonUtil.createBuildResponseMessage("verifycation success",HttpStatus.OK);

	}
	
	
	//@GetMapping("/reset-pswd")
	@Override
	public ResponseEntity<?> resetPassword(@RequestBody PswdResetRequest pswdResetRequest)throws Exception{
		userService.resetPassword(pswdResetRequest);
		return CommonUtil.createBuildResponseMessage("Password reset succes", HttpStatus.OK);

	//return null;
	}


	


}
