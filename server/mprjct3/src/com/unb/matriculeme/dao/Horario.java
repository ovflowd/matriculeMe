package com.unb.matriculeme.dao;

import javax.persistence.*;


@Entity
public class Horario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(nullable = false, length = 10)
    private String dia;
    @Column(nullable = false, length = 10)
    private String horarioInicio;
    @Column(nullable = false, length = 10)
    private String horarioFim;

    public Horario() {
        this.dia = "";
        this.horarioInicio = "";
        this.horarioFim = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(String horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public String getHorarioFim() {
        return horarioFim;
    }

    public void setHorarioFim(String horarioFim) {
        this.horarioFim = horarioFim;
    }
}