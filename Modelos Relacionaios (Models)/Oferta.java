package modules;

import javax.persistence.*;

@Entity
public class Oferta {
	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "disciplina_semestre")
	private DisciplinasCursadas disciplina;//'FK' Referência às disciplinas ofertadas
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "oferta_semestre")
	private Semestres semestre;//'FK' Referência ao respectivo período da disciplina
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	
	public void setDisciplinaCursadas(DisciplinasCursadas disciplina){
		this.disciplina =  disciplina;
	}
	
	public DisciplinasCursadas getDisciplinasCursadas(){
		return disciplina;
	}
	
	public void setSemestre(Semestres semestre ){
		this.semestre = semestre;
	}
	
	public Semestres getSemestre(){
		return semestre;
	}
}
