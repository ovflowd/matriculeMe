package com.unb.matriculeme.dao;

import javax.persistence.*;

@Entity
public class Mencao
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, unique = true)
	private int id; 
	@Column(nullable = false)
	private String codigo;

	public Mencao() 
	{
		this.codigo = new String();
	}
	public int getId() 
	{
		return id;
	}
	public String getCodigo()
	{
		return codigo;
	}
	public void setCodigo(String codigo) 
	{
		this.codigo = codigo;
	}
}
