package com.unb.matriculeme.domain;

import com.google.gson.Gson;
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
    public Response trial(@PathParam("intext") String intext) {
        List disciplinas = PersistenceHelper.queryCustomLike("Disciplina", "nome", intext);

        return disciplinas.size() > 0 ? ClientUtils.sendResponse(disciplinas) : ClientUtils.sendMessage(new NotFoundMessage("The desired Discipline wasn't found in our system."));
    }

    @Path("/getDisciplina/nome={nome}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response example(@PathParam("nome") String nome) {
        List disciplinas = PersistenceHelper.queryCustom("Disciplina", "nome", nome, true);

        return disciplinas.size() > 0 ? Response.ok(new Gson().toJson(disciplinas.get(0)),
                MediaType.APPLICATION_JSON).build() : Response.status(404).build();
    }

    @Path("/setDisciplina/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setHorarios(List<Disciplina> allDis) throws Exception {

        for (Disciplina dis : allDis) {
            Disciplina disciplina = new Disciplina();

            List department = PersistenceHelper.queryCustom("Departamento", "codigo", String.valueOf(dis.getDepartamento().getCodigo()), false);
            List<Requisito> requisitos = new ArrayList<Requisito>();
            
            for (int i = 0 ; i < dis.getRequisitoDisciplina().size(); i++)
            { 
            	PersistenceHelper.Persist(dis.getRequisitoDisciplina().get(i));
            	requisitos.add((Requisito)(PersistenceHelper.queryCustom("Requisito", "codigo", dis.getRequisitoDisciplina().get(i).getCodigo(), true).get(0)));
            }
            disciplina.setCodigo(dis.getCodigo());
            disciplina.setCredito(dis.getCredito()); 
            disciplina.setNome(dis.getNome()); 
            
            disciplina.setRequisitoDisciplina(requisitos);
            
            if (department.size() > 0)
            {
                disciplina.setDepartamento((Departamento) department.get(0));	
            }

            PersistenceHelper.Persist(disciplina);
        }
        return ClientUtils.sendMessage(new AllRightMessage("The Discipline was added successfully on the system."));
    }
}
