package com.datamining.rest.models;

public class DisciplinaCurriculo {
	
	private int codigo;
	private DepartamentoCurriculo departamento;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public DepartamentoCurriculo getDepartamento() {
		return departamento;
	}

	public void setDepartamento(DepartamentoCurriculo departamento) {
		this.departamento = departamento;
	}
	
}
