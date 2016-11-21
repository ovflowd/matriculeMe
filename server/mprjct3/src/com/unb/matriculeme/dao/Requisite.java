package com.unb.matriculeme.dao;

import javax.persistence.*;

@Entity
public class Requisite {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "disciplina_origem")
    private Discipline originDiscipline;

    @Column(name = "disciplina_requisito")
    private String requisiteDiscipline;

    @Column
    private int requisiteType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Discipline getOriginDiscipline() {
        return originDiscipline;
    }

    public void setOriginDiscipline(Discipline originDiscipline) {
        this.originDiscipline = originDiscipline;
    }

    public String getDisciplinaRequisito() {
        return requisiteDiscipline;
    }

    public void setDisciplinaRequisito(String disciplinaRequisito) {
        this.requisiteDiscipline = disciplinaRequisito;
    }

    public int getRequisiteType() {
        return requisiteType;
    }

    public void setRequisiteType(int requisiteType) {
        this.requisiteType = requisiteType;
    }

}
