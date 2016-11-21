package com.unb.matriculeme.dao;

import javax.persistence.*;

@Entity
@Table(name = "professor")
public class Professor {
    @Id
    @GeneratedValue
    @Column(nullable = false, unique = true)
    private int id;//Chave prim�ria da tabela Professores

    @Column
    private String nome;//Nome do professor

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
