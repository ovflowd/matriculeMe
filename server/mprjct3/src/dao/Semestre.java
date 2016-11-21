package dao;

import javax.persistence.*;

@Entity
@Table(name = "semestre")
public class Semestre {
    @Id
    @GeneratedValue
    @Column(nullable = false, unique = true)
    private int id;//Chave prim�ria da tabela Semestre

    @Column
    private String codigo;//

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
