package modules;

import javax.persistence.*;

@Entity
@Table(name = "disciplina")
public class Disciplina {
	@Id
	@GeneratedValue
	private int id;//Chave primária da tabela Disciplinas
	
	@Column
	private String nome;//Nome da disciplina
	
	@Column
	private int departamento;//'FK' referência ao Departamento que oferta a disciplina
	
	@Column
	private int credito;//Quantidade de créditos da disciplina
	
	@Column
	private int codigo;
	
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
	
	public void setDepartamento(int departamento){
		this.departamento = departamento;
	}
	
	public int getDepartamento(){
		return departamento;
	}	
	
	public void setCodigo(int codigo){
		this.codigo = codigo;
	}
	
	public int getcodigo(){
		return codigo;
	}
}
