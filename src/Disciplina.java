package com.datamining.rest.api;

import java.io.IOException;


import java.util.ArrayList;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Disciplina {
	private transient String codDpto;
	private transient String codDisc;
	private int codigo;
	private int departamento;
	private String nomeDisc;
	private int creditos;
	ArrayList<String> preReq = new ArrayList<String>();
	
	public void converter(){
		setDepartamento(Integer.parseInt(getCodDpto()));
		setCodigo(Integer.parseInt(getCodDisc()));
	}
	
	public void extrairCreditos() throws IOException{
		int flag = 0;
		Document doc = null;
		while(flag == 0){
			try{doc = Jsoup.connect("https://matriculaweb.unb.br/graduacao/oferta_dados.aspx?cod="+getCodDisc()+"&dep="+getCodDpto()).get();
			flag = 1;
			}
			catch(Exception e){
				System.out.println("Erro");
			}
		}
		Element table = doc.select("TD").first();
		Iterator<Element> ite = table.select("tr").iterator();
		String compInic = "(Teor-Prat-Ext-Est)";
		while(ite.hasNext()){
			String[] tratar = ite.next().text().split(" ");
			for(int i = 0; i < tratar.length; i++){
				if(tratar[i].equals(compInic)){
					String[] cred = tratar[i+1].split("-");
					creditos = Integer.parseInt(cred[0]) + Integer.parseInt(cred[1]);
				}
			}
		}
		//System.out.println(creditos);
	}
	
	public void extrairPreReq() throws IOException{
		int flag = 0;
		Document doc = null;
		while(flag == 0){
			try{doc = Jsoup.connect("https://matriculaweb.unb.br/graduacao/disciplina.aspx?cod="+getCodDisc()).get();
			flag = 1;
			}
			catch(Exception e){
				System.out.println("Erro");
			}
		}
		Element table = doc.select("TD").first();
		Iterator<Element> ite = table.select("tr").iterator();
		String compInic = "Vigência:";
		while(ite.hasNext()){
			if(compInic.equals(ite.next().text().split(" ")[0])){
				String tratar = ite.next().text();
				String[] parte1 = tratar.split(":");
				String[] parte2 = parte1[1].split(" OU ");
				for(int i = 0; i < parte2.length; i++){
					String[] divide = parte2[i].split("-");
					String construir = "";
					for(int j = 0; j < divide.length; j++){
						String[] divide2 = divide[j].split(" ");
						if(j != 0){
							if( j == divide.length-1){
								construir += divide2[0];
							}
							else{
								construir += divide2[0] + "+";
							}
						}
					}
					if(!construir.equals("requisitos")){
						preReq.add(construir);
					}
					//System.out.println(preReq.get(i));
				}
			}
		}
	}
	
	public String getCodDpto() {
		return codDpto;
	}

	public void setCodDpto(String codDpto) {
		this.codDpto = codDpto;
	}

	public String getCodDisc() {
		return codDisc;
	}

	public void setCodDisc(String codDisc) {
		this.codDisc = codDisc;
	}

	public String getNomeDisc() {
		return nomeDisc;
	}

	public void setNomeDisc(String nomeDisc) {
		this.nomeDisc = nomeDisc;
	}

	public int getCreditos() {
		return creditos;
	}

	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getDepartamento() {
		return departamento;
	}

	public void setDepartamento(int departamento) {
		this.departamento = departamento;
	}
}

