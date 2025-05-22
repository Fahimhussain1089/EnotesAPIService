package com.hussain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PswdResetRequest {

	private Integer uid;

	private String newPassword;

	public PswdResetRequest(Integer uid, String newPassword) {
		super();
		this.uid = uid;
		this.newPassword = newPassword;
	}

	public PswdResetRequest() {
		super();
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	

}
