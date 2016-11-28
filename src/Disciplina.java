public class Disciplina implements Comparable<Disciplina>
	{
		Requisitos[] Prerequisitos; //disciplina
		char[] nome ;
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
			ofertas = GETFROMDB(this.codigo);
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
			for(Requisitos C : this.Prerequisitos)	
			{
				if(C.tipo==1)
				{} //Juntas disciplinas se nao foram cursadas (super disciplina)
				//tratar entre ou de listaDePrerequisitos
			String[] requisitos = C.disciplina_requisito.toString().split("+");
			for(String req : requisitos)
			{
				boolean validaAux = false;
				for(HistoricoDB ha : aluno.historicoAprovado)
				{ 
				if(String.valueOf(ha.Codigo).equals(req) & C.tipo==0) //prerequisito
					{
					validaAux = true;	
					}
				}
				
				//// Coorequisito a Tratar
				
				
				//tem este requisito
				if(validaAux)
				{valida = true;}
			}
			if(valida & this.vagasExistentes==true) //ja tem pre requisitos e existem vagas
				{
				this.metrica = aluno.PerfilporDepartamento(this.departamento) + ML.PesoSemestre(this.ofertas.semestrefluxo,aluno.semestre) + ML.PesoTipo(this.tipo)+ this.Preferencia(aluno.preferencia);
				}
			else
				this.metrica = 0;
			}	
		}


	}