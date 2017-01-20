package com.unb.matriculeme.domain;

import com.google.gson.Gson;
import com.unb.matriculeme.dao.*;
import com.unb.matriculeme.helpers.ClientUtils;
import com.unb.matriculeme.helpers.PersistenceHelper;
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
    public Response getSuperDisciplinas(@PathParam("codigo") int codigo) {
        List cursos = PersistenceHelper.queryCustom("Curso", "codigo", codigo);
        List curriculo = PersistenceHelper.queryCustom("Curriculo", "curso", ((Curso) cursos.get(0)).getId());

        List<SuperDisciplina> superDisciplinas = new ArrayList<>();
        for (Object aCurriculo : curriculo) {
            SuperDisciplina sup1 = new SuperDisciplina(((Curriculo) aCurriculo).getDisciplina());
            List ofertas = PersistenceHelper.queryCustom("Oferta", "disciplina_id", ((Curriculo) curriculo.get(0)).getDisciplina().getId());
            if (ofertas.size() > 0) {
                System.out.println(((Oferta) ofertas.get(0)).getId());
                List<Turma> turmas = PersistenceHelper.queryCustom("Turma", "oferta_id", ((Oferta) ofertas.get(0)).getId());
                if (turmas.size() > 0) {
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
 