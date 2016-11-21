package com.datamining.rest.models;

import java.util.ArrayList;

public class TurmaEnviar {
	private String codigo;
	private Professor professor;
	private int campus;
	private int vagas;
	ArrayList<Horario> horario = new ArrayList<Horario>();
	
	public TurmaEnviar(String codigo2, String horario2, String professor2, int vagas2, int campus2) {
		setCodigo(codigo2);
		setCampus(campus2);
		Professor prof = new Professor(professor2);
		setProfessor(prof);
		String[] dividirHora = horario2.split(";");
		for(int i = 0; i < dividirHora.length; i++){
			String[] dividirHoraParte2 = dividirHora[i].split(",");
			Horario temp = new Horario(dividirHoraParte2[0], dividirHoraParte2[1], dividirHoraParte2[2]);
			horario.add(temp);
		}
		setVagas(vagas2);
	}
	public TurmaEnviar() {
		setCodigo("XXX");
		// TODO Auto-generated constructor stub
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Professor getProfessor() {
		return professor;
	}
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	public int getCampus() {
		return campus;
	}
	public void setCampus(int campus) {
		this.campus = campus;
	}
	public int getVagas() {
		return vagas;
	}
	public void setVagas(int vagas) {
		this.vagas = vagas;
	}
}
