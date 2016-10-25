
//import org.apache.spark.SparkContext //Necessario pra rodar Spark stuffz
//import org.apache.spark.SparkConf
import java.util.*;
import java.math.*;
public class ML {
	
	public int PesoSemestre(int sDepartamento,int sAluno){return Math.max(sAluno - sDepartamento,0);}
	public int PesoTipo(int tipo){return tipo*10;}
	public class Turma
	{
		int[] day;
		int[] start;
		int[] end;
		
	}
	
	public Disciplina[] LoadDisciplinas()
	{
		return new Disciplina[0];
	}
	public Aluno LoadAluno()
	{
		
		return new Aluno();
	}
	
	static public int[] GeraPerfil(Disciplina[] listDisciplina)
	{
		int[] perfil = new int[99];
		int[] auxValue = new int[99];
		for(Disciplina disciplina : listDisciplina)
		{
			perfil[disciplina.departamento] += disciplina.nota;
			auxValue[disciplina.departamento] ++;
		}
		for(int i =0; i<99;i++) {perfil[i] = perfil[i]/Math.max(1,auxValue[i]);}
		return perfil;
	}
	public class Disciplina implements Comparable<Disciplina>
	{
		String[] Prerequisitos;
		int creditos;
		int codigo;
		int tipo; //Coorequisito
		int departamento;
		Turma[] turmas;
		int vagas;
		int metrica;
		int semestrefluxo;
		int nota;
		
		//Retorna lista decrescente
		
		@Override
		public int compareTo(Disciplina o) {
			
			if (this.metrica > o.metrica) {
				return -11;
			} else if (this.metrica == o.metrica) {
				return 0;
			} else {
				return 1;
			}
		}
		
		public int Preferencia(int[] prefList)
			{
			for(int code : prefList)	
			{	if(codigo == code)
					{return 200;}}
				
			return 0;
			}
		
		private void GeraMetrica(Aluno aluno) 
		{
			boolean valida = false;
			for(String C : this.Prerequisitos)	
			{
				//tratar entre ou de listaDePrerequisitos
			String[] requisitos = C.split("+");
			for(String req : requisitos)
				for(int ha: aluno.historicoAprovado)
				{
				if(String.valueOf(ha).equals(req))
					{
					valida = true;	
					}
				}
			if(valida & this.vagas>0) //ja tem pre requisitos e existem vagas
				{
				this.metrica = aluno.PerfilporDepartamento(this.departamento) + PesoSemestre(this.semestrefluxo,aluno.semestre) + PesoTipo(this.tipo)+ this.Preferencia(aluno.preferencia);
				}
			else
				this.metrica = 0;
			}	
		}


	}
	
	public class Aluno
	{
		int[] preferencia;
		int semestre;
		int[] historicoAprovado;
		String[] historico;
		int[] perfil;
		public int PerfilporDepartamento(int dDep){return perfil[dDep];}
		
		
		
	}
	public class Grades /////Classe Manipula as informações do algoritmo
	{
		LinkedList<Disciplina> listaOrdenada = null;
		String pertencentes = "";
		String[] horario = new String[108];
		int metricaTotal = 0;
		int totalCreditos = 0;

		public Grades(LinkedList<Disciplina> l,String p,String[] h, int m, int t)
		{
		listaOrdenada = l;
		pertencentes = p; //disciplinas "com" da árvore
		horario = h;
		metricaTotal = m; //métrica resultante das pertencentes
		totalCreditos = t; //total de créditos
		}
		
		public Grades(LinkedList<Disciplina> l)
		{
		listaOrdenada = l;
		}
		
		
		public Grades(String[] h)
		{
		horario = h;
		}
		
		
		
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
				int turmaId = 0;
				for(Turma oferta : first.turmas) ////Paralelismo das arvores para cada turma da disciplina em questão
					{  
						turmaId++;
						Grades auxInclude = new Grades(grade.horario);
						Grades auxExclude = new Grades(grade.horario);
						Grades auxSelecionado = new Grades(grade.horario);
						boolean valid = true;
						
						for(int aloc = 0 ; aloc < oferta.day.length ; aloc++)
						{
							
						for(int slot = (int) Math.pow(9, oferta.day[aloc]) + oferta.start[aloc]; slot < (int) Math.pow(9, oferta.day[aloc])+oferta.start[aloc]; slot++) 
							{
								if(grade.horario[slot].equals(null))
								{ valid=false;}
							}
						}	
						if(valid)
							{
							for(int aloc = 0 ; aloc < oferta.day.length ; aloc++)//se valido altera horario
							{
							for(int slot = (int) Math.pow(9, oferta.day[aloc]) + oferta.start[aloc]; slot < (int) Math.pow(9, oferta.day[aloc])+oferta.start[aloc]; slot++) 
								{
								auxSelecionado.horario[slot]=String.valueOf(first.codigo+String.valueOf(turmaId));
								}
							}
							//Ramo que inclui essa disciplina
							auxInclude = GetGrid(new Grades(grade.listaOrdenada,
								grade.pertencentes.concat(String.valueOf(first.codigo)),
								auxSelecionado.horario,
								grade.metricaTotal+first.metrica,
								grade.totalCreditos+first.creditos));

							if(grade.metricaTotal>10 | first.metrica<100) ////Poda soluções das arvores que excluem as disciplinas iniciais ate o nível de 10 creditos totais ou que tem um peso muito grande / que não incluem disciplinas mandatorias
								{
								auxExclude = GetGrid(new Grades(grade.listaOrdenada,
								grade.pertencentes,
								auxSelecionado.horario,
								grade.metricaTotal,
								grade.totalCreditos));
								}
							} 
						if(auxExclude.metricaTotal > auxInclude.metricaTotal) //Pega o maior entre as 2 solucões (incluir ou nao)
						{auxSelecionado = auxExclude;}
						else
						{auxSelecionado = auxInclude;}
						if(auxSelecionado.metricaTotal > selecionado.metricaTotal) //Das multiplas interações retorna a melhor
						{selecionado = auxSelecionado;}

				        }
				return selecionado;
				}

			else   ///Se a solução não pode incluir essa disciplinas pois estoura o número de creditos
				{
					selecionado = GetGrid(new Grades(grade.listaOrdenada,grade.pertencentes,grade.horario,grade.metricaTotal,grade.totalCreditos));
				}
				return selecionado;
		}
	else
	{return grade;}
		
	}


	



	public Grades main(String[] args) {
		Disciplina[] bloco = LoadDisciplinas(); //funcao de carga do conteudo do aluno e historico a definir
		Aluno cliente = LoadAluno();
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
