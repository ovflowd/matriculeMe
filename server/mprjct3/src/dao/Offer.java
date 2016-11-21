package dao;

import javax.persistence.*;

@Entity
@Table(name = "oferta")
public class Offer {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "disciplina_semestre")
    private CoursedDisciplines discipline;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "oferta_semestre")
    private Semester semester;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CoursedDisciplines getCoursedDiscipline() {
        return discipline;
    }

    public void setCoursedDiscipline(CoursedDisciplines discipline) {
        this.discipline = discipline;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }
}
