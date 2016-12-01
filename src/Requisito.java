package projeto.matriculeme.REST;
public class Requisito {
	private int id;
	private String codigo; 
	private int tipo;
	
	public Requisito() 
	{
		this.codigo = new String();
		this.tipo = 0;
	}
	public int getId() 
	{
		return id;
	}
	public String getDisciplinaRequisito() 
	{
		return codigo;
	}
	public void setTipo(int tipo)
	{
		this.tipo = tipo;
	}
	public int getTipo() 
	{
		return tipo;
	}
}