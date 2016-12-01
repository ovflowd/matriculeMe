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
			Curriculo curr = grade.listaOrdenada.remove(0);
			Disciplina first = curr.getDisciplina();
			System.out.println("Disciplina em analise "+first.getNome());
			// existe creditos disponiveis
			if (first.metrica < 30 - grade.totalCreditos) {
				Grades auxInclude = new Grades(grade.SLOT);
				Grades auxExclude = new Grades(grade.SLOT);
				Grades auxSelecionado = new Grades(grade.SLOT);
				for (Turma oferta : first.getTurmas()) //// Paralelismo das
															//// arvores para
															//// cada turma da
															//// disciplina em
															//// quest�o
				{

					boolean valid = true;
					Grades auxIncludeInter = new Grades(grade.SLOT);
					for (int aloc = 0; aloc < oferta.getHorario().size(); aloc++)
					{
						for(Horario h : oferta.getHorario())
						{
							for( int i = Integer.parseInt(h.getHorarioInicio()); i <= Integer.parseInt(h.getHorarioFim());i++)
							{
								if(!((grade.SLOT[Integer.parseInt(h.getDia())][i])==0))
								{
									System.out.print("Ocupado");
									valid = false;
								}
							}
						}
						
					}
					if (valid) // existe vaga, caso n�o exista pula ramo de
								// solu��o
					{
						for(Horario h : oferta.getHorario())
						{
							for( int i = Integer.parseInt(h.getHorarioInicio()); i <= Integer.parseInt(h.getHorarioFim());i++)
							{
								auxInclude.SLOT[Integer.parseInt(h.getDia())][i] = first.getCodigo();// + "-" + String.valueOf(oferta.getCodigo()));
								System.out.println("Valido");
									
							}
						}
						
							// Ramo que inclui essa disciplina
						grade.listaPertence.add(curr);
						auxIncludeInter = GetGrid(new Grades(grade.listaOrdenada,
								grade.pertencentes.concat(
										String.valueOf(first.getCodigo()) + "-" + String.valueOf(oferta.getCodigo()) + "|"),
								 grade.metricaTotal + first.metrica,
								grade.totalCreditos + first.getCredito(),
								grade.listaPertence, auxInclude.SLOT
								));
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
				
					auxExclude = GetGrid(new Grades(grade.listaOrdenada, grade.pertencentes,
							grade.metricaTotal, grade.totalCreditos,grade.listaPertence, auxSelecionado.SLOT));
					if (grade.metricaTotal > 10 | first.metrica < 100) 
					{
						System.out.println("Branch avoidable");
						
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
				selecionado = GetGrid(new Grades(grade.listaOrdenada, grade.pertencentes,
						grade.metricaTotal, grade.totalCreditos,grade.listaPertence, grade.SLOT));
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
		curriculoAluno = transformar.fromJson(cliente.receberDados("http://homol.redes.unb.br/ptr022016-b/mprjct3/curriculos/getCurriculos/codigoCurso="+String.valueOf(aluno.getCurso().getCodigo())), new TypeToken<ArrayList<Curriculo>>(){}.getType());
		}catch (Exception i)
		{
			System.out.print("ERRO\n");
		}
	    //try and catch  FIM
		
		
		ArrayList<Curriculo> arrayDiscACursar = curriculoAluno;
		try{
		for(int i = curriculoAluno.size() -1 ; i > 0; i--)
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
		System.out.print(arrayDiscACursar.size()+" a cursar\n");
		Perfil perf = new Perfil();
		try{
		perf.aluno = aluno;
		perf.GeraPerfil(aluno.getDisciplinasCursadas());
		
		for(double p : perf.getMetrica())
		{
			if(p>0)
			{
				System.out.println(p + " metric!");
				perf.metricaString += String.valueOf(p);
			}
		}		
		}catch(Exception i)
		{
			System.out.print("ERRO 3\n");
		}
		
		List<Curriculo> disciplineList = new ArrayList<Curriculo>();
		// varre entradas, atribui pesos e filtra apenas elementos que podem ser
		// cursados para lista ponderada
		
		for (Curriculo disc : arrayDiscACursar) {
			
			disc.GeraMetrica(aluno,perf,curriculoAluno);
			System.out.print(disc.getDisciplina().metrica+" metrica | ");
			if (disc.getDisciplina().metrica > 0) 
			{
				disciplineList.add(disc);
			}
			}
		try{
		}catch(Exception i)
		{
			System.out.print("ERRO 4\n");
		}
		// ordena lista
		Collections.sort(disciplineList);
		System.out.println(disciplineList.get(0).getDisciplina().metrica+" first metrica");
		// instancia inicio de operacoes
		Grades result = GetGrid(new Grades(disciplineList));
		
		ArrayList<Sugestao> finalForm = new ArrayList<Sugestao>();
		int prio = 0;
		
		for(int[] s :result.SLOT)
		{
			result.horarios.concat("/"+String.join("/", s.toString()));
		}
		result.horarios.replaceFirst("/", "");
		result.horarios = result.horarios.replaceAll("/null/", "/0/");
		
		try{
			
		for(Curriculo resultado: disciplineList)// result.listaPertence)
		{
			Sugestao sugestion = new Sugestao();
			sugestion.setCreditos(resultado.getDisciplina().getCredito());
			sugestion.setPrioridade(prio);
			prio++;
			sugestion.setMotivo(result.horarios);
			sugestion.setVagas(true);
			sugestion.setCurriculo(resultado);
			finalForm.add(sugestion);
		}
		}catch(Exception i)
		{
			System.out.print("ERRO 5");
		}
		perf.aluno.sugestoes = finalForm;
		
	//	cliente.enviarDados(transformar.toJson(perf), "http://homol.redes.unb.br/ptr022016-b/mprjct3/perfil/SetPerfil/matricula="+String.valueOf(aluno.getMatricula()));
		// PROFIT
		
		return transformar.toJson(perf);
	}

	
	/// horarios >>> sugestao.motivo
	

	

}
