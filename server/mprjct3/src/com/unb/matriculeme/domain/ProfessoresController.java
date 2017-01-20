package com.unb.matriculeme.domain;
//TODO: esse ta liso, refazer o domain

import com.unb.matriculeme.dao.Professor;
import com.unb.matriculeme.helpers.*;
import com.unb.matriculeme.helpers.PersistenceHelper;
import com.unb.matriculeme.messages.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/professor")
public class ProfessoresController {

    @Path("/setProfessor")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response sayPlainTextHello(Professor professor) throws Exception {
        Professor p1 = new Professor();
        p1.setNome(professor.getNome());
        PersistenceHelper.Persist(p1);

        return ClientUtils.sendMessage(new AllRightMessage("Professor set successfully."));
    }

    @Path("/getProfessorNome/name={nome}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public Response convertFeetToInch(@PathParam("nome") String nome) {

        List professors = PersistenceHelper.queryCustom("Professor", "nome", nome);

        return professors.size() > 0 ? ClientUtils.sendResponse(professors.get(0)) :
                ClientUtils.sendMessage(new NotFoundMessage("This Professor wasn't found on our system."));
    }

    @Path("/alterProfessor/name={nome}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response alterProfessor(@PathParam("nome") String nome, Professor professor) throws Exception {
        List professors = PersistenceHelper.queryCustom("Professor", "nome", nome);

        if (professors.size() > 0) {
            //PersistenceHelper.update(professors.get(0), professor);
        }

        return ClientUtils.sendMessage(professors.size() > 0 ? new AllRightMessage("Professor changed successfully.") :
                new NotFoundMessage("This professor wasn't found on our system."));
    }
}
