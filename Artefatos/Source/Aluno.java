import java.util.LinkedList;

public class Aluno
	{
		
		int curso;
		int[] preferencia;
		int semestre;
		DisciplinaDB[] historicoAprovado;
		String[] historico;
		int[] perfil;
		public int PerfilporDepartamento(int dDep){return perfil[dDep];}
		int login;
		public LinkedList<Disciplina> LoadDisciplinasACursar() {
			DisciplinaDB[] listaACursar = GetFromDB(this.curso);
			LinkedList<Disciplina> result = new LinkedList<Disciplina>();
			for(DisciplinaDB aCursar : listaACursar)
			{
				for(DisciplinaDB ha : this.historicoAprovado)
				{
					if(ha.codigo==aCursar.codigo)
					{result.add(new Disciplina(aCursar));}
				}
			}	
			return result;
		}
		
	}