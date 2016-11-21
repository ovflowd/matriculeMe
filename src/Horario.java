package com.datamining.rest.models;

public class Horario {
	private String dia;
	private String horarioInicio;
	private String horarioFinal;
	
	public Horario(String string, String string2, String string3) {
		setDia(string);
		setHorarioInicio(string2);
		setHorarioFinal(string3);
	}
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
	public String getHorarioInicio() {
		return horarioInicio;
	}
	public void setHorarioInicio(String horarioInicio) {
		this.horarioInicio = horarioInicio;
	}
	public String getHorarioFinal() {
		return horarioFinal;
	}
	public void setHorarioFinal(String horarioFinal) {
		this.horarioFinal = horarioFinal;
	}
	
}
