package modules;

import javax.persistence.*;
@Entity
@Table(name = "curriculo")
public class CurriculoCurso {
	
	@Id
	@GeneratedValue
	private int id;//Chave primária da Tabela Curriculo
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "disciplina_curriculo")
	private Disciplinas disciplina;//'FK' Referência às diciplinas no curriculo
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "curso_curriculo")
	private Cursos curso;//'FK' Referência ao curso ao qual o curriculo pertence
	
	@Column(name = "semestre_disciplina")
	private int semestreDisciplina;
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	
	public void setCurso(Cursos curso){
		this.curso = curso;
	}
	
	public Cursos getCurso(){
		return curso;
	}
	
	public void setDiciplina(Disciplinas disciplina ){
		this.disciplina = disciplina;
	}
	
	public Disciplinas getDisciplina(){
		return disciplina;
	}
	
	public void setSemestreDisciplina(int semestreDisciplina){
		this.semestreDisciplina = semestreDisciplina;
	}
	
	public int getSemestreDisciplina(){
		return semestreDisciplina;
	}
}
