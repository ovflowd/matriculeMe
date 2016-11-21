package dao;

import javax.persistence.*;


@Entity
@Table(name = "disciplinas_cursadas")
public class DisciplinasCursadas {
    @Id
    @GeneratedValue
    private int id; //Chave Prim�ria da tabela hist�rico

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "aluno_disciplina")
    private Student student; //'FK' Refer�ncia ao student cujo hist�rico est� sendo referenciado

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "mencao_disciplina")
    private Mencao mencao; //'FK' referencia a men��o do student em determinada disciplina

    @ManyToOne
    @JoinTable(name = "oferta_disciplina")
    private Oferta oferta;//'FK'

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getAlunos() {
        return student;
    }

    public void setAlunos(Student student) {
        this.student = student;
    }

    public Mencao getMencao() {
        return mencao;
    }

    public void setMencao(Mencao mencao) {
        this.mencao = mencao;
    }

    public void setDisciplinas(Oferta oferta) {
        this.oferta = oferta;
    }

    public Oferta getOferta() {
        return oferta;
    }
}
