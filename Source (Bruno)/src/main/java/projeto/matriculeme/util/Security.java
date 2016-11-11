package projeto.matriculeme.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import projeto.matriculeme.DAO.GenericDAO;
import projeto.matriculeme.domain.Login;
import projeto.matriculeme.domain.Token;
import projeto.matriculeme.domain.URL;

public class Security 
{
	public final static long TIMEOUT_CONECTION = 1200000;
	public final static long TIMEOUT_URL= 7000;
	
	public static String stringToMD5(String text)
	{
		try 
		{
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        md.update(text.getBytes());
	        byte[] digest = md.digest();
	        StringBuffer sb = new StringBuffer();
	        for (byte b : digest)
	            sb.append(String.format("%02x", b & 0xff));
	        return String.valueOf(sb.toString());
	    } 
		catch(NoSuchAlgorithmException e)
		{
	        return "";
	    }
	}
	
	public static String stringToSHA1(String text)
	{
		try 
		{
	        MessageDigest md = MessageDigest.getInstance("SHA-1");
	        md.update(text.getBytes());
	        byte[] digest = md.digest();
	        StringBuffer sb = new StringBuffer();
	        for (byte b : digest)
	            sb.append(String.format("%02x", b & 0xff));
	        return String.valueOf(sb.toString());
	    } 
		catch(NoSuchAlgorithmException e)
		{
	        return "";
	    }
	}
	
	public static boolean authenticate(Login login)
	{
		Session sesion = HibernateUtil.getConection().openSession();
		try 
		{
			Criteria consult = sesion.createCriteria(Login.class);
			consult.add(Restrictions.eq("accessKey", login.getAccessKey()));
			consult.add(Restrictions.eq("password",Security.stringToSHA1(login.getPassword())));
			Login result = (Login) consult.uniqueResult();
			if(result.equals(null))
				return false;
			else if((login.getAccessKey() == result.getAccessKey()) && (login.getPassword() == result.getPassword()))
				return true;
			else
				return false;
		} 
		catch (RuntimeException erro) 
		{
			throw erro;
		} 
		finally 
		{
			sesion.close();
		}
	}
	
	public static String tokenGenerator(String token)
	{
		Random random = new Random();
		int multiplier = random.nextInt(9)+1;
		long time = System.currentTimeMillis()*multiplier;
		String millis = String.valueOf(time);
		millis = millis.substring(3);
		multiplier = random.nextInt(10);
		int rows = (int) ((Integer.valueOf(millis.charAt(multiplier)-48)+multiplier)/4);
		token += String.valueOf(time);
		for(int i = 0;i <= rows;i++)
			token += Security.stringToMD5(token);
		return Security.stringToMD5(token);
	}

	public static boolean tokenChecker(String token)
	{
		GenericDAO<Token> dao = new GenericDAO<>(Token.class);
		Token result = dao.searchByColumn("token", token);
		if(result == null)
			return false;
		else
		{
			long time = System.currentTimeMillis()-result.getTime();
			if(time > TIMEOUT_CONECTION)
				return false;
			else if(time <= TIMEOUT_CONECTION)
				return true;
		}
		return false;
	}
	
	public static boolean URLChecker(String url)
	{
		GenericDAO<URL> dao = new GenericDAO<>(URL.class);
		URL result = dao.searchByColumn("url", url);
		if(result == null)
			return true;
		else
			return false;
	}
}
