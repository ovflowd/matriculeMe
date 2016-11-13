package controller;


import helpers.Querys;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import modules.Curriculo;
import modules.Curso;
import modules.Disciplina;


@Path("/curriculos")
public class CurriculoController {
	
	@Path("/getCurriculos/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCurriculos() throws Exception
	{
		List<Curriculo> curriculos = Querys.queryGetList("Curriculo");
		Gson gson = new Gson(); 
		String json = gson.toJson(curriculos);
		return Response.ok(json, MediaType.APPLICATION_JSON).build();
	}
	
	@Path("/setAllCurr")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response setAllCoisas(List<Curriculo> curriculos) throws Exception
	{
		//Problema: se o cara nao passar id, nao eh possivel fazer a referencia, dai tem q instanciar
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDB");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		for (int i = 0; i < curriculos.size(); i++) 
		{
			Curriculo c = new Curriculo();
			c.setSemestreDisciplina(curriculos.get(i).getSemestreDisciplina());
			List<Curso> c1 = Querys.queryCustom("Curso", "codigo", String.valueOf(curriculos.get(i).getCurso().getCodigo()), false);
			c.setCurso(c1.get(0));
			List<Disciplina> d1 = Querys.queryCustom("Disciplina", "codigo", String.valueOf(curriculos.get(i).getDisciplina().getCodigo()), false);
			c.setDisciplina(d1.get(0));
			Querys.Persist(c); 
		}
		return Response.status(200).build(); 
	} 
}
