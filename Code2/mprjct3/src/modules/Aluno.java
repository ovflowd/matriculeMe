package modules;

import javax.persistence.*;

@Entity
@Table(name = "aluno")
public class Aluno {
	
	@Id
	@GeneratedValue
	private int id; //Chave primária da classe Alunos
	
	@Column
	private int matricula; //Matricula do aluno na Universidade
	
	@Column
	private String nome; //Nome do Aluno
	 
	@Column
	private int ira; //Indice de Rendimento Acadêmico do aluno
	
	@Column(name = "semestre_atual")
	private int semestreAtual;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "curso_aluno")
	private Curso curso;//'FK'
	
	@Column
	private String interesse;
	
	@OneToOne
	@JoinTable(name = "login_aluno")
	private Login login;
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	
	public void setNome(String nome){
		this.nome = nome;
	}
	
	public String getNome(){
		return nome;
	}
	
	public void setIra(int ira){
		this.ira = ira;
	}
	
	public int getIra(){
		return ira;
	}
	
	public void setMatricula(int matricula){
		this.matricula= matricula;
	}
	
	public int getMatricula(){
		return matricula;
	}
	
	public void setSemestreAtual(int semestreAtual){
		this.semestreAtual = semestreAtual;
	}
	
	public int getSemestreAtual(){
		return semestreAtual;
	}
	
	public void setCurso(Curso curso){
		this.curso = curso;
	}
	
	public Curso getCurso(){
		return curso;
	}
	
	public void setInteresse(String interesse){
		this.interesse = interesse;
	}
	
	public String getInteresse(){
		return interesse;
	}
	
	public void setLogin(Login login){
		this.login = login;
	}
	
	public Login getLogin(){
		return login;
	}
}
