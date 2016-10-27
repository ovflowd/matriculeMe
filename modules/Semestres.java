package modules;

import javax.persistence.*;

@Entity
@Table(name = "semestre")
public class Semestres {
	@Id
	@GeneratedValue
	@Column(name = "semstre_id", nullable = false, unique = true)
	private int id;//Chave primária da tabela Semestre
	
	@Column
	private String codigo;//
	
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
