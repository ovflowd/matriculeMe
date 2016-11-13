package com.matriculeMe.domain;

import javax.persistence.*;

@Entity
public class Alvo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(nullable = false, length = 150)
    private String string;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

}
