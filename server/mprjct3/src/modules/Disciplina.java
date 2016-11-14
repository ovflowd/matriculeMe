package modules;

import javax.persistence.*;

@Entity
@Table(name = "disciplina")
public class Disciplina {
    @Id
    @GeneratedValue
    private int id;//Chave prim�ria da tabela Disciplinas

    @Column
    private String nome;//Nome da disciplina

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinTable(name = "departamento_disciplina")
    private Departamento departamento;//'FK' refer�ncia ao Departamento que oferta a disciplina

    @Column
    private int credito;//Quantidade de cr�ditos da disciplina

    @Column
    private int codigo;

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

    public int getCredito() {
        return credito;
    }

    public void setCredito(int credito) {
        this.credito = credito;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
}
