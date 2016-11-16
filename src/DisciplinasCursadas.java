package com.datamining.rest.api;
import com.datamining.rest.models.*;
import java.util.ArrayList;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class DisciplinasCursadas {
	private Aluno aluno;
	private Oferta oferta;
	private String mencao;
	
	/*
	public void extrairHistorico(String HtmlHistorico){
		//Retirar quebra de linhas, troca " por \"
		Document doc = Jsoup.parse(HtmlHistorico);
		Iterator<Element> ite = doc.select("TR").iterator();
		String inic = "Período:";
		String fim = "Total de Créditos";
		while(ite.hasNext()){
			if(ite.next().text().split(" ")[0].equals(inic)){
				String tratar;
				do{
					tratar = ite.next().text();
					if(!(tratar.split(" ")[0].equals(inic))&&(!(tratar.equals(fim))) ){
						for(int i = 0; i < tratar.split(" ").length; i++){
							if(tratar.split(" ")[i].equals("SS")){
								mencao.add(tratar.split(" ")[i]);
							}else if(tratar.split(" ")[i].equals("MS")){
								mencao.add(tratar.split(" ")[i]);
							}else if(tratar.split(" ")[i].equals("MM")){
								mencao.add(tratar.split(" ")[i]);
							}else if(tratar.split(" ")[i].equals("MI")){
								mencao.add(tratar.split(" ")[i]);
							}else if(tratar.split(" ")[i].equals("II")){
								mencao.add(tratar.split(" ")[i]);
							}else if(tratar.split(" ")[i].equals("SR")){
								mencao.add(tratar.split(" ")[i]);
							}
						}
						codDisc.add(tratar.split(" ")[0].split("-")[1]);
					}
				}while(!(tratar.equals(fim)));
			}
		}
	}
	*/
	

	public String getMencao() {
		return mencao;
	}

	public void setMencao(String mencao) {
		this.mencao = mencao;
	}

	public Oferta getOferta() {
		return oferta;
	}

	public void setOferta(Oferta oferta) {
		this.oferta = oferta;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
}
