package helpers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import modules.Curso;

public final class Querys {
	
	public static <T> void Persist(T t)
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDB");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(t);	
		em.getTransaction().commit();
		em.close();
		emf.close();
	}
	public static <T> void Delete(T t)
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDB");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		T cdelete = em.merge(t);
		em.remove(cdelete);
		em.getTransaction().commit();
		em.close();
	}
	
	public static <T> List<T> queryCustom(String table, String toQuery, String t, boolean isString)
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDB");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Query query;
		if (isString)
		{
			query = em.createQuery("from " + table + " where " +  toQuery + "='" + t + "'");
		}
		else
		{
			 query = em.createQuery("from " + table + " where " +  toQuery + "=" + t);
		}
		
		List<T> objects = query.getResultList();
		
		em.getTransaction().commit();
		em.close();
		
		return objects;
	}
	
	public static <T> List<T> queryGetList(String table)
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDB");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("from " + table);
			 
		List<T> objects = query.getResultList();
		
		em.getTransaction().commit();
		em.close();
		
		return objects;
	}
	
	public static <T> List<T> queryCustomLike(String table, String toQuery, String intext)
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDB");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("from " + table + " where " +  toQuery + " LIKE '%" + intext + "%'");
		
		List<T> objects = query.getResultList();
		
		em.getTransaction().commit();
		em.close();
		
		return objects;
	}
	
	public static <T> List<T> queryCustom(String table, String toQuery, String value,
										  String andQuery, String andValue)
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDB");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("from " + table + " where " +  toQuery + "='" + value + "' AND " + 
									 							   andQuery + "='" + andValue + "'");
		
		List<T> objects = query.getResultList();
		
		em.getTransaction().commit();
		em.close();
		
		return objects;
	}
	

	
}
