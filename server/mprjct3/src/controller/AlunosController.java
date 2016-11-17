package controller;

import com.google.gson.Gson;
import helpers.Queries;
import modules.Aluno;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/alunos")
public class AlunosController {

    private Response studentsResponse(List students) {
        return students.size() > 0 ?
                Response.ok(new Gson().toJson(students.get(0)),
                        MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*")
                        .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS").build()
                : Response.ok("{}", MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS").build();
    }

    @Path("/getAluno/nome={nome}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response example(@PathParam("nome") String nome) {
        List students = Queries.queryCustom("Aluno", "nome", nome, true);
        return studentsResponse(students);
    }

    @Path("/getAluno/login={login}&senha={senha}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response example(@PathParam("login") String login, @PathParam("senha") String senha) {
        List students = Queries.queryCustom("Aluno", "login", login, "senha", senha);
        return studentsResponse(students);
    }

    @Path("/setAluno/")
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public Response setHorarios(String mandatory) throws Exception {
        Aluno student = new Gson().fromJson(mandatory, Aluno.class);

        List students = Queries.queryCustom("Login", "accessKey", student.getLogin().getAccessKey(), true);

        // User Doesn't Exists
        if (students.size() == 0) {
            Queries.Persist(student.getLogin());
            Queries.Persist(student);
        }

        // If User Exists Return 200. If not, Return 403.
        return students.size() > 0 ?
                Response.status(483)
                        .header("Access-Control-Allow-Origin", "*")
                        .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS")
                        .allow("OPTIONS")
                        .build() :
                Response.status(200).header("Access-Control-Allow-Origin", "*")
                        .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS")
                        .allow("OPTIONS")
                        .build();
    }
}
