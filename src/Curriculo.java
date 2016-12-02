package projeto.matriculeme.REST;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Curriculo  implements Comparable<Curriculo>{
	private Disciplina disciplina;
	private Curso curso;
	private int semestreDisciplina;
    private int id;
    
    
    
    
    @Override
	public int compareTo(Curriculo o) {
		
		if (disciplina.metrica > o.getDisciplina().metrica) {
			return -1;
		} else if (disciplina.metrica == o.getDisciplina().metrica) {
			return 0;
		} else {
			return 1;
		}
	}
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
	public Curriculo() {
		this.disciplina = new Disciplina();
		this.curso = new Curso();
		this.semestreDisciplina = 0;
	}
	public Disciplina getDisciplina()
	{
		return disciplina;
	}
	public void setDisciplina(Disciplina disciplina)
	{
		this.disciplina = disciplina;
	}
	public Curso getCurso()
	{
		return curso;
	}
	public void setCurso(Curso curso)
	{
		this.curso = curso;
	}
	public int getSemestreDisciplina()
	{
		return semestreDisciplina;
	}
	public void setSemestreDisciplina(int semestreDisciplina)
	{
		this.semestreDisciplina = semestreDisciplina;
	}
	
	
	void GeraMetrica(Aluno aluno,Perfil perf, ArrayList<Curriculo> disciplinasACursar) 
	{
		boolean valida = false;
		
		if(this.disciplina.getRequisitoDisciplina().isEmpty())
		{valida = true;
		//System.out.print("Sem Requisito");
		}
		else{
			for(Requisito C : this.disciplina.getRequisitoDisciplina())	
			{   ////System.out.println(C.getDisciplinaRequisito()+"\n");
				if(C.getTipo()==1)
				{
					break;
				} //Juntas disciplinas se nao foram cursadas (super disciplina)
				//tratar entre ou de listaDePrerequisitos
				
				String[] requisitos ;// tenta separar os requisitos (caso multiplos)
				try{
				 requisitos = C.getDisciplinaRequisito().split("+");
				}
				catch(Exception e)
				{
					requisitos = new String[] {C.getDisciplinaRequisito()};
				}
				
				for(String req : requisitos)
				{
					if(req=="")
					{
						valida = true;	//sem requisitos
						break;
					}
					boolean validaAux = false;
					//for(Curriculo ha : disciplinasACursar)  
					for(DisciplinasCursadas ha : aluno.getDisciplinasCursadas())
					{ 
					if(String.valueOf(ha.getOferta().getDisciplina().getCodigo()).equals(req) && C.getTipo()==0) //prerequisito
						{
						validaAux = true;	
						}
					}
					
					//// Coorequisito a Tratar
					
					
					//tem este requisito
					if(validaAux)
					{
						valida = true;
						}
					
				}
				
			
			}
		}
		boolean vagasExistentes = false;
		
		ClientRest cliente = new ClientRest();
		Gson transformar = new Gson();
		
		this.getDisciplina().turmas = transformar.fromJson(cliente.receberDados("http://homol.redes.unb.br/ptr022016-b/mprjct3/turmas/getTurmas/codDisciplina="+String.valueOf(this.getDisciplina().getCodigo())), new TypeToken<List<Turma>>(){}.getType());
			
		try{
			for(Turma T : this.disciplina.getTurmas())
			{
				if(T.getVagas()>0)
				{
				vagasExistentes = true;
				}
				
			}
		}
		catch(Exception e)
		{
			//System.out.println("Falha de recebimento de turma");
		}
	

		if(valida & vagasExistentes) //ja tem pre requisitos e existem vagas
			{
			//System.out.println("Valido " + disciplina.getNome());
				this.disciplina.metrica =(int) perf.PerfilporDepartamento(disciplina.getDepartamento().getCodigo()) + PesoSemestre(this.getSemestreDisciplina(),aluno.getSemestreAtual());
			}
		else
				{disciplina.metrica = 0;}
			
	}
	public int PesoSemestre(int sDisciplina,int sAluno){return Math.max(sAluno - sDisciplina,0);}
	public int PesoTipo(int tipo)
		{
		if(tipo == 0)
			{return 0;}
		return (10-tipo)*10;
		}
	
}
