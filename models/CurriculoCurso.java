package modules;

import javax.persistence.*;

@Entity
@Table(name = "curriculo")
public class CurriculoCurso {
	
	@Id
	@GeneratedValue
	@Column(name = "curriculo_id", nullable = false, unique = true)
	private int id;//Chave primária da Tabela Curriculo
	
	@Column
	private  int codigo;//identificação de um curriculo do curso
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "curso_curriculo")
	private Cursos curso;//'FK' Referência ao curso ao qual o curriculo pertence
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "disciplina_curriculo")
	private Disciplinas disciplina;//'FK' Referência às diciplinas no curriculo
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "departamento_curriculo")
	private Departamentos departamento;
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	
	public void setCodgio(int codigo ){
		this.codigo = codigo;
	}
	
	public int getCodigo(){
		return codigo;
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
}
