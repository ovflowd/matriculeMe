package com.unb.matriculeme.dao;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Curriculo 
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	private int id;
	@ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH})
	@JoinColumn  
	private Disciplina disciplina;    
	@ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH})
	@JoinColumn  
	private Curso curso;  
	@Column(nullable = false)
	private int semestreDisciplina; 
 
	public Curriculo() 
	{
		this.disciplina = new Disciplina();
		this.curso = new Curso();
		this.semestreDisciplina = 0;
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
		return disciplina;
	}
	public void setDisciplina(Disciplina disciplina)
	{
		this.disciplina = disciplina;
	}
	public Curso getCurso()
	{
		return curso;
	}
	public void setCurso(Curso curso)
	{
		this.curso = curso;
	}
	public int getSemestreDisciplina()
	{
		return semestreDisciplina;
	}
	public void setSemestreDisciplina(int semestreDisciplina)
	{
		this.semestreDisciplina = semestreDisciplina;
	}
}
