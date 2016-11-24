package com.unb.matriculeme.domain;

import com.mysema.commons.lang.Pair;
import com.unb.matriculeme.dao.Curso;
import com.unb.matriculeme.helpers.ClientUtils;
import com.unb.matriculeme.helpers.Persistence;
import com.unb.matriculeme.messages.AllRightMessage;
import com.unb.matriculeme.messages.NotFoundMessage;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/cursos")
public class CursosController {
    @Path("/alterCurso/name={nome}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response alterCurso(@PathParam("nome") String nome, Curso curso) throws Exception {
        List<Curso> cursos = Persistence.select(Curso.class, Persistence.createExpression(new Pair<>("nome", nome)), true);

        if (cursos.size() > 0) {
            Persistence.update(cursos.get(0), curso);
        }

        return ClientUtils.sendMessage(cursos.size() > 0 ? new AllRightMessage("The course meta data was updated successfully.") : new NotFoundMessage("The course wasn't found on our system."));
    }

    @Path("/setCursos")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCourse(List<Curso> cursos) throws Exception {
        cursos.stream().filter(curso -> Persistence.select(Curso.class, Persistence.createExpression(new Pair<>("codigo", curso.getCodigo())), true).size() == 0).forEach(Persistence::insert);

        return ClientUtils.sendMessage(new AllRightMessage("Course registered successfully in the system."));
    }

    @Path("/getCurso/nome={nome}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCursoByNome(@PathParam("nome") String nome) {
        List<Curso> cursos = Persistence.select(Curso.class, Persistence.createExpression(new Pair<>("nome", nome)), true);

        return cursos.size() > 0 ? ClientUtils.sendResponse(cursos.get(0)) : ClientUtils.sendMessage(new NotFoundMessage("The course wasn't found in the system."));
    }

    @Path("/getCurso/nome={nome}&codigo={codigo}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCourseByNome(@PathParam("nome") String nome, @PathParam("codigo") int codigo) {
        List<Curso> cursos = Persistence.select(Curso.class, Persistence.createExpression(new Pair<>("nome", nome), new Pair<>("codigo", codigo)), true);

        return cursos.size() > 0 ? ClientUtils.sendResponse(cursos.get(0)) : ClientUtils.sendMessage(new NotFoundMessage("The course wasn't found on the system."));
    }
}
