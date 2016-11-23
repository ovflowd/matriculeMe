package com.unb.matriculeme.dao;

import javax.persistence.*;

@Entity
public class Perfil 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH})
	@JoinColumn
	private Departamento departamento;
	@Column(nullable = false)
	private int metrica;
	@OneToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn
	private Aluno aluno;
	
	public Perfil() 
	{
		this.departamento = new Departamento();
		this.metrica = 0;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public Departamento getDepartamento()
	{
		return departamento;
	}
	public void setDepartamento(Departamento departamento)
	{
		this.departamento = departamento;
	}
	public int getMetrica()
	{
		return metrica;
	}
	public void setMetrica(int metrica)
	{
		this.metrica = metrica;
	}
	public Aluno getAluno(){
		return this.aluno;
	}
	public void setAluno(Aluno aluno){
		this.aluno = aluno;
	}
}