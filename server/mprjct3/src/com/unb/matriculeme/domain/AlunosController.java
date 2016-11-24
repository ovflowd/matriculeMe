package com.unb.matriculeme.domain;

import com.google.gson.Gson;
import com.unb.matriculeme.dao.Aluno;
import com.unb.matriculeme.dao.Curso;
import com.unb.matriculeme.dao.Login;
import com.unb.matriculeme.helpers.ClientUtils;
import com.unb.matriculeme.helpers.PersistenceHelper;
import com.unb.matriculeme.messages.AllRightMessage;
import com.unb.matriculeme.messages.BaseMessage;
import com.unb.matriculeme.messages.NotFoundMessage;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/alunos")
public class AlunosController {
    @Path("/getAluno/nome={nome}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAlunoByNome(@PathParam("nome") String nome) {
        List students = PersistenceHelper.queryCustom(Aluno.class, "nome", nome);

        return students.size() > 0 ? ClientUtils.sendResponse(students.get(0)) : ClientUtils.sendMessage(new NotFoundMessage("This User wasn't found on our system."));
    }

    @Path("/updateAlunoCurso/matricula={matricula}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCurso(Curso curso, @PathParam("matricula") int matricula) {
        List alunos = PersistenceHelper.queryCustom(Aluno.class, "matricula", matricula),
                cursos = PersistenceHelper.queryCustom(Curso.class, "codigo", curso.getCodigo());

        Aluno aluno = (Aluno) alunos.get(0);
        aluno.setCurso((Curso) cursos.get(0));

        if (alunos.size() > 0 && cursos.size() > 0) {
            PersistenceHelper.update(alunos.get(0), aluno);
        }

        return ClientUtils.sendMessage(alunos.size() > 0 && cursos.size() > 0 ? new AllRightMessage("The meta data from the course was updated successfully.") :
                new NotFoundMessage("This User wasn't found on our system."));
    }

    @Path("/getAluno/login={login}&senha={senha}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAlunoByLogin(@PathParam("login") String accessKey, @PathParam("senha") String senha) {
        List students = PersistenceHelper.queryCustom(Login.class, "accessKey", accessKey, "password", senha), alunos = new ArrayList();

        if (students.size() > 0) {
            alunos = PersistenceHelper.queryCustom(Aluno.class, "id", ((Login) students.get(0)).getId());
        }

        return students.size() > 0 ? ClientUtils.sendResponse(alunos.get(0)) : ClientUtils.sendMessage(new NotFoundMessage("This User wasn't found on our system."));
    }

    @Path("/setAluno/")
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public Response setHorarios(String aluno) throws Exception {
        Aluno student = new Gson().fromJson(aluno, Aluno.class);
        List students = PersistenceHelper.queryCustom(Login.class, "accessKey", student.getLogin().getAccessKey());

        // User Doesn't Exists
        // Profile & Course set by default and can be changed latter.
        if (students.size() == 0) {
            student.setCurso((Curso) (PersistenceHelper.queryCustom(Curso.class, "codigo", 0).get(0)));
            PersistenceHelper.insert(student.getLogin());
            PersistenceHelper.insert(student);
        }

        // If User Exists Return 403. If not, Return 200.
        return ClientUtils.sendMessage(students.size() > 0 ? new BaseMessage(483, "User Already Exists. Creation not Allowed.") : new AllRightMessage("User created successfully in the system."));
    }
}
