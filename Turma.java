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
		Document doc = Jsoup.connect("https://matriculaweb.unb.br/graduacao/oferta_dados.aspx?cod="+codDisc).get();
		Element table = doc.select("TD").first();
		Iterator<Element> ite = table.select("tr").iterator();
		String compInic = "Turma Vagas Turno Hor�rio/Local Professor Obs";
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
		
		String compInicio = "Turma Vagas Turno Hor�rio/Local Professor Obs";
		String compFinal = "Legenda do campo 'Obs': Disciplina restrita (s� pode ser cursada pelo aluno que possu�-la em seu curr�culo) Turma de hor�rio fixo Turma com reserva exclusiva (s� matricula aluno do curso para o qual est� reservada)";
		//String de compara��o in�cio "Turma Vagas Turno Hor�rio/Local Professor Obs"
		//String de compara��o final "Legenda do campo 'Obs': Disciplina restrita (s� pode ser cursada pelo aluno que possu�-la em seu curr�culo) Turma de hor�rio fixo Turma com reserva exclusiva (s� matricula aluno do curso para o qual est� reservada)"
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
				String tratarDia = tratar.replaceAll(Character.toString((char) 160), " ");
				//System.out.println(tratar);
				StringTokenizer st = new StringTokenizer(tratarDia, " ");
				turma = st.nextToken();
				while(st.hasMoreTokens()){
					divTratarD = st.nextToken();
					//System.out.println(divTratar);
					if(divTratarD.equals("Segunda")){
						//System.out.println(divTratar);
						diaDisc.add("1");
						}
					else if(divTratarD.equals("Ter�a")){
						//System.out.println(divTratar);
						diaDisc.add("2");
						}
					else if(divTratarD.equals("Quarta")){
						//System.out.println(divTratar);
						diaDisc.add("3");
					}
					else if(divTratarD.equals("Quinta")){
						//System.out.println(divTratar);
						diaDisc.add("4");
						}
					else if(divTratarD.equals("Sexta")){
						//System.out.println(divTratar);
						diaDisc.add("5");
						}
					else if(divTratarD.equals("S�bado")){
						//System.out.println(divTratar);
						diaDisc.add("6");
						}
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

	public String getCodTurma() {
		return turmas;
	}

	public void setCodTurma(String codTurma) {
		this.turmas = codTurma;
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
