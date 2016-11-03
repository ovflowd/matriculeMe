package com.datamining.rest.api;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.ArrayList;
import com.google.gson.Gson; 

@Path("/datamining") 

public class DataMining {

	@GET
	@Path("/departamento")
	@Produces("application/textplain")
	public String getDepartamento() throws IOException{
		Worker criador = new Worker();
		criador.gerarDepartamentos();
		ArrayList<String> arrayJson = new ArrayList<String>();
		for(int i = 0; i < criador.getDepartamentos().size(); i++){
			Gson json = new Gson();
			String guardar = json.toJson(criador.getDepartamentos().get(i));
			arrayJson.add(guardar);
		}
		String jsonEnviar = "[";
		for(int i = 0; i < arrayJson.size(); i++){
			if(i < arrayJson.size()-1){
				jsonEnviar += arrayJson.get(i) + ",";
			} else {
				jsonEnviar += arrayJson.get(i);
			}
		}
		jsonEnviar += "]";
		//System.out.print("\\");
		//guardar2 = guardar2.replaceAll("\\", "");
		return jsonEnviar;
	}
	@GET
	@Path("/disciplina")
	@Produces("application/textplain")
	public String getDiscipina() throws IOException{
		Worker criador = new Worker();
		criador.gerarDisciplinas();
		ArrayList<String> arrayJson = new ArrayList<String>();
		for(int i = 0; i < criador.getDisciplinas().size(); i++){
			System.out.println(i);
			Gson json = new Gson();
			String guardar = json.toJson(criador.getDisciplinas().get(i));
			arrayJson.add(guardar);
		}
		String jsonEnviar = "[";
		for(int i = 0; i < arrayJson.size(); i++){
			if(i < arrayJson.size()-1){
				jsonEnviar += arrayJson.get(i) + ",";
			} else {
				jsonEnviar += arrayJson.get(i);
			}
		}
		jsonEnviar += "]";
		//System.out.print("\\");
		//guardar2 = guardar2.replaceAll("\\", "");
		System.out.println(jsonEnviar);
		return jsonEnviar;
	}
	@GET
	@Path("/curso")
	@Produces("application/textplain")
	public String getCurso() throws IOException{
		Worker criador = new Worker();
		criador.gerarHabilitacoes();
		ArrayList<String> arrayJson = new ArrayList<String>();
		for(int i = 0; i < criador.getHabilitacoes().size(); i++){
			Gson json = new Gson();
			String guardar = json.toJson(criador.getHabilitacoes().get(i));
			arrayJson.add(guardar);
		}
		String jsonEnviar = "[";
		for(int i = 0; i < arrayJson.size(); i++){
			if(i < arrayJson.size()-1){
				jsonEnviar += arrayJson.get(i) + ",";
			} else {
				jsonEnviar += arrayJson.get(i);
			}
		}
		jsonEnviar += "]";
		//System.out.print("\\");
		//guardar2 = guardar2.replaceAll("\\", "");
		System.out.println(jsonEnviar);
		return jsonEnviar;
	}
	@GET
	@Path("/curriculo")
	@Produces("application/textplain")
	public String getCurriculo() throws IOException{
		Worker criador = new Worker();
		criador.gerarCurriculos();
		ArrayList<String> arrayJson = new ArrayList<String>();
		for(int i = 0; i < criador.getCurriculos().size(); i++){
			Gson json = new Gson();
			String guardar = json.toJson(criador.getCurriculos().get(i));
			arrayJson.add(guardar);
		}
		String jsonEnviar = "[";
		for(int i = 0; i < arrayJson.size(); i++){
			if(i < arrayJson.size()-1){
				jsonEnviar += arrayJson.get(i) + ",";
			} else {
				jsonEnviar += arrayJson.get(i);
			}
		}
		jsonEnviar += "]";
		//System.out.print("\\");
		//guardar2 = guardar2.replaceAll("\\", "");
		System.out.println(jsonEnviar);
		return jsonEnviar;
	}
	/*
	@GET
	@Path("/oferta")
	@Produces("Application/json")
	public String getOferta(){
		return Arraylist<OFERTA>;
	}
	*/
	@GET
	@Path("/turma")
	@Produces("application/textplain")
	public String getTurma() throws IOException{
		Worker criador = new Worker();
		criador.gerarTurmas();
		ArrayList<String> arrayJson = new ArrayList<String>();
		for(int i = 0; i < criador.getTurmas().size(); i++){
			Gson json = new Gson();
			String guardar = json.toJson(criador.getTurmas().get(i));
			arrayJson.add(guardar);
		}
		String jsonEnviar = "[";
		for(int i = 0; i < arrayJson.size(); i++){
			if(i < arrayJson.size()-1){
				jsonEnviar += arrayJson.get(i) + ",";
			} else {
				jsonEnviar += arrayJson.get(i);
			}
		}
		jsonEnviar += "]";
		//System.out.print("\\");
		//guardar2 = guardar2.replaceAll("\\", "");
		System.out.println(jsonEnviar);
		return jsonEnviar;
	}
	@POST
	@Path("/historico")
	@Consumes("application/textplain")
	public String Historico(String htmlHist){
		Worker criador = new Worker();
		criador.gerarDisciplinasCursadas(htmlHist);
		ArrayList<String> arrayJson = new ArrayList<String>();
		for(int i = 0; i < criador.getDisciplinasCursadas().size(); i++){
			Gson json = new Gson();
			String guardar = json.toJson(criador.getDisciplinasCursadas().get(i));
			arrayJson.add(guardar);
		}
		String jsonEnviar = "[";
		for(int i = 0; i < arrayJson.size(); i++){
			if(i < arrayJson.size()-1){
				jsonEnviar += arrayJson.get(i) + ",";
			} else {
				jsonEnviar += arrayJson.get(i);
			}
		}
		jsonEnviar += "]";
		//System.out.print("\\");
		//guardar2 = guardar2.replaceAll("\\", "");
		//ClientRest cliente = new ClientRest();
		//cliente.enviarDados(jsonEnviar);
		//System.out.println(jsonEnviar);
		return jsonEnviar;
	}
} 

