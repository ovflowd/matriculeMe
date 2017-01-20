package com.unb.matriculeme.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


@Entity
public class Disciplina 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(nullable = false,length = 120)
	private String nome;
	@ManyToOne(cascade ={CascadeType.MERGE,CascadeType.REFRESH},fetch = FetchType.EAGER)
	@JoinColumn
	private Departamento departamento;
	@Column(nullable = false)
	private int credito;  
	@Column(nullable = false)
	private int codigo; 
	 
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.ALL},fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	@JoinTable(name="Requisito_Disciplina")
	private List<Requisito> requisitoDisciplina; 


	public Disciplina() 
	{
		this.nome = new String(); 
		this.departamento = new Departamento();
		this.credito = 0;
		this.codigo = 0;
		this.requisitoDisciplina = new ArrayList<Requisito>();
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getNome()
	{
		return nome;
	}
	public void setNome(String nome)
	{
		this.nome = nome;
	}
	public Departamento getDepartamento()
	{
		return departamento;
	}
	public void setDepartamento(Departamento departamento)
	{
		this.departamento = departamento;
	}
	public int getCredito()
	{
		return credito;
	}
	public void setCredito(int credito)
	{
		this.credito = credito;
	}
	public int getCodigo()
	{
		return codigo;
	}
	public void setCodigo(int codigo)
	{
		this.codigo = codigo;
	}
	public List<Requisito> getRequisitoDisciplina() 
	{
		return requisitoDisciplina;
	} 
	public void setRequisitoDisciplina(List<Requisito> requisitoDisciplina)
	{
		this.requisitoDisciplina = requisitoDisciplina;
	}
}