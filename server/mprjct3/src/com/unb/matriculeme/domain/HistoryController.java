package com.unb.matriculeme.domain;

import com.unb.matriculeme.helpers.ClientUtils;
import com.unb.matriculeme.manager.DataMining;
import com.unb.matriculeme.messages.AllRightMessage;
import com.unb.matriculeme.messages.BaseMessage;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/historico")
public class HistoryController {

    // Change to "/setHistorico"
    @Path("/setHist/")
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public Response setHistory(String history) throws Exception {
        return ClientUtils.sendMessage(DataMining.RequestHistory(history) ? new AllRightMessage("History Inserted successfully.") :
                new BaseMessage(500, "The History wasn't recovered successfully."));
    }
}
