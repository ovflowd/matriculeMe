package dao;

import javax.persistence.*;

@Entity
@Table(name = "disciplinas_cursadas")
public class CoursedDisciplines {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "aluno_disciplina")
    private Student student;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "mencao_disciplina")
    private Mention mention;

    @ManyToOne
    @JoinTable(name = "oferta_disciplina")
    private Offer offer;

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

    public Mention getMention() {
        return mention;
    }

    public void setMention(Mention mention) {
        this.mention = mention;
    }

    public void setDisciplinas(Offer offer) {
        this.offer = offer;
    }

    public Offer getOffer() {
        return offer;
    }
}
