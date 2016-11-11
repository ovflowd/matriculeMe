package modules;

import javax.persistence.*;

@Entity
@Table(name = "professor")
public class Professores {
	@Id
	@GeneratedValue
	@Column(name = "professores_id", nullable = false, unique = true)
	private int id;//Chave primária da tabela Professores
	
	@Column
	private String nome;//Nome do professor
	
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
}
