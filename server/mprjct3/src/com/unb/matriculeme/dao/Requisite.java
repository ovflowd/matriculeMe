package com.unb.matriculeme.dao;

import javax.persistence.*;

@Entity
public class Requisite {

    @Id
    @GeneratedValue
    private int id;

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
