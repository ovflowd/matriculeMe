package com.datamining.rest.api;
import com.google.gson.Gson;

public class EnviarDados {
	String jsonDisc;
	
	public void gerarJson(Object obj){
		Gson parse = new Gson();
		jsonDisc = parse.toJson(obj);
		System.out.println(jsonDisc);
	}
	
	public void conectaServidor(){
		
	}
	
	public void enviaParaServidor(){
		
	}
}
