package modules;

import javax.persistence.*;

@Entity
@Table(name = "aluno")
public class Aluno {

    @Id
    @GeneratedValue
    private int id; //Chave prim�ria da classe Alunos

    @Column
    private int matricula; //Matricula do aluno na Universidade

    @Column
    private String nome; //Nome do Aluno

    @Column
    private int ira; //Indice de Rendimento Acad�mico do aluno

    @Column(name = "semestre_atual")
    private int semestreAtual;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "curso_aluno")
    private Curso curso;//'FK'

    @Column
    private String interesse;

    @OneToOne
    @JoinTable(name = "login_aluno")
    private Login login;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
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

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }
}
