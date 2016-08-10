package com.neu.kickstarter_experimental.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import com.neu.kickstarter_experimental.pojo.CreatedProject;
import com.neu.kickstarter_experimental.pojo.User;

public class AuthorizeProjectDao extends DAO{

	public List getProjects(){
		try {
			begin();
			List projects = getSession().createCriteria(CreatedProject.class)
					.add(Restrictions.eq("approved", "false")).list();
            System.out.println(projects.size());
            commit();
            return projects;
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return null;
	}
	
	public List getProjects(String status){
		try {
			begin();
			List projects = getSession().createCriteria(CreatedProject.class)
					.add(Restrictions.eq("approved", status)).list();
            System.out.println(projects.size());
            commit();
            return projects;
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean authorizeProject(int projectId){
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
	}
	
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
