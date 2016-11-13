package controller;

//TODO: esse ta liso, refazer o controller
import helpers.Querys;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import modules.Horario;
import modules.Turma;

@Path("/horarios")
public class HorariosController {

	@Path("/setHorario")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response setHorario(Horario horario)
	{
		Horario h1 = new Horario();
		//h1.setCodigo(horario.getCodigo());
		h1.setHorarioFim(horario.getHorarioFim());
		h1.setHorarioInicio(horario.getHorarioInicio());
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDB");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		//Turma t1 = em.find(Turma.class, horario.getTurma().getId());
		//h1.setTurma(t1);
		Querys.Persist(h1);
		return Response.status(200).build();
	}
}
