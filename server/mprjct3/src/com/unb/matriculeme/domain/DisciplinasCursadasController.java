package com.unb.matriculeme.domain;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.unb.matriculeme.dao.Aluno;
import com.unb.matriculeme.dao.DisciplinasCursadas;
import com.unb.matriculeme.helpers.PersistenceHelper;
import com.unb.matriculeme.helpers.RestClient;

import java.util.List;

@Path("/disciplinasCursadas/")
public class DisciplinasCursadasController {

	@Path("setHist")
	@POST
	@Consumes(MediaType.TEXT_PLAIN)       
	public Response setHist(String string)   
	{  
		System.out.println("\n\n chegou" + string);  
		System.out.println("\n\n passou 2! \n\n");      
		Trigger thread = new Trigger(string, "http://homol.redes.unb.br/ptr022016-b/mining/rest/datamining/historico", false);
        thread.start(); 
		System.out.println("\n\n passou! \n\n");
		return Response.status(200).build();  
	} 
	
	@Path("/getDisciplinasCursadas/matricula={mat}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDisciplinasCursadas(@PathParam("mat") int Matricula)
	{
//		List<Aluno> aluno = Queries.queryCustom("Aluno", "matricula", String.valueOf(Matricula), false);
//		aluno.get(0).getDisciplinasCursadas();
//		Essa esta em duvwidas para matheus
		return Response.status(200).build();
	}
	
    @Path("/setDisciplinasCursadas/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response example(List<DisciplinasCursadas> disciplinasCursadas) {
    	//List aluno = Queries.queryCustom("Aluno", "matricula", String.valueOf(disciplinasCursadas.get(0).getAluno().getMatricula()), false);
    	
    	for (int i = 0; i < disciplinasCursadas.size(); i++)
    	{
    		List mencao = PersistenceHelper.queryCustom("Mencao", "codigo", disciplinasCursadas.get(0).getMencao().getCodigo(), true);
    		PersistenceHelper.Persist(disciplinasCursadas.get(i));
    	} 
    	return Response.status(200).build();
    }
}
