package modules;

import javax.persistence.*;

@Entity
@Table(name = "motivo")
public class Motivo {
    @Id
    @GeneratedValue
    @Column(nullable = false, unique = true)
    private int id;//chave prim�ria da Tabela Motivos

    @Column
    private String descricao;//Descri��o do porque a sugest�o est� sendo feita a determinadao aluno

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
