package com.unb.matriculeme.domain;

import com.mysema.commons.lang.Pair;
import com.unb.matriculeme.dao.Professor;
import com.unb.matriculeme.helpers.ClientUtils;
import com.unb.matriculeme.helpers.Persistence;
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
        Persistence.insert(professor);

        return ClientUtils.sendMessage(new AllRightMessage("Professor added successfully in the system."));
    }

    @Path("/getProfessorNome/nome={nome}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getProfessorByNome(@PathParam("nome") String nome) {
        List<Professor> professors = Persistence.select(Professor.class, Persistence.createExpression(new Pair<>("nome", nome)), true);

        return professors.size() > 0 ? ClientUtils.sendResponse(professors.get(0)) : ClientUtils.sendMessage(new NotFoundMessage("This Professor wasn't found on our system."));
    }

    @Path("/alterProfessor/nome={nome}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateProfessor(@PathParam("nome") String nome, Professor professor) throws Exception {
        List<Professor> professors = Persistence.select(Professor.class, Persistence.createExpression(new Pair<>("nome", nome)), true);

        professor.setId(professors.get(0).getId());

        if (professors.size() > 0) {
            Persistence.update(professors.get(0), professor);
        }

        return ClientUtils.sendMessage(professors.size() > 0 ? new AllRightMessage("Professor changed successfully.") : new NotFoundMessage("This professor wasn't found on our system."));
    }
}
