package projeto.matriculeme.REST;

import java.util.ArrayList;
import java.util.List;

public class Disciplina{
	private String nome;
	private Departamento departamento;
	private int credito;
	private int codigo;
	private List<Requisito> requisitoDisciplina;
	transient public List<Turma> turmas;
    private int id;
    transient public double metrica;
    
    
	
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
	public Disciplina() 
	{	
		this.nome = new String();
		this.departamento = new Departamento();
		this.credito = 0;
		this.codigo = 0;
		this.requisitoDisciplina = new ArrayList<Requisito>();
		
	}
	public String getNome()
	{
		return nome;
	}
	public void setNome(String nome)
	{
		this.nome = nome;
	}
	public Departamento getDepartamento()
	{
		return departamento;
	}
	public void setDepartamento(Departamento departamento)
	{
		this.departamento = departamento;
	}
	public int getCredito()
	{
		return credito;
	}
	public void setCredito(int credito)
	{
		this.credito = credito;
	}
	public int getCodigo()
	{
		return codigo;
	}
	public void setCodigo(int codigo)
	{
		this.codigo = codigo;
	}
	public List<Requisito> getRequisitoDisciplina()
	{
		return requisitoDisciplina;
	}
	public void setRequisitoDisciplina(List<Requisito> requisitoDisciplina)
	{
		this.requisitoDisciplina = requisitoDisciplina;
	}
	public List<Turma> getTurmas()
	{
		return turmas;
	}
	public void setTurmas(List<Turma> turmas)
	{
		this.turmas = turmas;
	}
}