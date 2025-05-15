package com.hussain.util;

//public class CommonUtil {
//
//}


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

//import com.hussain.handler.GenericResponse;

import com.hussain.handler.GenericResponse;

public class CommonUtil {

	public static ResponseEntity<?> createBuildResponse(Object data, HttpStatus status) {

//		GenericResponse response = GenericResponse.builder()
//				.responseStatus(status)
//				.status("succes")
//				.message("succes")
//				.data(data)
//				.build();
		GenericResponse response = new GenericResponse.Builder()
			    .responseStatus(status)
			    .status("success")
			    .message("success")
			    .data(data)
			    .build();
		return response.create();
	}
	
	public static ResponseEntity<?> createBuildResponseMessage(String message, HttpStatus status) {

//		GenericResponse response = GenericResponse.Builder()
//				.responseStatus(status)
//				.status("succes")
//				.message(message)
//				.build();
		GenericResponse response = new GenericResponse.Builder()
			    .responseStatus(status)
			    .status("succes")
			    .message(message)
			  
			    .build();
		return response.create();
	}
	
	public static ResponseEntity<?> createErrorResponse(Object data, HttpStatus status) {

//		GenericResponse response = GenericResponse.builder()
//				.responseStatus(status)
//				.status("failed")
//				.message("failed")
//				.data(data)
//				.build();
		GenericResponse response = new GenericResponse.Builder()
			    .responseStatus(status)
			    .status("failed")
			    .message("failed")
			    .data(data)
			    .build();

		return response.create();
	}
	
	public static ResponseEntity<?> createErrorResponseMessage(String message, HttpStatus status) {

//		GenericResponse response = GenericResponse.builder()
//				.responseStatus(status)
//				.status("failed")
//				.message(message)
//				.build();
		GenericResponse response = new GenericResponse.Builder()
			    .responseStatus(status)
			    .status("failed")
			    .message(message)
			  
			    .build();

		return response.create();
	}

}