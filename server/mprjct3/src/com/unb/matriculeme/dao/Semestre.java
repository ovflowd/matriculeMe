package com.unb.matriculeme.dao;

import javax.persistence.*;

@Entity
public class Semestre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(nullable = false, length = 50)
    private String codigo;

    public Semestre() {
        this.codigo = new String();
    }

    public int getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
