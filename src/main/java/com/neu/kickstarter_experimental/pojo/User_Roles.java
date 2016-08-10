package com.neu.kickstarter_experimental.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="User_Roles")
public class User_Roles {
	
	public User_Roles(){
		
	}
	
	@Id
	@GeneratedValue
	@Column(name = "user_role_id", unique=true, nullable = false)
	private int user_role_id;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
	private User user;
	
	@Column(name = "role")
	private String role;
	
	public User_Roles(User user, String role){
		this.user = user;
		this.role = role;
	}

	public int getUser_role_id() {
		return user_role_id;
	}

	public void setUser_role_id(int user_role_id) {
		this.user_role_id = user_role_id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}	
	

}
