package modules;

import javax.persistence.*;

@Entity
public class Sugestao {
	@Id
	@GeneratedValue
	@Column(name = "sugestao_id", nullable = false, unique = true)
	private int id;//Chave primária da tabela Sugestão
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "aluno_sugestao")
	private Alunos aluno;//'FK' referência ao aluno que receberá a respectiva sugestão
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "curriculo_sugestao")
	private CurriculoCurso curriculo ;//'FK' Referência a disciplina sugerida
	
	@Column
	private int prioridade;//prioridade da disciplina na matricula
		
	@Column
	private boolean vagas;//Quantidade de vagas na turma
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "motivo_sugestao")
	private Motivos motivo;//'FK" Referência ao motivo pelo qual a disciplina está sendo sugerida
	
	@Column
	private int creditos;//Quantidade total de créditos sugeridos
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	public void setAluno(Alunos aluno){
		this.aluno = aluno;
	}
	
	public Alunos getAluno(){
		return aluno;
	}
	
	public void setCuriculo(CurriculoCurso curriculo){
		this.curriculo = curriculo;
	}
	
	public CurriculoCurso getCurriculo(){
		return curriculo;
	}
	
	public void setMotivo(Motivos motivo){
		this.motivo = motivo;
	}
	
	public Motivos getMotivo(){
		return motivo;
	}
	
	public void setPrioridade(int prioridade){
		this.prioridade = prioridade;
	}
	
	public int getPrioridade(){
		return prioridade;
	}
	public void setCreditos(int creditos){
		this.creditos = creditos;
	}
	
	public int getCreditos(){
		return creditos;
	}
	
	
	public void setVagas(boolean vagas ){
		this.vagas = vagas;
	}
	
	public boolean getVagas(){
		return vagas;
	}
}

