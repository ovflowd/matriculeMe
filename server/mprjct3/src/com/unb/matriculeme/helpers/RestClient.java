package com.unb.matriculeme.helpers;

import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class RestClient {

    public static void sendData(String data, String url, boolean isDavid) {
        try {
            //@TODO Remove this thing
            WebTarget target = ClientBuilder.newClient(new ClientConfig()).target(url);
            Response response = null;
            if (isDavid) {
                response = target.request(MediaType.TEXT_PLAIN).post(Entity.entity(data, MediaType.TEXT_PLAIN), Response.class); // What to do here
            } else {
                response = target.request("application/textplain").post(Entity.entity(data, "application/textplain"), Response.class); // What to do here
            }

            //@TODO This shouldn't exist.
            //String jsonLine = response.readEntity(String.class);
            //System.out.println(jsonLine);

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}