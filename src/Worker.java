package com.datamining.rest.api;
import com.datamining.rest.models.*;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.ws.http.HTTPException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Worker {
	private ArrayList<Departamento> departamentos = new ArrayList<Departamento>();
	private ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>();
	private ArrayList<Turmas> turmas = new ArrayList<Turmas>();
	private ArrayList<CurriculoEnviar> curriculos = new ArrayList<CurriculoEnviar>();
	private ArrayList<Habilitacao> habilitacoes = new ArrayList<Habilitacao>();
	private ArrayList<DisciplinasCursadas> disciplinasCursadas = new ArrayList<DisciplinasCursadas>();
	
	public void gerarCurriculos() throws IOException{
		CriadorFluxo gerador;
		gerarHabilitacoes();
		for(int i = 0; i < habilitacoes.size(); i++){
			gerador = new CriadorFluxo();
			gerador.extrairFluxo(habilitacoes.get(i).getCodHab());
			gerador.extrairOpt(habilitacoes.get(i).getCodHab());
			Curriculo temp;
			for(int j = 0; j < gerador.fluxo.size(); j++){
				temp = new Curriculo();
				temp.setCodCurso(habilitacoes.get(i).getCodHab());
				//System.out.println(gerador.fluxo.get(j)[2]);
				temp.setCodDisc(gerador.fluxo.get(j)[2]);
				temp.setSemestre1(gerador.fluxo.get(j)[0]);
				temp.converter();
				CurriculoEnviar tempEnviar = new CurriculoEnviar(habilitacoes.get(i), gerador.fluxo.get(j)[2], gerador.fluxo.get(j)[0]);
				curriculos.add(tempEnviar);
			}
		}
		
	}
	
	public void gerarHabilitacoes() throws IOException{
		CampusDarcyCursos cursos = new CampusDarcyCursos();
		cursos.extrairCursos();
		Curso extrator = new Curso();
		ArrayList<String> codHab = new ArrayList<String>();
		for(int i = 0; i < cursos.codCurso.size(); i++){
			Habilitacao temp; 
			codHab = extrator.extrairHab(cursos.codCurso.get(i));
			for(int j = 0; j < codHab.size(); j++){
				//System.out.println(cursos.curso.get(i));
				temp = new Habilitacao();
				temp.setNomeCurso(cursos.curso.get(i));
				temp.setCodHab(codHab.get(j));
				temp.extrairCredLimite();
				temp.converter();
				habilitacoes.add(temp);
			}
		}
	}
	
	public void gerarDepartamentos() throws IOException{
		
		CampusDarcyOferta ofertaPrimeiraExt = new CampusDarcyOferta();
		ofertaPrimeiraExt.extrairDepartamentos();
		for(int i = 0; i < ofertaPrimeiraExt.codDepto.size(); i++){
			Departamento temp = new Departamento();
			temp.setNome(ofertaPrimeiraExt.nomeDepto.get(i));
			temp.setCod(ofertaPrimeiraExt.codDepto.get(i));
			temp.setCodigo(Integer.parseInt(ofertaPrimeiraExt.codDepto.get(i)));
			//temp.extrairDiscOfertadas();
			departamentos.add(temp);
		}	
		
	}
	
	public void gerarDisciplinas() throws IOException{
		
		gerarDepartamentos();
		
		ArrayList<String> discOfertadas = new ArrayList<String>();
		ArrayList<String> codDiscOfertadas = new ArrayList<String>();
		
		for(int i = 0; i < departamentos.size(); i++){
			if(!(departamentos.get(i).getCod().equals("377")||departamentos.get(i).getCod().equals("372"))){
				int flag = 0;
				Document doc = null;
				while(flag == 0){
					try{doc = Jsoup.connect("https://matriculaweb.unb.br/graduacao/oferta_dis.aspx?cod="+departamentos.get(i).getCod()).get();
					flag = 1;
					}
					catch(Exception e){
						System.out.println("Erro");
					}
				}
				Element table = doc.select("TD").first();
				Iterator<Element> ite2 = table.select("tr").iterator();
				while(!(ite2.next().text()).equals("C�digo Denomina��o Ementa"));
				String stringMW = ite2.next().text();
				String comp ="� 2016 CPD - Centro de Inform�tica UnB - Universidade de Bras�lia";
				int tamanhoAnterior = codDiscOfertadas.size();
				while(!(stringMW.equals(comp))){
					String[] fraseSeparada = stringMW.split(" ");
					codDiscOfertadas.add(fraseSeparada[0]);
					String subfrase = "";
					for(int j = 1; j < fraseSeparada.length; j++){
						subfrase += fraseSeparada[j] + " ";
					}
					discOfertadas.add(subfrase);
					stringMW = ite2.next().text();
				} 
				System.out.println(departamentos.get(i).getNome());
				Disciplina temp;
				for(int j = tamanhoAnterior; j < codDiscOfertadas.size(); j++){
					temp = new Disciplina();
					temp.setCodDisc(codDiscOfertadas.get(j));
					temp.setNomeDisc(discOfertadas.get(j));
					temp.setCodDpto(departamentos.get(i).getCod());
					temp.setDepartamento(departamentos.get(i));
					temp.extrairPreReq();
					temp.extrairCreditos();
					temp.converterPreReq();
					temp.converter();
					disciplinas.add(temp);
				}
			//System.out.print(stringMW);
			}
		}
	}

	public void gerarTurmas() throws IOException{
		
		gerarDisciplinas();
		
		for(int i = 0; i < disciplinas.size(); i++){
			if(!disciplinas.get(i).getCodDisc().equals("135097")){
			Turma temp = new Turma();
			temp.setCodDisc(disciplinas.get(i).getCodDisc());
			temp.extrairTurmas(disciplinas.get(i).getCodDpto(), disciplinas.get(i).getCodDisc());
			//professor
			temp.extrairVagas();
			//convers�o
			Turmas temp1 = null;
			String[] divTemp = temp.getTurmas().split(";");
			String[] divTemp2 = temp.getHorario().split(";");
			String[] divVagas = temp.getVagas().split(";");
			//System.out.println(divTemp[0]);
			//System.out.println(divTemp2[0]);
			//System.out.println(divVagas.length);
			String hora = "";
			int controle = 0;
			int valorComp = (disciplinas.get(i).getCreditos()/2);
			//System.out.println(valorComp);
			int tamanhoAnt = turmas.size();
			if(valorComp > 0){
				for(int j = 0; j < divTemp.length; ){
					System.out.println(disciplinas.get(i).getNomeDisc());
					for(int k = 0; k < valorComp; k++){
						if(divTemp2.length > k+j){
							//System.out.println(divTemp[j]+" "+ divTemp2[j+k]);
							//System.out.println(divTemp2[j]);
							hora += divTemp2[j + k] + ";";
						}
					}
					if(controle < divVagas.length){
						Oferta ofertaTemp = new Oferta(disciplinas.get(i), "2000/0");
						temp1 = new Turmas(divTemp[j], temp.getProfDisc(), temp.getCampus(), hora, ofertaTemp, divVagas[controle]);
						hora = "";
						turmas.add(temp1);
						//System.out.println(turmas.size());
					}
					controle++;
					j += valorComp;
					}
					for(int w = tamanhoAnt; w < turmas.size(); w++){
						//turmas.get(w).setVagas(Integer.parseInt(divVagas[w]));
						System.out.println(turmas.get(w).getCodigo() + " " + turmas.get(w).getHorario() + " " + turmas.get(w).getVagas());
					}
				}
			}
		}
	}
	
	public void gerarDisciplinasCursadas(String htmlHist){
		ArrayList<String> codDisc = new ArrayList<String>();
		ArrayList<String> mencao = new ArrayList<String>();
		//Retirar quebra de linhas, troca " por \"
		Document doc = Jsoup.parse(htmlHist);
		Iterator<Element> ite = doc.select("TR").iterator();
		String inic = "Per�odo:";
		String fim = "Total de Cr�ditos";
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
		for(int i = 0; i < codDisc.size(); i++){
			DisciplinasCursadas temp = new DisciplinasCursadas();
			temp.setMencao(mencao.get(i));
			//System.out.println(codDisc.get(i));
			Disciplina discTemp = new Disciplina();
			discTemp.setCodDisc(codDisc.get(i));
			discTemp.extrairPreReq();
			discTemp.extrairCreditos();
			discTemp.converter();
			discTemp.converterPreReq();
			Aluno aluno = new Aluno();
			aluno.setMatricula(11111111);
			temp.setAluno(aluno);
			Oferta oferta = new Oferta(discTemp, "2000/0");
			temp.setOferta(oferta);
			disciplinasCursadas.add(temp);
		}
	}

	public ArrayList<Departamento> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(ArrayList<Departamento> departamentos) {
		this.departamentos = departamentos;
	}

	public ArrayList<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(ArrayList<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}


	public ArrayList<Turmas> getTurmas() {
		return turmas;
	}

	public void setTurmas(ArrayList<Turmas> turmas) {
		this.turmas = turmas;
	}

	public ArrayList<CurriculoEnviar> getCurriculos() {
		return curriculos;
	}

	public void setCurriculos(ArrayList<CurriculoEnviar> curriculos) {
		this.curriculos = curriculos;
	}

	public ArrayList<Habilitacao> getHabilitacoes() {
		return habilitacoes;
	}

	public void setHabilitacoes(ArrayList<Habilitacao> habilitacoes) {
		this.habilitacoes = habilitacoes;
	}

	public ArrayList<DisciplinasCursadas> getDisciplinasCursadas() {
		return disciplinasCursadas;
	}

	public void setDisciplinasCursadas(ArrayList<DisciplinasCursadas> disciplinasCursadas) {
		this.disciplinasCursadas = disciplinasCursadas;
	}
}