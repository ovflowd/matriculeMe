package controller;

//TODO: esse ta liso, refazer o controller
import helpers.Querys;
import helpers.ClientRest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import modules.Aluno;

import org.json.JSONObject;

@Path ("/historico")
public class HistoricoController {
	

//	@Path("/setHist/")
//	@POST
//	@Consumes(MediaType.TEXT_PLAIN)
//	public Response setHorarios(String mandado) throws Exception
//	{
//	ClientRest exClient = new ClientRest();
//	System.out.println(mandado);
//	exClient.enviarDados(mandado);
//	return Response.status(200) 
//			.header("Access-Control-Allow-Origin", "*")
//			.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS")
//			.allow("OPTIONS")
//			.build(); 
//	}
//	
	@Path("/setHist/")
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	public Response setHorarios(String mandado) throws Exception
	{
	ClientRest exClient = new ClientRest();
	System.out.println(mandado);
	exClient.enviarDados(mandado);
	return Response.status(200) 
			.header("Access-Control-Allow-Origin", "*")
			.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS")
			.allow("OPTIONS")
			.build(); 
	}
}
