package com.unb.matriculeme.dao;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(nullable = false)
    private int matricula;
    @Column(nullable = false, length = 150)
    private String nome;
    @Column
    private int ira;
    @Column
    private int semestreAtual;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(nullable = true)
    private Curso curso;

    @Column
    private String interesse;

    //@OneToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy = "aluno")
    //@JoinColumn (nullable = true)
    //private Perfil perfil;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable(name = "disciplinasCursadas")
    private List<DisciplinasCursadas> disciplinasCursadas;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE})
    @JoinColum
    private Login login;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "aluno_sugestoes")
    private List<Sugestao> sugestoes;

    public Aluno() {
        this.matricula = 0;
        this.nome = new String();
        this.ira = 0;
        this.semestreAtual = 0;
        this.curso = new Curso();
        this.interesse = new String();
        //this.perfil = new Perfil();
        this.login = new Login();
        this.disciplinasCursadas = new ArrayList<DisciplinasCursadas>();
        this.sugestoes = new ArrayList<Sugestao>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIra() {
        return ira;
    }

    public void setIra(int ira) {
        this.ira = ira;
    }

    public int getSemestreAtual() {
        return semestreAtual;
    }

    public void setSemestreAtual(int semestreAtual) {
        this.semestreAtual = semestreAtual;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public String getInteresse() {
        return interesse;
    }

    public void setInteresse(String interesse) {
        this.interesse = interesse;
    }

    /*public Perfil getPerfil()
    {
        return perfil;
    }
    public void setPerfil(Perfil perfil2)
    {
        this.perfil = perfil2;
    }*/
    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public List<DisciplinasCursadas> getDisciplinasCursadas() {
        return disciplinasCursadas;
    }

    public void setDisciplinasCursadas(List<DisciplinasCursadas> disciplinasCursadas) {
        this.disciplinasCursadas = disciplinasCursadas;
    }

    public List<Sugestao> getSugestoes() {
        return sugestoes;
    }

    public void setSugestoes(List<Sugestao> sugestoes) {
        this.sugestoes = sugestoes;
    }
}
