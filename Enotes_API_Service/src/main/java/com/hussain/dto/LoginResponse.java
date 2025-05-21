package com.hussain.dto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {

//	private UserRequest user;
	private UserResponse user;

	private String token;

	public LoginResponse(UserResponse user, String token) {
		super();
		this.user = user;
		this.token = token;
	}

	public LoginResponse() {
		super();
	}

	public UserResponse getUser() {
		return user;
	}

	public void setUser(UserResponse user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		//this is methode basically set the showt he token 
		this.token = token;
	}
	

}


/*
 {
    "status": "success",
    "message": "success",
    "data": {   //status , message data =>handle it handle util class ,
        "user": {
            "id": 22,
            "firstName": "Testing5",
            "lastName": "programmer5",
            "email": "fahimhusain98@gmail.com",
            "mobNo": "+919832542120",
            "password": "$2a$10$nDFPeioB6pvEgFzDirx7DO9BD3YIPbwpQkzMgPc1HOjxqtD.ftkUe",
            "roles": [
                {
                    "id": 1,
                    "name": "admin"
                }
            ]
        },
        "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJmYWhpbWh1c2Fpbjk4QGdtYWlsLmNvbSIsInJvbGUiOlt7ImlkIjoxLCJuYW1lIjoiYWRtaW4ifV0sImlkIjoyMiwiZXhwIjoxNzQ3NzE4NzY1LCJpYXQiOjE3NDc3MTg3MjksInN0YXR1cyI6dHJ1ZX0.XsQcuI8b401Zf9TZWkMDuvrVR6cbcFLrFmZ5Hw0mvqc"
    }
} 
  
  
  
  
  
  
  
  
  
  
 
  
  */
