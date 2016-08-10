package com.neu.kickstarter_experimental.dao;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.neu.kickstarter_experimental.pojo.CreatedProject;
import com.neu.kickstarter_experimental.pojo.User;
import com.neu.kickstarter_experimental.pojo.User_Roles;

public class UserDaoImpl extends DAO{
	
	public UserDaoImpl() {
    }
	
	public User get(String username, String password)
            throws Exception {
        try {
            begin();
            Query q = getSession().createQuery("from User where username = :username and password = :password");
            q.setString("username", username);
//            q.setString("status", "active");
            q.setString("password", password);
            User user = (User) q.uniqueResult();
            commit();
            if(user!=null){
            	System.out.println("DAO: "+user.getFirstName());
            	return user;
            }
            
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not get user " + username, e);
        }
        return null;
    }

	public User get(int userId)
            throws Exception {
        try {
            begin();
            Query q = getSession().createQuery("from User where userId = :userId");
            q.setInteger("userId", userId);
//            q.setString("status", "active");
//            q.setString("password", password);
            User user = (User) q.uniqueResult();
            commit();
            if(user!=null){
            	System.out.println("DAO: "+user.getFirstName());
            	return user;
            }
            
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not get user " + e);
        }
        return null;
    }
	
    //public User create(String username, String email, String password, String fname, String lname, String active, Date createdOn )
    public User create(User newUser)
            throws Exception {
        try {
            begin();
            System.out.println("inside DAO");
            
            
//            User user=new User(newUser.getUsername(),newUser.getPassword(),newUser.getEmail());
            
//            user.setPassword(password);
//            user.setUsername(username);
//            
//            user.setEmail(email);
//            
//            user.setFirstName(fname);
//            user.setLastName(lname);
//            newUser.setStatus("validate");
            newUser.setCreatedOn(new Date());
            newUser.setProfilepic("NA");
            
            User_Roles userRole = new User_Roles(newUser, "ROLE_USER");
            newUser.setUserRole(userRole);
                        
            getSession().save(newUser);
            getSession().save(userRole);
            
            commit();
            return newUser;
        } catch (HibernateException e){
            rollback();
            //throw new AdException("Could not create user " + username, e);
            throw new Exception("Exception while creating user: " + e.getMessage());
        }
    }
    
    public User update(User user)
            throws Exception {
    	try {
    		System.out.println("Updating user...");
			begin();
//			User u = get(username, password);
//			User mergedUser = (User)getSession().merge(user);
//			user.setUserId(u.getUserId());
//			System.out.println("Original user: "+u);
			System.out.println("Updated user: "+user);
			getSession().update(user);			
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
    	return null;
    	
    }
    
    public void createProject(CreatedProject project)
            throws Exception {
    	try {
    		begin();
            System.out.println("inside DAO");
            project.setApproved("false");
            project.setCreatedDate(new Date());
//            System.out.println(project);
            getSession().save(project);
            commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public User setActive(User user)
            throws Exception {
    	try {
    		System.out.println("Updating user...");
			begin();
			user.setStatus("active");
			getSession().update(user);			
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
    	return null;
    	
    }

    public void delete(User user)
            throws Exception {
        try {
            begin();
            user.setStatus("inactive");
            getSession().update(user);
//            getSession().delete(user);
            
            commit();
        } catch (HibernateException e) {
            rollback();
//            throw new Exception("Could not delete user " + user.getUsername(), e);
            e.printStackTrace();
        }
    }

}
