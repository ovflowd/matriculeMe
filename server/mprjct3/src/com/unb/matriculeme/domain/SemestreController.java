package com.unb.matriculeme.domain;

import com.unb.matriculeme.dao.Semestre;
import com.unb.matriculeme.helpers.ClientUtils;
import com.unb.matriculeme.helpers.PersistenceHelper;
import com.unb.matriculeme.messages.AllRightMessage;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

//@TODO: Need implement the Get Method of this CLass
@Path("/semestre")
public class SemestreController {
    @Path("/setSemestre")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setSemestre(List<Semestre> semestre) {
        semestre.forEach(PersistenceHelper::insert);

        return ClientUtils.sendMessage(new AllRightMessage("The semester was added successfully on the system."));
    }
}
