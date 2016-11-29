package projeto.matriculeme.REST;
public class Sugestao {
	private int id;
	private Curriculo curriculo;
	private int prioridade;
	private boolean vagas;
	private Motivo motivo;
	private int creditos;

	public Sugestao() 
	{
		this.curriculo = new Curriculo();
		this.prioridade = 0;
		this.vagas = false;
		this.motivo = new Motivo();
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
	public Curriculo getCurriculo()
	{
		return curriculo;
	}
	public void setCurriculo(Curriculo curriculo)
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
	public Motivo getMotivo()
	{
		return motivo;
	}
	public void setMotivo(Motivo motivo)
	{
		this.motivo = motivo;
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
