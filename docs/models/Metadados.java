package modules;
import javax.persistence.*;

@Entity
public class Metadados {
	@Id
	@GeneratedValue
	private int id;
	
	@Column
	private String json;
	
	@Column
	private String classe;
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	
	public void setJSon(String json){
		this.json = json;
	}
	
	public String getJSon(){
		return json;
	}
	
	public void setClasse(String classe){
		this.classe = classe;
	}
	
	public String getClasse(){
		return classe;
	}
}
