package com.unb.matriculeme.dao;

import javax.persistence.*;

@Entity
public class Departamento
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(nullable = false,length = 65)
	private String nome;
	@Column(length = 255)
	private String descricao;
	@Column(nullable = false)
	private int codigo;

	public Departamento() 
	{
		this.nome = new String();
		this.descricao = new String();
		this.codigo = 0;
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
	public String getDescricao() 
	{
		return descricao;
	}
	public void setDescricao(String descricao) 
	{
		this.descricao = descricao;
	}
	public int getCodigo()
	{
		return codigo;
	}
	public void setCodigo(int codigo) 
	{
		this.codigo = codigo;
	}
}
