package projeto.matriculeme.REST;

import java.util.ArrayList;
import java.util.List;

public class Aluno{
	private int matricula;
	private String nome;
	private int ira;
	private int semestreAtual;
	private Curso curso;
	private String interesse;
	private List<DisciplinasCursadas> disciplinasCursadas;
	private Login login;
	private List<Sugestao> sugestoes;
    private int id;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    
    
    
    
    
	public Aluno(){
		this.matricula = 0;
		this.nome = new String();
		this.ira = 0;
		this.semestreAtual = 0;
		this.curso = new Curso();
		this.interesse = new String();
		
		this.login = new Login();
		this.disciplinasCursadas = new ArrayList<DisciplinasCursadas>();
		this.sugestoes = new ArrayList<Sugestao>();
	}
	public int getMatricula()
	{
		return matricula;
	}
	public void setMatricula(int matricula)
	{
		this.matricula = matricula;
	}
	public String getNome()
	{
		return nome;
	}
	public void setNome(String nome)
	{
		this.nome = nome;
	}
	public int getIra()
	{
		return ira;
	}
	public void setIra(int ira)
	{
		this.ira = ira;
	}
	public int getSemestreAtual()
	{
		return semestreAtual;
	}
	public void setSemestreAtual(int semestreAtual)
	{
		this.semestreAtual = semestreAtual;
	}
	public Curso getCurso()
	{
		return curso;
	}
	public void setCurso(Curso curso)
	{
		this.curso = curso;
	}
	public String getInteresse()
	{
		return interesse;
	}
	public void setInteresse(String interesse)
	{
		this.interesse = interesse;
	}
	

	public Login getLogin()
	{
		return login;
	}
	public void setLogin(Login login)
	{
		this.login = login;
	}
	public List<DisciplinasCursadas> getDisciplinasCursadas()
	{
		return disciplinasCursadas;
	}
	public void setDisciplinasCursadas(List<DisciplinasCursadas> disciplinasCursadas)
	{
		this.disciplinasCursadas = disciplinasCursadas;
	}
	public List<Sugestao> getSugestoes()
	{
		return sugestoes;
	}
	public void setSugestoes(List<Sugestao> sugestoes)
	{
		this.sugestoes = sugestoes;
	}
}
