package com.unb.matriculeme.dao;

import javax.persistence.*;

@Entity
public class Motivo 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(length = 255)
	private String descricao;

	public Motivo() 
	{
		this.descricao = new String();
	}
	public int getId() 
	{
		return id;
	}
	public String getDescricao() 
	{
		return descricao;
	}
	public void setDescricao(String descricao) 
	{
		this.descricao = descricao;
	}
}
