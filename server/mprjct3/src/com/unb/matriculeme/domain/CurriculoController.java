package com.unb.matriculeme.domain;

import com.unb.matriculeme.dao.Curriculo;
import com.unb.matriculeme.dao.Curso;
import com.unb.matriculeme.dao.Disciplina;
import com.unb.matriculeme.helpers.ClientUtils;
import com.unb.matriculeme.helpers.PersistenceHelper;
import com.unb.matriculeme.messages.AllRightMessage;
import com.unb.matriculeme.messages.NotFoundMessage;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/curriculos")
public class CurriculoController {

    @Path("/getCurriculos/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCurriculos() throws Exception {
        List curriculos = PersistenceHelper.queryGetList(Curriculo.class);

        return curriculos.size() > 0 ? ClientUtils.sendResponse(curriculos) : ClientUtils.sendMessage(new NotFoundMessage("No one resume was found in our system."));
    }

    @Path("/setAllCurr")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addManyResumes(List<Curriculo> curriculos) throws Exception {
        //@TODO: Need pass the Identifier (Obligatorily)
        //@TODO: Need check if Identifiers are given.
        for (Curriculo curriculo : curriculos) {
            Curriculo curr = new Curriculo();
            curr.setSemestreDisciplina(curriculo.getSemestreDisciplina());

            List cursos = PersistenceHelper.queryCustom(Curso.class, "codigo", curriculo.getCurso().getCodigo());

            curr.setCurso((Curso) cursos.get(0));

            List disciplinas = PersistenceHelper.queryCustom(Disciplina.class, "codigo", curriculo.getDisciplina().getCodigo());

            curr.setDisciplina((Disciplina) disciplinas.get(0));

            PersistenceHelper.insert(curr);
        }

        return ClientUtils.sendMessage(new AllRightMessage("All resumes were added correctly in the system."));
    }

    @Path("/getCurriculos/nomeCurso={nome}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCurriculoByNome(@PathParam("nome") String nome) throws Exception {
        List cursos = PersistenceHelper.queryCustom(Curso.class, "nome", nome);
        List curriculo = PersistenceHelper.queryCustom(Curriculo.class, "curso", ((Curso) cursos.get(0)).getId());

        return curriculo.size() > 0 ? ClientUtils.sendResponse(curriculo.get(0)) :
                ClientUtils.sendMessage(new NotFoundMessage("This curriculum wasn't found on our system."));
    }
}
