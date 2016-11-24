package com.unb.matriculeme.domain;

import com.mysema.commons.lang.Pair;
import com.unb.matriculeme.dao.Disciplina;
import com.unb.matriculeme.dao.Oferta;
import com.unb.matriculeme.dao.Semestre;
import com.unb.matriculeme.helpers.ClientUtils;
import com.unb.matriculeme.helpers.Persistence;
import com.unb.matriculeme.messages.AllRightMessage;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//@TODO: Add GET method (Which Parameter)
//@TODO: Recommendation: By Name of Discipline
@Path("/oferta")
public class OfertaController {
    @Path("/setOferta")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setOferta(Oferta oferta) {
        oferta.setDisciplina((Disciplina) (Persistence.select(Disciplina.class, Persistence.createExpression(new Pair<>("codigo", oferta.getDisciplina().getCodigo())), true).get(0)));
        oferta.setSemestre((Semestre) (Persistence.select(Semestre.class, Persistence.createExpression(new Pair<>("codigo", oferta.getSemestre().getCodigo())), true).get(0)));

        Persistence.insert(oferta);

        return ClientUtils.sendMessage(new AllRightMessage("The oferta was inserted successfully in the system."));
    }
}
