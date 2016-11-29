package projeto.matriculeme.REST;
public class Curso{
	private String nome;
	private int codigo;
	private int creditosLimite;
    private int id;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
	public Curso() {
		this.nome = new String();
		this.codigo = 0;
		this.creditosLimite = 0;
	}
	public String getNome() 
	{
		return nome;
	}
	public void setNome(String nome) 
	{
		this.nome = nome;
	}
	public int getCodigo() 
	{
		return codigo;
	}
	public void setCodigo(int codigo) 
	{
		this.codigo = codigo;
	}
	public int getCreditosLimite() 
	{
		return creditosLimite;
	}
	public void setCreditosLimite(int creditosLimite) 
	{
		this.creditosLimite = creditosLimite;
	}
}
