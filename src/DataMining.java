package com.datamining.rest.api;
import java.io.IOException;



import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import java.util.ArrayList;
import com.google.gson.Gson;

@Path("/datamining") 

public class DataMining {
	@GET
	@Path("/script")
	@Produces("application/textplain")
	public String getBD(){
		ScriptRest script = new ScriptRest();
		Thread execute = new Thread(script);
		execute.start();
		return "OK";
	}
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
		System.out.println(jsonEnviar);
		return jsonEnviar;
	}
	@GET
	@Path("/disciplina")
	@Produces("application/textplain")
	public String getDisciplina() throws IOException{
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
	@GET
	@Path("/oferta")
	@Produces("application/textplain")
	public String getOferta() throws IOException{
		Worker criador = new Worker();
		criador.gerarTurmas();
		ArrayList<String> arrayJson = new ArrayList<String>();
		for(int i = 0; i < criador.getOfertas().size(); i++){
			Gson json = new Gson();
			String guardar = json.toJson(criador.getOfertas().get(i));
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
	public String postHistorico(String htmlReceiver){
		Worker criador = new Worker();
		HTMLs html = (new Gson()).fromJson(htmlReceiver, HTMLs.class);
		criador.gerarDisciplinasCursadas(html.getHtmlHist());
		Aluno aluno = new Aluno(html.getHtmlQR());
		aluno.pegarMatriCursoIraSemestre();
		aluno.setDisciplinasCursadas(criador.getDisciplinasCursadas());
		aluno.pegarEQ();
		Gson transformar = new Gson();
		String jsonEnviar = transformar.toJson(aluno);
		//System.out.println();
		ClientRest cliente = new ClientRest();
		cliente.enviarDados(jsonEnviar, "http://homol.redes.unb.br/ptr022016-b/mprjct3/alunos/updateAluno/disciplinasCursadas");
		System.out.println(jsonEnviar);
		return "Enviado.";
	}
} 

