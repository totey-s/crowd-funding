package com.neu.kickstarter_experimental.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import com.neu.kickstarter_experimental.pojo.User;

@Entity
@Indexed
@Table(name="createdProject")
public class CreatedProject {

	@Id
	@GeneratedValue
	@Column(name = "projectId", unique=true, nullable = false)
	private int projectId;
	
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	@Column(name = "projectName")
	private String projectName;
		
	@Column(name = "projectPic")
	private String projectPic;
		
	@Column(name = "description")
	private String description;
	
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	@Column(name = "location")
	private String location;
	
	/*@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@Column(name = "location")
    private String category_name;*/
	
	//@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	@JoinColumn(name = "categoryId")
	private int category;
	
	@Transient
	private String categoryName;
	
	@Column(name = "createdDate")
	private Date createdDate;
	
	@Column(name = "completedDate")
	private Date completedDate;
	
	@Column(name = "fundGoal")
	private long fundGoal;
	
	@Column(name = "duration")
	private int duration;
	
//	@OneToMany(fetch=FetchType.LAZY, mappedBy="paymentId")
//	@JoinColumn(name = "fundReceived")
	@Transient
	private List<PaymentDetails> fundReceived = new ArrayList<PaymentDetails>();
	
	@Transient
	private int backers;
	
//	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name = "user")
	@JsonIgnore
	private User owner;
	
	@Transient
	private String owner_Fname;
	@Transient
	private String owner_Lname;
	
	@Column(name = "approved")
	private String approved;
	
	/*@Column(name = "comments")
	private Comments comments;*/
	
	public CreatedProject(){
		
	}
	
	public CreatedProject(String projectName, String location, User owner,int category_id, String description){
		this.projectName = projectName;
		this.location = location;
		this.owner = owner;
		this.category = category_id;
		this.description = description;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

/*	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}*/

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getCompletedDate() {
		return completedDate;
	}

	public void setCompletedDate(Date completedDate) {
		this.completedDate = completedDate;
	}

	public long getFundGoal() {
		return fundGoal;
	}

	public void setFundGoal(long fundGoal) {
		this.fundGoal = fundGoal;
	}
	/*
	public FundsReceived getFundReceived() {
		return fundReceived;
	}

	public void setFundReceived(FundsReceived fundReceived) {
		this.fundReceived = fundReceived;
	}*/

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public String getApproved() {
		return approved;
	}

	public void setApproved(String approved) {
		this.approved = approved;
	}

	public String getProjectPic() {
		return projectPic;
	}

	public void setProjectPic(String projectPic) {
		this.projectPic = projectPic;
	}
	
	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	

	public String getOwner_Fname() {
		return owner_Fname;
	}

	public void setOwner_Fname(String owner_Fname) {
		this.owner_Fname = owner_Fname;
	}

	public String getOwner_Lname() {
		return owner_Lname;
	}

	public void setOwner_Lname(String owner_Lname) {
		this.owner_Lname = owner_Lname;
	}
	
	

	
	public List<PaymentDetails> getFundReceived() {
		return fundReceived;
	}

	public void setFundReceived(List<PaymentDetails> fundReceived) {
		this.fundReceived = fundReceived;
	}

	public void addFunds(PaymentDetails funds){
		fundReceived.add(funds);
	}
	
	public int getBackers() {
		return backers;
	}

	public void setBackers(int backers) {
		this.backers = backers;
	}

	@Override
	public String toString() {
		return "ProjectId: "+getProjectId()+"ProjectName: "+getProjectName()+"---Location: "+getLocation()+""
				+ "---Description: "+getDescription()+"---Category: "+getCategory()+"---Created Date: "+getCreatedDate()+""
						+"---User: "+getOwner()+ ""
								+ "---Approved: "+getApproved();
	}		
	
}
