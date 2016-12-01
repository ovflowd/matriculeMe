package projeto.matriculeme.REST;

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
		if (!grade.listaOrdenada.isEmpty()) {
			Grades selecionado = grade;
			Curriculo curr = grade.listaOrdenada.removeFirst();
			Disciplina first = curr.getDisciplina();
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
						LinkedList<Curriculo> clone = grade.listaPertence;
						curr.getDisciplina().setTurmas(new ArrayList<Turma>()); //define apenas 1 turma para aquela disciplina
						clone.add(curr);
						// Ramo que inclui essa disciplina
						auxIncludeInter = GetGrid(new Grades(grade.listaOrdenada,
								grade.pertencentes.concat(
										String.valueOf(first.getCodigo()) + "-" + String.valueOf(oferta.getCodigo()) + "|"),
								auxSelecionado.horario, grade.metricaTotal + first.metrica,
								grade.totalCreditos + first.getCredito(),
								clone));
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
							grade.metricaTotal, grade.totalCreditos,grade.listaPertence));
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
						grade.metricaTotal, grade.totalCreditos,grade.listaPertence));
			}
			return selecionado;
		} else /// No folha, lista vazia
		{
			return grade;
		}

	}

	public String MachineLearn(Aluno aluno) {
		
		ClientRest cliente = new ClientRest();
		Gson transformar = new Gson();
		
		
		//TRY AND CATCH
		ArrayList<Curriculo> curriculoAluno = new ArrayList<Curriculo>();
		try
		{
		curriculoAluno = transformar.fromJson(cliente.receberDados("http://homol.redes.unb.br/ptr0220160-b/mprjct3/curriculo/getCurriculo/aluno="+String.valueOf(aluno.getCurso().getCodigo())), new TypeToken<ArrayList<Curriculo>>(){}.getType());
		}catch (Exception i)
		{
			System.out.print("ERRO\n");
		}
	    //try and catch  FIM
		
		
		ArrayList<Curriculo> arrayDiscACursar = new ArrayList<Curriculo>();
		try{
		for(int i = 0; i < curriculoAluno.size(); i++)
		{ 
			for(int j = 0; j < aluno.getDisciplinasCursadas().size(); j++){
				if(curriculoAluno.get(i).getDisciplina().getCodigo() == aluno.getDisciplinasCursadas().get(j).getOferta().getDisciplina().getCodigo() ){
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
					}else if( curriculoAluno.get(i).getDisciplina().getNome()=="" | curriculoAluno.get(i).getDisciplina().getNome()==null){
						arrayDiscACursar.remove(i);
						break;
					}	
				}
			}
		}		
		}catch (Exception i)
		{
			System.out.print("ERRO 2\n");
		}
		
		Perfil perf = new Perfil();
		try{
		perf.aluno = aluno;
		perf.GeraPerfil(aluno.getDisciplinasCursadas());
		
		
		for(double p : perf.metrica)
		{
			if(p>0)
			{
				perf.metricaString += String.valueOf(p);
		}
		}		
		}catch(Exception i)
		{
			System.out.print("ERRO 3\n");
		}
		
		LinkedList<Curriculo> disciplineList = new LinkedList<Curriculo>();
		// varre entradas, atribui pesos e filtra apenas elementos que podem ser
		// cursados para lista ponderada
		try{
		for (Curriculo disc : arrayDiscACursar) {
			
			disc.GeraMetrica(aluno,perf,curriculoAluno);
			if (disc.getDisciplina().metrica > 0) {
				disciplineList.addLast(disc);
			}
		}
		}catch(Exception i)
		{
			System.out.print("ERRO 4\n");
		}
		// ordena lista
		Collections.sort(disciplineList);
		// instancia inicio de operacoes
		Grades result = GetGrid(new Grades(disciplineList));
		ArrayList<Sugestao> finalForm = new ArrayList<Sugestao>();
		int prio = 0;
		try{
		for(Curriculo resultado: result.listaPertence)
		{
			Sugestao sugestion = new Sugestao();
			sugestion.setCreditos(resultado.getDisciplina().getCredito());
			sugestion.setPrioridade(prio);
			prio++;
			sugestion.setVagas(true);
			sugestion.setCurriculo(resultado);
			finalForm.add(sugestion);
		}
		}catch(Exception i)
		{
			System.out.print("ERRO 5");
		}
		perf.aluno.setSugestoes(finalForm);
		
		System.out.println(transformar.toJson(perf));
		cliente.enviarDados(transformar.toJson(perf), "http://homol.redes.unb.br/ptr022016-b/mprjct3/perfil/SetPerfil/matricula="+String.valueOf(aluno.getMatricula()));
		// PROFIT
		return transformar.toJson(perf);
	}

	

	

}
