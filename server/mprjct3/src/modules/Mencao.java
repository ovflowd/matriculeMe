package modules;

import javax.persistence.*;

@Entity
@Table(name = "mencao")
public class Mencao {
	@Id
	@GeneratedValue
	@Column(nullable = false, unique = true)
	private int id; //Chave Primária da tabela Menções
	
	@Column
	private String codigo; // Menção atribuida ao aluno
	
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
