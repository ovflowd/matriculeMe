package controller;

import helpers.Queries;
import modules.DisciplinasCursadas;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/disciplinasCursadas/")
public class DisciplinasCursadasController {

    @Path("/setDisciplinasCursadas/")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response example(List<DisciplinasCursadas> disciplinasCursadas) {
        disciplinasCursadas.forEach(Queries::Persist);
        return Response.status(200).build();
    }
}
