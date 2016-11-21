package com.datamining.rest.models;
import com.datamining.rest.api.*;

public class Oferta {
	
	private TurmaEnviar turma;
	private Disciplina disciplina;
	private Semestre semestre;
	
	public Oferta(Disciplina disc, String codigo, TurmaEnviar turma){
		setDisciplina(disc);
		Semestre temp = new Semestre();
		temp.setCodigo(codigo);
		setSemestre(temp);
		setTurma(turma);
	}
	public Disciplina getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	public Semestre getSemestre() {
		return semestre;
	}
	public void setSemestre(Semestre semestre) {
		this.semestre = semestre;
	}
	public TurmaEnviar getTurma() {
		return turma;
	}
	public void setTurma(TurmaEnviar turma) {
		this.turma = turma;
	}
	
}
