package com.datamining.rest.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Curso {
	
	public ArrayList<String> extrairHab(String codCurso) throws IOException{
		Document doc = Jsoup.connect("https://matriculaweb.unb.br/graduacao/curso_dados.aspx?cod="+codCurso).get();
		Element table = doc.select("TD").first();
		Iterator<Element> ite = table.select("tr").iterator();
		//Rodar 5x o ite.next
		//Rodar 8x para pegar próxima habilitação
		int i = 0;
		ArrayList<String> arrayTempCod = new ArrayList<String>();
		ArrayList<String> codHabil = new ArrayList<String>();
		while(ite.hasNext()){
			String temp = ite.next().text();
			i++;
			if(i > 5){
				arrayTempCod.add((temp.split(" "))[0]);
			}
		}
		
		int numCurric = arrayTempCod.size()/9;
		for(int j = 0; j < numCurric; j++){
			codHabil.add(arrayTempCod.get(j*9));
			//System.out.println(codCurric.get(j));
		}
		return codHabil;
	}
}
