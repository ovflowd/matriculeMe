package com.unb.matriculeme.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Metadados {
    @Id
    @GeneratedValue
    private int id;

    @Column
    private String json;

    @Column
    private String classe;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJSon() {
        return json;
    }

    public void setJSon(String json) {
        this.json = json;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }
}
