package helpers;


import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientResponse;

public class ClientRest {
	
	public void enviarDados(String envio){
	try {
		Client client = ClientBuilder.newClient();
		 
		ClientConfig config = new ClientConfig();
        client = ClientBuilder.newClient(config); 
        WebTarget target = client.target("http://172.16.5.95:8081/DataMining/rest/datamining/historico");
        String payload = envio;   
        Response response = target.request("application/textplain").post(Entity.entity(payload,"application/textplain"), Response.class); // What to do here
        String jsonLine = response.readEntity(String.class);
        System.out.println(jsonLine);
        
		if (response.getStatus() != 200) { 
			throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
		}  
		
	  	} catch (Exception e) {
	  		e.printStackTrace();
	  		}
	}
}