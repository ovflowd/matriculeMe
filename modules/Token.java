package modules;

import javax.persistence.*;

@Entity
public class Token {
	@Id
	@GeneratedValue
	private int id;
	
	@Column(nullable = false, length = 32)
	private String token;
	
	@Column(nullable = false)
	private boolean valid;
	
	@Column(nullable = false)
	private long time;
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	
	public void setToken(String token){
		this.token = token;
	}
	
	public String getToken(){
		return token;
	}
	
	public void setValid(boolean valid){
		this.valid = valid;
	}
	
	public boolean getValid(){
		return valid;
	}
	
	public void setTime(long time){
		this.time = time;
	}
	
	public long getTime(){
		return time;
	}
}
