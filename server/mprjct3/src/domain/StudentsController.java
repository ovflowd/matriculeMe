package domain;

import com.google.gson.Gson;
import dao.Student;
import helpers.ClientUtils;
import helpers.PersistenceHelper;
import messages.BaseMessage;
import messages.NotFoundMessage;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/alunos")
public class StudentsController {

    // Recommended change "nome" to "name"
    @Path("/getAluno/nome={nome}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentData(@PathParam("nome") String nome) {
        List students = PersistenceHelper.queryCustom("Student", "nome", nome, true);
        return students.size() > 0 ? ClientUtils.sendResponse(students.get(0)) :
                ClientUtils.sendMessage(new NotFoundMessage("This User wasn't found on our system."));
    }

    // Recommended change "senha" to "password"
    @Path("/getAluno/login={login}&senha={senha}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentData(@PathParam("login") String login, @PathParam("senha") String senha) {
        List students = PersistenceHelper.queryCustom("Student", "login", login, "senha", senha);
        return students.size() > 0 ? ClientUtils.sendResponse(students.get(0)) :
                ClientUtils.sendMessage(new NotFoundMessage("This User wasn't found on our system."));
    }

    @Path("/setAluno/")
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public Response setSchedules(String mandatory) throws Exception {
        Student student = new Gson().fromJson(mandatory, Student.class);

        List students = PersistenceHelper.queryCustom("Login", "accessKey", student.getLogin().getAccessKey(), true);

        // User Doesn't Exists
        if (students.size() == 0) {
            PersistenceHelper.Persist(student.getLogin());
            PersistenceHelper.Persist(student);
        }

        return ClientUtils.sendMessage(students.size() > 0 ? new BaseMessage(483, "User Already Exists. Creation not Allowed.") :
                new BaseMessage(200, "User Doesn't exists. Creation Allowed."));
    }
}
