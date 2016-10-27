package modules;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Teste {
	public static void clearTable(String clearedTable)
	{
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDB");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("DELETE FROM " + clearedTable);
		
		query.executeUpdate();
		em.getTransaction().commit();
		em.close();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Alunos a1 = new Alunos();
		Cursos cr = new Cursos();
		Professores pf = new Professores();		
		pf.setNome("oi");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDB");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		
		em.persist(a1);
		em.persist(cr);
		em.persist(pf);
		em.getTransaction().commit();
		em.close();
	}

}