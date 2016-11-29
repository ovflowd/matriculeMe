package projeto.matriculeme.REST;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import com.google.gson.Gson;


@Path("/MachineLearn")
public class MachineLearn {
	
	@POST
	@Path("/loadAluno")
	@Consumes(MediaType.TEXT_PLAIN)
	public void postAluno(String json){
		Gson gson = new Gson();
		Aluno aluno = new Aluno();
		aluno = gson.fromJson(json,Aluno.class);
		ML criador = new ML();
		criador.MachineLearn(aluno);
	}
	public void main()
	{
		ClientRest criador = new ClientRest();
		criador.receberDados("http://homol.redes.unb.br/ptr022016-b/mprjct3/alunos/getAluno/nome='batata'");
	}
}