package com.neu.kickstarter_experimental.pojo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonManagedReference;

@Entity
@Table(name="categorytable")
public class Category {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="categoryId", unique = true, nullable = false)
	private int categoryId;
	
	@Column(name="categoryName")
	private String categoryName;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="category")
	@JsonBackReference
	private List<CreatedProject> projects = new ArrayList<CreatedProject>();
	
	public Category(String categoryName){
		this.categoryName = categoryName;
	}

	public Category(){
		
	}
	
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void addProjects(CreatedProject project){
		getProjects().add(project);
	}
	public List<CreatedProject> getProjects() {
		return projects;
	}
	public void setProjects(List<CreatedProject> projects) {
		this.projects = projects;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
	
	
	
}
