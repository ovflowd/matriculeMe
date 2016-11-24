package com.unb.matriculeme.domain;

import com.unb.matriculeme.dao.Departamento;
import com.unb.matriculeme.dao.Disciplina;
import com.unb.matriculeme.dao.Requisito;
import com.unb.matriculeme.helpers.ClientUtils;
import com.unb.matriculeme.helpers.PersistenceHelper;
import com.unb.matriculeme.messages.AllRightMessage;
import com.unb.matriculeme.messages.NotFoundMessage;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/disciplinas")
public class DisciplinasController {
    @Path("/getDisciplina/innome={intext}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDisciplinaByCriteria(@PathParam("intext") String disciplineText) {
        List disciplinas = PersistenceHelper.queryCustomLike(Disciplina.class, "nome", disciplineText);

        return disciplinas.size() > 0 ? ClientUtils.sendResponse(disciplinas) : ClientUtils.sendMessage(new NotFoundMessage("The desired Discipline wasn't found in our system."));
    }

    @Path("/getDisciplina/nome={nome}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDisciplinaByNome(@PathParam("nome") String nome) {
        List disciplinas = PersistenceHelper.queryCustom(Disciplina.class, "nome", nome);

        return disciplinas.size() > 0 ? ClientUtils.sendResponse(disciplinas.get(0)) : ClientUtils.sendMessage(new NotFoundMessage("The discipline wasn't found on the system."));
    }

    @Path("/setDisciplina/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setManyDisciplinas(List<Disciplina> allDis) throws Exception {
        for (Disciplina dis : allDis) {
            Disciplina disciplina = new Disciplina();

            List department = PersistenceHelper.queryCustom(Departamento.class, "codigo", dis.getDepartamento().getCodigo());
            List<Requisito> requisitos = new ArrayList<>();

            for (int i = 0; i < dis.getRequisitoDisciplina().size(); i++) {
                PersistenceHelper.insert(dis.getRequisitoDisciplina().get(i));

                requisitos.add((Requisito) (PersistenceHelper.queryCustom(Requisito.class, "codigo", dis.getRequisitoDisciplina().get(i).getCodigo()).get(0)));
            }

            disciplina.setCodigo(dis.getCodigo());
            disciplina.setCredito(dis.getCredito());
            disciplina.setNome(dis.getNome());

            disciplina.setRequisitoDisciplina(requisitos);

            if (department.size() > 0) {
                disciplina.setDepartamento((Departamento) department.get(0));
            }

            PersistenceHelper.insert(disciplina);
        }

        return ClientUtils.sendMessage(new AllRightMessage("The Disciplines was added successfully on the system."));
    }
}
