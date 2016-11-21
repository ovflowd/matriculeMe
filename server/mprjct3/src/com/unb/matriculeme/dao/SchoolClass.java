package com.unb.matriculeme.dao;

import java.util.List;

import javax.persistence.*;

@Entity
public class SchoolClass {
    @Id
    @GeneratedValue
    @Column(nullable = false, unique = true)
    private int id;//Chave prim�ria da tabela Turmas

    @Column
    private String codigo;//SchoolClass de uma disciplina, pode valer de A-Z

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "professor_turma")
    private Professor professor;//'FK' Refer�ncia ao professor que ministrar� a disciplina
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name="horarios_turma")
    private List<Schedule> schedules;

    @Column
    private int campus;//qual unidade regional da universidade a disciplina � ofertada

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "turma_oferta")
    private Offer offer;//lista de disciplinas e turmas disponiveis a matricula em um periodo letivo
    
    @Column
    private int vagas;//qtd de vagas em uma turma

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public int getVagas() {
        return vagas;
    }

    public void setVagas(int vagas) {
        this.vagas = vagas;
    }
    public int getCampos() {
        return campus;
    }

    public void setCampos(int campos) {
        this.campus = campos;
    }
    
    public List<Schedule> getSchedules(){
    	return this.schedules;
    }
    public void setSchedules(List<Schedule> schedules){
    	this.schedules = schedules;
    }
}
