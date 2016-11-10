package projeto.matriculeme.util;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.google.gson.Gson;

import projeto.matriculeme.domain.Login;
import projeto.matriculeme.domain.Token;
import projeto.matriculeme.domain.URL;
import projeto.matriculeme.domain.User;

public class FactoryUtil 
{
	public static Token newToken(String token)
	{
		Gson gson = new Gson();
		Token out = gson.fromJson(token,Token.class);
		out.setToken(Security.tokenGenerator(out.getToken()));
		out.setValid(true);
		out.setTime(System.currentTimeMillis());
		return out;
	}
	public static User newUser(String user)
	{
		Gson gson = new Gson();
		User out = gson.fromJson(user,User.class);
		return out;
	}
	public static Login newLogin(String login)
	{
		Gson gson = new Gson();
		Login out = gson.fromJson(login,Login.class);
		out.setAccessKey(out.getAccessKey());
		out.setPassword(Security.stringToSHA1(out.getPassword()));
		return out;
	}
	public static URL newURL(String url)
	{
		Gson gson = new Gson();
		URL out = gson.fromJson(url,URL.class);
		out.setTime(System.currentTimeMillis());
		return out;
	}
	public static Object executeMethod(String class_name,String method,Class[] param_class,Object[] param) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException 
	{
		try 
		{
			Class intern_class = Class.forName(class_name);
			Object object = intern_class.newInstance();
			Method m = object.getClass().getDeclaredMethod(method, param_class);
			return m.invoke(object, param);
		} 
		catch (IllegalAccessException|InstantiationException e) 
		{
			System.out.println("ERROR: "+e.getClass());
		}
		return null;
	}
	public static Object executeMethod(Object object,String method,Class[] param_class,Object[] param) 
	{
		try 
		{
			Method m = object.getClass().getDeclaredMethod(method, param_class);
			return m.invoke(object, param);
		} 
		catch (NoSuchMethodException|SecurityException|IllegalAccessException|IllegalArgumentException|InvocationTargetException e) 
		{
			System.out.println("ERROR: "+e.getClass());
		}
		return null;
	}
	public static Class[] createParameters(String setter)
	{
		if(setter.equals("edit"))
		{
			Class[] out = {String.class,String.class};
			return out;
		}
		else if((setter.equals("insert")) || (setter.equals("delete")) || (setter.equals("get")))
		{
			Class[] out = {String.class};
			return out;
		}
		else
			return null;
	}
	public static Object[] createArgs(String setter)
	{
		setter = setter.replace("&","=");
		String[] parameters = setter.split("=");
		setter = "";
		for (int i = 0;i<parameters.length;i++)
			if(i % 2 != 0)
				setter += parameters[i].toString()+"=";
		setter = setter.substring(0,setter.length()-1);
		parameters = setter.split("=");
		Object[] args = parameters;
		return args;
	}
	public static Object[] createArgsJSON(String setter)
	{
		String[] parameters = setter.split("&");
		Object[] args = parameters;
		return args;
	}
}
