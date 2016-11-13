package modules;

import javax.persistence.*;


@Entity
@Table(name = "disciplinas_cursadas")
public class DisciplinasCursadas {
	@Id
	@GeneratedValue
	private int id; //Chave Primária da tabela histórico
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "aluno_disciplina")
	private Aluno aluno; //'FK' Referência ao aluno cujo histórico está sendo referenciado
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "mencao_disciplina")
	private Mencao mencao; //'FK' referencia a menção do aluno em determinada disciplina
	
	@ManyToOne
	@JoinTable(name = "oferta_disciplina")
	private Oferta oferta;//'FK' 
	
	public void setId(int id){
		this.id= id;
	}
	public int getId(){
		return id;
	}
	
	public void setAlunos(Aluno aluno){
		this.aluno = aluno;
	}
	
	public Aluno getAlunos(){
		return aluno;
	}
	
	public void setMencao(Mencao mencao){
		this.mencao = mencao;
	}
	
	public Mencao  getMencao(){
		return mencao;
	}
	public void setDisciplinas(Oferta oferta){
		this.oferta = oferta;
	}
	
	public Oferta getOferta(){
		return oferta;
	}	
}
