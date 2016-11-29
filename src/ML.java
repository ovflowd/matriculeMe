import java.util.*;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

//FK viram classes mudar isso

public class ML {

	public static int PesoSemestre(int sDepartamento, int sAluno) {
		return Math.max(sAluno - sDepartamento, 0);
	}

	public static int PesoTipo(int tipo)
	{
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
			Disciplina first = grade.listaOrdenada.removeFirst().getDisciplina();
			// existe creditos disponiveis
			if (first.metrica < 30 - grade.totalCreditos) {
				Grades auxInclude = new Grades(grade.horario);
				Grades auxExclude = new Grades(grade.horario);
				Grades auxSelecionado = new Grades(grade.horario);
				for (Turma oferta : first.getTurmas()) //// Paralelismo das
															//// arvores para
															//// cada turma da
															//// disciplina em
															//// quest�o
				{

					boolean valid = true;
					Grades auxIncludeInter = new Grades(grade.horario);
					for (int aloc = 0; aloc < oferta.getHorario().size(); aloc++) {
						Horario[] hour = (Horario[]) oferta.getHorario().toArray();
						for (int slot = (int) Math.pow(9, Integer.parseInt(hour[aloc].getDia()))
								+Integer.parseInt(hour[aloc].getHorarioInicio()); slot < (int) Math.pow(9,
										Integer.parseInt(hour[aloc].getDia()))
										+ Integer.parseInt(hour[aloc].getHorarioFim()); slot++) {
							if (grade.horario[slot].equals(null)) {
								valid = false;
							}
						}
					}
					if (valid) // existe vaga, caso n�o exista pula ramo de
								// solu��o
					{
						for (int aloc = 0; aloc < oferta.getHorario().size(); aloc++) {
							Horario[] hour = (Horario[]) oferta.getHorario().toArray();
							for (int slot = (int) Math.pow(9, Integer.parseInt(hour[aloc].getDia()))
									+Integer.parseInt(hour[aloc].getHorarioInicio()); slot < (int) Math.pow(9,
											Integer.parseInt(hour[aloc].getDia()))
											+ Integer.parseInt(hour[aloc].getHorarioFim()); slot++) 
							{
								auxSelecionado.horario[slot] = String
										.valueOf(first.getCodigo() + "-" + String.valueOf(oferta.getCodigo()));
							}
						}
						// Ramo que inclui essa disciplina
						auxIncludeInter = GetGrid(new Grades(grade.listaOrdenada,
								grade.pertencentes.concat(
										String.valueOf(first.getCodigo()) + "-" + String.valueOf(oferta.getCodigo()) + "|"),
								auxSelecionado.horario, grade.metricaTotal + first.metrica,
								grade.totalCreditos + first.getCredito()));
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

	public int MachineLearn(Aluno aluno) {
		
		ClientRest cliente = new ClientRest();
		Gson transformar = new Gson();
		ArrayList<Curriculo> curriculoAluno = transformar.fromJson(cliente.receberDados("//Curriculo/GetCurriculo/CodigoCurso="+aluno.getCurso().getCodigo()), new TypeToken<ArrayList<Curriculo>>(){}.getType());
		
		ArrayList<Curriculo> arrayDiscACursar = curriculoAluno;
		for(int i = 0; i < curriculoAluno.size(); i++)
		{ 
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
					}else if(aluno.getDisciplinasCursadas().get(j).getMencao().getCodigo().equals("CC")){
						arrayDiscACursar.remove(i);
						break;
					}					
				}
			}
		}		
	
		
		
		aluno.GeraPerfil(aluno.getDisciplinasCursadas());
		LinkedList<Curriculo> disciplineList = new LinkedList<Curriculo>();
		// varre entradas, atribui pesos e filtra apenas elementos que podem ser
		// cursados para lista ponderada
		for (Curriculo disc : arrayDiscACursar) {
			
			disc.GeraMetrica(aluno,curriculoAluno);
			if (disc.getDisciplina().metrica > 0) {
				disciplineList.addLast(disc);
			}
		}
		// ordena lista
		Collections.sort(disciplineList);
		// instancia inicio de operacoes
		Grades result = GetGrid(new Grades(disciplineList));
		ArrayList<Sugestao> finalForm = new ArrayList<Sugestao>();
		int prio = 0;
		for(Curriculo resultado: result.listaOrdenada)
		{
			Sugestao sugestion = new Sugestao();
			sugestion.setCreditos(resultado.getDisciplina().getCredito());
			sugestion.setPrioridade(prio);
			prio++;
			sugestion.setVagas(true);
			sugestion.setCurriculo(resultado);
			finalForm.add(sugestion);
		}
		
		
		cliente.enviarDados(transformar.toJson(finalForm), "//Aluno/sugestao?");
		// PROFIT
		return 0;
	}

	

	

}
