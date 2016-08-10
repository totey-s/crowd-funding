package com.neu.kickstarter_experimental.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;

import com.neu.kickstarter_experimental.pojo.CreatedProject;

public class SearchDao extends DAO{
	
	public static List search(String searchString){
		
//		EntityManager em = getEmf().createEntityManager();
//		FullTextEntityManager fullTextEntityManager =
//		    org.hibernate.search.jpa.Search.getFullTextEntityManager(em);
//		em.getTransaction().begin();

		// create native Lucene query unsing the query DSL
		// alternatively you can write the Lucene query using the Lucene query parser
		// or the Lucene programmatic API. The Hibernate Search DSL is recommended though
		FullTextSession searchSession = Search.getFullTextSession(getSession());
		
		/*String field = "";
		for(int i=0;i<fields.length;i++){
			if(i!=fields.length-1){
				field+=fields[i]+", ";
			}else{
				field+=fields[i];
			}
		}
		System.out.println(field);*/
		
		System.out.println("In Search: searchString: "+searchString);
		
		QueryBuilder qb = searchSession.getSearchFactory()
		    .buildQueryBuilder().forEntity(CreatedProject.class).get();
		org.apache.lucene.search.Query luceneQuery = qb
		  .keyword()
//		  .onFields(field)
		  .onFields("projectName", "location", "category")
		  .matching(searchString)		  
		  .createQuery();

		// wrap Lucene query in a javax.persistence.Query
		
//		javax.persistence.Query jpaQuery =
//		    fullTextEntityManager.createFullTextQuery(luceneQuery, CreatedProject.class);
		
		org.hibernate.Query jpaQuery = searchSession.createFullTextQuery(luceneQuery, CreatedProject.class);
		// execute search
//		List result = jpaQuery.getResultList();
		
		List result = jpaQuery.list();
		
		System.out.println(result.size());
//		em.getTransaction().commit();
//		em.close();
		
		return result;
	}
	
/*	public static void main(String[] args) {
//		SearchDao search = new SearchDao();
//		doIndexing();
		String[] fields = {"category"};
		System.out.println(SearchDao.search("Cathedral").size());
	}*/

}
