package com.unb.matriculeme.domain;

import com.unb.matriculeme.dao.Requisito;
import com.unb.matriculeme.helpers.ClientUtils;
import com.unb.matriculeme.helpers.Persistence;
import com.unb.matriculeme.messages.AllRightMessage;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//@TODO: Need create the Get Method
//@TODO What the fuck this class does?
@Path("/requisito")
public class RequisitoController {
    @Path("/setRequisito")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setRequisito(Requisito requisito) {
        Persistence.insert(requisito);

        return ClientUtils.sendMessage(new AllRightMessage("Requisito was added successfully in the system."));
    }
}
