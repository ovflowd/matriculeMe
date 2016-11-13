package modules;

import javax.persistence.*;

@Entity
@Table(name = "departamento")
public class Departamento {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column
	private String nome;
	
	@Column
	private String descricao;
	
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
	public void setDescricao(String descricao){
		this.descricao = descricao;
	}
	public String getDescricao(){
		return descricao;
	}
	
	public void setCodigo(int codigo){
		this.codigo = codigo;
	}
	
	public int getCodigo(){
		return codigo;
	}
}
