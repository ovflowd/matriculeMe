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
  
@Path("/departamentos/")
public class DepartamentosController {
	@Path("/getDepartamento/nome={nome}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response example(@PathParam("nome") String nome)
	{
		List<Departamento> departamentos = Querys.queryCustom("Departamento", "nome", nome, true);
	    if (departamentos.size() > 0)
	    {
	    	Gson gson = new Gson();
	    	String json = gson.toJson(departamentos.get(0));
	    	return Response
	    			.ok(json,  MediaType.APPLICATION_JSON)
	    			.header("Access-Control-Allow-Origin", "*")
	    			.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
	    			.build();
	    }
	    else 
	    {
	    	return Response.status(404).build();
	    }	    
	}
	
	@Path("/setAllDeps/")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response setAllCoisas(List<Departamento> d_all) throws Exception
	{
		System.out.println("received size: " +  d_all.size());
		for (int i = 0; i < d_all.size() ; i++)
		{
			Querys.Persist(d_all.get(i));
		}
		return Response.status(200).build();
	}
	
	@Path("/setDepartamento/")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response setHorarios(Departamento d0) throws Exception
	{
		Querys.Persist(d0);
		return Response.status(200).build();   
	}   
}
