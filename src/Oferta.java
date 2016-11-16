package com.datamining.rest.models;
import com.datamining.rest.api.*;

public class Oferta {
	
	private Disciplina disciplinas;
	private Semestre semestre;
	
	public Oferta(Disciplina disc, String codigo){
		setDisciplina(disc);
		Semestre temp = new Semestre();
		temp.setCodigo(codigo);
		setSemestre(temp);
	}
	public Disciplina getDisciplina() {
		return disciplinas;
	}
	public void setDisciplina(Disciplina disciplina) {
		this.disciplinas = disciplina;
	}
	public Semestre getSemestre() {
		return semestre;
	}
	public void setSemestre(Semestre semestre) {
		this.semestre = semestre;
	}
	
}
