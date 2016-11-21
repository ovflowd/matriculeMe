package com.datamining.rest.models;

public class Professor {
	private String nome;
	
	public Professor(String professor2) {
		setNome(professor2);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
