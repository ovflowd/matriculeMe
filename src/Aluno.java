package com.datamining.rest.api;

import java.util.ArrayList;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.datamining.rest.models.Mencao;
import com.datamining.rest.models.Oferta;

public class Aluno {
	
	private int matricula;
	private Habilitacao curso; 
	private ArrayList<DisciplinasCursadas> disciplinasCursadas = new ArrayList<DisciplinasCursadas>();
	private int ira;
	private transient String quadroResumo;
	private int semestreAtual;

	public Aluno(String htmlQuadroResumo) {
		// TODO Auto-generated constructor stub
		setQuadroResumo(htmlQuadroResumo);
	}

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public void pegarMatriCursoIraSemestre() {
		// TODO Auto-generated method stub
		int flag = 0;
		Document doc = null;
		while(flag == 0){
			try{doc = Jsoup.parse(getQuadroResumo());
			flag = 1;
			}
			catch(Exception e){
				System.out.println("Erro");
			}
		}
		Iterator<Element> ite = doc.select("TD").iterator();
		String matriculaTemp = null;
		String tratar;
		String iraTemp = null;
		String cursoTemp = null;
		String semestreAtualTemp = null;
		String creditosLimite = null;
		while(ite.hasNext()){
			String stringSimples = ite.next().text();
			if(stringSimples.equals("Aluno: Curso: Opção:")||stringSimples.equals("Aluna: Curso: Opção:")){
				tratar = ite.next().text();
				matriculaTemp = tratar.split(" ")[0];
				cursoTemp = tratar.split(" ")[2];
			}else if(stringSimples.equals("Índ. Rend. Acad.")){
				iraTemp = ite.next().text();
			}else if(stringSimples.equals("Nr. Per. Opção:")){
				semestreAtualTemp = ite.next().text();
			}else if(stringSimples.equals("Máx. Créd. Período:")){
				creditosLimite = ite.next().text();
			}
		}
		matriculaTemp = matriculaTemp.replaceAll("/", "");
		setMatricula(Integer.parseInt(matriculaTemp));
		Habilitacao cursoObjeto = new Habilitacao();
		cursoObjeto.setCodigo(Integer.parseInt(cursoTemp));
		cursoObjeto.setCredLimite(Integer.parseInt(creditosLimite));
		setCurso(cursoObjeto);
		setSemestreAtual(Integer.parseInt(semestreAtualTemp));
		iraTemp = iraTemp.replaceAll(",", "");
		setIra(Integer.parseInt(iraTemp));
	}

	public Habilitacao getCurso() {
		return curso;
	}

	public void setCurso(Habilitacao curso) {
		this.curso = curso;
	}

	public ArrayList<DisciplinasCursadas> getDisciplinasCursadas() {
		return disciplinasCursadas;
	}

	public void setDisciplinasCursadas(ArrayList<DisciplinasCursadas> disciplinasCursadas) {
		this.disciplinasCursadas = disciplinasCursadas;
	}

	public int getIra() {
		return ira;
	}

	public void setIra(int ira) {
		this.ira = ira;
	}

	public String getQuadroResumo() {
		return quadroResumo;
	}

	public void setQuadroResumo(String quadroResumo) {
		this.quadroResumo = quadroResumo;
	}

	public int getSemestreAtual() {
		return semestreAtual;
	}

	public void setSemestreAtual(int semestreAtual) {
		this.semestreAtual = semestreAtual;
	}

	public void pegarEQ() {
		// TODO Auto-generated method stub
		int flag = 0;
		Document doc = null;
		while(flag == 0){
			try{doc = Jsoup.parse(getQuadroResumo());
			flag = 1;
			}
			catch(Exception e){
				System.out.println("Erro");
			}
		}
		Iterator<Element> ite = doc.select("TD").iterator();
		String stringSimples = "";
		boolean flagLaco = true;
		while(ite.hasNext()&&(!stringSimples.equals("Créd.:"))&&(flagLaco)){
			stringSimples = ite.next().text();
			stringSimples = stringSimples.replaceAll("\u00a0"," ");
			String arrayString[] = stringSimples.split(" ");
			for(int i = 0; i < arrayString.length; i++){
				//System.out.println(arrayString[i]);
				if(arrayString[i].equals("EQ")){
					if(arrayString[i+7].equals("=")){
						flagLaco = false;
						break;
					}
					DisciplinasCursadas tempDC = new DisciplinasCursadas();
					Mencao tempMencao = new Mencao();
					Disciplina discTemp = new Disciplina();
					discTemp.setCodDisc(arrayString[i+7]);
					discTemp.extrairPreReq();
					discTemp.extrairCreditos();
					discTemp.converter();
					discTemp.converterPreReq();    
					Oferta tempOferta = new Oferta(discTemp, "2016/2");
					tempOferta.setDisciplina(discTemp);
					tempMencao.setCodigo("EQ");
					tempDC.setMencao(tempMencao);
					tempDC.setOferta(tempOferta);
					this.getDisciplinasCursadas().add(tempDC);
				}
			}
		}
	}
	
}
