package modules;

import javax.persistence.*;

@Entity
@Table(name = "mencao")
public class Mencao {
	@Id
	@GeneratedValue
	@Column(nullable = false, unique = true)
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
