package com.neu.kickstarter_experimental.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.neu.kickstarter_experimental.pojo.Category;
import com.neu.kickstarter_experimental.pojo.User;

public class CategoryDao extends DAO{

	public List getCategories(){
//		List categories = new ArrayList<Category>();
		
		try {
            begin();
            Query q = getSession().createQuery("FROM Category");
//            q.setString("username", username);
//            q.setString("password", password);
//            User user = (User) q.uniqueResult();
            List categories = q.list();
            commit();
            Map<Integer, String> categoriesMap = new HashMap<Integer, String>();
            
            if(categories!=null){
            	System.out.println("DAO: "+categories.size());
            	/*Iterator it = categories.iterator();
            	while(it.hasNext()){
            		Category c = (Category) it.next();
            		categoriesMap.put(c.getCategoryId(), c.getCategoryName());
            	}*/
//            	return categoriesMap;
            	return categories;
            }
            
        } catch (HibernateException e) {
            rollback();
            e.printStackTrace();
//            throw new Exception("Could not get user " + username, e);
        }
        return null;
	}
	
	public void setCategory(Category category){
		try {
			begin();
			getSession().save(category);
			commit();
		} catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create user " + username, e);
            e.printStackTrace();
        }
	}
	
	public void save(Category category){
        try {
            begin();
            getSession().update(category);
            commit();
        } catch (HibernateException e) {
            rollback();
            e.printStackTrace();
        }
    }
	
	public Category get(int id){
        try {
            begin();
            Query q=getSession().createQuery("from Category where categoryId= :categoryId");
            q.setInteger("categoryId",id);
            Category category=(Category)q.uniqueResult();
            commit();
            return category;
        } catch (HibernateException e) {
            rollback();
            e.printStackTrace();
        }
        return null;
    }

}
