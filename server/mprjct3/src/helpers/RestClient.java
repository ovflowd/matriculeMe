package helpers;

import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class RestClient {

    public void sendData(String data) {
        try {
            //@TODO Remove this thing
            WebTarget target = ClientBuilder.newClient(new ClientConfig()).
                    target("http://172.16.5.95:8081/DataMining/rest/datamining/historico");

            Response response = target.request("application/textplain").post(Entity.entity(data, "application/textplain"), Response.class); // What to do here

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