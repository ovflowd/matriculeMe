package com.unb.matriculeme.domain;

import com.google.gson.Gson;
import com.unb.matriculeme.dao.Course;
import com.unb.matriculeme.dao.Curriculum;
import com.unb.matriculeme.helpers.ClientUtils;
import com.unb.matriculeme.helpers.PersistenceHelper;
import com.unb.matriculeme.dao.Discipline;
import com.unb.matriculeme.messages.AllRightMessage;
import com.unb.matriculeme.messages.BaseMessage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/curriculos")
public class CurriculumController  {

    @Path("/getCurriculo/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCurriculum() throws Exception {
        List curriculum = PersistenceHelper.queryGetList("curriculo");
        return ClientUtils.sendResponse(curriculum);
    }

    // Why not setAllCurriculos?
    @Path("/setAllCurr")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addAllResumes(List<Curriculum> resumes) throws Exception {
        //@TODO: If the Identifier it's not passed, isn't possible do the reference.
        //@TODO: Create a way to check if the Identifier is present. Obviously. And if not, result a BaseMessage()

        for (Curriculum curriculum : resumes) {
            Curriculum curr = new Curriculum();

            curr.setSemester(curriculum.getSemester());

            List cursos = PersistenceHelper.queryCustom("curso", "codigo", String.valueOf(curriculum.getCourse().getCode()), false);

            curr.setCourse((Course) cursos.get(0));

            List disciplinas = PersistenceHelper.queryCustom("disciplina", "codigo", String.valueOf(curriculum.getDiscipline().getCode()), false);

            curr.setDiscipline((Discipline) disciplinas.get(0));

            PersistenceHelper.persist(curr);
        }

        return ClientUtils.sendMessage(new AllRightMessage("The entire set of curriculum was added on the system."));
    }
}
