import java.util.LinkedList;

public class Aluno {
	int id;
	int curso;
	int[] preferencia;
	int semestre;
	transient HistoricoDB[] historicoAprovado;
	int historico;
	int[] perfil;

	public int PerfilporDepartamento(int dDep) {
		return perfil[dDep];
	}

	int login;

	public Aluno() {
		historicoAprovado = HistGetFromDB(this.id);
	}

	public LinkedList<Disciplina> LoadDisciplinasACursar() {
		DisciplinaDB[] listaACursar = GetFromDB(this.curso);
		LinkedList<Disciplina> result = new LinkedList<Disciplina>();
		for (DisciplinaDB aCursar : listaACursar) {

			for (HistoricoDB ha : this.historicoAprovado) {
				if (ha.Codigo == aCursar.codigo) {
					result.add(new Disciplina(aCursar));
					ha.departamento = aCursar.departamento;
				}
			}
		}
		return result;
	}

}