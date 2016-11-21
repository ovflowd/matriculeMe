package com.unb.matriculeme.dao;

import javax.persistence.*;

@Entity
public class Perfil 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH, CascadeType.ALL})
	@JoinColumn
	private Departamento departamentos;
	@Column(nullable = false)
	private int metrica;
	
	public Perfil() 
	{
		this.departamentos = new Departamento();
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
	public Departamento getDepartamentos()
	{
		return departamentos;
	}
	public void setDepartamentos(Departamento departamentos)
	{
		this.departamentos = departamentos;
	}
	public int getMetrica()
	{
		return metrica;
	}
	public void setMetrica(int metrica)
	{
		this.metrica = metrica;
	}
}