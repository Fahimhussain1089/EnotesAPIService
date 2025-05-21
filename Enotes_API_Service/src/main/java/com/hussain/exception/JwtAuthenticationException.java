package com.hussain.exception;

public class JwtAuthenticationException extends RuntimeException {

	public JwtAuthenticationException(String message) {
		super(message);
	}

}
