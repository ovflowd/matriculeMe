package com.datamining.rest.api;

import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Curriculo {
	private transient String codCurso;
	private transient String codDisc;
	private transient String semestre1; 
	private transient String siglaDepto;
	private int curso;
	private int disciplina;
	private int semestre;
	
	public void converter(){
		setCurso(Integer.parseInt(codCurso));
		setDisciplina(Integer.parseInt(codDisc));
		setSemestre(Integer.parseInt(semestre1));
		int flag = 0;
		Document doc = null;
		while(flag == 0){
			try{doc = Jsoup.connect("https://matriculaweb.unb.br/graduacao/disciplina.aspx?cod="+codDisc).get();
			flag = 1;
			}
			catch(Exception e){
				System.out.println("Erro");
			}
		}
		Element table = doc.select("TD").first();
		Iterator<Element> ite = table.select("tr").iterator();
		while(ite.hasNext()){
			String textoTratar = ite.next().text();
			if(textoTratar.split(" ")[0].equals("Órgão:")){
				setSiglaDepto(textoTratar.split(" ")[1]);
			}
		}
	}
	
	public String getCodCurso() {
		return codCurso;
	}
	public void setCodCurso(String codCurso) {
		this.codCurso = codCurso;
	}
	public String getCodDisc() {
		return codDisc;
	}
	public void setCodDisc(String codDisc) {
		this.codDisc = codDisc;
	}
	public String getSemestre1() {
		return semestre1;
	}
	public void setSemestre1(String semestre) {
		this.semestre1 = semestre;
	}
	public int getCurso() {
		return curso;
	}
	public void setCurso(int codCursoEnviar) {
		this.curso = codCursoEnviar;
	}
	public int disciplina() {
		return disciplina;
	}
	public void setDisciplina(int codDiscEnviar) {
		this.disciplina = codDiscEnviar;
	}
	public int getSemestre() {
		return semestre;
	}
	public void setSemestre(int semestreEnviar) {
		this.semestre = semestreEnviar;
	}

	public String getSiglaDepto() {
		return siglaDepto;
	}

	public void setSiglaDepto(String siglaDepto) {
		this.siglaDepto = siglaDepto;
	}

	public void converterSemDpto() {
		// TODO Auto-generated method stub
		setCurso(Integer.parseInt(codCurso));
		setDisciplina(Integer.parseInt(codDisc));
		setSemestre(Integer.parseInt(semestre1));
		setSiglaDepto("FLW");
	}
}
