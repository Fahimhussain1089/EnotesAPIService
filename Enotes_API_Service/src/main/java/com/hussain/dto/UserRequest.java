package com.hussain.dto;

import java.util.List;

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
public class UserRequest {

	private Integer id;

	private String firstName;

	private String lastName;

	private String email;

	private String mobNo;

	private String password;

	private List<RoleDto> roles;
	

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}








	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobNo() {
		return mobNo;
	}
	public void setMobNo(String mobNo) {
		this.mobNo = mobNo;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<RoleDto> getRoles() {
		return roles;
	}
	public void setRoles(List<RoleDto> roles) {
		this.roles = roles;
	}

	public UserRequest() {
		super();
	}

	public UserRequest(Integer id, String firstName, String lastName, String email, String mobNo, String password,
			List<RoleDto> roles) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobNo = mobNo;
		this.password = password;
		this.roles = roles;
	}








	//*****************************************************************************************
	//*****************************************************************************************

	@AllArgsConstructor
	@NoArgsConstructor
	@Getter
	@Setter
	@Builder
	public static class RoleDto {
		private Integer id;
		private String name;
		
		
		public RoleDto() {
			super();
		}
		public RoleDto(Integer id, String name) {
			super();
			this.id = id;
			this.name = name;
		}
		
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	
	}
}