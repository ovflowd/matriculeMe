import java.util.List;

public class Turma 
{
	private int id;   
    
    
    private String codigo;
    private Professor professor;
    private int campus;
    private Oferta oferta;
    private int vagas;
    private List<Horario> horario;
    public Turma() {
        this.codigo = new String();
        this.professor = new Professor();
        this.campus = 0;
        this.oferta = new Oferta();
        this.vagas = 0;
    }
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

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public int getCampus() {
        return campus;
    }

    public void setCampus(int campus) {
        this.campus = campus;
    }

    public Oferta getOferta() {
        return oferta;
    }

    public void setOferta(Oferta oferta) {
        this.oferta = oferta;
    }

    public int getVagas() {
        return vagas;
    }

    public void setVagas(int vagas) {
        this.vagas = vagas;
    }

    public List<Horario> getHorario() {
        return this.horario;
    }

    public void setHotario(List<Horario> horario) {
        this.horario = horario;
    }
    }
