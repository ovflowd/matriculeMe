package com.unb.matriculeme.domain;

import com.unb.matriculeme.helpers.ClientUtils;
import com.unb.matriculeme.helpers.PersistenceHelper;
import com.unb.matriculeme.messages.NotFoundMessage;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/rest")
public class RestController {
    @Path("/{table}/{queried}/{toQuery}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response restCommon(@PathParam("table") String table, @PathParam("queried") String queried,
                               @PathParam("toQuery") String toQuery) {
        boolean parsable = true;
        try {
            Integer.parseInt(toQuery);
        } catch (NumberFormatException e) {
            parsable = false;
        }
        List response = new ArrayList<>();
        if (parsable) {
            response = PersistenceHelper.queryCustom(table, queried, Integer.valueOf(toQuery));
        } else { //false, meaning is string
            response = PersistenceHelper.queryCustom(table, queried, toQuery);
        }
        return response.size() > 0 ? ClientUtils.sendResponse(response) :
                ClientUtils.sendMessage(new NotFoundMessage("Nothing found with those parameters"));
    }
}
