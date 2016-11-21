package com.unb.matriculeme.dao;

import javax.persistence.*;

@Entity
public class Schedule {
    @Id
    @GeneratedValue
    private int id;

    @OneToOne
    @JoinTable(name = "turma_id")
    private SchoolClass classId;

    @Column
    private String day;

    @Column(name = "horario_inicio")
    private String classStart;

    @Column(name = "horario_fim")
    private String classEnds;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClassStart() {
        return classStart;
    }

    public void setClassStart(String classStart) {
        this.classStart = classStart;
    }

    public String getClassEnds() {
        return classEnds;
    }

    public void setClassEnds(String classEnds) {
        this.classEnds = classEnds;
    }

    public SchoolClass getTurmas() {
        return classId;
    }

    public void setTurmas(SchoolClass turmaId) {
        this.classId = turmaId;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
