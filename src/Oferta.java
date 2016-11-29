package projeto.matriculeme.REST;
public class Oferta {
	private int id;
	private Disciplina disciplina;
	private Semestre semestre;
	
	public Oferta() 
	{
		this.disciplina = new Disciplina();
		this.semestre = new Semestre();
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public Disciplina getDisciplina()
	{
		return disciplina;
	}
	public void setDisciplina(Disciplina disciplina)
	{
		this.disciplina = disciplina;
	}
	public Semestre getSemestre()
	{
		return semestre;
	}
	public void setSemestre(Semestre semestre)
	{
		this.semestre = semestre;
	}
}