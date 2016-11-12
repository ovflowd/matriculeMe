package modules;

import javax.persistence.*;


@Entity
@Table(name = "disciplinas_cursadas")
public class DisciplinasCursadas {
	@Id
	@GeneratedValue
	private int id; //Chave Prim�ria da tabela hist�rico
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "aluno_disciplina")
	private Alunos aluno; //'FK' Refer�ncia ao aluno cujo hist�rico est� sendo referenciado
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "mencao_disciplina")
	private Mencoes mencao; //'FK' referencia a men��o do aluno em determinada disciplina
	
	@ManyToOne
	@JoinTable(name = "oferta_disciplina")
	private Oferta oferta;//'FK' 
	
	public void setId(int id){
		this.id= id;
	}
	public int getId(){
		return id;
	}
	
	public void setAlunos(Alunos alunos){
		this.aluno = alunos;
	}
	
	public Alunos getAlunos(){
		return aluno;
	}
	
	public void setMencao(Mencoes mencao){
		this.mencao = mencao;
	}
	
	public Mencoes  getMencao(){
		return mencao;
	}
	public void setDisciplinas(Oferta oferta){
		this.oferta = oferta;
	}
	
	public Oferta getOferta(){
		return oferta;
	}	
}
