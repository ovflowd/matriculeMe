package com.unb.matriculeme.dao;

import javax.persistence.*;

@Entity
@Table(name = "curso")
public class Course {
    @Id
    @GeneratedValue
    private int id;

    @Column
    private String name;

    @Column
    private int code;

    @Column(name = "creditos_limite")
    private int limitOfCredits;

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

    public int getLimitOfCredits() {
        return limitOfCredits;
    }

    public void setLimitOfCredits(int limitOfCredits) {
        this.limitOfCredits = limitOfCredits;
    }
}
