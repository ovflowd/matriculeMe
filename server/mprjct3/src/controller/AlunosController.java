package controller;

import helpers.Querys;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;
import modules.Aluno;
import modules.Login;

@Path("/alunos")
public class AlunosController {
	
	@Path("/getAluno/nome={nome}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response example(@PathParam("nome") String nome)
	{
		List<Aluno> alunos = Querys.queryCustom("Aluno", "nome", nome, true);
		if (alunos.size() > 0)
		{
			Gson gson = new Gson(); 
			String json = gson.toJson(alunos.get(0));  
			return Response.ok(json, MediaType.APPLICATION_JSON)
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS").build();
		}
		else
		{
			return Response.status(404).build();
		}
	}
	
	@Path("/getAluno/login={login}&senha={senha}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response example(@PathParam("login") String login,
							@PathParam("senha") String senha)
	{
		List<Aluno> alunos = Querys.queryCustom("Aluno", "login", login, "senha", senha);
	    if (alunos.size() > 0)
	    {
	    	
	    	Gson gson = new Gson(); 
			String json = gson.toJson(alunos.get(0));  
			return Response.ok(json, MediaType.APPLICATION_JSON)
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS").build();
	    }
	    else 
	    {
	    	return Response.status(404).build();
	    }	    
	}
	
	@Path("/setAluno/")
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	public Response setHorarios(String mandado) throws Exception
	{
		 Gson gson = new Gson();
	     Aluno aluno = gson.fromJson(mandado, Aluno.class);
	     Login login = aluno.getLogin();
	     List<Aluno> alunos = Querys.queryCustom("Login", "accessKey", aluno.getLogin().getAccessKey(), true);
		 if (alunos.size() > 0) //Ja existe o access key (unsuario) do tio
		 {
					 return Response.status(483)  
			 			.header("Access-Control-Allow-Origin", "*")
		    			.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS")
		    			.allow("OPTIONS")
		    			.build();
		 }  
		 Querys.Persist(login);
		 Querys.Persist(aluno);
		 return Response.status(200) 
				.header("Access-Control-Allow-Origin", "*")
    			.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS")
    			.allow("OPTIONS")
    			.build(); 
	}
}
