package com.unb.matriculeme.domain;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.unb.matriculeme.dao.Curso;
import com.unb.matriculeme.dao.Disciplina;
import com.unb.matriculeme.dao.Oferta;
import com.unb.matriculeme.dao.Semestre;
import com.unb.matriculeme.dao.Turma;
import com.unb.matriculeme.helpers.ClientUtils;
import com.unb.matriculeme.helpers.PersistenceHelper;
import com.unb.matriculeme.messages.NotFoundMessage;

@Path("/turmas/")
public class TurmaController {
	/*
	 * POST
	 * Professor - Persistir(Professor)
	 * Oferta  - Persistir(Oferta) Vai mandar oferta (Disciplina e Semestre ja vao existir)
	 * Horarios - Persistir (Horarios) 
	 */
	
	@Path("/getTurmas/codigo={codigo}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllCoisas(@PathParam("codigo") String codigo) throws Exception {
		List Turmas = PersistenceHelper.queryCustom("Turma", "codigo", codigo, true);
		 return Turmas.size() > 0 ? ClientUtils.sendResponse(Turmas) :
	            ClientUtils.sendMessage(new NotFoundMessage("This User wasn't found on our system."));
	}
	
	@Path("/setTurmas/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setAllCoisas(List<Turma> allTurmas) throws Exception {
		
        for (int i = 0; i < allTurmas.size(); i++)
        {
        	PersistenceHelper.Persist(allTurmas.get(i).getProfessor());
        	allTurmas.get(i).getOferta().setDisciplina((Disciplina)(PersistenceHelper.queryCustom("Disciplina", "codigo", String.valueOf(allTurmas.get(i).getOferta().getDisciplina().getCodigo()), false).get(0)));
        	allTurmas.get(i).getOferta().setSemestre((Semestre)(PersistenceHelper.queryCustom("Semestre", "codigo", allTurmas.get(i).getOferta().getSemestre().getCodigo(), true).get(0)));
        	PersistenceHelper.Persist(allTurmas.get(i).getOferta());  
        	for (int j = 0 ; j < allTurmas.get(i).getHorario().size(); j++)
        	{
        		PersistenceHelper.Persist(allTurmas.get(i).getHorario().get(j));
        	}  
        	PersistenceHelper.Persist(allTurmas.get(i)); 
       	}

        return Response.status(200).build();
    }
	
}
