package projeto.matriculeme.DAO;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.google.gson.Gson;

import java.util.List;

import projeto.matriculeme.util.HibernateUtil;

public class GenericDAO<Entity>
{
	private Class<Entity> classe;

	@SuppressWarnings("unchecked")
	public GenericDAO(@SuppressWarnings("rawtypes") Class temp) 
	{
		this.classe = temp;
	}

	public boolean save(Entity entity) 
	{
		Session sesion = HibernateUtil.getConection().openSession();
		Transaction transaction = null;
		try 
		{
			transaction = sesion.beginTransaction();
			sesion.save(entity);
			transaction.commit();
			sesion.close();
			return true;
		} 
		catch (RuntimeException erro) 
		{
			if (transaction != null)
				transaction.rollback();
			sesion.close();
			return false;
		}
	}
	public boolean delete(Entity entity) 
	{
		Session sesion = HibernateUtil.getConection().openSession();
		Transaction transaction = null;
		try 
		{
			transaction = sesion.beginTransaction();
			sesion.delete(entity);
			transaction.commit();
			sesion.close();
			return true;
		}
		catch (RuntimeException erro) 
		{
			if (transaction != null)
				transaction.rollback();
			sesion.close();
			return false;
		}
	}
	public boolean edit(Entity entity) 
	{
		Session sesion = HibernateUtil.getConection().openSession();
		Transaction transaction = null;
		try 
		{
			transaction = sesion.beginTransaction();
			sesion.update(entity);
			transaction.commit();
			sesion.close();
			return true;
		} 
		catch (RuntimeException erro) 
		{
			if (transaction != null)
				transaction.rollback();
			sesion.close();
			return false;
		} 
	}
	@SuppressWarnings("unchecked")
	public List<Entity> list() 
	{
		Session sesion = HibernateUtil.getConection().openSession();
		try 
		{
			Criteria consulta = sesion.createCriteria(classe);
			List<Entity> resultado = consulta.list();
			sesion.close();
			return resultado;
		} 
		catch (RuntimeException erro) 
		{
			sesion.close();
			return null;
		} 
	}
	@SuppressWarnings("unchecked")
	public List<Entity> listOrderedByColumn(String column) 
	{
		Session sesion = HibernateUtil.getConection().openSession();
		try 
		{
			Criteria consulta = sesion.createCriteria(classe);
			consulta.addOrder(Order.asc(column));
			List<Entity> resultado = consulta.list();
			sesion.close();
			return resultado;
		} 
		catch (RuntimeException erro) 
		{
			sesion.close();
			return null;
		} 
	}
	public Object search(String json,String json_reference) throws HibernateException
	{
		
		String[] args = JsonManipuled(json);
		String[] args_reference = JsonManipuled(json_reference);
		Session sesion = HibernateUtil.getConection().openSession();
		try 
		{
			Criteria consult = sesion.createCriteria(classe);
			for(int i=0;i<=args.length-1;i++)
			{
				String[] temp = args[i].split(":");
				String setter = typeJson(args_reference,temp[0]);
				try 
				{
					if(!args[i].contains("[") && !args[i].contains("]"))
					{
						Object parameter = Class.forName(setter);
						System.out.println(parameter);
						parameter = temp[1].replaceAll("\"", "");
						System.out.println(parameter);
						consult.add(Restrictions.ilike(temp[0].replaceAll("\"", ""),parameter));
					}
						
				}
				catch (ClassNotFoundException e)
				{
					return "Some parameter in the Json is not correct.";
				}
			}
			Gson gson = new Gson();
			Object result = (Object) consult.uniqueResult();
			return gson.toJson(result);
		} 
		catch (RuntimeException erro) 
		{
			System.out.println(erro.getMessage());
			return null;
		} 
		finally 
		{
			sesion.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public Entity searchMetadata(String classe) 
	{
		Session sesion = HibernateUtil.getConection().openSession();
		try 
		{
			Criteria consult = sesion.createCriteria(classe);
			consult.add(Restrictions.eq("classe", classe));
			Entity result = (Entity) consult.uniqueResult();
			sesion.close();
			return result;
		} 
		catch (RuntimeException erro) 
		{
			sesion.close();
			return null;
		} 
	}
	private String[] JsonManipuled(String json)
	{
		json = json.replaceAll(", ",",");
		json = json.replaceAll(" ,",",");
		json = json.replaceAll(": ",":");
		json = json.replaceAll(" :",":");
		json = json.replaceAll("\" ","\"");
		json = json.replaceAll(" \"","\"");
		json = json.substring(1,json.length()-1);
		boolean subs = true;
		String temp = "";
		for(int i =0;i<json.length();i++)
		{
			if(json.charAt(i) == ',')
			{
				if(subs == true)
					temp += "&";
				else if(subs == false)
					temp += ",";
			}
			else
				temp += json.charAt(i);
			if(json.charAt(i) == '[')
				subs = false;
			else if(json.charAt(i) == ']')
				subs = true;
		}
		json = temp;
		return json.split("&");
	}
	private String typeJson(String[] json,String match)
	{
		for(int i = 0;i<json.length;i++)
		{
			String[] temp = json[i].split(":");
			if(temp[0].equals(match))
				return temp[1];
		}
		return null;
	}
}