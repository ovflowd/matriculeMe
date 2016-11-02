
//import org.apache.spark.SparkContext //Necessario pra rodar Spark stuffz
//import org.apache.spark.SparkConf
import java.util.*;
import java.math.*;
import java.lang.*;
import com.google.code.gson;

public class ML {
	
	public int PesoSemestre(int sDepartamento,int sAluno){return Math.max(sAluno - sDepartamento,0);}
	public int PesoTipo(int tipo)
	{
		if(tipo == 0)
			{return 0;}
		return (10-tipo)*10;
		}
	

	
	public class Turma
	{
		HorarioAula[] horarios;
		int vagas;
		int vaga_reserva;
		int oferta;
		int tipo_reserva_id;
		int professor;
		char[] codigo = new char[5];
		int id;
		
	}
	public class HorarioAula
	{	
		int diaInt;
		int horario_inicioInt ;
		int horario_finalInt;
		
		char[] dia = new char[10];
		char[] horario_inicio = new char[10];
		char[] horario_final = new char[10];
		public HorarioAula()
		{
			diaInt = Integer.valueOf(String.valueOf(dia));
			horario_inicioInt = Integer.valueOf(String.valueOf(horario_inicio));
			horario_finalInt = Integer.valueOf(String.valueOf(horario_final));
		}
		
	}
	
	
	
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
	
	public class Oferta
	{
		int id;
		Turma[] turmas;
		int semestrefluxo;
		
	}
	
	public class DisciplinaDB
	{
		requisitos[] Prerequisitos;
		char[] nome = new char[20];
		int id;
		int creditos;
		int codigo;
		int departamento;
				
	}
	
	public class requisitos
	{
		int disciplina_origem;
		char[] disciplina_requisito = new char[20];
		int tipo; //coorequisito ou pre
		
	}
	
	public class Disciplina implements Comparable<Disciplina>
	{
		requisitos[] Prerequisitos; //disciplina
		char[] nome = new char[20];
		int id;
		int creditos;
		int codigo;
		int tipo; //obrigatoria ou nao
		Oferta ofertas;
		int departamento;
		
		boolean vagasExistentes = false;
		int metrica;
	
		int nota;
		
		public Disciplina(DisciplinaDB DB)
		{
			ofertas = GETOFERTAS(this.codigo);
			Prerequisitos = DB.Prerequisitos;
			nome = DB.nome;
			id = DB.id;
			creditos = DB.creditos;
			codigo = DB.codigo;
			for(Turma t : ofertas.turmas)
			{
				if(t.vagas != 0 | (t.tipo_reserva_id == 0 & t.vaga_reserva != 0))
				{
					vagasExistentes=true;
				}
			}
			if(nota>3) //materia ja cursada e ja foi aprovada
			{
				vagasExistentes=false;
			}
		}
		//Retorna lista decrescente
		
		@Override
		public int compareTo(Disciplina o) {
			
			if (this.metrica > o.metrica) {
				return -1;
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
			for(requisitos C : this.Prerequisitos)	
			{
				if(C.tipo==1)
				{} //Juntas disciplinas se nao foram cursadas (super disciplina)
				//tratar entre ou de listaDePrerequisitos
			String[] requisitos = C.disciplina_requisito.toString().split("+");
			for(String req : requisitos)
			{
				boolean validaAux = false;
				for(DisciplinaDB ha : aluno.historicoAprovado)
				{ 
				if(String.valueOf(ha.codigo).equals(req) & C.tipo==0) //prerequisito
					{
					validaAux = true;	
					}
				}
				//tem este requisito
				if(validaAux)
				{valida = true;}
			}
			if(valida & this.vagasExistentes==true) //ja tem pre requisitos e existem vagas
				{
				this.metrica = aluno.PerfilporDepartamento(this.departamento) + PesoSemestre(this.ofertas.semestrefluxo,aluno.semestre) + PesoTipo(this.tipo)+ this.Preferencia(aluno.preferencia);
				}
			else
				this.metrica = 0;
			}	
		}


	}
	
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