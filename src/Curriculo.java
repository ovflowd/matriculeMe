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
	
	
	void GeraMetrica(Aluno aluno,Perfil perf, ArrayList<Curriculo> historico) 
	{
		boolean valida = false;
		
		
		{
			for(Requisito C : this.disciplina.getRequisitoDisciplina())	
			{   //ln(C.getDisciplinaRequisito()+"\n");
				if(C.getTipo()==1)
				{} //Juntas disciplinas se nao foram cursadas (super disciplina)
				//tratar entre ou de listaDePrerequisitos
				
				String[] requisitos;
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
						valida = true;	
						break;
					}
					boolean validaAux = false;
					for(Curriculo ha : historico)  
					{ 
						
						
					//if(String.valueOf(ha.getDisciplina().getCodigo()).equals(req)) //prerequisito
						if(req.contains(String.valueOf(ha.getDisciplina().getCodigo()))) //prerequisito
						{
						validaAux = true;	
						}
					
					
					}
					
					//// Coorequisito a Tratar
					
					
					//tem este requisito
					if(validaAux)
					{valida = true;}
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
			System.out.print("Erro");
		}
	

		if(valida & vagasExistentes) //ja tem pre requisitos e existem vagas
			{
				this.disciplina.metrica =(int) perf.PerfilporDepartamento(disciplina.getDepartamento().getCodigo()) + PesoSemestre(this.getSemestreDisciplina(),disciplina.getCodigo());
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