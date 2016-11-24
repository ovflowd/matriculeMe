package com.unb.matriculeme.domain;

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
import java.util.ArrayList;
import java.util.List;

@Path("/perfil")
public class PerfilController {
    @Path("/setPerfil/matricula={matricula}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setPerfilByMatricula(@PathParam("matricula") int matricula, Perfil profile) throws Exception {
        List alunos = Persistence.queryCustom(Aluno.class, "matricula", matricula);

        if (alunos.size() > 0) {
            profile.setAluno((Aluno) alunos.get(0));

            profile.setDepartamento(((Departamento) Persistence.queryCustom(Departamento.class, "codigo", profile.getDepartamento().getCodigo()).get(0)));

            Persistence.insert(profile);
        }

        return ClientUtils.sendMessage(new AllRightMessage("The profile was inserted successfully in the system."));
    }

    @Path("/getPerfil/aluno={matricula}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPerfil(@PathParam("matricula") int matricula) throws IllegalAccessException {
        List alunos = Persistence.queryCustom(Aluno.class, "matricula", matricula), perfis = new ArrayList();

        if (alunos.size() > 0) {
            perfis = Persistence.queryCustom(Perfil.class, "aluno", ((Aluno) alunos.get(0)).getId());
        }

        return perfis.size() > 0 ? ClientUtils.sendResponse(perfis.get(0)) : ClientUtils.sendMessage(new NotFoundMessage("This Profile wasn't found on our system."));
    }
}