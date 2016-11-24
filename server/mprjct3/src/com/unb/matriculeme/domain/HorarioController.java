package com.unb.matriculeme.domain;

import com.unb.matriculeme.dao.Horario;
import com.unb.matriculeme.helpers.ClientUtils;
import com.unb.matriculeme.helpers.PersistenceHelper;
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
        Horario horario1 = new Horario();

        horario1.setDia(horario.getDia());
        horario1.setHorarioFim(horario.getHorarioFim());
        horario1.setHorarioInicio(horario.getHorarioInicio());

        PersistenceHelper.insert(horario1);

        return ClientUtils.sendMessage(new AllRightMessage("The course was inserted on the system successfully."));
    }

    @Path("/getHorario/dia={dia}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHorarioByDia(@PathParam("dia") String dia) {
        List horario = PersistenceHelper.queryCustom(Horario.class, "dia", dia);

        return horario.size() > 0 ? ClientUtils.sendResponse(horario) : ClientUtils.sendMessage(new NotFoundMessage("The Horario was not found on the system."));
    }

    @Path("/getHorario/fim={horarioFim}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHorarioByEndDate(@PathParam("horarioFim") String horarioFim) {
        List horario = PersistenceHelper.queryCustom(Horario.class, "fim", horarioFim);

        return horario.size() > 0 ? ClientUtils.sendResponse(horario) : ClientUtils.sendMessage(new NotFoundMessage("The Horario was not found on the system."));
    }

    @Path("/getHorario/inicio={horarioInicio}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHorarioByStartDate(@PathParam("horarioInicio") String horarioInicio) {
        List horario = PersistenceHelper.queryCustom(Horario.class, "horarioInicio", horarioInicio);

        return horario.size() > 0 ? ClientUtils.sendResponse(horario) :
                ClientUtils.sendMessage(new NotFoundMessage("The Horario was not found on the system."));
    }

    @Path("/getHorario/dia={dia}&inicio={horarioInicio}&fim={horarioFim}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHorarioByStartDate(@PathParam("dia") String dia, @PathParam("horarioInicio") String horarioInicio, @PathParam("horarioFim") String horarioFim) {
        List horario = PersistenceHelper.queryCustom(Horario.class, "dia", dia, "horarioInicio", horarioInicio, "horarioFim", horarioFim);

        return horario.size() > 0 ? ClientUtils.sendResponse(horario) :
                ClientUtils.sendMessage(new NotFoundMessage("The Horario was not found on the system."));
    }

    @Path("/getHorario/inicio={horarioInicio}&fim={horarioFim}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHorarioByStartDate(@PathParam("horarioInicio") String horarioInicio, @PathParam("horarioFim") String horarioFim) {

        List horario = PersistenceHelper.queryCustom(Horario.class, "horarioInicio", horarioInicio, "horarioFim", horarioFim);

        return horario.size() > 0 ? ClientUtils.sendResponse(horario) :
                ClientUtils.sendMessage(new NotFoundMessage("The Horario was not found on the system."));
    }
}

