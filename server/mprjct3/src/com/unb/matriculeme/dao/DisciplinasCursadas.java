package com.unb.matriculeme.dao;

import javax.persistence.*;

@Entity
public class DisciplinasCursadas 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn
	private Mencao mencao;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn
	private Oferta oferta;

	public DisciplinasCursadas() 
	{
		this.mencao = new Mencao();
		this.oferta = new Oferta();
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public Mencao getMencao()
	{
		return mencao;
	}
	public void setMencao(Mencao mencao)
	{
		this.mencao = mencao;
	}
	public Oferta getOferta()
	{
		return oferta;
	}
	public void setOferta(Oferta oferta)
	{
		this.oferta = oferta;
	}
}