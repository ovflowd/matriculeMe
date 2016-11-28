package com.unb.matriculeme.domain;

import com.mysema.commons.lang.Pair;
import com.unb.matriculeme.dao.Aluno;
import com.unb.matriculeme.dao.Departamento;
import com.unb.matriculeme.dao.Perfil;
import com.unb.matriculeme.helpers.ClientUtils;
import com.unb.matriculeme.helpers.Persistence;
import com.unb.matriculeme.messages.AllRightMessage;
import com.unb.matriculeme.messages.NotFoundMessage;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/perfil")
public class PerfilController {
    @Path("/setPerfil/matricula={matricula}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setPerfilByMatricula(@PathParam("matricula") int matricula, Perfil profile) throws Exception {
        List<Aluno> alunos = Persistence.select(Aluno.class, Persistence.createExpression(new Pair<>("matricula", matricula)), true);

        if (alunos.size() > 0) {
            profile.setAluno(alunos.get(0));
            profile.setDepartamento(((Departamento) Persistence.select(Departamento.class, Persistence.createExpression(new Pair<>("codigo", profile.getDepartamento().getCodigo())), true).get(0)));

            Persistence.insert(profile);
        }

        return ClientUtils.sendMessage(new AllRightMessage("The profile was inserted successfully in the system."));
    }

    @Path("/getPerfil/aluno={matricula}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPerfil(@PathParam("matricula") int matricula) throws IllegalAccessException {
        List<Aluno> alunos = Persistence.select(Aluno.class, Persistence.createExpression(new Pair<>("matricula", matricula)), true);
        return alunos.size() > 0 ? ClientUtils.sendResponse(Persistence.select(Perfil.class, Persistence.createExpression(new Pair<>("aluno", alunos.get(0).getId())), true).get(0)) :
                ClientUtils.sendMessage(new NotFoundMessage("This Profile wasn't found on our system."));
    }
}