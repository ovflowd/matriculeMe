package projeto.matriculeme.REST;
public class Departamento{
	private String nome;
	private String descricao;
	private int codigo;
    private int id;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
	public Departamento() {
		this.nome = new String();
		this.descricao = new String();
		this.codigo = 0;
	}
	public String getNome() 
	{
		return nome;
	}
	public void setNome(String nome) 
	{
		this.nome = nome;
	}
	public String getDescricao() 
	{
		return descricao;
	}
	public void setDescricao(String descricao) 
	{
		this.descricao = descricao;
	}
	public int getCodigo()
	{
		return codigo;
	}
	public void setCodigo(int codigo) 
	{
		this.codigo = codigo;
	}
}
