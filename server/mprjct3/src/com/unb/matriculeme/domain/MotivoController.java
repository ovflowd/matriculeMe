package com.unb.matriculeme.domain;

import com.unb.matriculeme.dao.Motivo;
import com.unb.matriculeme.helpers.ClientUtils;
import com.unb.matriculeme.helpers.PersistenceHelper;
import com.unb.matriculeme.messages.AllRightMessage;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//@TODO: What the fuck this class does?
@Path("/motivo")
public class MotivoController {
    @Path("/setMotivo")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setMotivo(Motivo motivo) {
        PersistenceHelper.insert(motivo);

        return ClientUtils.sendMessage(new AllRightMessage("The 'motivo' was added successfully."));
    }
}
