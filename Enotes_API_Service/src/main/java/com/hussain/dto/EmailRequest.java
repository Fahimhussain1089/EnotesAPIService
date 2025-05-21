package com.hussain.dto;

//public class EmailRequest {
//
//}


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EmailRequest {

	private String to;

	private String subject;

	private String title;

	private String message;

	public EmailRequest(String to, String subject, String title, String message) {
		super();
		this.to = to;
		this.subject = subject;
		this.title = title;
		this.message = message;
	}

	public EmailRequest() {
		super();
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}