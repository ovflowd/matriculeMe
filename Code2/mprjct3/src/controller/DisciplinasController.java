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

import modules.Departamento;
import modules.Disciplina;

@Path("/disciplinas")
public class DisciplinasController {
	
	@Path("/getDisciplina/innome={intext}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response trial(@PathParam("intext") String intext)
	{
		List<Disciplina> disciplinas = Querys.queryCustomLike("Disciplina", "nome", intext);
		if (disciplinas.size() > 0)
		{
		Gson gson = new Gson(); 
		String json = gson.toJson(disciplinas);
			return Response.ok(json,  MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*")
	    			.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS").build();
	    }
		else
		{
			return Response.status(404).header("Access-Control-Allow-Origin", "*")
	    			.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS").build();
		}
	}
	
	@Path("/getDisciplina/nome={nome}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response example(@PathParam("nome") String nome)
	{
		List<Disciplina> disciplinas = Querys.queryCustom("Disciplina", "nome", nome, true);
	    if (disciplinas.size() > 0)
	    {
	    	Gson gson = new Gson(); 
			String json = gson.toJson(disciplinas.get(0));
	    	return Response.ok(json,  MediaType.APPLICATION_JSON).build();
	    }
	    else 
	    {
	    	return Response.status(404).build();
	    }	    
	}
	
	
	@Path("/setDisciplina/")  
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response setHorarios(List<Disciplina> d0) throws Exception
	{
		for (int i = 0; i < d0.size(); i++)
		{
			Disciplina disciplina = new Disciplina();
			List<Departamento> d1 = Querys.queryCustom("Departamento", "codigo", String.valueOf(d0.get(0).getDepartamento().getCodigo()), false);
			disciplina.setCodigo(d0.get(i).getCodigo());
			disciplina.setCredito(d0.get(i).getCredito());
			disciplina.setNome(d0.get(i).getNome());
			disciplina.setDepartamento(d1.get(0));
			Querys.Persist(disciplina);
		}
		return Response.status(200).build();
	}
	
}
