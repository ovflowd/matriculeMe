package com.unb.matriculeme.domain;

import com.unb.matriculeme.dao.Curso;
import com.unb.matriculeme.helpers.ClientUtils;
import com.unb.matriculeme.helpers.PersistenceHelper;
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
        List cursos = PersistenceHelper.queryCustom(Curso.class, "nome", nome);

        if (cursos.size() > 0) {
            PersistenceHelper.update(cursos.get(0), curso);
        }

        return ClientUtils.sendMessage(cursos.size() > 0 ? new AllRightMessage("The course meta data was updated successfully.") : new NotFoundMessage("The course wasn't found on our system."));
    }

    @Path("/setCursos")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCourse(List<Curso> cursos) throws Exception {
        cursos.stream().filter(curso -> PersistenceHelper.queryCustom(Curso.class, "codigo", curso.getCodigo()).size() == 0).forEach(PersistenceHelper::insert);

        return ClientUtils.sendMessage(new AllRightMessage("Course registered successfully in the system."));
    }

    @Path("/getCurso/nome={nome}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCursoByNome(@PathParam("nome") String nome) {
        List cursos = PersistenceHelper.queryCustom(Curso.class, "nome", nome);

        return cursos.size() > 0 ? ClientUtils.sendResponse(cursos.get(0)) : ClientUtils.sendMessage(new NotFoundMessage("The course wasn't found in the system."));
    }

    @Path("/getCurso/nome={nome}&codigo={codigo}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCourseByNome(@PathParam("nome") String nome, @PathParam("codigo") int codigo) {
        List cursos = PersistenceHelper.queryCustom(Curso.class, "nome", nome, "codigo", codigo);

        return cursos.size() > 0 ? ClientUtils.sendResponse(cursos.get(0)) : ClientUtils.sendMessage(new NotFoundMessage("The course wasn't found on the system."));
    }
}
