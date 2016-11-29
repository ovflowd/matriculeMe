public class Horario {
	private Turma turma;
	private String dia;
	private String horarioInicio;
	private String horarioFim;
    private int id;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
	public Horario() 
	{
		this.turma = new Turma();
		this.dia = "";
		this.horarioInicio = "";
		this.horarioFim = "";
	}
	public Turma getTurma()
	{
		return turma;
	}
	public void setTurma(Turma turma)
	{
		this.turma = turma;
	}
	public String getDia()
	{
		return dia;
	}
	public void setDia(String dia)
	{
		this.dia = dia;
	}
	public String getHorarioInicio()
	{
		return horarioInicio;
	}
	public void setHorarioInicio(String horarioInicio)
	{
		this.horarioInicio = horarioInicio;
	}
	public String getHorarioFim()
	{
		return horarioFim;
	}
	public void setHorarioFim(String horarioFim)
	{
		this.horarioFim = horarioFim;
	}
}