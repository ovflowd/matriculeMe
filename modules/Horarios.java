package modules;

import javax.persistence.*;

@Entity
@Table(name = "horario")
public class Horarios {
	@Id
	@GeneratedValue
	private int id;//Chave prim�ria da tabela Hor�rios
	
	@OneToOne
	@JoinTable(name = "horario_turma")
	private Turmas turmaId;
	
	@Column
	private String dia;/*Dia da semana que ocorrem aulas em inteiros onde , segunda = 2, ter�a = 3
					  e assim sucessivamente*/
	
	@Column(name = "horario_inicio")
	private String horarioInicio;//Hor�rio em que uma aula come�a
	
	@Column(name = "horario_fim")
	private String horarioFim;//Hor�rio em que uma aula termina
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	
	public void setHorarioInicio(String horarioInicio ){
		this.horarioInicio = horarioInicio;
	}
	
	public String getHorarioInicio(){
		return horarioInicio;
	}
	
	public void setHorarioFim(String horarioFim){
		this.horarioFim = horarioFim ;
	}
	
	public String getHorarioFim(){
		return horarioFim;
	}
	
	public void setTurmas(Turmas turmaId){
		this.turmaId = turmaId;
	}
	
	public Turmas getTurmas(){
		return turmaId;
	}
}
