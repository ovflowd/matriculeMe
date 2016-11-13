package modules;

import javax.persistence.*;

@Entity
@Table(name = "requisito")
public class Requisito {

	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "discplina_origem")
	private Disciplina disciplinaOrigem;//código da disciplina requisito
	
	@Column(name = "disciplina_requisito")
	private String discicplinaRequisito; //'FK' Referencia às disciplinas requisitos
	
	@Column
	private int tipo; //tipo de requisito, co, pré, etc
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	
	public void setDisciplinaOrigem(Disciplina disciplinaOrigem){
		this.disciplinaOrigem =  disciplinaOrigem;
	}
	
	public Disciplina getDisciplinaOrigem(){
		return disciplinaOrigem;
	}
	
	public void setDisciplinaRequisito(String disciplinaRequisito){
		this.discicplinaRequisito = disciplinaRequisito;
	}
	
	public String getDisciplinaRequisito(){
		return discicplinaRequisito;
	}
	
	public void setTipo(int tipo){
		this.tipo= tipo;
	}
	
	public int getTipo(){
		return tipo;
	}
	
}
