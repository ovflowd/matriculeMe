package com.unb.matriculeme.dao;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Turma 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, unique = true)
	private int id;
	@Column(nullable= false,length = 50)
	private String codigo;
	@ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH,})
	@JoinColumn
	private Professor professor; 
	@Column(nullable = false)
	private int campus;
	@ManyToOne(cascade = {CascadeType.MERGE})
	@JoinColumn
	private Oferta oferta;
	@Column(nullable = false)
	private int vagas;
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH},fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	@JoinTable(name="Turma_Horario")
	private List<Horario> horario;
	
	public Turma() 
	{
		this.codigo = new String();
		this.professor = new Professor();
		this.campus = 0;
		this.oferta = new Oferta();
		this.vagas = 0;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getCodigo()
	{
		return codigo;
	}
	public void setCodigo(String codigo)
	{
		this.codigo = codigo;
	}
	public Professor getProfessor()
	{
		return professor;
	}
	public void setProfessor(Professor professor)
	{
		this.professor = professor;
	}
	public int getCampus()
	{
		return campus;
	}
	public void setCampus(int campus)
	{
		this.campus = campus;
	}
	public Oferta getOferta()
	{
		return oferta;
	}
	public void setOferta(Oferta oferta)
	{
		this.oferta = oferta;
	}
	public int getVagas()
	{
		return vagas;
	}
	public void setVagas(int vagas)
	{
		this.vagas = vagas;
	}
	public List<Horario> getHorario(){
		return this.horario;
	}
	public void setHotario(List<Horario> horario){
		this.horario = horario;
	}
} 
