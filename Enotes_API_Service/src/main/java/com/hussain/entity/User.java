package com.hussain.entity;
import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String firstName;

	private String lastName;

	private String email;

	private String mobNo;
	
	private String password;

	//@OneToMany(cascade = CascadeType.ALL)
	 @ManyToMany(fetch = FetchType.EAGER) // ← Change from LAZY to EAGER
		/*
		Lazy Loading: By default, Hibernate loads relationships (like user.roles) only when accessed.

		Closed Session: Your transaction ends before the roles are accessed (typically after your UserService method completes).

		Token Generation: When generating the JWT token, you're trying to access user.getRoles() after the transaction is closed.
		Pros: Simple
		Cons: 	May cause unnecessary data loading 
		  
		  
		  
		  */
	private List<Role> roles;
	
	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name = "status_id")
	private AccountStatus status;
	


	public User(Integer id, String firstName, String lastName, String email, String mobNo, String password,
			List<Role> roles,AccountStatus status) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobNo = mobNo;
		this.password = password;
		this.roles = roles;
		this.status = status;
	}

	public User() {
		super();
	}

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

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public AccountStatus getStatus() {
		return status;
	}

	public void setStatus(AccountStatus status) {
		this.status = status;
	}
	
	
	

}
