public class Motivo {
	private int id;
	private String descricao;

	public Motivo() 
	{
		this.descricao = new String();
	}
	public int getId() 
	{
		return id;
	}
	public String getDescricao() 
	{
		return descricao;
	}
	public void setDescricao(String descricao) 
	{
		this.descricao = descricao;
	}
}
