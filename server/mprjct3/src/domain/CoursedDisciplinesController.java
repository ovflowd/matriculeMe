package domain;

import helpers.PersistenceHelper;
import dao.CoursedDisciplines;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/disciplinasCursadas/")
public class CoursedDisciplinesController {

    @Path("/setDisciplinasCursadas/")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response example(List<CoursedDisciplines> disciplinasCursadas) {
        disciplinasCursadas.forEach(PersistenceHelper::persist);
        return Response.status(200).build();
    }
}
