package modules;

import javax.persistence.*;

@Entity
public class Oferta {
	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "disciplina_oferta")
	private Disciplina disciplina;//'FK' Referência às disciplinas ofertadas
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "oferta_semestre")
	private Semestre semestre;//'FK' Referência ao respectivo período da disciplina
	
	@OneToMany
	@JoinTable(name = "disciplinas_cursadas_oferta")
	private DisciplinasCursadas disciplinasCursadas;
		
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	
	public void setDisciplina(Disciplina disciplina){
		this.disciplina =  disciplina;
	}
	
	public Disciplina getDisciplina(){
		return disciplina;
	}
	
	public void setSemestre(Semestre semestre ){
		this.semestre = semestre;
	}
	
	public Semestre getSemestre(){
		return semestre;
	}
	
	public void setDisciplinasCursadas(DisciplinasCursadas disciplinasCursadas){
		this.disciplinasCursadas = disciplinasCursadas;
	}
	
	public DisciplinasCursadas getDisciplinasCursadas(){
		return disciplinasCursadas;
	}
}
