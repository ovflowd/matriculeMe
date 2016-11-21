package com.unb.matriculeme.dao;

import javax.persistence.*;

@Entity
@Table(name = "curriculo")
public class Curriculum {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinTable(name = "disciplina_curriculo")
    private Discipline discipline;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinTable(name = "curso_curriculo")
    private Course course;

    @Column(name = "semestre_disciplina")
    private int semester;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }
}
