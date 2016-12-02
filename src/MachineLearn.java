package projeto.matriculeme.REST;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.google.gson.Gson;


@Path("/MachineLearn")
public class MachineLearn {
	
	@POST
	@Path("/loadAluno")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public String postAluno(String json){
		Gson gson = new Gson();
		Aluno aluno = new Aluno();
		aluno = gson.fromJson(json,Aluno.class);
		ML criador = new ML();
		return criador.MachineLearn(aluno);
	
	}
	
}