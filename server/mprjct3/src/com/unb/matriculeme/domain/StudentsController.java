package com.unb.matriculeme.domain;

import com.google.gson.Gson;
import com.unb.matriculeme.dao.Student;
import com.unb.matriculeme.helpers.ClientUtils;
import com.unb.matriculeme.helpers.PersistenceHelper;
import com.unb.matriculeme.messages.BaseMessage;
import com.unb.matriculeme.messages.NotFoundMessage;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/alunos")
public class StudentsController {

    // Recommended change "nome" to "name"
    @Path("/alterAluno/nome={nome}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response alterAluno(@PathParam("nome") String nome, Student student) throws Exception {
        List students = PersistenceHelper.queryCustom("Aluno", "nome", nome, true);

        if (students.size() > 0) {
            PersistenceHelper.update((Student) students.get(0), student);
        }

        return ClientUtils.sendMessage(students.size() > 0 ? new BaseMessage(200, "Student changed successfully.") :
                new NotFoundMessage("This student wasn't found on our system."));
    }

    // Recommended change "nome" to "name"
    @Path("/getAluno/nome={nome}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentData(@PathParam("nome") String nome) {
        List students = PersistenceHelper.queryCustom("Aluno", "nome", nome, true);
        return students.size() > 0 ? ClientUtils.sendResponse(students.get(0)) :
                ClientUtils.sendMessage(new NotFoundMessage("This User wasn't found on our system."));
    }

    // Recommended change "senha" to "password"
    @Path("/getAluno/login={login}&senha={senha}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentData(@PathParam("login") String login, @PathParam("senha") String senha) {
        List students = PersistenceHelper.queryCustom("Aluno", "login", login, "senha", senha);
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
            PersistenceHelper.persist(student.getLogin());
            PersistenceHelper.persist(student);
        }

        // What is code "483" ???
        return ClientUtils.sendMessage(students.size() > 0 ? new BaseMessage(483, "User Already Exists. Creation not Allowed.") :
                new BaseMessage(200, "User Doesn't exists. Creation Allowed."));
    }
}
