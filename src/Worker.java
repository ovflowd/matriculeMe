package com.datamining.rest.api;
import com.datamining.rest.models.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Worker {
	private ArrayList<Departamento> departamentos = new ArrayList<Departamento>();
	private ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>();
	private ArrayList<TurmaEnviar> turmas = new ArrayList<TurmaEnviar>();
	private ArrayList<CurriculoEnviar> curriculos = new ArrayList<CurriculoEnviar>();
	private ArrayList<Habilitacao> habilitacoes = new ArrayList<Habilitacao>();
	private ArrayList<DisciplinasCursadas> disciplinasCursadas = new ArrayList<DisciplinasCursadas>();
	private ArrayList<Oferta> ofertas = new ArrayList<Oferta>();
	
	public void gerarCurriculos() throws IOException{
		CriadorFluxo gerador;
		
		if(habilitacoes.size() == 0){
			gerarHabilitacoes();
		}
		
		for(int i = 0; i < habilitacoes.size(); i++){
			gerador = new CriadorFluxo();
			gerador.extrairFluxo(habilitacoes.get(i).getCodHab());
			gerador.extrairOpt(habilitacoes.get(i).getCodHab());
			Curriculo temp;
			for(int j = 0; j < gerador.fluxo.size(); j++){
				temp = new Curriculo();
				temp.setCodCurso(habilitacoes.get(i).getCodHab());
				//System.out.println(gerador.fluxo.get(j)[2]);
				System.out.println(gerador.fluxo.get(j)[1]);
				if(gerador.fluxo.get(j)[1].equals("NÃO CADASTRADA ")){
					System.out.println("Entrou");
					temp.setCodDisc(gerador.fluxo.get(j)[2]);
					temp.setSemestre1(gerador.fluxo.get(j)[0]);
					temp.converterSemDpto();
				}else{
					temp.setCodDisc(gerador.fluxo.get(j)[2]);
					temp.setSemestre1(gerador.fluxo.get(j)[0]);
					temp.converter();
				}
				//System.out.println(temp.getSiglaDepto());
				CurriculoEnviar tempEnviar = new CurriculoEnviar(habilitacoes.get(i), gerador.fluxo.get(j)[2],
						gerador.fluxo.get(j)[0], temp.getSiglaDepto());
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
			temp.setSigla(ofertaPrimeiraExt.siglaDepto.get(i));
			//temp.extrairDiscOfertadas();
			departamentos.add(temp);
		}	
		
	}
	
	public void gerarDisciplinas() throws IOException{
		if(departamentos.size() == 0){
			gerarDepartamentos();
		}
		
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
				while(!(ite2.next().text()).equals("Código Denominação Ementa"));
				String stringMW = ite2.next().text();
				String comp ="© 2016 CPD - Centro de Informática UnB - Universidade de Brasília";
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
					temp.converter();
					temp.converterPreReq();
					disciplinas.add(temp);
				}
			//System.out.print(stringMW);
			}
		}
	}

	public void gerarTurmas() throws IOException{
		if(disciplinas.size() == 0){
			gerarDisciplinas();
		}
		
		for(int i = 0; i < disciplinas.size(); i++){
			if(!(disciplinas.get(i).getCodDisc().equals("135097")||disciplinas.get(i).getCodDisc().equals("156949"))){
			Turma temp = new Turma();
			temp.setCodDisc(disciplinas.get(i).getCodDisc());
			temp.extrairTurmas(disciplinas.get(i).getCodDpto(), disciplinas.get(i).getCodDisc());
			temp.extrairProfessor();
			temp.extrairVagas();
			//conversão
			Turmas temp1 = null;
			String[] divTemp = temp.getTurmas().split(";");
			String[] divTemp2 = temp.getHorario().split(";");
			String[] divVagas = temp.getVagas().split(";");
			String[] divProf = temp.getProfDisc().split(";");
			//System.out.println(divTemp[0]);
			//System.out.println(divTemp2[0]);
			//System.out.println(divVagas.length);
			String hora = "";
			int controle = 0;
			int valorComp = (disciplinas.get(i).getCreditos()/2);
			//System.out.println(valorComp);
			//int tamanhoAnt = turmas.size();
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
						//Oferta ofertaTemp = new Oferta(disciplinas.get(i), "2000/0");
						temp1 = new Turmas(divTemp[j], divProf[controle], temp.getCampus(), hora, divVagas[controle]);
						hora = "";
						TurmaEnviar tempEnviar = new TurmaEnviar(temp1.getCodigo(), temp1.getHorario(), divProf[controle],
								temp1.getVagas(), temp1.getCampus());
						Oferta ofertaTemp = new Oferta(disciplinas.get(i),"2016/2");
						tempEnviar.setOferta(ofertaTemp);
						//turmas.add(tempEnviar);
						turmas.add(tempEnviar);
						//disciplinas.get(i).turmas.add(tempEnviar);
						//System.out.println(ofertas.get(ofertas.size()-1).getDisciplina().getNomeDisc());
					}
					controle++;
					j += valorComp;
					}
					/*
					for(int w = tamanhoAnt; w < turmas.size(); w++){
						//turmas.get(w).setVagas(Integer.parseInt(divVagas[w]));
						System.out.println(turmas.get(w).getCodigo() + " " + turmas.get(w).getHorario() + " " + turmas.get(w).getVagas());
					}
					*/
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
		String inic = "Período:";
		String fim = "Total de Créditos";
		while(ite.hasNext()){
			String comparar = ite.next().text();
			if(comparar.split(" ")[0].equals(inic)){
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
								}else if(tratar.split(" ")[i].equals("CC")){
									mencao.add(tratar.split(" ")[i]);
								}else if(tratar.split(" ")[i].equals("TR")){
									mencao.add(tratar.split(" ")[i]);
								}
							}
							codDisc.add(tratar.split(" ")[0].split("-")[1]);
						}
					}while(!(tratar.equals(fim)));
				}
			}
		for(int i = 0; i < mencao.size(); i++){
			DisciplinasCursadas temp = new DisciplinasCursadas();
			Mencao tempMencao = new Mencao();
			tempMencao.setCodigo(mencao.get(i));
			temp.setMencao(tempMencao);
			Disciplina discTemp = new Disciplina();
			discTemp.setCodDisc(codDisc.get(i));
			discTemp.extrairPreReq();
			discTemp.extrairCreditos();
			discTemp.converter();
			discTemp.converterPreReq();
			Oferta oferta = new Oferta(discTemp, "2016/2");
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


	public ArrayList<TurmaEnviar> getTurmas() {
		return turmas;
	}

	public void setTurmas(ArrayList<TurmaEnviar> turmas) {
		this.turmas = turmas;
	}

	public ArrayList<CurriculoEnviar> getCurriculos() {
		return curriculos;
	}

	public void setCurriculos(ArrayList<CurriculoEnviar> curriculos) {
		this.curriculos = curriculos;
	}
	
	public ArrayList<Oferta> getOfertas() {
		return ofertas;
	}

	public void setOfertas(ArrayList<Oferta> ofertas) {
		this.ofertas = ofertas;
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
