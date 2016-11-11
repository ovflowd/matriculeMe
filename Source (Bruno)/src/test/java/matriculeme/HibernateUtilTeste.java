package matriculeme;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Ignore;
import org.junit.Test;

import com.google.gson.Gson;

import projeto.matriculeme.DAO.GenericDAO;
import projeto.matriculeme.REST.REST;
import projeto.matriculeme.domain.Alvo;
import projeto.matriculeme.domain.Login;
import projeto.matriculeme.domain.URL;
import projeto.matriculeme.util.Security;
//import projeto.matriculeme.util.FactoryUtil;
import projeto.matriculeme.util.HibernateUtil;

public class HibernateUtilTeste 
{
	@Test
	@Ignore
	public void conection() 
	{
		Session sessao = HibernateUtil.getConection().openSession();
		sessao.close();
		HibernateUtil.getConection().close();
	}
	@Test
	@Ignore
	public void teste()
	{
		GenericDAO<Login> ola = new GenericDAO<>(Login.class);
		Login oi = new Login();
		oi.setPassword("q1w2e3r4");
		ola.save(oi);
	}
	@Test
	@Ignore
	public void crypto()
	{
		URL url = new URL();
		long time = System.currentTimeMillis();
		url.setTime(time);
		System.out.println(time);
		url.setUrl("OLA");
		GenericDAO<URL> oi = new GenericDAO<>(URL.class);
		oi.save(url);
		GenericDAO<Alvo> ola = new GenericDAO<>(Alvo.class);
		Alvo meta = new Alvo();
		meta.setString("To aq mas vc nao poderá me ver hsauhaushua");
		meta.setId(1);
		ola.save(meta);
	}
	@Test
	@Ignore
	public void tokenGenerator()
	{
		String token = Security.stringToMD5("bruno");
		System.err.println(System.currentTimeMillis());
		Random random = new Random();
		int multiplier = random.nextInt(9)+1;
		long time = System.currentTimeMillis()*multiplier;
		String millis = String.valueOf(time);
		millis = millis.substring(3);
		multiplier = random.nextInt(10);
		int rows = (int) ((Integer.valueOf(millis.charAt(multiplier)-48)+multiplier)/4);
		token += String.valueOf(time);
		for(int i = 1;i < rows;i++)
			token += Security.stringToMD5(token);
		token = Security.stringToMD5(token);
		System.out.println(token);
	}
	@Test
	@Ignore
	public void r()
	{
		String t = "0/efefsdfsdfsdfs/2/3/4/5/6";
		String[] out = t.split("/");
		System.out.println(out[1]);
	}
	@Test
	@Ignore
	public void k()
	{
		Login um = new Login();
		um .setAccessKey("ISA");
		um.setPassword("BRUNO");
		GenericDAO<Login> dao = new GenericDAO<>(Login.class);
		dao.save(um);
	}
	@Test
	@Ignore
	public void p() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		String setter = "Bruno";
		Object[] param_object = new Object[1];
		param_object[0] = setter;
		Class[] param = new Class[1];
		param[0] = String.class;
		System.out.println(exe("projeto.matriculeme.domain.Login","setAccessKey",param,param_object));
		System.out.println("!!!!!!!!!!");
		System.out.println(exe("projeto.matriculeme.domain.Login","getAccessKey",null,null));
	}
	public Object exe(String class_name,String method,Class[] param_class,Object[] param) 
	{
		try 
		{
			Class intern_class = Class.forName(class_name);
			Object object = intern_class.newInstance();
			Method m = object.getClass().getDeclaredMethod(method, param_class);
			return m.invoke(object, param);
		} 
		catch (NoSuchMethodException|SecurityException|IllegalAccessException|IllegalArgumentException|ClassNotFoundException|InstantiationException|InvocationTargetException e) 
		{
			System.out.println("ERROR: "+e.getClass());
		}
		return null;
	}
	@Test
	@Ignore
	public void rdyd()
	{
		String json = "{ \"prop1\" : \"String\" , \"prop2\": 123, \"prop3\" :{\"String2\",14,15}}";
		System.out.println(json);
		json = json.replaceAll(", ",",");
		json = json.replaceAll(" ,",",");
		json = json.replaceAll(": ",":");
		json = json.replaceAll(" :",":");
		json = json.replaceAll("\" ","\"");
		json = json.replaceAll(" \"","\"");
		System.out.println(json);
		json = json.substring(1,json.length()-1);
		System.out.println(json);
		boolean subs = true;
		String json2 = "";
		for(int i =0;i<json.length();i++)
		{
			if(json.charAt(i) == ',')
			{
				if(subs == true)
					json2 += "&";
				else if(subs == false)
					json2 += ",";
			}
			else
				json2 += json.charAt(i);
			if(json.charAt(i) == '{')
				subs = false;
			else if(json.charAt(i) == '}')
				subs = true;
		}
		json = json2;
		System.out.println(json+"\n\n\n\n");
		String[] temp = json.split("&");
		for (String string : temp) 
			System.out.println(string);
	}
	@Test
	@Ignore
	public void ahsfj() throws ClassNotFoundException
	{
//		String search = "{\"token\":\"TESTE\",\"time\":1477404067370}";
//		System.out.println(search);
//		Class type = Class.forName("projeto.matriculeme.domain.Token");
//		GenericDAO<Object> dao = new GenericDAO<>(type);
////		Object out = dao.search(search);
//		if(out == null)
//			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//		else
//		{
//			System.out.println("AEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
//			System.out.println(FactoryUtil.executeMethod(out, "getTime", null, null));
//		}
	}
	@Test
	@Ignore
	public void afsrte() throws ClassNotFoundException
	{
//		String json = "{\"token\":\"ISA\"}";
//		Class type = Class.forName("projeto.matriculeme.domain.Token");
//		GenericDAO<Object> dao = new GenericDAO<>(type);
//		Object out = dao.search(json);
//		dao.delete(out);
//		try {
//			REST.delete("projeto.matriculeme.domain.Token", json);
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
	}
	@Test
	@Ignore
	public void wegdg() throws ClassNotFoundException
	{
		String json = "{ \"prop1\" : \"String\" , \"prop2\": 123, \"prop3\" :[\"String2\",14,15]}";
		System.out.println(json+"\n");
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
		String[] out = json.split("&");
		for(String in : out)
			System.out.println(in);
		System.out.println(REST.get("projeto.matriculeme.domain.Login", json));
	}
	@Test
	@Ignore
	public void awr() throws ClassNotFoundException
	{
		String json = "{\"password\":\"BRUNO\"}";
		String json_reference = "{\"id\":java.lang.Integer,\"password\":java.lang.String,\"accessKey\":java.lang.String}";
		Class type = Class.forName("projeto.matriculeme.domain.Login");
		GenericDAO<Object> dao = new GenericDAO<>(type);
		System.out.println(dao.search(json,json_reference));
//		String io = "oi:123";
//		String match = "oi";
//		String[] json = io.split(":");
//		for(int i = 0;i<json.length;i++)
//		{
//			String[] temp = json[i].split(":");
//			if(temp[0] == match)
//			{
//				System.out.println("!!!!!!!!!!!!!!!!!!!!");
//			}
//			else
//			{
//				System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
//			}
//		}
	}
	@Test
//	@Ignore
	public void sefmegnjfdvdf() throws ClassNotFoundException
	{
		String json = "{\"token\":\"TESTE\",  \"time\":  1477494409982}";
		String json_reference = "{\"id\":java.lang.Integer,\"token\":java.lang.String,\"valid\":java.lang.Boolean,\"time\":java.lang.Long}";
		Class classe = Class.forName("projeto.matriculeme.domain.Login");
		Object obj = search(json, json_reference, classe);
		Gson gson = new Gson();
		System.out.println(gson.toJson(obj));
	}
	public Object search(String json,String json_reference,Class classe) throws HibernateException
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
					Object parameter = Class.forName(setter);
					System.out.println(parameter);
					parameter = temp[1].replaceAll("\"", "");
					System.out.println(parameter);
					consult.add(Restrictions.ilike(temp[0].replaceAll("\"", ""),parameter));
				}
				catch (ClassNotFoundException e)
				{
					return "Some parameter in the Json is not correct.";
				}
			}
			Object result = (Object) consult.uniqueResult();
			return result;
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
}
