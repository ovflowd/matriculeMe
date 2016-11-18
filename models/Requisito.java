import javax.persistence.*;

@Entity
@Table(name = "requisito")
public class Requisito {

	@Id
	@GeneratedValue
	private int id;
	
	@Column(name = "disciplina_requisito")
	private String discicplinaRequisito; //'FK' Referencia �s disciplinas requisitos
	
	@Column
	private int tipo; //tipo de requisito, co, pr�, etc
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	
	public String getDisciplinaRequisito(){
		return discicplinaRequisito;
	}
	
	public void setTipo(int tipo){
		this.tipo= tipo;
	}
	
	public int getTipo(){
		return tipo;
	}
	
}