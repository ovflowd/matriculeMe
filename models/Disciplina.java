package modules;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "disciplina")
public class Disciplina {
	@Id
	@GeneratedValue
	private int id;//Chave primária da tabela Disciplinas
	
	@Column
	private String nome;//Nome da disciplina
	
	@ManyToOne
	@JoinTable(name = "departamento_disciplina")
	private Departamento departamento;//'FK' referência ao Departamento que oferta a disciplina
	
	@Column
	private int credito;//Quantidade de créditos da disciplina
	
	@Column
	private int codigo;
	
	@OneToMany(orphanRemoval = true)
	@JoinTable(name = "requisito_disciplina")
	private List<Requisito> requisitoDisciplina;
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	
	public void setNome(String nome){
		this.nome = nome;
	}
	
	public String getNome(){
		return nome;
	}
		
	public void setCredito(int credito){
		this.credito = credito;
	}
	
	public int getCredito(){
		return credito;
	}
	
	public void setDepartamento(Departamento departamento){
		this.departamento = departamento;
	}
	
	public Departamento getDepartamento(){
		return departamento;
	}	
	
	public void setCodigo(int codigo){
		this.codigo = codigo;
	}
	
	public int getCodigo(){
		return codigo;
	}
	
	public void setRequisitoDisciplina(List<Requisito> requisitoDisciplina){
		this.requisitoDisciplina = requisitoDisciplina;
	}
	
	public List<Requisito> getRequisitoDisciplina(){
		return requisitoDisciplina;
	}
}