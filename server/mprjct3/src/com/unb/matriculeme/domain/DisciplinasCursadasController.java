package com.unb.matriculeme.domain;

import com.unb.matriculeme.dao.DisciplinasCursadas;
import com.unb.matriculeme.helpers.PersistenceHelper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/disciplinasCursadas/")
public class DisciplinasCursadasController {

    @Path("setHist")
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public Response setHist(String string) {
        Trigger thread = new Trigger(string, "http://homol.redes.unb.br/ptr022016-b/mining/rest/datamining/historico", false);

        thread.start();

        return ClientUtils.sendMessage(new AllRightMessage("History set succesffuly.."));
    }

    @Path("/getDisciplinasCursadas/matricula={mat}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDisciplinasCursadas(@PathParam("mat") int Matricula) {
        //		List<Aluno> aluno = Queries.queryCustom("Aluno", "matricula", String.valueOf(Matricula), false);
        //		aluno.get(0).getDisciplinasCursadas();
        //		Essa esta em duvwidas para matheus
        return ClientUtils.sendMessage(new AllRightMessage("Wat.."));
    }

    @Path("/setDisciplinasCursadas/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response example(List<DisciplinasCursadas> disciplinasCursadas) {
        //List aluno = Queries.queryCustom("Aluno", "matricula", String.valueOf(disciplinasCursadas.get(0).getAluno().getMatricula()), false);

        for (int i = 0; i < disciplinasCursadas.size(); i++) {
            List mencao = PersistenceHelper.queryCustom("Mencao", "codigo", disciplinasCursadas.get(0).getMencao().getCodigo());
            PersistenceHelper.Persist(disciplinasCursadas.get(i));
        }

        return ClientUtils.sendMessage(new AllRightMessage("Coursed disciplines set.."));
    }
}
