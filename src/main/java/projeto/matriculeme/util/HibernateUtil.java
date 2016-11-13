package projeto.matriculeme.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil 
{
	private static SessionFactory conection = newconection();

	public static SessionFactory getConection() 
	{
		return conection;
	}

	private static SessionFactory newconection() 
	{
		try 
		{
			Configuration config = new Configuration().configure();
			ServiceRegistry registro = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
			SessionFactory factory = config.buildSessionFactory(registro);
			return factory;
		} 
		catch (Throwable ex) 
		{
			System.err.println("Conection failed. ERROR: " + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
}