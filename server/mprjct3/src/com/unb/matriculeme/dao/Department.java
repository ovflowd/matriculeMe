package com.unb.matriculeme.dao;

import javax.persistence.*;

@Entity
public class Department {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private String name;

    @Column
    private int code;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
