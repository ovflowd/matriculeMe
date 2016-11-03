package com.datamining.rest.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class CriadorFluxo {
	ArrayList<String[]> fluxo = new ArrayList<String[]>();
	
	public void extrairOpt(String codHab) throws IOException{
		int flag = 0;
		Document doc = null;
		while(flag == 0){
			try{doc = Jsoup.connect("https://matriculaweb.unb.br/graduacao/curriculo.aspx?cod="+codHab).get();
			flag = 1;
			}
			catch(Exception e){
				System.out.println("Erro");
			}
		}
		Element table = doc.select("TD").first();
		Iterator<Element> ite = table.select("tr").iterator();
		String inic = "DISCIPLINASOPTATIVAS";
		String fim = "© 2016 CPD - Centro de Informática UnB - Universidade de Brasília";
		String codTemp;
		String nomeTemp = "";
		while(ite.hasNext()){
			String tratar = ite.next().text();
			if((tratar.split(" ")[0] + tratar.split(" ")[1]).equals(inic)){
				ite.next();
				String checkTemp = ite.next().text();
				while(!(checkTemp.equals(fim))){
					codTemp = checkTemp.split(" ")[0];
					for(int i = 2; i < checkTemp.split(" ").length - 5; i++){
						nomeTemp += checkTemp.split(" ")[i] + " ";
					}
					String[] guardar = {"0", nomeTemp, codTemp};
					fluxo.add(guardar);
					nomeTemp = "";
					checkTemp = ite.next().text();
				}
			}
		}
		/*
		for(int i = 0; i < fluxo.size(); i++){
			System.out.println(fluxo.get(i)[0] + " - " + fluxo.get(i)[1] + " - " + fluxo.get(i)[2]);
		}
		*/
	}
	
	public void extrairFluxo(String codHab) throws IOException{
		int flag = 0;
		Document doc = null;
		while(flag == 0){
			try{doc = Jsoup.connect("https://matriculaweb.unb.br/graduacao/fluxo.aspx?cod="+codHab).get();
			flag = 1;
			}
			catch(Exception e){
				System.out.println("Erro");
			}
		}
		Element table = doc.select("TD").first();
		Iterator<Element> ite = table.select("tr").iterator();
		//Rodar 5x o ite.next
		//Rodar 8x para pegar próxima habilitação
		String checarInic = "PERÍODODEREFERÊNCIA";
		String checarFinal = "© 2016 CPD - Centro de Informática UnB - Universidade de Brasília";
		String periodoTemp = "";
		String disciplinaTemp = "";
		String disciplinaTempCod;
		while(ite.hasNext()){
			String tempCheck = ite.next().text();
			if((tempCheck.split(" ")[0] + tempCheck.split(" ")[1] + tempCheck.split(" ")[2]).equals(checarInic)){
				tempCheck = ite.next().text();
				while(!(tempCheck.equals(checarFinal))){
					if(tempCheck.split(" ")[0].equals("PERÍODO:")){
						periodoTemp = tempCheck.split(" ")[1].substring(0, 1);
					} else if(!(tempCheck.equals("Pr. Tipo Cód. Nome Créditos"))){
						//System.out.println(tempCheck);
						String[] tempDisc = tempCheck.split(" ");
						disciplinaTempCod = tempDisc[4];
						for(int i = 5; i < tempDisc.length - 7; i++){
							disciplinaTemp += tempDisc[i] + " ";
						}
						String[] fluxoTemp = {periodoTemp, disciplinaTemp, disciplinaTempCod};
						fluxo.add(fluxoTemp);
						disciplinaTemp = "";
					}
					tempCheck = ite.next().text();
				}
			}
		}
		/*
		for(int i = 0; i < fluxo.size(); i++){
			System.out.println(fluxo.get(i)[0] + " - " + fluxo.get(i)[1] + " - " + fluxo.get(i)[2]);
		}
		*/
		
	}
}
