package com.datamining.rest.api;

import java.io.IOException;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Habilitacao {
	private String nome;
	private transient String codHab;
	private int codigo;
	private int creditosLimite;
	
	public void converter(){
		setCodigo(Integer.parseInt(getCodHab()));
	}
	
	public void extrairCredLimite() throws IOException{
		int flag = 0;
		Document doc = null;
		while(flag == 0){
			try{doc = Jsoup.connect("https://matriculaweb.unb.br/graduacao/curriculo.aspx?cod="+getCodHab()).get();
			flag = 1;
			}
			catch(Exception e){
				System.out.println("Erro");
			}
		}
		Element table = doc.select("TD").first();
		Iterator<Element> ite = table.select("tr").iterator();
		String compInic = "Créditosporperíodo:";
		while(ite.hasNext()){
			String[] tratar = ite.next().text().split(" ");
			if(tratar.length > 2){
				if((tratar[0] + tratar[1] + tratar[2]).equals(compInic)){
					creditosLimite = Integer.parseInt(tratar[tratar.length-1]);
					return;
				}
			}
		}
	}
	
	public String getNomeCurso() {
		return nome;
	}
	public void setNomeCurso(String nomeCurso) {
		this.nome = nomeCurso;
	}
	public String getCodHab() {
		return codHab;
	}
	public void setCodHab(String codHab) {
		this.codHab = codHab;
	}
	public int getCredLimite() {
		return creditosLimite;
	}
	public void setCredLimite(int credLimite) {
		this.creditosLimite = credLimite;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
}
