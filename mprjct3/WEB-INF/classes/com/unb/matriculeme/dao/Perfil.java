package com.unb.matriculeme.dao;

import java.util.List;

import javax.persistence.*;

@Entity
public class Perfil 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	private int id;
	
	@OneToMany(cascade = {CascadeType.MERGE,CascadeType.REFRESH})
	@JoinTable(name = "perfil_departamento")
	private List<Departamento> departamentos; 
	
	@Column
	private String metricaString;
	
	@OneToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn
	private Aluno aluno;
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id; 
	}
	public List<Departamento> getDepartamentos()
	{
		return departamentos;
	}
	public void setDepartamentos(List<Departamento> departamentos)
	{
		this.departamentos = departamentos;
	}
	public String getMetricaString() 
	{
		return metricaString;
	}
	public void setMetricaString(String metricaString)
	{
		this.metricaString = metricaString;  
	}
	public Aluno getAluno(){
		return this.aluno; 
	}
	public void setAluno(Aluno aluno){
		this.aluno = aluno;
	}
}