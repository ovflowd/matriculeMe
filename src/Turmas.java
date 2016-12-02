package com.datamining.rest.api;

public class Turmas {
	private String codigo;
	private String professor;
	private int campus;
	private String horario;
	private int vagas;
	
	public Turmas(String cod, String prof, String camp, String hora, String vag){
		setCodigo(cod);
		setProfessor(prof);
		//setCampus(Integer.parseInt(camp));
		//setOferta(disc);
		setHorario(hora);
		setVagas(Integer.parseInt(vag));
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getProfessor() {
		return professor;
	}

	public void setProfessor(String professor) {
		this.professor = professor;
	}

	public int getCampus() {
		return campus;
	}

	public void setCampus(int campus) {
		this.campus = campus;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public int getVagas() {
		return vagas;
	}

	public void setVagas(int vagas) {
		this.vagas = vagas;
	}
}
