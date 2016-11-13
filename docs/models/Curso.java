package modules;
import javax.persistence.*;

@Entity
@Table(name = "Curso")
public class Curso {
	@Id
	@GeneratedValue
	private int id;//Chave prim�ria da Tabela Cursos
	
	@Column
	private String nome;//Nome do curso
	
	@Column
	private int codigo;//C�digo do curso
	
	@Column(name = "creditos_limite")
	private int creditosLimite;
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	
	public void setNome(String nome){
		this.nome = nome;
	}
	
	public String getNome(){
		return nome;
	}
	
	public void setCodigo(int codigo){
		this.codigo = codigo;
	}
	
	public int getCodigo(){
		return codigo;
	}
	
	public void setCreditosLimite(int creditosLimite){
		this.creditosLimite = creditosLimite;
	}
	
	public int getCreditosLimite(){
		return creditosLimite;
	}
}
