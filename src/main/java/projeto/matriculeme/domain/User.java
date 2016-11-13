package projeto.matriculeme.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class User 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@OneToOne
	@JoinColumn(nullable = false)
	private Login login;
	@OneToOne
	@JoinColumn(nullable = false)
	private Token token;
	@Column(nullable = false, length = 100)
	private String name;
	
	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public Login getLogin() 
	{
		return login;
	}
	
	public void setLogin(Login login)
	{
		this.login = login;
	}
	
	public Token getToken()
	{
		return token;
	}
	
	public void setToken(Token token) 
	{
		this.token = token;
	}
	
	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
}
