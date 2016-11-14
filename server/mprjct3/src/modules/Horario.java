package modules;

import javax.persistence.*;

@Entity
@Table(name = "horario")
public class Horario {
    @Id
    @GeneratedValue
    private int id;//Chave prim�ria da tabela Hor�rios

    @OneToOne
    @JoinTable(name = "turma_id")
    private Turma turmaId;

    @Column
    private String dia;/*Dia da semana que ocorrem aulas em inteiros onde , segunda = 2, ter�a = 3
                      e assim sucessivamente*/

    @Column(name = "horario_inicio")
    private String horarioInicio;//Hor�rio em que uma aula come�a

    @Column(name = "horario_fim")
    private String horarioFim;//Hor�rio em que uma aula termina

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(String horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public String getHorarioFim() {
        return horarioFim;
    }

    public void setHorarioFim(String horarioFim) {
        this.horarioFim = horarioFim;
    }

    public Turma getTurmas() {
        return turmaId;
    }

    public void setTurmas(Turma turmaId) {
        this.turmaId = turmaId;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }
}
