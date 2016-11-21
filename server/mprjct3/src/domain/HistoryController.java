package domain;

import manager.DataMining;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/historico")
public class HistoryController {

    @Path("/setHist/")
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public Response setHistory(String mandado) throws Exception {

        DataMining.RequestHistory(mandado);

        return Response.status(200)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS")
                .allow("OPTIONS")
                .build();
    }
}
