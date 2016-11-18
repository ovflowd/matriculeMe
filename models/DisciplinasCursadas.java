import javax.persistence.*;


@Entity
@Table(name = "disciplinas_cursadas")
public class DisciplinasCursadas {
	@Id
	@GeneratedValue
	private int id; //Chave Prim�ria da tabela hist�rico
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "aluno_disciplina")
	private Aluno aluno; //'FK' Refer�ncia ao aluno cujo hist�rico est� sendo referenciado
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "mencao_disciplina")
	private Mencao mencao; //'FK' referencia a men��o do aluno em determinada disciplina
	
	@ManyToOne
	@JoinTable(name = "oferta_disciplinas_cursadas")
	private Oferta ofertaDisciplinasCursadas;
		
	public void setId(int id){
		this.id= id;
	}
	public int getId(){
		return id;
	}
	
	public void setAluno(Aluno aluno){
		this.aluno = aluno;
	}
	
	public Aluno getAluno(){
		return aluno;
	}
	
	public void setMencao(Mencao mencao){
		this.mencao = mencao;
	}
	
	public Mencao  getMencao(){
		return mencao;
	}	
	
	public void setOfertaDisciplinasCursadas(Oferta ofertaDisciplinasCursadas){
		this.ofertaDisciplinasCursadas = ofertaDisciplinasCursadas;
	}
	
	public Oferta getOfertaDisciplinasCursadas(){
		return ofertaDisciplinasCursadas;
	}
}