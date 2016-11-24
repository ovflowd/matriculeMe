package com.unb.matriculeme.domain;

import com.unb.matriculeme.dao.Professor;
import com.unb.matriculeme.helpers.ClientUtils;
import com.unb.matriculeme.helpers.PersistenceHelper;
import com.unb.matriculeme.messages.AllRightMessage;
import com.unb.matriculeme.messages.NotFoundMessage;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/professor")
public class ProfessoresController {
    @Path("/setProfessor")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setProfessor(Professor professor) throws Exception {
        Professor p1 = new Professor();

        p1.setNome(professor.getNome());

        PersistenceHelper.insert(p1);

        return ClientUtils.sendMessage(new AllRightMessage("Professor added successfully in the system."));
    }

    @Path("/getProfessorNome/nome={nome}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getProfessorByNome(@PathParam("nome") String nome) {
        List professors = PersistenceHelper.queryCustom(Professor.class, "nome", nome);

        return professors.size() > 0 ? ClientUtils.sendResponse(professors.get(0)) : ClientUtils.sendMessage(new NotFoundMessage("This Professor wasn't found on our system."));
    }

    @Path("/alterProfessor/nome={nome}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateProfessor(@PathParam("nome") String nome, Professor professor) throws Exception {
        List professors = PersistenceHelper.queryCustom(Professor.class, "nome", nome);

        if (professors.size() > 0) {
            PersistenceHelper.update(professors.get(0), professor);
        }

        return ClientUtils.sendMessage(professors.size() > 0 ? new AllRightMessage("Professor changed successfully.") : new NotFoundMessage("This professor wasn't found on our system."));
    }
}
