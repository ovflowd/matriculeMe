package projeto.matriculeme.REST;



import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.google.gson.annotations.JsonAdapter;
import com.sun.research.ws.wadl.Application;

import projeto.matriculeme.util.FactoryUtil;
import projeto.matriculeme.util.Security;


@Path("/rest")
public class ManagerREST 
{
	@GET
	@Path("/login")//http://127.0.0.1:8080/matriculeme/web/rest/login
	public String login(@Context HttpServletResponse response,@Context HttpServletRequest request) throws IOException
	{
		return "ACESSA AI DNV FERA";
	}

	@GET
	@Path("/{token}/{class}/get/{parameters}")
	@Consumes(MediaType.APPLICATION_JSON)
	public String restGET(@PathParam("token") String token,@PathParam("class") String classe,String parameters,@Context HttpServletResponse response,@Context HttpServletRequest request) throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		if((Security.tokenChecker(token) == false) || (Security.URLChecker(request.getRequestURL().toString()) == false))
		{
			response.sendRedirect("http://127.0.0.1:8080/matriculeme/web/rest/login");
			return "";
		}
		else
		{
			try 
			{
				Object out = FactoryUtil.executeMethod(PATH+classe,"get",FactoryUtil.createParameters(method),FactoryUtil.createArgsJSON(parameters));
				return out.toString();
			}
			catch (NoSuchMethodException e) 
			{
				return "The method required: "+ method + "cannot be found.";
			} 
			catch (SecurityException e)
			{
				return "The page cannot be accessed.";
			} 
			catch (IllegalArgumentException e) 
			{
				return "The parameters: "+ parameters + "cannot be used as a parameter.";
			} 
			catch (InvocationTargetException e) 
			{
				return "The method required: "+ method + "failed in execution for de parameters: "+ parameters;
			}
		}
	}
	@DELETE//http://127.0.0.1:8080/matriculeme/web/rest/[TOKEN]/classREST/delete?JSON
	@Path("/{token}/{class}/{method}?{parameters}")
	public String restDELETE(@PathParam("token") String token,@PathParam("class") String classe,@PathParam("method") String method,@PathParam("parameters") String parameters,@Context HttpServletResponse response,@Context HttpServletRequest request) throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		if((Security.tokenChecker(token) == false) || (Security.URLChecker(request.getRequestURL().toString()) == false))
		{
			response.sendRedirect("http://127.0.0.1:8080/matriculeme/web/rest/login");
			return "";
		}
		else
		{
			try 
			{
				Object out = FactoryUtil.executeMethod(PATH+classe+"REST", method, FactoryUtil.createParameters(method),FactoryUtil.createArgsJSON(parameters));
				return out.toString();
			}
			catch (NoSuchMethodException e) 
			{
				return "The method required: "+ method + "cannot be found.";
			} 
			catch (SecurityException e)
			{
				return "The page cannot be accessed.";
			} 
			catch (IllegalArgumentException e) 
			{
				return "The parameters: "+ parameters + "cannot be used as a parameter.";
			} 
			catch (InvocationTargetException e) 
			{
				return "The method required: "+ method + "failed in execution for de parameters: "+ parameters;
			}
		}
	}
	@POST//http://127.0.0.1:8080/matriculeme/web/rest/[TOKEN]/classREST/insert?JSON
	@Path("/{token}/{class}/{method}?{parameters}")
	public String restPUT(@PathParam("token") String token,@PathParam("class") String classe,@PathParam("method") String method,@PathParam("parameters") String parameters,@Context HttpServletResponse response,@Context HttpServletRequest request) throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		if((Security.tokenChecker(token) == false) || (Security.URLChecker(request.getRequestURL().toString()) == false))
		{
			response.sendRedirect("http://127.0.0.1:8080/matriculeme/web/rest/login");
			return "";
		}
		else
		{
			try 
			{
				Object out = FactoryUtil.executeMethod(PATH+classe+"REST", method, FactoryUtil.createParameters(method),FactoryUtil.createArgsJSON(parameters));
				return out.toString();
			}
			catch (NoSuchMethodException e) 
			{
				return "The method required: "+ method + "cannot be found.";
			} 
			catch (SecurityException e)
			{
				return "The page cannot be accessed.";
			} 
			catch (IllegalArgumentException e) 
			{
				return "The parameters: "+ parameters + "cannot be used as a parameter.";
			} 
			catch (InvocationTargetException e) 
			{
				return "The method required: "+ method + "failed in execution for de parameters: "+ parameters;
			}
		}
	}
	@PUT//http://127.0.0.1:8080/matriculeme/web/rest/[TOKEN]/classREST/edit?[JSON1]&[JSON2]
	@Path("/{token}/{class}/{method}?{parameters}")
	public String restPOST(@PathParam("token") String token,@PathParam("class") String classe,@PathParam("method") String method,@PathParam("parameters") String parameters,	@Context HttpServletResponse response,@Context HttpServletRequest request) throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		if((Security.tokenChecker(token) == false) || (Security.URLChecker(request.getRequestURL().toString()) == false))
		{
			response.sendRedirect("http://127.0.0.1:8080/matriculeme/web/rest/login");
			return "";
		}
		else
		{
			try 
			{
				Object out = FactoryUtil.executeMethod(PATH+classe+"REST", method, FactoryUtil.createParameters(method),FactoryUtil.createArgsJSON(parameters));
				return out.toString();
			}
			catch (NoSuchMethodException e) 
			{
				return "The method required: "+ method + "cannot be found.";
			} 
			catch (SecurityException e)
			{
				return "The page cannot be accessed.";
			} 
			catch (IllegalArgumentException e) 
			{
				return "The parameters: "+ parameters + "cannot be used as a parameter.";
			} 
			catch (InvocationTargetException e) 
			{
				return "The method required: "+ method + "failed in execution for de parameters: "+ parameters;
			}
		}
	}
}
