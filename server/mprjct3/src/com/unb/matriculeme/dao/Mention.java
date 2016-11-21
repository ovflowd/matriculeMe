package com.unb.matriculeme.dao;

import javax.persistence.*;

@Entity
@Table(name = "mencao")
public class Mention {
    @Id
    @GeneratedValue
    @Column(nullable = false, unique = true)
    private int id;

    @Column
    private String code;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
