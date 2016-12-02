package projeto.matriculeme.REST;
public class DisciplinasCursadas {
	private Mencao mencao;
	private Oferta oferta;
    private int id;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
	public DisciplinasCursadas() 
	{
		this.mencao = new Mencao();
		this.oferta = new Oferta();
	}
	public Mencao getMencao()
	{
		return mencao;
	}
	public void setMencao(Mencao mencao)
	{
		this.mencao = mencao;
	}
	public Oferta getOferta()
	{
		return oferta;
	}
	public void setOferta(Oferta oferta)
	{
		this.oferta = oferta;
	}
	int Nota(int i) {
		switch (this.mencao.getCodigo().toLowerCase()) {
		case "ss":
			return 5;
		case "EQ":
		case "CC":
			return i/1000;

		case "ms":
			return 4;
		case "mm":
			return 3;
		case "mi":
			return 2;
		case "ii":
			return 1;
		case "sr":
			return 0;
		}
		return -1;
	}
}
