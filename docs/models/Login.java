package modules;

import javax.persistence.*;

@Entity
public class Login {
	@Id
	@GeneratedValue
	private int id;
	
	@Column(nullable = false,length=40)
	private String password;
	
	@Column(nullable = false, length = 100)
	private String accessKey;
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setAccesKey(String accessKey){
		this.accessKey = accessKey;
	}
	
	public String getAccessKey(){
		return accessKey;
	}
}
