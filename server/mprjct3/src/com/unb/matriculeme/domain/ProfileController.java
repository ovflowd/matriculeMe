package com.unb.matriculeme.domain;
//TODO: esse ta liso, refazer o com.unb.matriculeme.domain. tb tirar as alteracoes que deram problema no fk  perfil->departamento
//@TODO Concordo, colocou query direto aqui.


import com.unb.matriculeme.helpers.*;
import com.unb.matriculeme.messages.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;

import com.unb.matriculeme.dao.Professor;
import com.unb.matriculeme.dao.Perfil;
import com.unb.matriculeme.helpers.PersistenceHelper;

@Path("/perfil")
public class ProfileController {


    @Path("/setPerfil")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response sayPlainTextHello(Perfil profile) throws Exception {
        Perfil profile1 = new Perfil();
        profile1.setDepartamentos(profile.getDepartamentos());
        profile1.setMetrica(profile.getMetrica());
        PersistenceHelper.persist(profile1);
        return Response.status(200).build();
    }

    @Path("/getPerfilInfo/Aluno={aluno_id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response alterPerfil(@PathParam("aluno_id") int alunoId, Perfil perfil) throws IllegalAccessException {

        List perfis = PersistenceHelper.queryCustom("Profile", "aluno_id", String.valueOf(alunoId), false);

        //DataMining  firstThread = new DataMining();
        if (perfis.size() > 0) {
            //PersistenceHelper.update(perfis.get(0), perfil);
        }
        return ClientUtils.sendMessage(perfis.size() > 0 ? new BaseMessage(200, "Profile changed successfully.") :
        new NotFoundMessage("This profile/student wasn't found."));
    }
}
