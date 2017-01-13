package com.unb.matriculeme.dao;

import javax.persistence.*;

@Entity
public class Sugestao 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
	@JoinColumn
	private Disciplina disciplina; 
	@Column(nullable = false)
	private int prioridade;
	@Column
	private boolean vagas;
	@Column
	private String motivo; 
	@Column(nullable = false)
	private int creditos;

	public Sugestao() 
	{
		
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public Disciplina getDisciplina()
	{
		return this.disciplina; 
	}
	public void setDisciplina(Disciplina disciplina)
	{
		this.disciplina = disciplina;
	}
	public int getPrioridade()
	{
		return prioridade;
	}
	public void setPrioridade(int prioridade)
	{
		this.prioridade = prioridade;
	}
	public boolean isVagas()
	{
		return vagas;
	}
	public void setVagas(boolean vagas)
	{
		this.vagas = vagas;
	}
	public String getMotivo()
	{
		return motivo;
	}
	public void setMotivo(String motivo)
	{
		this.motivo = motivo;
	}
	public int getCreditos()
	{ 
		return creditos;
	}
	public void setCreditos(int creditos)
	{
		this.creditos = creditos;
	}
}
