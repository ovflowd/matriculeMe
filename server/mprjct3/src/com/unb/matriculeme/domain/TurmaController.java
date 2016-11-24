package com.unb.matriculeme.domain;

import com.unb.matriculeme.dao.Disciplina;
import com.unb.matriculeme.dao.Semestre;
import com.unb.matriculeme.dao.Turma;
import com.unb.matriculeme.helpers.ClientUtils;
import com.unb.matriculeme.helpers.Persistence;
import com.unb.matriculeme.messages.AllRightMessage;
import com.unb.matriculeme.messages.NotFoundMessage;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/turmas/")
public class TurmaController {
    @Path("/getTurmas/codigo={codigo}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTurmaByCodigo(@PathParam("codigo") String codigo) throws Exception {
        List turmas = Persistence.queryCustom(Turma.class, "codigo", codigo);

        return turmas.size() > 0 ? ClientUtils.sendResponse(turmas) : ClientUtils.sendMessage(new NotFoundMessage("This User wasn't found on our system."));
    }

    @Path("/setTurmas/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setTurma(List<Turma> allTurmas) throws Exception {
        for (Turma allTurma : allTurmas) {
            Persistence.insert(allTurma.getProfessor());
            allTurma.getOferta().setDisciplina((Disciplina) (Persistence.queryCustom(Disciplina.class, "codigo", allTurma.getOferta().getDisciplina().getCodigo()).get(0)));
            allTurma.getOferta().setSemestre((Semestre) (Persistence.queryCustom(Semestre.class, "codigo", allTurma.getOferta().getSemestre().getCodigo()).get(0)));
            Persistence.insert(allTurma.getOferta());

            for (int j = 0; j < allTurma.getHorario().size(); j++) {
                Persistence.insert(allTurma.getHorario().get(j));
            }

            Persistence.insert(allTurma);
        }

        return ClientUtils.sendMessage(new AllRightMessage("The turma was added successfully on the system."));
    }
}
