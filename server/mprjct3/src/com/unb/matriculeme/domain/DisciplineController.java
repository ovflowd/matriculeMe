package com.unb.matriculeme.domain;

import com.google.gson.Gson;
import com.unb.matriculeme.dao.Departamento;
import com.unb.matriculeme.dao.Disciplina;
import com.unb.matriculeme.helpers.ClientUtils;
import com.unb.matriculeme.helpers.PersistenceHelper;
import com.unb.matriculeme.messages.AllRightMessage;
import com.unb.matriculeme.messages.NotFoundMessage;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/disciplinas")
public class DisciplineController {

    // What is "in-nome"?? (Change "nome" to "name")
    @Path("/getDiscipline/innome={nome}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response trial(@PathParam("nome") String innome) {
        List disciplinas = PersistenceHelper.queryCustomLike("Disciplina", "nome", innome);
        return disciplinas.size() > 0 ? ClientUtils.sendResponse(disciplinas) : ClientUtils.sendMessage(new NotFoundMessage("The desired Discipline wasn't found in our system."));
    }

    @Path("/getDiscipline/nome={nome}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response example(@PathParam("nome") String nome) {
        List disciplinas = PersistenceHelper.queryCustom("Disciplina", "nome", nome, true);

        return disciplinas.size() > 0 ? Response.ok(new Gson().toJson(disciplinas.get(0)),
                MediaType.APPLICATION_JSON).build() : Response.status(404).build();
    }

    @Path("/setDiscipline/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setHorarios(List<Disciplina> allDis) throws Exception {

        for (Disciplina dis : allDis) {
            Disciplina discipline = new Disciplina();

            List department = PersistenceHelper.queryCustom("Departamento", "codigo", String.valueOf(allDis.get(0).getDepartamento().getCodigo()), false);

            discipline.setCodigo(dis.getCodigo());
            discipline.setCredito(dis.getCredito());
            discipline.setNome(dis.getNome());
            discipline.setRequisitoDisciplina(dis.getRequisitoDisciplina());
            discipline.setTurmas(dis.getTurmas());

            discipline.setDepartamento((Departamento) department.get(0));

            PersistenceHelper.persist(discipline);
        }
        return ClientUtils.sendMessage(new AllRightMessage("The Discipline was added successfully on the system."));
    }
}
