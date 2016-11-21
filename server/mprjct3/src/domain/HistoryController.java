package domain;

import helpers.ClientUtils;
import manager.DataMining;
import messages.AllRightMessage;
import messages.BaseMessage;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/historico")
public class HistoryController {

    @Path("/setHistory/")
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public Response setHistory(String history) throws Exception {
        return ClientUtils.sendMessage(DataMining.RequestHistory(history) ? new AllRightMessage("History Inserted successfully.") :
                new BaseMessage(500, "The History wasn't recovered successfully."));
    }
}
