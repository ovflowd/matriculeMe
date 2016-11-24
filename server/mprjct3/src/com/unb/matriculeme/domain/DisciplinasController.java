package com.unb.matriculeme.domain;

import com.mysema.commons.lang.Pair;
import com.unb.matriculeme.dao.Departamento;
import com.unb.matriculeme.dao.Disciplina;
import com.unb.matriculeme.dao.Requisito;
import com.unb.matriculeme.helpers.ClientUtils;
import com.unb.matriculeme.helpers.Persistence;
import com.unb.matriculeme.messages.AllRightMessage;
import com.unb.matriculeme.messages.NotFoundMessage;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/disciplinas")
public class DisciplinasController {
    @Path("/getDisciplina/innome={likeText}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDisciplinasByCriteria(@PathParam("likeText") String disciplineText) {
        List<Disciplina> disciplinas = Persistence.select(Disciplina.class, Persistence.createExpression(new Pair<>("nome", disciplineText)), false);

        return disciplinas.size() > 0 ? ClientUtils.sendResponse(disciplinas) : ClientUtils.sendMessage(new NotFoundMessage("The desired Discipline wasn't found in our system."));
    }

    @Path("/getDisciplina/nome={nome}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDisciplinaByNome(@PathParam("nome") String nome) {
        List<Disciplina> disciplinas = Persistence.select(Disciplina.class, Persistence.createExpression(new Pair<>("nome", nome)), true);

        return disciplinas.size() > 0 ? ClientUtils.sendResponse(disciplinas.get(0)) : ClientUtils.sendMessage(new NotFoundMessage("The discipline wasn't found on the system."));
    }

    @Path("/setDisciplina/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setManyDisciplinas(List<Disciplina> allDis) throws Exception {
        for (Disciplina dis : allDis) {
            Disciplina disciplina = new Disciplina();

            List<Departamento> departamentos = Persistence.select(Departamento.class, Persistence.createExpression(new Pair<>("codigo", dis.getDepartamento().getCodigo())), true);
            List<Requisito> requisitos = new ArrayList<>();

            for (int i = 0; i < dis.getRequisitoDisciplina().size(); i++) {
                Persistence.insert(dis.getRequisitoDisciplina().get(i));

                requisitos.add((Requisito) Persistence.select(Requisito.class, Persistence.createExpression(new Pair<>("codigo", dis.getRequisitoDisciplina().get(i).getCodigo())), true).get(0));
            }

            disciplina.setCodigo(dis.getCodigo());
            disciplina.setCredito(dis.getCredito());
            disciplina.setNome(dis.getNome());

            disciplina.setRequisitoDisciplina(requisitos);

            if (departamentos.size() > 0) {
                disciplina.setDepartamento(departamentos.get(0));
            }

            Persistence.insert(disciplina);
        }

        return ClientUtils.sendMessage(new AllRightMessage("The Disciplines was added successfully on the system."));
    }
}
