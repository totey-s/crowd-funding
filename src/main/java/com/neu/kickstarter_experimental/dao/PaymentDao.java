package com.neu.kickstarter_experimental.dao;

import java.util.Date;

import org.hibernate.HibernateException;

import com.neu.kickstarter_experimental.pojo.CreatedProject;
import com.neu.kickstarter_experimental.pojo.PaymentDetails;


public class PaymentDao extends DAO{
	
	 public void create(PaymentDetails paymentInfo)
	            throws Exception {
	        try {
	            begin();
	            System.out.println("inside DAO");
	            
	            paymentInfo.setCreatedOn(new Date()); 
	            
//	            paymentInfo.setFundId(funds.getFundId());
//	            funds.setPaymentId(paymentInfo.getPaymentId());
	            
	            getSession().save(paymentInfo);
//	            getSession().update(project);
//	            getSession().save(funds);
	            
	            commit();
	        } catch (HibernateException e) {
	            rollback();
	            //throw new AdException("Could not create user " + username, e);
	            throw new Exception("Exception while creating user: " + e.getMessage());
	        }
	    }
	 
	 
	 
	

}
