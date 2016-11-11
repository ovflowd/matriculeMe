package com.datamining.rest.api;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class ClientRest {
	public void enviarDados(String envio){
	try {
		Client client = Client.create();

		WebResource webResource = client.resource("http://172.16.5.81:8080/mprjct3/departamentos/setAllDeps");

		ClientResponse response = webResource.type("application/json").post(ClientResponse.class, envio);

		if (response.getStatus() != 201) {
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
