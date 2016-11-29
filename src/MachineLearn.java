
import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.io.IOException;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

package projeto.matriculeme.REST;
@Path("/MachineLearn")
public class MachineLearn {
	
	@POST
	@Path("/loadAluno")
	@Consumes("application/textplain")
	public String postAluno(String htmlHist){
		ML criador = new ML();
		Gson json = new Gson();
		criador.MachineLearn(json.fromJson(htmlHist, Aluno));
		
		ClientRest cliente = new ClientRest();
		cliente.enviarDados(jsonEnviar);
		System.out.println(jsonEnviar);
		return jsonEnviar;
	}
	public void main()
	{
		receberDados("http://homol.redes.unb.br/ptr022016-b/mprjct3/alunos/getAluno/nome='batata'");
	}
}
