package com.datamining.rest.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Turma {
	private String turmas;
	private String codDisc;
	//private String semestre;
	private String horario;
	private String profDisc;
	private String campus;
	private String vagas;
	
	public void extrairVagas() throws IOException{
		int flag = 0;
		Document doc = null;
		while(flag == 0){
			try{doc = Jsoup.connect("https://matriculaweb.unb.br/graduacao/oferta_dados.aspx?cod="+codDisc).get();
			flag = 1;
			}
			catch(Exception e){
				System.out.println("Erro");
			}
		}
		Element table = doc.select("TD").first();
		Iterator<Element> ite = table.select("tr").iterator();
		String compInic = "Turma Vagas Turno Horário/Local Professor Obs";
		vagas = "";
		while(ite.hasNext()){
			if(ite.next().text().equals(compInic)){
				ite.next();
				ite.next();
				ite.next();
				vagas += ite.next().text().split(" ")[1] + ";";
			}
		}
	}
	
	public void extrairTurmas(String dpto, String disc) throws IOException{
		ArrayList<String> diaDisc = new ArrayList<String>();
		ArrayList<String> horaDisc = new ArrayList<String>();
		
		Document doc = Jsoup.connect("https://matriculaweb.unb.br/graduacao/oferta_dados.aspx?cod=+"+disc+"&dep="+dpto).get();
		Element table = doc.select("TD").first();
		Iterator<Element> ite = table.select("tr").iterator();
		
		String compInicio = "Turma Vagas Turno Horário/Local Professor Obs";
		String compFinal = "Legenda do campo 'Obs': Disciplina restrita (só pode ser cursada pelo aluno que possuí-la em seu currículo) Turma de horário fixo Turma com reserva exclusiva (só matricula aluno do curso para o qual está reservada)";
		//String de comparação início "Turma Vagas Turno Horário/Local Professor Obs"
		//String de comparação final "Legenda do campo 'Obs': Disciplina restrita (só pode ser cursada pelo aluno que possuí-la em seu currículo) Turma de horário fixo Turma com reserva exclusiva (só matricula aluno do curso para o qual está reservada)"
		String temp;
		String tratar;
		String divTratarD;
		int ind = 0;
		String turmaTemp = "";
		String horarioTemp = "";
		String turma;
		
		do{								
			temp = ite.next().text();
			if(compInicio.equals(temp)){
				tratar = ite.next().text();
				String[] divTratarH = tratar.split(":");
				for(int j = 1; j < divTratarH.length; j++){
					horaDisc.add(divTratarH[j-1].substring((divTratarH[j-1].length())-2,(divTratarH[j-1].length()))+ 
							":" +divTratarH[j].substring(0,2));
				}
				String tratarDia = tratar.replaceAll(Character.toString((char) 160), "");
				//System.out.println(tratar);
				String[] diaSemana = tratarDia.split(" ");
				//StringTokenizer st = new StringTokenizer(tratarDia, " ");
				turma = diaSemana[0];
				int i = 0;
				while(i < diaSemana.length){
					divTratarD = diaSemana[i];
					//System.out.println(divTratarD);
					int segunda = divTratarD.split("unda").length;
					int terca = divTratarD.split("erça").length;
					int quarta = divTratarD.split("arta").length;
					int quinta = divTratarD.split("inta").length;
					int sexta = divTratarD.split("exta").length;
					int sabado = divTratarD.split("bado").length;
					if(segunda > 1){
						diaDisc.add("1");
					}else if(terca > 1){
						diaDisc.add("2");
					}else if(quarta > 1){
						diaDisc.add("3");
					}else if(quinta > 1){
						diaDisc.add("4");
					}else if(sexta > 1){
						diaDisc.add("5");
					}else if(sabado > 1){
						diaDisc.add("6");
					}
					i++;
				}
				while(ind < diaDisc.size()){
					turmaTemp += turma + ";";
					horarioTemp += diaDisc.get(ind) + "," + horaDisc.get(2*ind) + "," + horaDisc.get((2*ind)+1) + ";";
					//diaHoraDisc.add(turma + ":" + diaDisc.get(ind) + ":" + horaDisc.get(2*ind) 
					//+ "-" + horaDisc.get((2*ind)+1));
					ind++;
				}
				
			}
		}while(!(compFinal.equals(temp)));
		this.setTurmas(turmaTemp);
		this.setHorario(horarioTemp);
		//System.out.println(turmaTemp);
		//System.out.println(horarioTemp);
	}

	public String getCodDisc() {
		return codDisc;
	}

	public void setCodDisc(String codDisc) {
		this.codDisc = codDisc;
	}

	public String getCampus() {
		return campus;
	}

	public void setCampus(String campus) {
		this.campus = campus;
	}

	public String getTurmas() {
		return turmas;
	}

	public void setTurmas(String turmas) {
		this.turmas = turmas;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getProfDisc() {
		return profDisc;
	}

	public void setProfDisc(String profDisc) {
		this.profDisc = profDisc;
	}

	public String getVagas() {
		return vagas;
	}

	public void setVagas(String vagas) {
		this.vagas = vagas;
	}
}
