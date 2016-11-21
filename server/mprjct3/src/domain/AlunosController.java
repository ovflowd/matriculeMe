package domain;

import com.google.gson.Gson;
import dao.Aluno;
import helpers.ClientUtils;
import helpers.PersistenceHelper;
import messages.NotFoundMessage;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/alunos")
public class AlunosController {

    private Response StudentsResponse(List students) {
        return students.size() > 0 ? ClientUtils.SendResponse(students.get(0), MediaType.APPLICATION_JSON) :
                ClientUtils.SendMessage(new NotFoundMessage());
    }

    @Path("/getAluno/nome={nome}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response example(@PathParam("nome") String nome) {
        List students = PersistenceHelper.queryCustom("Aluno", "nome", nome, true);
        return StudentsResponse(students);
    }

    @Path("/getAluno/login={login}&senha={senha}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response example(@PathParam("login") String login, @PathParam("senha") String senha) {
        List students = PersistenceHelper.queryCustom("Aluno", "login", login, "senha", senha);
        return StudentsResponse(students);
    }

    @Path("/setAluno/")
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public Response setHorarios(String mandatory) throws Exception {
        Aluno student = new Gson().fromJson(mandatory, Aluno.class);

        List students = PersistenceHelper.queryCustom("Login", "accessKey", student.getLogin().getAccessKey(), true);

        // User Doesn't Exists
        if (students.size() == 0) {
            PersistenceHelper.Persist(student.getLogin());
            PersistenceHelper.Persist(student);
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
