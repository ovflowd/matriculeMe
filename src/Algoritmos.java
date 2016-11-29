import java.util.ArrayList;
import com.google.gson.Gson;

public class Algoritmos {
	
	ArrayList<Curriculo> arrayDiscACursar = new ArrayList<Curriculo>();
	
	public void gerarSugestões(Aluno aluno){
		ClientRest cliente = new ClientRest();
		Gson transformar = new Gson();
		ArrayList<Curriculo> curriculoAluno = transformar.fromJson(cliente.receberDados(aluno.getCurso().getCodigo()));
		arrayDiscACursar = curriculoAluno;
		for(int i = 0; i < curriculoAluno.size(); i++){
			for(int j = 0; j < aluno.getDisciplinasCursadas().size(); j++){
				if(curriculoAluno.get(i).getDisciplina().getCodigo() == aluno.getDisciplinasCursadas().get(j).getOferta().getDisciplina().getCodigo()){
					if(aluno.getDisciplinasCursadas().get(j).getMencao().getCodigo().equals("MM")){
						arrayDiscACursar.remove(i);
						break;
					}else if(aluno.getDisciplinasCursadas().get(j).getMencao().getCodigo().equals("MS")){
						arrayDiscACursar.remove(i);
						break;
					}else if(aluno.getDisciplinasCursadas().get(j).getMencao().getCodigo().equals("SS")){
						arrayDiscACursar.remove(i);
						break;
					}					
				}
			}
		}		
	}
}
