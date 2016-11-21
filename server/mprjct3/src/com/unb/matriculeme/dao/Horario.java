package com.unb.matriculeme.dao;

import javax.persistence.*;

@Entity
public class Horario 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@OneToOne(cascade = {CascadeType.MERGE})
	@JoinColumn
	private Turma turma;
	@Column(nullable = false,length = 10)
	private String dia;
	@Column(nullable = false,length = 10)
	private String horarioInicio;
	@Column(nullable = false,length = 10)
	private String horarioFim;

	public Horario() 
	{
		this.turma = new Turma();
		this.dia = "";
		this.horarioInicio = "";
		this.horarioFim = "";
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public Turma getTurma()
	{
		return turma;
	}
	public void setTurma(Turma turma)
	{
		this.turma = turma;
	}
	public String getDia()
	{
		return dia;
	}
	public void setDia(String dia)
	{
		this.dia = dia;
	}
	public String getHorarioInicio()
	{
		return horarioInicio;
	}
	public void setHorarioInicio(String horarioInicio)
	{
		this.horarioInicio = horarioInicio;
	}
	public String getHorarioFim()
	{
		return horarioFim;
	}
	public void setHorarioFim(String horarioFim)
	{
		this.horarioFim = horarioFim;
	}
}