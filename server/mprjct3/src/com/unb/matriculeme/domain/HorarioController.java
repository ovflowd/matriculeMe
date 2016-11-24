package com.unb.matriculeme.domain;

import com.mysema.commons.lang.Pair;
import com.unb.matriculeme.dao.Horario;
import com.unb.matriculeme.helpers.ClientUtils;
import com.unb.matriculeme.helpers.Persistence;
import com.unb.matriculeme.messages.AllRightMessage;
import com.unb.matriculeme.messages.NotFoundMessage;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/horario")
public class HorarioController {
    @Path("/setHorario")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setHorario(Horario horario) {
        Persistence.insert(horario);

        return ClientUtils.sendMessage(new AllRightMessage("The course was inserted on the system successfully."));
    }

    @Path("/getHorario/dia={dia}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHorarioByDia(@PathParam("dia") String dia) {
        List<Horario> horario = Persistence.select(Horario.class, Persistence.createExpression(new Pair<>("dia", dia)), true);

        return horario.size() > 0 ? ClientUtils.sendResponse(horario) : ClientUtils.sendMessage(new NotFoundMessage("The Horario was not found on the system."));
    }

    @Path("/getHorario/fim={horarioFim}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHorarioByEndDate(@PathParam("horarioFim") String horarioFim) {
        List<Horario> horario = Persistence.select(Horario.class, Persistence.createExpression(new Pair<>("fim", horarioFim)), true);

        return horario.size() > 0 ? ClientUtils.sendResponse(horario) : ClientUtils.sendMessage(new NotFoundMessage("The Horario was not found on the system."));
    }

    @Path("/getHorario/inicio={horarioInicio}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHorarioByStartDate(@PathParam("horarioInicio") String horarioInicio) {
        List<Horario> horario = Persistence.select(Horario.class, Persistence.createExpression(new Pair<>("horarioInicio", horarioInicio)), true);

        return horario.size() > 0 ? ClientUtils.sendResponse(horario) : ClientUtils.sendMessage(new NotFoundMessage("The Horario was not found on the system."));
    }

    @Path("/getHorario/dia={dia}&inicio={horarioInicio}&fim={horarioFim}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHorarioByAllCriterias(@PathParam("dia") String dia, @PathParam("horarioInicio") String horarioInicio, @PathParam("horarioFim") String horarioFim) {
        List<Horario> horario = Persistence.select(Horario.class, Persistence.createExpression(new Pair<>("dia", dia), new Pair<>("horarioInicio", horarioInicio), new Pair<>("horarioFim", horarioFim)), true);

        return horario.size() > 0 ? ClientUtils.sendResponse(horario) : ClientUtils.sendMessage(new NotFoundMessage("The Horario was not found on the system."));
    }

    @Path("/getHorario/inicio={horarioInicio}&fim={horarioFim}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHorarioByStartAndEndDate(@PathParam("horarioInicio") String horarioInicio, @PathParam("horarioFim") String horarioFim) {
        List<Horario> horario = Persistence.select(Horario.class, Persistence.createExpression(new Pair<>("horarioInicio", horarioInicio), new Pair<>("horarioFim", horarioFim)), true);

        return horario.size() > 0 ? ClientUtils.sendResponse(horario) : ClientUtils.sendMessage(new NotFoundMessage("The Horario was not found on the system."));
    }
}

