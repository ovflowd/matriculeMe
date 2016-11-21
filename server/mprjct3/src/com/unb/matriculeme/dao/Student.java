package com.unb.matriculeme.dao;

import javax.persistence.*;

@Entity
@Table(name = "aluno")
public class Student {

    @Id
    @GeneratedValue
    private int id;

    // Matricula from student
    @Column
    private int registerId;

    @Column
    private String name;

    @Column
    private int ira;

    @Column(name = "semestre_atual")
    private int actualSemester;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "curso_aluno")
    private Course course;

    @Column
    private String interest;

    @OneToOne
    @JoinTable(name = "login_aluno")
    private Login login;

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

    public int getIra() {
        return ira;
    }

    public void setIra(int ira) {
        this.ira = ira;
    }

    public int getRegisterId() {
        return registerId;
    }

    public void setRegisterId(int registerId) {
        this.registerId = registerId;
    }

    public int getActualSemester() {
        return actualSemester;
    }

    public void setActualSemester(int actualSemester) {
        this.actualSemester = actualSemester;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }
}
