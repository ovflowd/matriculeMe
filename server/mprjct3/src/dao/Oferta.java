package dao;

import javax.persistence.*;

@Entity
public class Oferta {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "disciplina_semestre")
    private DisciplinasCursadas disciplina;//'FK' Refer�ncia �s disciplinas ofertadas

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "oferta_semestre")
    private Semestre semestre;//'FK' Refer�ncia ao respectivo per�odo da disciplina

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDisciplinaCursadas(DisciplinasCursadas disciplina) {
        this.disciplina = disciplina;
    }

    public DisciplinasCursadas getDisciplinasCursadas() {
        return disciplina;
    }

    public Semestre getSemestre() {
        return semestre;
    }

    public void setSemestre(Semestre semestre) {
        this.semestre = semestre;
    }
}
