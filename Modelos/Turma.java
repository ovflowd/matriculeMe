package modules;

import javax.persistence.*;

@Entity
@Table(name = "turma")
public class Turma {
	@Id
	@GeneratedValue
	@Column(nullable = false, unique = true)
	private int id;//Chave primária da tabela Turmas
	
	@Column
	private String codigo;//Turma de uma disciplina, pode valer de A-Z
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "professor_turma")
	private Professor professor;//'FK' Referência ao professor que ministrará a disciplina
	
	@Column
	private int campos;//qual unidade regional da universidade a disciplina é ofertada
		
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "turma_oferta")
	private Oferta oferta;//lista de disciplinas e turmas disponiveis a matricula em um periodo letivo
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "tipo_reserva_id")
	private TipoReserva tipoReserva;//Qualifica a reserva de uma turma como por exemplo se 
									//ela é disponivel apenas a um determinado curso
	
	@Column
	private int vagas;//qtd de vagas em uma turma
	
	@Column(name="vagas_reserva")
	private int vagasReserva;//qtd de vagas reservadas a um determinado curso
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	
	public void setCodigo(String codigo){
		this.codigo = codigo;
	}
	
	public String getCodigo(){
		return codigo;
	}
	
	public void setProfessor(Professor professor){
		this.professor = professor;
	}
	
	public Professor getProfessor(){
		return professor;
	}
	
	public void setOferta(Oferta oferta){
		this.oferta = oferta;
	}
	
	public Oferta getOferta(){
		return oferta;
	}
	
	public void setTiporeserva(TipoReserva tipoReserva){
		this.tipoReserva = tipoReserva;
	}
	
	public TipoReserva getTipoReserva(){
		return tipoReserva;
	}
	
	public void setVagas(int vagas){
		this.vagas = vagas;
	}
	
	public int getVagas(){
		return vagas;
	}
	
	public void setVagasReserva(int vagasReserva){
		this.vagasReserva = vagasReserva;
	}
	
	public int getVagasReserva(){
		return vagasReserva;
	}
	
	public void setCampos(int campos){
		this.campos = campos;
	}
	
	public int getCampos(){
		return campos;
	}
}
