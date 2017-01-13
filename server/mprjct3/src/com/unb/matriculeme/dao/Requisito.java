package com.unb.matriculeme.dao;

import java.util.List;

import javax.persistence.*;

@Entity
public class Requisito 
{
 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(length = 120)
	private String codigo; 
	@Column
	private String tipo;
	
	public Requisito() 
	{
		this.codigo = new String();
		this.tipo = new String();
	}
	public int getId() 
	{
		return id;
	}
	public String getCodigo() 
	{
		return this.codigo;
	}
	public void setCodigo(String codigo)
	{
		this.codigo = codigo;
	}
	public void setTipo(String tipo)
	{
		this.tipo = tipo;
	}
	public String getTipo() 
	{
		return tipo;
	}
}