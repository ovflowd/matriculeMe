package com.unb.matriculeme.domain;


import com.unb.matriculeme.dao.Curriculo;
import com.unb.matriculeme.dao.Curso;
import com.unb.matriculeme.dao.Departamento;
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
        List curriculos = PersistenceHelper.queryGetList("Curriculo");
        return ClientUtils.sendResponse(curriculos);
    }

    @Path("/setAllCurr")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setAllCoisas(List<Curriculo> curriculos) throws Exception {
        //Problema: se o cara nao passar id, nao eh possivel fazer a referencia, dai tem q instanciar
        for (Curriculo curriculo : curriculos) {
            Curriculo curr = new Curriculo();

            curr.setSemestreDisciplina(curriculo.getSemestreDisciplina());

            List cursos = PersistenceHelper.queryCustom("Curso", "codigo", curriculo.getCurso().getCodigo());

            if (cursos.size() > 0) {
                curr.setCurso((Curso) cursos.get(0));
            }

            List disciplinas = PersistenceHelper.queryCustom("Disciplina", "codigo", curriculo.getDisciplina().getCodigo());

            if (disciplinas.size() > 0) {
                curr.setDisciplina((Disciplina) disciplinas.get(0));
            } else {
                List departments = PersistenceHelper.queryCustom("Departamento", "sigla", curriculo.getDisciplina().getDepartamento().getSigla());
                if (departments.size() > 0) {
                    curriculo.getDisciplina().setDepartamento((Departamento) departments.get(0));
                } else {
                    PersistenceHelper.Persist(curriculo.getDisciplina().getDepartamento());
                    curriculo.getDisciplina().setDepartamento((Departamento) PersistenceHelper.queryCustom("Departamento", "sigla", curriculo.getDisciplina().getDepartamento().getSigla()).get(0));
                }
                PersistenceHelper.Persist(curriculo.getDisciplina());
                curr.setDisciplina((Disciplina) (PersistenceHelper.queryCustom("Disciplina", "codigo", curriculo.getDisciplina().getCodigo()).get(0)));
            }

            PersistenceHelper.Persist(curr);
        }

        return ClientUtils.sendMessage(new AllRightMessage());
    }

    @Path("/getCurriculos/nomeCurso={nome}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCurriculosByName(@PathParam("nome") String nome) throws Exception {
        List cursos = PersistenceHelper.queryCustom("Curso", "nome", nome);
        //List curriculo = PersistenceHelper.queryCustom("Curriculo", "curso", ((Curso) cursos.get(0)).getId());
        List curriculo = PersistenceHelper.queryCustom("Curriculo", "curso", ((Curso) cursos.get(0)).getNome());

        return curriculo.size() > 0 ? ClientUtils.sendResponse(curriculo) : ClientUtils.sendMessage(new NotFoundMessage("This User wasn't found on our system."));
    }

    @Path("/getCurriculos/codigoCurso={codigo}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCurriculosByCodigo(@PathParam("codigo") int codigo) throws Exception {
        List cursos = PersistenceHelper.queryCustom("Curso", "codigo", codigo);

        if (cursos.size() == 0)
            return ClientUtils.sendMessage(new NotFoundMessage("This Course wasn't found on our system."));

        List curriculo = PersistenceHelper.queryCustom("Curriculo", "curso", (Curso) cursos.get(0));

        return curriculo.size() > 0 ? ClientUtils.sendResponse(curriculo) : ClientUtils.sendMessage(new NotFoundMessage("Any curriculum for this Course wasn't found on our system."));
    }
}
