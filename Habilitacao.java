package com.datamining.rest.api;

import java.io.IOException;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Habilitacao {
	private String nomeCurso;
	private String codHab;
	private int credLimite;
	
	public void extrairCredLimite() throws IOException{
		Document doc = Jsoup.connect("https://matriculaweb.unb.br/graduacao/curriculo.aspx?cod="+getCodHab()).get();
		Element table = doc.select("TD").first();
		Iterator<Element> ite = table.select("tr").iterator();
		String compInic = "Cr�ditosporper�odo:";
		while(ite.hasNext()){
			String[] tratar = ite.next().text().split(" ");
			if(tratar.length > 2){
				if((tratar[0] + tratar[1] + tratar[2]).equals(compInic)){
					credLimite = Integer.parseInt(tratar[tratar.length-1]);
					return;
				}
			}
		}
	}
	
	public String getNomeCurso() {
		return nomeCurso;
	}
	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}
	public String getCodHab() {
		return codHab;
	}
	public void setCodHab(String codHab) {
		this.codHab = codHab;
	}
	public int getCredLimite() {
		return credLimite;
	}
	public void setCredLimite(int credLimite) {
		this.credLimite = credLimite;
	}
	
}
