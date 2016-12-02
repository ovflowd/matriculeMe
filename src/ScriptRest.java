package com.datamining.rest.api;
import java.util.ArrayList;

import com.google.gson.Gson;

public class ScriptRest implements Runnable{
	
	public String geradorJsonDpto(Worker criador){
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
		//System.out.println(jsonEnviar);
		return jsonEnviar;
	}
	public String geradorJsonCursos(Worker criador){
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
		//System.out.println(jsonEnviar);
		return jsonEnviar;
	}
	public String geradorJsonDisc(Worker criador){
		ArrayList<String> arrayJson = new ArrayList<String>();
		for(int i = 0; i < criador.getDisciplinas().size(); i++){
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
		//System.out.println(jsonEnviar);
		return jsonEnviar;
	}
	public String geradorJsonCurriculo(Worker criador){
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
		//System.out.println(jsonEnviar);
		return jsonEnviar;
	}
	public String geradorJsonTurmas(Worker criador){
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
		//System.out.println(jsonEnviar);
		return jsonEnviar;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		long inic = System.currentTimeMillis();
		System.out.println("Tempo de começo: "+inic);
		ClientRest cliente = new ClientRest();
		Worker worker = new Worker();
		try {
			worker.gerarDepartamentos();
			//geradorJsonDpto(worker);
			cliente.enviarDados(geradorJsonDpto(worker), "http://homol.redes.unb.br/ptr022016-b/mprjct3/departamentos/setAllDeps");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			System.out.println("Erro de conexão Departamentos");
		}
		System.out.println("Tempo Departamentos: "+(System.currentTimeMillis()-inic));
		try {
			worker.gerarHabilitacoes();
			//geradorJsonCursos(worker);
			cliente.enviarDados(geradorJsonCursos(worker), "http://homol.redes.unb.br/ptr022016-b/mprjct3/cursos/setCursos");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Erro de conexão Cursos");
		}
		System.out.println("Tempo Cursos:"+(System.currentTimeMillis()-inic));
		try {
			worker.gerarDisciplinas();
			//geradorJsonDisc(worker);
			cliente.enviarDados(geradorJsonDisc(worker), "http://homol.redes.unb.br/ptr022016-b/mprjct3/disciplinas/setDisciplina");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Erro de conexão Disciplinas");
		}
		System.out.println("Tempo Disciplinas: "+(System.currentTimeMillis()-inic));
		try {
			worker.gerarCurriculos();
			//System.out.println(geradorJsonCurriculo(worker));
			cliente.enviarDados(geradorJsonCurriculo(worker), "http://homol.redes.unb.br/ptr022016-b/mprjct3/curriculos/setAllCurr");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Erro de conexão Currículos");
		}
		System.out.println("Tempo Curriculos: "+(System.currentTimeMillis()-inic));
		try {
			worker.gerarTurmas();
			//System.out.println(geradorJsonTurmas(worker));
			cliente.enviarDados(geradorJsonTurmas(worker), "http://homol.redes.unb.br/ptr022016-b/mprjct3/turmas/setTurmas");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Erro de conexão Turmas");
		}
		System.out.println("Tempo Turmas: "+(System.currentTimeMillis()-inic));
		System.out.println("O tempo final foi de: "+(System.currentTimeMillis() - inic));
	}	
}
