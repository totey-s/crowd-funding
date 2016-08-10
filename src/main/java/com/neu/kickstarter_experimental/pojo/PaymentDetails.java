package com.neu.kickstarter_experimental.pojo;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="paymentDetails")
public class PaymentDetails {

	@Id
	@GeneratedValue	
	@Column(name = "paymentId", unique=true, nullable = false)
	private int paymentId;
	
//	@OneToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name="fundId")
//	private FundsReceived fund;	
	
	private String streetAddress;
	private String city;
	private String zipcode;
	private String email;
	private String firstName;
	private String lastName;
	private String cardNumber;
	private String expiry;
	private int cvc;
	private double fundAmount;
	
	@Column(name = "projectId")
	private int projectId;	
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;
	
//	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@Column(name = "userId")
	private int createdBy;
	
	public int getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
//	public FundsReceived getFund() {
//		return fund;
//	}
//	public void setFund(FundsReceived fund) {
//		this.fund = fund;
//	}
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getExpiry() {
		return expiry;
	}
	public void setExpiry(String expiry) {
		this.expiry = expiry;
	}
	public int getCvc() {
		return cvc;
	}
	public void setCvc(int cvc) {
		this.cvc = cvc;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public double getFundAmount() {
		return fundAmount;
	}
	public void setFundAmount(double fundAmount) {
		this.fundAmount = fundAmount;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	
	
	
}
