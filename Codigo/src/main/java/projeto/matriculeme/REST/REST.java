package projeto.matriculeme.REST;

import com.google.gson.Gson;

import projeto.matriculeme.DAO.GenericDAO;
import projeto.matriculeme.domain.Login;
import projeto.matriculeme.domain.Metadata;
import projeto.matriculeme.util.Security;

public class REST 
{
	private final static String PATH = "projeto.matriculeme.domain.";
	
	
	public static String insert(String classe,String json)
	{
		if((classe == "Token") || (classe == "URL"))
			return "Access denied to this class.";
		else
		{
			try
			{
				Gson gson = new Gson();
				if(classe == "Login")
				{
					Login login = newLogin(json);
					GenericDAO<Login> dao = new GenericDAO<>(Login.class);
					boolean success = dao.save(login);
					if(success == true)
						return gson.toJson(login);
					else
						return "Failed to save the object Login.";
				}
				else
				{
					Object object = gson.fromJson(json,Class.forName(classe));
					GenericDAO<Object> dao = new GenericDAO<>(Object.class);
					boolean success = dao.save(object);
					if(success == true)
						return gson.toJson(object);
					else
						return "Failed to save the object "+ classe +".";
				}
			}
			catch(ClassNotFoundException e)
			{
				return "The class "+ classe +" cannot be located.";
			}
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public static String delete(String classe,String json)
	{
		if((classe == "Token") || (classe == "URL") || (classe == "Login"))
			return "Access denied to this class.";
		else
		{
			try
			{
				Gson gson = new Gson();
				GenericDAO<Metadata> dao_metadata = new GenericDAO<>(Metadata.class);
				Metadata base = dao_metadata.searchMetadata(classe);
				String json_reference = base.getJson();
				Class type = Class.forName(PATH+classe);
				Object object = gson.fromJson(json,type);
				GenericDAO<Object> dao = new GenericDAO<>(type);
				object = dao.search(json,json_reference);
				boolean success = dao.delete(object);
				if(success == true)
					return gson.toJson(object);
				else
					return "Failed to delete the object "+ classe +".";
			}
			catch(ClassNotFoundException e)
			{
				return "The class "+ classe +" cannot be located.";
			}
			
		}
	}
	
	
	public static String get(String classe,String json)
	{
		try
		{
			Gson gson = new Gson();
			GenericDAO<Metadata> dao_metadata = new GenericDAO<>(Metadata.class);
			Metadata base = dao_metadata.searchMetadata(classe);
			String json_reference = base.getJson();
			Class type = Class.forName(PATH+classe);
			GenericDAO<Object> dao = new GenericDAO<>(type);
			Object object = dao.search(json,json_reference);
			return gson.toJson(object);
		}
		catch(ClassNotFoundException e)
		{
			return "The class "+ classe +" cannot be located.";
		}
	}

	
	@SuppressWarnings("unchecked")
	public String edit(String classe,String json, String json_update)
	{
		try
		{
			Gson gson = new Gson();
			GenericDAO<Metadata> dao_metadata = new GenericDAO<>(Metadata.class);
			Metadata base = dao_metadata.searchMetadata(classe);
			String json_reference = base.getJson();
			Class type = Class.forName(PATH+classe);
			GenericDAO<Object> dao = new GenericDAO<>(type);
			Object object = dao.search(json,json_reference);
			Object object_update = gson.fromJson(json,type);
			object = object_update;
			boolean success = dao.edit(object);
			if(success == true)
				return gson.toJson(object);
			else
				return "Failed to edit the object "+ classe +".";
		}
		catch(ClassNotFoundException e)
		{
			return "The class "+ classe +" cannot be located.";
		}
	}
	
	
	private static Login newLogin(String login)
	{
		Gson gson = new Gson();
		Login out = gson.fromJson(login,Login.class);
		out.setAccessKey(out.getAccessKey());
		out.setPassword(Security.stringToSHA1(out.getPassword()));
		return out;
	}

}
