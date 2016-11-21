package com.unb.matriculeme.dao;

import javax.persistence.*;

@Entity
@Table(name = "sugestao")
public class Suggestion {
    @Id
    @GeneratedValue
    @Column(nullable = false, unique = true)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "aluno_sugestao")
    private Student student;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "curriculo_sugestao")
    private Curriculum curriculum;

    @Column
    private int priority;

    @Column
    private boolean vacancies;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "motivo_sugestao")
    private Reason reason;

    @Column
    private int credits;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setCuriculo(Curriculum curriculo) {
        this.curriculum = curriculo;
    }

    public Curriculum getCurriculum() {
        return curriculum;
    }

    public Reason getReason() {
        return reason;
    }

    public void setReason(Reason reason) {
        this.reason = reason;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public boolean getVacancies() {
        return vacancies;
    }

    public void setVacancies(boolean vacancies) {
        this.vacancies = vacancies;
    }
}

