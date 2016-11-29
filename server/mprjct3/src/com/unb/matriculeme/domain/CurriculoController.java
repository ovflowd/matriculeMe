package com.unb.matriculeme.domain;

import com.mysema.commons.lang.Pair;
import com.unb.matriculeme.dao.Curriculo;
import com.unb.matriculeme.dao.Curso;
import com.unb.matriculeme.dao.Disciplina;
import com.unb.matriculeme.helpers.ClientUtils;
import com.unb.matriculeme.helpers.Persistence;
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
        List<Curriculo> curriculos = Persistence.select(Curriculo.class);

        return curriculos.size() > 0 ? ClientUtils.sendResponse(curriculos) : ClientUtils.sendMessage(new NotFoundMessage("No one resume was found in our system."));
    }

    @Path("/setAllCurr")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addManyResumes(List<Curriculo> curriculos) throws Exception {
        for (Curriculo curriculo : curriculos) {
            curriculo.setSemestreDisciplina(curriculo.getSemestreDisciplina());

            List<Curso> cursos = Persistence.select(Curso.class, Persistence.createExpression(new Pair<>("codigo", curriculo.getCurso().getCodigo())), true);

            curriculo.setCurso(cursos.get(0));

            List<Disciplina> disciplinas = Persistence.select(Disciplina.class, Persistence.createExpression(new Pair<>("codigo", curriculo.getDisciplina().getCodigo())), true);

            if (disciplinas.size() > 0 )
            {
                curriculo.setDisciplina(disciplinas.get(0));
            }
            else
            { 
                List<Departamento> deps = Persistence.select(Departamento.class, Persistence.createExpression(new Pair<>("sigla", curriculo.getDisciplina().getDepartamento().getSigla())), true);
                curriculo.getDisciplina().setDepartamento(deps.get(0));
                Persistence.insert(curriculo.getDisciplina());
                curr.setDisciplina(Persistence.select(Disciplina.class, Persistence.createExpression(new Pair<>("codigo", curriculo.getDisciplina().getCodigo())), false).get(0)));
            }

            Persistence.insert(curriculo);
        }

        return ClientUtils.sendMessage(new AllRightMessage("All resumes were added correctly in the system."));
    }

    @Path("/getCurriculos/nomeCurso={nome}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCurriculoByNome(@PathParam("nome") String nome) throws Exception {
        List<Curso> cursos = Persistence.select(Curso.class, Persistence.createExpression(new Pair<>("nome", nome)), true);
        List<Curriculo> curriculo = Persistence.select(Curriculo.class, Persistence.createExpression(new Pair<>("curso", (cursos.get(0)).getId())), true);

        return curriculo.size() > 0 ? ClientUtils.sendResponse(curriculo.get(0)) :
                ClientUtils.sendMessage(new NotFoundMessage("This curriculum wasn't found on our system."));
    }

    @Path("/getCurriculos/codigoCurso={codigo}")
    @GET
    @Produces(MediaType.APPLICATION_JSON) 
    public Response getCurriculosByCodigo(@PathParam("codigo") int codigo) throws Exception {
        List<Curso> cursos = Persistence.select(Curso.class, Persistence.createExpression(new Pair<>("codigo", codigo)), false);
        List<Curriculo> curriculo = Persistence.select(Curriculo.class, Persistence.createExpression(new Pair<>("curso", (cursos.get(0)).getId())), true);

         return curriculo.size() > 0 ? ClientUtils.sendResponse(curriculo) : 
                ClientUtils.sendMessage(new NotFoundMessage("This User wasn't found on our system."));
    }  
}
