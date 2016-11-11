package modules;

import javax.persistence.*;

@Entity
public class Perfil {
	
	@Id
	@GeneratedValue
	private int id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "aluno_perfil")
	private Alunos aluno;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinTable(name = "departamento")
	private Departamentos departamento;
	
	@Column
	private int metrica;
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	
	public void setMetrica(int metrica){
		this.metrica = metrica;
	}
	
	public int getMetrica(){
		return metrica;
	}
	
	public void setDepartamento(Departamentos departamento){
		this.departamento = departamento;
	}
	
	public Departamentos getDepartamento(){
		return departamento;
	}
	
	public void setAluno(Alunos aluno){
		this.aluno = aluno;
	}
	
	public Alunos getAluno(){
		return aluno;
	}
}
