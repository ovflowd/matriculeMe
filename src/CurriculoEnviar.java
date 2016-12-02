package com.datamining.rest.models;
import java.io.IOException;

import com.datamining.rest.api.*;

public class CurriculoEnviar {
	
	private Habilitacao curso;
	private DisciplinaCurriculo disciplina;
	private int semestreDisciplina;
	
	public CurriculoEnviar(Habilitacao habilitacao, String discipli, String sem, String sigla) throws IOException {
		// TODO Auto-generated constructor stub
		this.setCurso(habilitacao);
		this.setSemestre(Integer.parseInt(sem));
		DisciplinaCurriculo enviar = new DisciplinaCurriculo();
		enviar.setCodigo(Integer.parseInt(discipli));
		DepartamentoCurriculo depto = new DepartamentoCurriculo();
		depto.setSigla(sigla);
		enviar.setDepartamento(depto);
		this.setDisciplinaCurriculo(enviar);
	}
	public Habilitacao getCurso() {
		return curso;
	}
	public void setCurso(Habilitacao curso) {
		this.curso = curso;
	}
	public DisciplinaCurriculo getDisciplina() {
		return disciplina;
	}
	public void setDisciplinaCurriculo(DisciplinaCurriculo disciplina) {
		this.disciplina = disciplina;
	}
	public int getSemestre() {
		return semestreDisciplina;
	}
	public void setSemestre(int semestre) {
		this.semestreDisciplina = semestre;
	}
}
