package modules;

import javax.persistence.*;


@Entity
public class Historico {
	@Id
	@GeneratedValue
	@Column(name = "historico_id", nullable = false, unique = true)
	private int id; //Chave Prim�ria da tabela hist�rico
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "aluno_historico")
	private Alunos aluno; //'FK' Refer�ncia ao aluno cujo hist�rico est� sendo referenciado
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "mencao_historico")
	private Mencoes mencao; //'FK' referencia a men��o do aluno em determinada disciplina
	
	@ManyToOne
	@JoinTable(name = "oferta_historico")
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
