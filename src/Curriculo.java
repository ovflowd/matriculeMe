package com.datamining.rest.api;

public class Curriculo {
	private transient String codCurso;
	private transient String codDisc;
	private transient String semestre1; 
	private int curso;
	private int disciplina;
	private int semestre;
	
	public void converter(){
		setCurso(Integer.parseInt(codCurso));
		setDisciplina(Integer.parseInt(codDisc));
		semestre1 = semestre1.trim();
		setSemestre(Integer.parseInt(semestre1));
	}
	
	public String getCodCurso() {
		return codCurso;
	}
	public void setCodCurso(String codCurso) {
		this.codCurso = codCurso;
	}
	public String getCodDisc() {
		return codDisc;
	}
	public void setCodDisc(String codDisc) {
		this.codDisc = codDisc;
	}
	public String getSemestre1() {
		return semestre1;
	}
	public void setSemestre1(String semestre) {
		this.semestre1 = semestre;
	}
	public int getCurso() {
		return curso;
	}
	public void setCurso(int codCursoEnviar) {
		this.curso = codCursoEnviar;
	}
	public int disciplina() {
		return disciplina;
	}
	public void setDisciplina(int codDiscEnviar) {
		this.disciplina = codDiscEnviar;
	}
	public int getSemestre() {
		return semestre;
	}
	public void setSemestre(int semestreEnviar) {
		this.semestre = semestreEnviar;
	}
}
