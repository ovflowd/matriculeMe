package com.unb.matriculeme.dao;

import javax.persistence.*;

@Entity
public class Requisito 
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(nullable = false,length = 120)
	private String discicplinaRequisito; 
	@Column(nullable = false)
	private int tipo;
	
	public Requisito() 
	{
		this.discicplinaRequisito = new String();
		this.tipo = 0;
	}
	public int getId() 
	{
		return id;
	}
	public String getDisciplinaRequisito() 
	{
		return discicplinaRequisito;
	}
	public void setTipo(int tipo)
	{
		this.tipo = tipo;
	}
	public int getTipo() 
	{
		return tipo;
	}
}