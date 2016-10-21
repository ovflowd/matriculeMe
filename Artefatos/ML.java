
//import org.apache.spark.SparkContext //Necessario pra rodar Spark stuffz
//import org.apache.spark.SparkConf
import java.util.*;
import java.math.*;
public class ML {
	public int PerfilporDepartamento(int dDep,int dAluno){return 0;}
	public int PesoSemestre(int sDepartamento,int sAluno){return 0;}
	public int PesoTipo(int tipo){return tipo*10;}
	public class Turma
	{
		int[] day;
		int[] start;
		int[] end;
		
	}
	
	public class Disciplina
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
		public int Preferencia(int[] prefList)
		{
		for(int code : prefList)	
		{	if(codigo == code)
				{return 200;}}
			
		return 0;
		}
	}
	public class Aluno
	{
		int[] preferencia;
		int semestre;
		int[] historicoAprovado;
		int[] perfil;
		public int perfilDep(int D){return perfil[D];}
		
		
	}
	public class Grades /////Classe Manipula as informa��es do algoritmo
	{
		LinkedList<Disciplina> listaOrdenada;
		String pertencentes = "";
		String[] horario = new String[108];
		int metricaTotal = 0;
		int totalCreditos = 0;

		public Grades(LinkedList<Disciplina> l,String p,String[] h, int m, int t)
		{
		listaOrdenada = l;
		pertencentes = p; //disciplinas "com" da �rvore
		horario = h;
		metricaTotal = m; //m�trica resultante das pertencentes
		totalCreditos = t; //total de cr�ditos
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

	

	private void GeraMetrica(Disciplina D,Aluno aluno) 
	{
		boolean valida = false;
		for(String C : D.Prerequisitos)	
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
		if(valida & D.vagas>0) //ja tem pre requisitos e existem vagas
			{
			D.metrica = PerfilporDepartamento(D.departamento,aluno.perfilDep(D.departamento)) + PesoSemestre(D.semestrefluxo,aluno.semestre) + PesoTipo(D.tipo)+ D.Preferencia(aluno.preferencia);
			}
		}	
	}



	private Grades GetGrid(Grades grade) //// Funcao geradora da grade final         
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
				for(Turma oferta : first.turmas) ////Paralelismo das arvores para cada turma da disciplina em quest�o
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

							if(grade.metricaTotal>10 | first.metrica<100) ////Poda solu��es das arvores que excluem as disciplinas iniciais ate o n�vel de 10 creditos totais ou que tem um peso muito grande / que n�o incluem disciplinas mandatorias
								{
								auxExclude = GetGrid(new Grades(grade.listaOrdenada,
								grade.pertencentes,
								auxSelecionado.horario,
								grade.metricaTotal,
								grade.totalCreditos));
								}
							} 
						if(auxExclude.metricaTotal > auxInclude.metricaTotal) //Pega o maior entre as 2 soluc�es (incluir ou nao)
						{auxSelecionado = auxExclude;}
						else
						{auxSelecionado = auxInclude;}
						if(auxSelecionado.metricaTotal > selecionado.metricaTotal) //Das multiplas intera��es retorna a melhor
						{selecionado = auxSelecionado;}

				        }
				return selecionado;
				}

			else   ///Se a solu��o n�o pode incluir essa disciplinas pois estoura o n�mero de creditos
				{
					selecionado = GetGrid(new Grades(grade.listaOrdenada,grade.pertencentes,grade.horario,grade.metricaTotal,grade.totalCreditos));
				}
				return selecionado;
		}
	else
	{return grade;}
		
	}



	public static void main(String[] args) {
		bloco = new list<Disciplina>(aluno); //funcao de carga do conteudo do aluno e historico a definir

		disciplineList = bloco.map(d -> d.GeraMetrica()).filter(d -> d.metrica>0); // varre entradas, atribui pesos e filtra apenas elementos que podem ser cursados para lista ponderada

		LinkedList<Disciplina>orderDisciplineList  = listadisciplinas.takeOrdered(listadiciplinas.lenght(),descending);//ordena
		megagrid = new Grades(new orderDisciplineList);//instancia classe inicial
		return grid = GetGrid(megagrid);
	}

}
