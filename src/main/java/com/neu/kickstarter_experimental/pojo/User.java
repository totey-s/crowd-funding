package com.neu.kickstarter_experimental.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="usertable")
@PrimaryKeyJoinColumn(name="userId")
public class User extends Person{

	public User(){
		
	}
	
//	private int userId;
		
	@Column(name="email")
	private String email;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
	private User_Roles userRole;
	
	@Column(name="status")
	private String status;
	
	@Column(name="createdOn")
	private Date createdOn;
	
	@Column(name="profilepic")
	private String profilepic;
	
//	@ManyToMany(cascade=CascadeType.ALL, mappedBy="backer")
//	private Set<FundsReceived> funds = new HashSet<FundsReceived>();

	
	public User(String name, String password, String email) {
        this.username = name;
        this.password = password;
        this.email = email;
    }
	
//	public void addFunds(FundsReceived fund){
//		funds.add(fund);
//	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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

	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public User_Roles getUserRole() {
		return userRole;
	}

	public void setUserRole(User_Roles userRole) {
		this.userRole = userRole;
	}

	public String getProfilepic() {
		return profilepic;
	}

	public void setProfilepic(String profilepic) {
		this.profilepic = profilepic;
	}

//	public Set<FundsReceived> getFunds() {
//		return funds;
//	}
//
//	public void setFunds(Set<FundsReceived> funds) {
//		this.funds = funds;
//	}

	@Override
	public String toString() {
		return "UserId: "+getUserId()+"---FName: "+getFirstName()+"---LName: "+getLastName()+""
				+ "---Email: "+getEmail()+"---Username: "+getUsername()+"---Password: "+getPassword()+""
						+"---UserRole: "+getUserRole().getRole()+ ""
								+ "---ProfilePic: "+getProfilepic();
	}	
	
	
	
	
}
