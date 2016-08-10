package com.neu.kickstarter_experimental.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.web.servlet.ModelAndView;

import com.neu.kickstarter_experimental.pojo.CreatedProject;
import com.neu.kickstarter_experimental.pojo.PaymentDetails;
import com.neu.kickstarter_experimental.pojo.User;

public class ProjectDao extends DAO{

	public List getProjects(User user, boolean include){
		try {
			begin();
			Criteria crit = getSession().createCriteria(CreatedProject.class);
			Criterion approve = null;
			Criterion User = null;
			if(!include){
				approve = Restrictions.eq("approved", "true");
				User = Restrictions.ne("owner", user);
				crit.add(approve);
			}else if(include){
//				approve = Restrictions.eq("approved", "true");
				User = Restrictions.eq("owner", user);
			}			
			crit.add(User);
			
			List projects = crit.list();
			 System.out.println(projects.size());
            commit();
            return projects;
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return null;
	}
	
	public CreatedProject getProjects(int id){
		try {
			begin();
			List projects = getSession().createCriteria(CreatedProject.class)
					.add(Restrictions.eq("projectId", id)).list();
            System.out.println(projects.size());
			Iterator it = projects.iterator();
			CreatedProject project = (CreatedProject)it.next();
            commit();
            return project;
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return null;
	}
	
	public List<PaymentDetails> getPaymentDetails(int id){
		List<PaymentDetails> payments = new ArrayList<PaymentDetails>();
		begin();
		
		try{
			List funds = getSession().createCriteria(PaymentDetails.class)
					.add(Restrictions.eq("projectId", id)).list();
	        System.out.println(funds.size());
			Iterator it = funds.iterator();
	        while(it.hasNext()){
	        	PaymentDetails cp = (PaymentDetails)it.next();
	        	payments.add(cp);
			}
	        commit();
	        return payments;
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return null;
	}
	
	public CreatedProject getPaymentDetailsAndBackers(int id, CreatedProject cp1){
		List<PaymentDetails> payments = new ArrayList<PaymentDetails>();
		begin();		
		try{
			List funds = getSession().createCriteria(PaymentDetails.class)
					.add(Restrictions.eq("projectId", id)).list();
	        System.out.println(funds.size());
			Iterator it = funds.iterator();
	        while(it.hasNext()){
	        	PaymentDetails cp = (PaymentDetails)it.next();
	        	payments.add(cp);
			}
	        Query q = getSession().createSQLQuery("Select count(distinct(userId)) from paymentdetails where projectId= :projectId");
	        q.setInteger("projectId", id);
	        Integer count = ((BigInteger) q.uniqueResult()).intValue();
	        System.out.println("Backers: "+count);
	        commit();
//	        ModelAndView mv = new ModelAndView();s
	        cp1.setBackers(count);
	        cp1.setFundReceived(payments);
//	        mv.addObject("backers", count);
	        return cp1;
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return null;
	}
	
	public void updateProject(CreatedProject project){
		try {
			begin();
			getSession().update(project);
            commit();
            
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
	}
	
	/*public boolean authorizeProject(int projectId){
		try {
			begin();
			Query query = getSession().createQuery("update CreatedProject c set c.approved = :approved where c.projectId = :projectId");
			query.setString("approved", "true");
			query.setInteger("projectId", projectId);
			int update = query.executeUpdate();
			commit();
			if(update>0){
				return true;
			}
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return false;
	}*/
	
	/*public static void main(String[] args){
		AuthorizeProjectDao a = new AuthorizeProjectDao();
		List projects = a.getProjects();
		Iterator it = projects.iterator();
		while(it.hasNext()){
			CreatedProject cp = (CreatedProject)it.next();
//			System.out.println(cp.getProjectName());
		}
	}*/
}
