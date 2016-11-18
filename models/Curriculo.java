import javax.persistence.*;

@Entity
@Table(name = "curriculo")
public class Curriculo {

    @Id
    @GeneratedValue
    private int id;//Chave primária da Tabela Curriculo

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinTable(name = "disciplina_curriculo")
    private Disciplina disciplina;//'FK' Referencia diciplinas no curriculo

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinTable(name = "curso_curriculo")
    private Curso curso;//'FK' Referencia ao curso a o qual o curriculo pertence

    @Column(name = "semestre_disciplina")
    private int semestreDisciplina;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public int getSemestreDisciplina() {
        return semestreDisciplina;
    }

    public void setSemestreDisciplina(int semestreDisciplina) {
        this.semestreDisciplina = semestreDisciplina;
    }
}
