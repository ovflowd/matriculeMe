package modules;

import javax.persistence.*;

@Entity
@Table(name = "mencao")
public class Mencoes {
	@Id
	@GeneratedValue
	@Column(name = "mencoes_id",nullable = false, unique = true)
	private int id; //Chave Prim�ria da tabela Men��es
	
	@Column
	private String codigo; // Men��o atribuida ao aluno
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	
	public void setCodigo(String codigo){
		this.codigo = codigo;
	}
	
	public String getCodigo(){
		return codigo;
	}
}
