package modules;

import javax.persistence.*;

@Entity
@Table(name = "requisito")
public class Requisito {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "discplina_origem")
    private Disciplina disciplinaOrigem;//c�digo da disciplina requisito

    @Column(name = "disciplina_requisito")
    private String discicplinaRequisito; //'FK' Referencia �s disciplinas requisitos

    @Column
    private int tipo; //tipo de requisito, co, pr�, etc

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Disciplina getDisciplinaOrigem() {
        return disciplinaOrigem;
    }

    public void setDisciplinaOrigem(Disciplina disciplinaOrigem) {
        this.disciplinaOrigem = disciplinaOrigem;
    }

    public String getDisciplinaRequisito() {
        return discicplinaRequisito;
    }

    public void setDisciplinaRequisito(String disciplinaRequisito) {
        this.discicplinaRequisito = disciplinaRequisito;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

}
