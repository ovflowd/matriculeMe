package controller;

//TODO: esse ta liso, refazer o controller

import helpers.RestClient;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/historico")
public class HistoricoController {

    //@TODO Por que comentou o Código?
    //	@Path("/setHist/")
    //	@POST
    //	@Consumes(MediaType.TEXT_PLAIN)
    //	public Response setHorarios(String mandado) throws Exception
    //	{
    //	RestClient exClient = new RestClient();
    //	System.out.println(mandado);
    //	exClient.sendData(mandado);
    //	return Response.status(200)
    //			.header("Access-Control-Allow-Origin", "*")
    //			.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS")
    //			.allow("OPTIONS")
    //			.build();
    //	}

    @Path("/setHist/")
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public Response setHorarios(String mandado) throws Exception {

        RestClient exClient = new RestClient();

        // Para que Isso jovem?
        System.out.println(mandado);

        exClient.sendData(mandado);

        return Response.status(200)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS")
                .allow("OPTIONS")
                .build();
    }
}
