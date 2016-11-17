package modules;

import javax.persistence.*;

@Entity
@Table(name = "Tipo_Disciplina")
public class TipoDisciplina {
	@Id
	@GeneratedValue
	@Column(name = "tipo_disicplina_id", nullable = false, unique = true)
	private int id;//Cahve primária da tabela Tipo_Disciplina
	
	@Column
	private String nome;//Tipo da disciplina
	
	public void setId(int id){
		this.id = id;
	}
	
	public void setNome(String nome){
		this.nome = nome;
	}
	
	public int getId(){
		return id;
	}
	
	public String getNome(){
		return nome;
	}
	
}
