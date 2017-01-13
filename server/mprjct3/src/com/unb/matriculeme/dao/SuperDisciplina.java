package com.unb.matriculeme.dao;

import java.util.ArrayList;
import java.util.List;

public class SuperDisciplina {

    private String nome;
    private Departamento departamento;
    private int credito;
    private int codigo;
    private List<Requisito> requisitoDisciplina;
    private List<Turma> turmas;

    public SuperDisciplina(Disciplina disciplina) {
        this.nome = disciplina.getNome();
        this.departamento = disciplina.getDepartamento();
        this.credito = disciplina.getCredito();
        this.codigo = disciplina.getCodigo();
        this.requisitoDisciplina = disciplina.getRequisitoDisciplina();
        this.turmas = new ArrayList<Turma>();
    }

    public List<Turma> getTurmas() {
        return this.turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }
}
