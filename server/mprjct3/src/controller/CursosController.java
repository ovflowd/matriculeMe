package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import helpers.Querys;
import modules.Curso;


@Path("/cursos")
public class CursosController {	
	
	public void Update(Curso curso, Curso newCurso)
	{	
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDB");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Curso c2 = em.merge(curso);
		c2.setNome(newCurso.getNome());
		c2.setCodigo(newCurso.getCodigo());
		em.getTransaction().commit();
		em.close();
	}
	
	@Path("/alterCurso/name={nome}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response alterCurso(@PathParam ("nome") String nome, 
								Curso curso) throws Exception
	{
		List<Curso> cursos = Querys.queryCustom("Curso", "nome" , nome, true);
		if (cursos.size() > 0)
		{
			Update(cursos.get(0), curso);
			return Response.status(200).build();
		}
		else
		{
			return Response.status(404).build();
		}
	}
	
	@Path("/setCurso")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response sayPlainTextHello(Curso curso) throws Exception
	{
		Querys.Persist(curso);
		return Response.status(200).build();
	}

	@Path("/getCurso/nome={nome}")
    @GET  
    @Produces(MediaType.APPLICATION_JSON)  
    public Response convertFeetToInch(@PathParam("nome") String nome) {
        
		List<Curso> cursos = Querys.queryCustom("Curso", "nome", nome, true);
		if (cursos.size() > 0)
		{
			Gson gson = new Gson(); 
			String json = gson.toJson(cursos);
			return Response.ok(json, MediaType.APPLICATION_JSON).build();
		}
		else
		{
			return Response.status(404).build();
		}
    }  
	
	@Path("/getCurso/nome={nome}&codigo={codigo}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response example(@PathParam("nome") String nome, 
						 @PathParam ("codigo") int codigo)
	{
		List<Curso> cursos = Querys.queryCustom("Curso", "nome", nome, "codigo", String.valueOf(codigo) );
	    if (cursos.size() > 0)
	    {
	    	Gson gson = new Gson();
	    	String json = gson.toJson(cursos); 
	    	return Response.ok(json,  MediaType.APPLICATION_JSON).build();
	    }
	    else 
	    {
	    	return Response.status(404).build();
	    }
	    
	}
}
