public class Requisito {
	private int id;
	private String discicplinaRequisito; 
	private int tipo;
	
	public Requisito() 
	{
		this.discicplinaRequisito = new String();
		this.tipo = 0;
	}
	public int getId() 
	{
		return id;
	}
	public String getDisciplinaRequisito() 
	{
		return discicplinaRequisito;
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