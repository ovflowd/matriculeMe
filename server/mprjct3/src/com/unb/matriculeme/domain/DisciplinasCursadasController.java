package com.unb.matriculeme.domain;

import com.unb.matriculeme.dao.DisciplinasCursadas;
import com.unb.matriculeme.dao.Mencao;
import com.unb.matriculeme.helpers.ClientUtils;
import com.unb.matriculeme.helpers.PersistenceHelper;
import com.unb.matriculeme.helpers.RestClient;
import com.unb.matriculeme.messages.AllRightMessage;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/disciplinasCursadas/")
public class DisciplinasCursadasController {
    @Path("setHist")
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public Response setHistory(String string) {
        RestClient rc = new RestClient();
        rc.sendData(string);

        return ClientUtils.sendMessage(new AllRightMessage("The entire history data was inserted successfully."));
    }

    //@TODO: Why a function that you're not using.
    @Path("/getDisciplinasCursadas/matricula={matricula}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDisciplinasCursadas(@PathParam("matricula") int Matricula) {
        //		List<Aluno> aluno = Queries.queryCustom("Aluno", "matricula", String.valueOf(Matricula), false);
        //		aluno.get(0).getDisciplinasCursadas();
        //		Essa esta em duvidas para matheus
        return Response.status(200).build();
    }

    @Path("/setDisciplinasCursadas/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setCoursedDisciplines(List<DisciplinasCursadas> disciplinasCursadas) {
        for (DisciplinasCursadas disciplinasCursada : disciplinasCursadas) {
            //@TODO: Why use a List that isn't being used.
            //List mencao =
            PersistenceHelper.queryCustom(Mencao.class, "codigo", disciplinasCursadas.get(0).getMencao().getCodigo());
            PersistenceHelper.insert(disciplinasCursada);
        }

        return ClientUtils.sendMessage(new AllRightMessage("The coursed disciplines was set."));
    }
}
