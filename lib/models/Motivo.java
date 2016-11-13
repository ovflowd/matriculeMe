package modules;

import javax.persistence.*;

@Entity
@Table(name = "motivo")
public class Motivo {
	@Id
	@GeneratedValue
	@Column(nullable = false, unique = true)
	private int id;//chave primária da Tabela Motivos
	
	@Column
	private String descricao;//Descrição do porque a sugestão está sendo feita a determinadao aluno
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	
	public void setDescricao(String descricao ){
		this.descricao = descricao;
	}
	
	public String getDescricao(){
		return descricao;
	}
}
