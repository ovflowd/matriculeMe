package com.unb.matriculeme.domain;

import com.mysema.commons.lang.Pair;
import com.unb.matriculeme.dao.Disciplina;
import com.unb.matriculeme.dao.Oferta;
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
        List<Turma> turmas = Persistence.select(Turma.class, Persistence.createExpression(new Pair<>("codigo", codigo)), true);

        return turmas.size() > 0 ? ClientUtils.sendResponse(turmas) : ClientUtils.sendMessage(new NotFoundMessage("This User wasn't found on our system."));
    }

    @Path("/getTurmas/dia={dia}&horarioInicio={horarioInicio}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTurmas(@PathParam("dia") String dia, @PathParam("horarioInicio") String horarioInicio) throws Exception {
        List Turmas = Persistence.selectJoin(Turma.class, dia, horarioInicio);
        return Turmas.size() > 0 ? ClientUtils.sendResponse(Turmas) :
                ClientUtils.sendMessage(new NotFoundMessage("This User wasn't found on our system."));
    }

    @Path("/setTurmas/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setTurma(List<Turma> allTurmas) throws Exception {
        for (Turma allTurma : allTurmas) {
            Persistence.insert(allTurma.getProfessor());

            allTurma.getOferta().setDisciplina((Disciplina) (Persistence.select(Disciplina.class, Persistence.createExpression(new Pair<>("codigo", allTurma.getOferta().getDisciplina().getCodigo())), true).get(0)));
            allTurma.getOferta().setSemestre((Semestre) (Persistence.select(Semestre.class, Persistence.createExpression(new Pair<>("codigo", allTurma.getOferta().getSemestre().getCodigo())), true).get(0)));

            Persistence.insert(allTurma.getOferta());

            for (int j = 0; j < allTurma.getHorario().size(); j++) {
                Persistence.insert(allTurma.getHorario().get(j));
            }

            Persistence.insert(allTurma);
        }

        return ClientUtils.sendMessage(new AllRightMessage("The turma was added successfully on the system."));
    }

    @Path("/getTurmas/disciplina={nome}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTurmas(@PathParam("nome") String nome) {
        List<Disciplina> disciplinas = Persistence.select(Disciplina.class, Persistence.createExpression(new Pair<>("nome", nome)), true);

        if (disciplinas.size() > 0) {
            List<Oferta> ofertas = Persistence.select(Oferta.class, Persistence.createExpression(new Pair<>("disciplina", disciplinas.get(0).getId())), true);
            if (ofertas.size() > 0) {
                List turmas = Persistence.select(Turma.class, Persistence.createExpression(new Pair<>("oferta", ofertas.get(0).getId())), true);
                return turmas.size() > 0 ? ClientUtils.sendResponse(turmas) : ClientUtils.sendMessage(new NotFoundMessage("This User wasn't found on our system."));
            }

        }
        return ClientUtils.sendMessage(new NotFoundMessage("This disciplina or oferta wasn't found on our system. "));
    }

}
