package com.datamining.rest.api;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class ClientRest {
	public void enviarDados(String envio, String url){
	try {
		Client client = Client.create();

		WebResource webResource = client.resource(url);

		ClientResponse response = webResource.type("application/json").post(ClientResponse.class, envio);

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
		}
		System.out.println("Output from Server .... \n");
		String output = response.getEntity(String.class);
		System.out.println(output);

	  	} catch (Exception e) {
	  		e.printStackTrace();
	  		}
	}
}
