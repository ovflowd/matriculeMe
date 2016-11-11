package modules;

import javax.persistence.*;

@Entity
@Table(name = "tipo_reserva")
public class TipoReserva {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column
	private int tipo;
	
	@Column
	private int valor;
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	
	public void setTipo(int tipo){
		this.tipo = tipo;
	}
	
	public int getTipo(){
		return tipo;
	}
	
	public void setValor(int valor){
		this.valor = valor;
	}
	
	public int getValor(){
		return valor;
	}
}
