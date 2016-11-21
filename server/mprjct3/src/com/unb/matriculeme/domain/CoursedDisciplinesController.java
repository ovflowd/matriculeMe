package com.unb.matriculeme.domain;

import com.unb.matriculeme.dao.DisciplinasCursadas;
import com.unb.matriculeme.helpers.ClientUtils;
import com.unb.matriculeme.helpers.PersistenceHelper;
import com.unb.matriculeme.messages.AllRightMessage;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/disciplinasCursadas/")
public class CoursedDisciplinesController {

    @Path("/setDisciplinasCursadas/")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response example(List<DisciplinasCursadas> coursedDisciplines) {
        //coursedDisciplines.forEach(PersistenceHelper::persist);
        return ClientUtils.sendMessage(new AllRightMessage("The set of coursed disciplines was added successfully."));
    }
}
