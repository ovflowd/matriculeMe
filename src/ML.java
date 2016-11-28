
//import org.apache.spark.SparkContext //Necessario pra rodar Spark stuffz
//import org.apache.spark.SparkConf
import java.util.*;
import java.math.*;

//FK viram classes mudar isso

public class ML {

	public static int PesoSemestre(int sDepartamento, int sAluno) {
		return Math.max(sAluno - sDepartamento, 0);
	}

	public static int PesoTipo(int tipo) {
		if (tipo == 0) {
			return 0;
		}
		return (10 - tipo) * 10;
	}

	public Grades GetGrid(Grades grade) //// Funcao geradora da grade final
	{
		// se nao alcancou a folha da arvore
		if (grade.listaOrdenada.isEmpty()) {
			Grades selecionado = grade;
			Disciplina first = grade.listaOrdenada.removeFirst();
			// existe creditos disponiveis
			if (first.metrica < 30 - grade.totalCreditos) {
				Grades auxInclude = new Grades(grade.horario);
				Grades auxExclude = new Grades(grade.horario);
				Grades auxSelecionado = new Grades(grade.horario);
				int turmaId = 0;
				for (Turma oferta : first.ofertas.turmas) //// Paralelismo das
															//// arvores para
															//// cada turma da
															//// disciplina em
															//// quest�o
				{

					boolean valid = true;
					Grades auxIncludeInter = new Grades(grade.horario);
					for (int aloc = 0; aloc < oferta.horariosNonDB.length; aloc++) {
						for (int slot = (int) Math.pow(9, oferta.horariosNonDB[aloc].diaInt)
								+ oferta.horariosNonDB[aloc].horario_inicioInt; slot < (int) Math.pow(9,
										oferta.horariosNonDB[aloc].diaInt)
										+ oferta.horariosNonDB[aloc].horario_finalInt; slot++) {
							if (grade.horario[slot].equals(null)) {
								valid = false;
							}
						}
					}
					if (valid) // existe vaga, caso n�o exista pula ramo de
								// solu��o
					{
						for (int aloc = 0; aloc < oferta.horariosNonDB.length; aloc++)// se
																						// valido
																						// altera
																						// horario
						{
							for (int slot = (int) Math.pow(9, oferta.horariosNonDB[aloc].diaInt)
									+ oferta.horariosNonDB[aloc].horario_inicioInt; slot < (int) Math.pow(9,
											oferta.horariosNonDB[aloc].diaInt)
											+ oferta.horariosNonDB[aloc].horario_finalInt; slot++)

							{
								auxSelecionado.horario[slot] = String
										.valueOf(first.codigo + "-" + String.valueOf(oferta.codigo));
							}
						}
						// Ramo que inclui essa disciplina
						auxIncludeInter = GetGrid(new Grades(grade.listaOrdenada,
								grade.pertencentes.concat(
										String.valueOf(first.codigo) + "-" + String.valueOf(oferta.codigo) + "|"),
								auxSelecionado.horario, grade.metricaTotal + first.metrica,
								grade.totalCreditos + first.creditos));
						if (auxIncludeInter.metricaTotal > auxInclude.metricaTotal) // Pega
																					// o
																					// maior
																					// entre
																					// as
																					// poss�ves
																					// soluc�es
																					// incluindo
						{
							auxInclude = auxIncludeInter;
						}

					}

				}
				// ramo que n�o inclui esta disciplina
				if (grade.metricaTotal > 10 | first.metrica < 100) //// Poda
																	//// solu��es
																	//// das
																	//// arvores,
																	//// excluem
																	//// as
																	//// disciplinas
																	//// iniciais
																	//// ate o
																	//// n�vel
																	//// de 10
																	//// creditos
																	//// totais
																	//// ou que
																	//// tem um
																	//// peso
																	//// muito
																	//// grande
																	//// / que
																	//// n�o
																	//// incluem
																	//// disciplinas
																	//// mandatorias
				{
					auxExclude = GetGrid(new Grades(grade.listaOrdenada, grade.pertencentes, auxSelecionado.horario,
							grade.metricaTotal, grade.totalCreditos));
				}

				if (auxExclude.metricaTotal > auxInclude.metricaTotal) // Pega o
																		// maior
																		// entre
																		// as 2
																		// soluc�es
																		// (incluir
																		// ou
																		// nao)
				{
					auxSelecionado = auxExclude;
				} else {
					auxSelecionado = auxInclude;
				}
				if (auxSelecionado.metricaTotal > selecionado.metricaTotal) // Das
																			// multiplas
																			// intera��es
																			// retorna
																			// a
																			// melhor
				{
					selecionado = auxSelecionado;
				}
				return selecionado;
			}

			else /// Se a solu��o n�o pode incluir essa disciplinas pois estoura
					/// o n�mero de creditos
			{
				selecionado = GetGrid(new Grades(grade.listaOrdenada, grade.pertencentes, grade.horario,
						grade.metricaTotal, grade.totalCreditos));
			}
			return selecionado;
		} else /// No folha, lista vazia
		{
			return grade;
		}

	}

	public int MachineLearn(Aluno cliente) {
		// Aluno cliente = LoadAluno(AlunoId);
		int curso = cliente.curso;

		LinkedList<Disciplina> bloco = cliente.LoadDisciplinasACursar("/Curriculo/" + curso); // funcao
																								// de
																								// carga
																								// do
																								// conteudo
																								// do
																								// aluno
																								// e
																								// historico
																								// a
																								// definir

		cliente.perfil = GeraPerfil(cliente.historicoAprovado);
		LinkedList<Disciplina> disciplineList = new LinkedList<Disciplina>();
		// varre entradas, atribui pesos e filtra apenas elementos que podem ser
		// cursados para lista ponderada
		for (Disciplina disc : bloco) {

			disc.GeraMetrica(cliente);
			if (disc.metrica > 0) {
				disciplineList.addLast(disc);
			}
		}
		// ordena lista
		Collections.sort(disciplineList);
		// instancia inicio de operacoes
		Grades result = GetGrid(new Grades(disciplineList));
		result.horarios = String.join("/", result.horario);
		result.horarios = result.horarios.replaceAll("//", "/0/");
		SENDRESULT(result);
		// PROFIT
		return 0;
	}

	public int[] GeraPerfil(HistoricoDB[] histDisciplinas) {
		HashMap<String, String> perfil = new HashMap<String, String>();
		int[] auxPerfil = new int[76];
		int[] auxValue = new int[76];
		int aux = 0;
		for (HistoricoDB disciplina : histDisciplinas) {

			if (!perfil.containsKey(disciplina.departamento)) {
				// disciplina ainda nao mapeada
				perfil.put(String.valueOf(disciplina.departamento), String.valueOf(aux));
				aux++;
			}
			if (NotaEq(disciplina.mencao) > 0) {
				auxPerfil[Integer.valueOf(perfil.get(String.valueOf(disciplina.departamento)))] += NotaEq(
						disciplina.mencao);
				auxValue[disciplina.departamento]++;
			}
		}
		for (int i = 0; i < 76; i++) {
			auxPerfil[i] = auxPerfil[i] / Math.max(1, auxValue[i]);
		}
		return auxPerfil;
	}

	public int NotaEq(String n) {
		n.toLowerCase();
		switch (n) {
		case "ss":
			return 5;

		case "ms":
			return 4;
		case "mm":
			return 3;
		case "mi":
			return 2;
		case "ii":
			return 1;
		case "sr":
			return 0;
		}
		return -1;
	}

}