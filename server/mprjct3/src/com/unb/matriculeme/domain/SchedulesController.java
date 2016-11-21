package com.unb.matriculeme.domain;

//TODO: esse ta liso, refazer o com.unb.matriculeme.domain

import com.unb.matriculeme.dao.Schedule;
import com.unb.matriculeme.helpers.ClientUtils;
import com.unb.matriculeme.helpers.PersistenceHelper;
import com.unb.matriculeme.messages.AllRightMessage;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/horarios")
public class SchedulesController {

    @Path("/setHorario")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setHorario(Schedule schedule) {
        //Schedule h1 = new Schedule();
        //h1.setCode(horario.getCode());
        //h1.setClassEnds(schedule.getClassEnds());
        //h1.setClassStart(schedule.getClassStart());
        //SchoolClass t1 = em.find(SchoolClass.class, horario.getTurma().getId());
        //h1.setTurma(t1);

        PersistenceHelper.persist(schedule);
        return ClientUtils.sendMessage(new AllRightMessage("The schedule was inserted successfully."));
    }
}
