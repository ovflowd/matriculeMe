package com.unb.matriculeme.domain;

import com.google.gson.Gson;
import com.unb.matriculeme.dao.Curriculo;
import com.unb.matriculeme.dao.Curso;
import com.unb.matriculeme.dao.Departamento;
import com.unb.matriculeme.dao.Disciplina;
import com.unb.matriculeme.dao.Oferta;
import com.unb.matriculeme.dao.Requisito;
import com.unb.matriculeme.dao.SuperDisciplina;
import com.unb.matriculeme.dao.Turma;
import com.unb.matriculeme.helpers.ClientUtils;
import com.unb.matriculeme.helpers.PersistenceHelper;
import com.unb.matriculeme.messages.AllRightMessage;
import com.unb.matriculeme.messages.NotFoundMessage;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;
 
@Path("/superDisciplina")
public class SuperDisciplinaController {
	@Path("/getSuperDisciplina/codCurso={codigo}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSuperDisciplinas(@PathParam ("codigo") int codigo){
		List cursos = PersistenceHelper.queryCustom("Curso", "codigo", String.valueOf(codigo), false);
        List curriculo = PersistenceHelper.queryCustom("Curriculo", "curso", String.valueOf(((Curso)cursos.get(0)).getId()) ,false);

        List<SuperDisciplina> superDisciplinas = new ArrayList<>();
        for (int i = 0; i < curriculo.size(); i++){
        	SuperDisciplina sup1 = new SuperDisciplina(((Curriculo)curriculo.get(i)).getDisciplina()); 
        	List ofertas	 = PersistenceHelper.queryCustom("Oferta", "disciplina_id", String.valueOf(((Curriculo)curriculo.get(0)).getDisciplina().getId()), false);
			if (ofertas.size() > 0)
			{
				System.out.println(((Oferta)ofertas.get(0)).getId());
				List<Turma> turmas	= PersistenceHelper.queryCustom("Turma", "oferta_id", String.valueOf(((Oferta)ofertas.get(0)).getId()), false);
				if (turmas.size() > 0){ 
					sup1.setTurmas(turmas); 
					System.out.println("\n\n tem turmas aqui \n\n");
				}  
			}  
			System.out.println("\n\n " + new Gson().toJson(sup1)); 
			superDisciplinas.add(sup1);  
		}
        return curriculo.size() > 0 ? ClientUtils.sendResponse(superDisciplinas) :
            ClientUtils.sendMessage(new NotFoundMessage("Esse curso nao esta presente."));
	}
}
 