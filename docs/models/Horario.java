package modules;

import javax.persistence.*;

@Entity
@Table(name = "horario")
public class Horario {
	@Id
	@GeneratedValue
	private int id;//Chave primária da tabela Horários
	
	@OneToOne
	@JoinTable(name = "turma_id")
	private Turma turmaId;
	
	@Column
	private String dia;/*Dia da semana que ocorrem aulas em inteiros onde , segunda = 2, terça = 3
					  e assim sucessivamente*/
	
	@Column(name = "horario_inicio")
	private String horarioInicio;//Horário em que uma aula começa
	
	@Column(name = "horario_fim")
	private String horarioFim;//Horário em que uma aula termina
	
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
	
	public void setTurmas(Turma turmaId){
		this.turmaId = turmaId;
	}
	
	public Turma getTurmas(){
		return turmaId;
	}
	
	public void setDia(String dia){
		this.dia = dia;
	}
	
	public String getDia(){
		return dia;
	}
}
