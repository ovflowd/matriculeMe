package com.datamining.rest.api;
import com.datamining.rest.models.*;

public class DisciplinasCursadas {
	private Oferta oferta;
	private transient String mencaoString;
	private Mencao mencao;

	public String getMencaoString() {
		return mencaoString;
	}

	public void setMencaoString(String mencaoString) {
		this.mencaoString = mencaoString;
	}

	public Oferta getOferta() {
		return oferta;
	}

	public void setOferta(Oferta oferta) {
		this.oferta = oferta;
	}

	public Mencao getMencao() {
		return mencao;
	}

	public void setMencao(Mencao mencao) {
		this.mencao = mencao;
	}
}
