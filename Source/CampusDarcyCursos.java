package com.datamining.rest.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class CampusDarcyCursos {
	
	ArrayList<String> curso = new ArrayList<String>();
	ArrayList<String> codCurso = new ArrayList<String>();
	ArrayList<String> turno = new ArrayList<String>();
	
	public void extrairCursos() throws IOException{
		for(int k = 1; k < 5; k++){
			int flag = 0;
			Document doc = null;
			while(flag == 0){
				try{doc = Jsoup.connect("https://matriculaweb.unb.br/graduacao/curso_rel.aspx?cod="+k).get();
				flag = 1;
				}
				catch(Exception e){
					System.out.println("Erro");
				}
			}
			Element table = doc.select("TD").first();
			Iterator<Element> ite = table.select("tr").iterator();
			String compInicio = "Modalidade Código Denominação Turno";
			String compFinal = "© 2016 CPD - Centro de Informática UnB - Universidade de Brasília";
			while(ite.hasNext()){
				if(ite.next().text().equals(compInicio)){
					String temp;
					do{
						temp = ite.next().text();
						if(!(temp.equals(compFinal))){
							String[] arrayTemp = temp.split(" ");
							String cursoTemp = "";
							for(int i = 2; i < arrayTemp.length-1; i++){
								cursoTemp += arrayTemp[i] + " ";
							}
							curso.add(cursoTemp);
							codCurso.add(arrayTemp[1]);
							turno.add(arrayTemp[arrayTemp.length-1]);
						}
					}while(!(temp.equals(compFinal)));
				}
			}
		}
	}
}
