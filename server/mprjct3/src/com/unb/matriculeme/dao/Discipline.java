package com.unb.matriculeme.dao;

import javax.persistence.*;

@Entity
@Table(name = "disciplina")
public class Discipline {
    @Id
    @GeneratedValue
    private int id;

    @Column
    private String name;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinTable(name = "departamento_disciplina")
    private Department department;

    @Column
    private int credits;

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

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
