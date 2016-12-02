package com.datamining.rest.models;

public class Horario {
	private String dia;
	private String horarioInicio;
	private String horarioFim;
	
	public Horario(String dia, String horaInic, String horaFim) {
		System.out.println("Dia: "+dia+" horaInicio: "+horaInic+" horaFim: "+horaFim);
		setDia(dia);
		horaInic = horaInic.split(":")[0];
		int tempHora = 0;
		setHorarioInicio(horaInic);
		
		if(horaFim.split(":")[0].equals("or")){
			tempHora = Integer.parseInt("0");
		}else{
			tempHora = Integer.parseInt(horaFim.split(":")[0]);
			tempHora = tempHora + 1;
		}
		horaFim = String.valueOf(tempHora);
		setHorarioFinal(horaFim);
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
		return horarioFim;
	}
	public void setHorarioFinal(String horarioFinal) {
		this.horarioFim = horarioFinal;
	}
	
}
