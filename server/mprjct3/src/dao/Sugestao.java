package dao;

import javax.persistence.*;

@Entity
public class Sugestao {
    @Id
    @GeneratedValue
    @Column(nullable = false, unique = true)
    private int id;//Chave prim�ria da tabela Sugest�o

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "aluno_sugestao")
    private Student student;//'FK' refer�ncia ao student que receber� a respectiva sugest�o

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "curriculo_sugestao")
    private Curriculo curriculo;//'FK' Refer�ncia a disciplina sugerida

    @Column
    private int prioridade;//prioridade da disciplina na matricula

    @Column
    private boolean vagas;//Quantidade de vagas na turma

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "motivo_sugestao")
    private Motivo motivo;//'FK" Refer�ncia ao motivo pelo qual a disciplina est� sendo sugerida

    @Column
    private int creditos;//Quantidade total de cr�ditos sugeridos

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

    public void setCuriculo(Curriculo curriculo) {
        this.curriculo = curriculo;
    }

    public Curriculo getCurriculo() {
        return curriculo;
    }

    public Motivo getMotivo() {
        return motivo;
    }

    public void setMotivo(Motivo motivo) {
        this.motivo = motivo;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public boolean getVagas() {
        return vagas;
    }

    public void setVagas(boolean vagas) {
        this.vagas = vagas;
    }
}

