package com.unb.matriculeme.dao;

import javax.persistence.*;


@Entity
public class Curso
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(nullable = false,length = 100)
	private String nome;
	@Column(nullable = false)
	private int codigo;
	@Column(nullable = false)
	private int creditosLimite;

	public Curso() 
	{
		this.nome = new String();
		this.codigo = 0;
		this.creditosLimite = 0;
	}
	public int getId() 
	{
		return id;
	}
	public String getNome() 
	{
		return nome;
	}
	public void setNome(String nome) 
	{
		this.nome = nome;
	}
	public int getCodigo() 
	{
		return codigo;
	}
	public void setCodigo(int codigo) 
	{
		this.codigo = codigo;
	}
	public int getCreditosLimite() 
	{
		return creditosLimite;
	}
	public void setCreditosLimite(int creditosLimite) 
	{
		this.creditosLimite = creditosLimite;
	}
}
