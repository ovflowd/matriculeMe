package com.unb.matriculeme.domain;

import com.unb.matriculeme.dao.Mencao;
import com.unb.matriculeme.helpers.ClientUtils;
import com.unb.matriculeme.helpers.Persistence;
import com.unb.matriculeme.messages.AllRightMessage;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

//@TODO: Implement all stub methods
@Path("/mencao")
public class MencaoController {
    @Path("/setMencoes")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setMencoes(List<Mencao> mencoes) {
        mencoes.forEach(Persistence::insert);

        return ClientUtils.sendMessage(new AllRightMessage("The mentions was added successfully in the system."));
    }
}
