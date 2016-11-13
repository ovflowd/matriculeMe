package controller;

import helpers.Querys;

import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import modules.DisciplinasCursadas;

@Path("/disciplinasCursadas/")
public class DisciplinasCursadasController {
	@Path("/setDisciplinasCursadas/")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response example (List<DisciplinasCursadas> disciplinascursadas)
	{
		for (int i = 0; i < disciplinascursadas.size(); i++)
		{ 
			DisciplinasCursadas d1 = new DisciplinasCursadas();
			d1 = disciplinascursadas.get(i);
			Querys.Persist(d1); 
		}  
		return Response.status(200).build(); 
	}
	
}
