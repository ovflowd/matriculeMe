package domain;

import com.google.gson.Gson;
import dao.Department;
import dao.Discipline;
import helpers.PersistenceHelper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/disciplinas")
public class DisciplineController {

    @Path("/getDiscipline/innome={intext}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response trial(@PathParam("intext") String intext) {
        List disciplinas = PersistenceHelper.queryCustomLike("Discipline", "nome", intext);

        return disciplinas.size() > 0 ?
                Response.ok(new Gson().toJson(disciplinas),
                        MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*")
                        .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS").build() :
                Response.status(404).header("Access-Control-Allow-Origin", "*")
                        .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS").build();
    }

    @Path("/getDiscipline/nome={nome}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response example(@PathParam("nome") String nome) {
        List disciplinas = PersistenceHelper.queryCustom("Discipline", "nome", nome, true);

        return disciplinas.size() > 0 ? Response.ok(new Gson().toJson(disciplinas.get(0)),
                MediaType.APPLICATION_JSON).build() : Response.status(404).build();
    }

    @Path("/setDiscipline/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setHorarios(List<Discipline> allDis) throws Exception {

        for (Discipline dis : allDis) {
            Discipline discipline = new Discipline();

            List department = PersistenceHelper.queryCustom("Department", "codigo", String.valueOf(allDis.get(0).getDepartment().getCode()), false);

            discipline.setCode(dis.getCode());
            discipline.setCredits(dis.getCredits());
            discipline.setName(dis.getName());

            discipline.setDepartment((Department) department.get(0));

            PersistenceHelper.persist(discipline);
        }
        return Response.status(200).build();
    }
}
