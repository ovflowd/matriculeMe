package modules;
import javax.persistence.*;

@Entity
public class Url {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(nullable = false, length = 150)
	private String url;
	
	@Column(nullable = false)
	private long time;
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	
	public void setUrl(String url){
		this.url = url;
	}
	
	public String getUrl(){
		return url;
	}
	
	public void setTime(long time){
		this.time = time;
	}
	
	public long getTime(){
		return time;
	}
}
