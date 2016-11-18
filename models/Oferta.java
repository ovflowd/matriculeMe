import javax.persistence.*;

@Entity
public class Oferta {
	@Id
	@GeneratedValue
	private int id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "disciplina_oferta")
	private Disciplina disciplina;//'FK' Referncia s disciplinas ofertadas
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "oferta_semestre")
	private Semestre semestre;//'FK' Referncia ao respectivo  da disciplina
	
	@OneToOne
	@JoinTable(name = "oferta_turma")
	private Turma turmas;
	
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
		return this.disciplina;
	}
	
	public void setSemestre(Semestre semestre ){
		this.semestre = semestre;
	}
	
	public Semestre getSemestre(){
		return semestre;
	}
	
	public Turma getTurma(){
		return this.turmas;
	}
	
	public void setTurma(Turma turmas){
		this.turmas = turmas;
	}

}