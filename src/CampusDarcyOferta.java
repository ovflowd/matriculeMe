package com.datamining.rest.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class CampusDarcyOferta {
	ArrayList<String> nomeDepto = new ArrayList<String>();
	ArrayList<String> siglaDepto = new ArrayList<String>();
	ArrayList<String> codDepto = new ArrayList<String>();
	
	public void extrairDepartamentos() throws IOException{
		int flag = 0;
		Document doc = null;
		while(flag == 0){
			try{doc = Jsoup.connect("https://matriculaweb.unb.br/graduacao/oferta_dep.aspx?cod=1").get();
			flag = 1;
			}
			catch(Exception e){
				System.out.println("Erro");
			}
		}
		Element table = doc.select("TD").first();
		Iterator<Element> ite = table.select("tr").iterator();
		String inic = "Código Sigla Denominação";
		String fim = "© 2016 CPD - Centro de Informática UnB - Universidade de Brasília";
		while(ite.hasNext()){
			if(ite.next().text().equals(inic)){
				String tratar = ite.next().text();
				while(!(tratar.equals(fim))){
					//System.out.println(tratar);
					String[] fraseSeparada = tratar.split(" ");
					codDepto.add(fraseSeparada[0]);
					siglaDepto.add(fraseSeparada[1]);
					String subfrase = "";
					for(int j = 2; j < fraseSeparada.length; j++){
						subfrase += fraseSeparada[j] + " ";
					}
					tratar = ite.next().text();
					nomeDepto.add(subfrase);
					//System.out.println(nomeDepto.size()-1 +" "+ nomeDepto.get(nomeDepto.size()-1));
				}
			}
		}
	}
}
