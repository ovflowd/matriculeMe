package com.unb.matriculeme.domain;

import com.unb.matriculeme.dao.Requisito;
import com.unb.matriculeme.helpers.ClientUtils;
import com.unb.matriculeme.helpers.PersistenceHelper;
import com.unb.matriculeme.messages.AllRightMessage;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//Comentario da classe: O que diabos eh essa classe? sem fk nenhuma e solta por ai?
//R: So tem uma string, o David e a Alessandra vao tratar
//TODO: Get da classe.

@Path("/requisito")
public class RequisitoController {
    @Path("/setRequisito")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setRequisito(Requisito requisito) {
        PersistenceHelper.Persist(requisito);
        return ClientUtils.sendMessage(new AllRightMessage("Requisito set succesffully."));
    }

//	@Path("/getRequisito/")
}
