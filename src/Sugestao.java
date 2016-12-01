package projeto.matriculeme.REST;
public class Sugestao {
	private int id;
	private Disciplina curriculo;
	private int prioridade;
	private boolean vagas;
	private String motivo;
	private int creditos;

	public Sugestao() 
	{
		this.curriculo = new Disciplina();
		this.prioridade = 0;
		this.vagas = false;
		this.motivo = new String();
		this.creditos = 0;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public Disciplina getCurriculo()
	{
		return curriculo;
	}
	public void setCurriculo(Disciplina curriculo)
	{
		this.curriculo = curriculo;
	}
	public int getPrioridade()
	{
		return prioridade;
	}
	public void setPrioridade(int prioridade)
	{
		this.prioridade = prioridade;
	}
	public boolean isVagas()
	{
		return vagas;
	}
	public void setVagas(boolean vagas)
	{
		this.vagas = vagas;
	}
	public String getMotivo()
	{
		return motivo;
	}
	public void setMotivo(String horarios)
	{
		this.motivo = horarios;
	}
	public int getCreditos()
	{
		return creditos;
	}
	public void setCreditos(int creditos)
	{
		this.creditos = creditos;
	}
}