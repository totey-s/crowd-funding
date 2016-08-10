package com.neu.kickstarter_experimental.pojo;

import javax.persistence.Column;

public class LoginModel {

	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	public LoginModel(){
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
