//import org.apache.spark.SparkContext //Necessario pra rodar Spark stuffz
//import org.apache.spark.SparkConf
import java.util.*;
import java.math.*;
import java.lang.*;
import com.google.code.gson;

public class ML {
	
	
	
	
	public Disciplina[] LoadDisciplinas()
	{
		return new Disciplina[0];
	}
	public Aluno LoadAluno()
	{
		
		return new Aluno();
	}
	
	public int[] GeraPerfil(LinkedList<Disciplina> bloco)
	{
		HashMap<String,String> perfil = new HashMap<String,String>();
		int[] auxPerfil = new int[76];
		int[] auxValue = new int[76];
		int aux = 0;
		for(Disciplina disciplina : bloco)
		{
			if(!perfil.containsKey(disciplina.departamento))
			{
			//disciplina ainda nao mapeada
				perfil.put(String.valueOf(disciplina.departamento),String.valueOf(aux));
				aux++;	
			}
			auxPerfil[Integer.valueOf(perfil.get(String.valueOf(disciplina.departamento)))] += disciplina.nota;
			auxValue[disciplina.departamento] ++;
		}
		for(int i =0; i<76;i++) {auxPerfil[i] = auxPerfil[i]/Math.max(1,auxValue[i]);}
		return auxPerfil;
	}
	
	public Grades GetGrid(Grades grade) //// Funcao geradora da grade final         
	{
		 // se nao alcancou a folha da arvore
	if(grade.listaOrdenada.isEmpty())  
		{
			Grades selecionado = grade;
			Disciplina first = grade.listaOrdenada.removeFirst();
			//existe creditos disponiveis
			if(first.metrica < 30 - grade.totalCreditos)
			{
				Grades auxInclude = new Grades(grade.horario);
				Grades auxExclude = new Grades(grade.horario);
				Grades auxSelecionado = new Grades(grade.horario);
				int turmaId = 0;
				for(Turma oferta : first.ofertas.turmas) ////Paralelismo das arvores para cada turma da disciplina em questão
					{  
						
						boolean valid = true;
						Grades auxIncludeInter = new Grades(grade.horario);
						for(int aloc = 0 ; aloc < oferta.horarios.length ; aloc++)
						{
						for(int slot = (int) Math.pow(9, oferta.horarios[aloc].diaInt) + oferta.horarios[aloc].horario_inicioInt; slot < (int) Math.pow(9, oferta.horarios[aloc].diaInt)+oferta.horarios[aloc].horario_finalInt; slot++) 
							{
								if(grade.horario[slot].equals(null))
								{ valid=false;}
							}
						}	
						if(valid) //existe vaga, caso não exista pula ramo de solução
							{
							for(int aloc = 0 ; aloc < oferta.horarios.length ; aloc++)//se valido altera horario
							{
								for(int slot = (int) Math.pow(9, oferta.horarios[aloc].diaInt) + oferta.horarios[aloc].horario_inicioInt; slot < (int) Math.pow(9, oferta.horarios[aloc].diaInt)+oferta.horarios[aloc].horario_finalInt; slot++) 
								{
								auxSelecionado.horario[slot]=String.valueOf(first.codigo+"-"+String.valueOf(oferta.codigo));
								}
							}
							//Ramo que inclui essa disciplina
							auxIncludeInter = GetGrid(new Grades(grade.listaOrdenada,
								grade.pertencentes.concat(String.valueOf(first.codigo)+"-"+String.valueOf(oferta.codigo)+"|"),
								auxSelecionado.horario,
								grade.metricaTotal+first.metrica,
								grade.totalCreditos+first.creditos));
							if(auxIncludeInter.metricaTotal > auxInclude.metricaTotal) //Pega o maior entre as possíves solucões incluindo
								{auxInclude = auxIncludeInter;}
							
							} 
						
					
				      		}
				//ramo que não inclui esta disciplina
				if(grade.metricaTotal>10 | first.metrica<100) ////Poda soluções das arvores, excluem as disciplinas iniciais ate o nível de 10 creditos totais ou que tem um peso muito grande / que não incluem disciplinas mandatorias
					{
					auxExclude = GetGrid(new Grades(grade.listaOrdenada,
					grade.pertencentes,
					auxSelecionado.horario,
					grade.metricaTotal,
					grade.totalCreditos));
					}
					
					if(auxExclude.metricaTotal > auxInclude.metricaTotal) //Pega o maior entre as 2 solucões (incluir ou nao)
						{auxSelecionado = auxExclude;}
					else
						{auxSelecionado = auxInclude;}
				if(auxSelecionado.metricaTotal > selecionado.metricaTotal) //Das multiplas interações retorna a melhor
					{selecionado = auxSelecionado;}
				return selecionado;
				}

			else   ///Se a solução não pode incluir essa disciplinas pois estoura o número de creditos
				{
					selecionado = GetGrid(new Grades(grade.listaOrdenada,grade.pertencentes,grade.horario,grade.metricaTotal,grade.totalCreditos));
				}
				return selecionado;
		}
	else ///No folha, lista vazia
	{return grade;}
		
	}

	public Grades main(String[] args) {
		Aluno cliente = LoadAluno();
		int curso = cliente.curso;
		
		LinkedList<Disciplina> bloco = cliente.LoadDisciplinasACursar(); //funcao de carga do conteudo do aluno e historico a definir
		
		cliente.perfil = GeraPerfil(bloco);
		LinkedList<Disciplina> disciplineList = new LinkedList<Disciplina>();
		// varre entradas, atribui pesos e filtra apenas elementos que podem ser cursados para lista ponderada
		for(Disciplina disc : bloco)
			{ 
			
			disc.GeraMetrica(cliente);
			if(disc.metrica>0)
				{
				disciplineList.addLast(disc);
				}
			}
		//ordena lista
		Collections.sort(disciplineList);
		//instancia  inicio de operacoes
		return GetGrid(new Grades(disciplineList));
		
		//PROFIT
	}
	

}