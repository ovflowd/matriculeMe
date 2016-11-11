package modules;

import javax.persistence.*;

@Entity
public class Perfil {
	
	@Id
	@GeneratedValue
	private int id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "aluno_id")
	private Aluno aluno;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinTable(name = "departamento")
	private Departamento departamento;
	
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
	
	public void setDepartamento(Departamento departamento){
		this.departamento = departamento;
	}
	
	public Departamento getDepartamento(){
		return departamento;
	}
	
	public void setAluno(Aluno aluno){
		this.aluno = aluno;
	}
	
	public Aluno getAluno(){
		return aluno;
	}
}
