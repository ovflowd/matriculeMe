package com.datamining.rest.api;
public class Departamento {
	private String nome;
	private int codigo;
	private String sigla;
	private transient String cod;
	
	//ArrayList<String> discOfertadas = new ArrayList<String>();
	//ArrayList<String> codDiscOfertadas = new ArrayList<String>();
	/*
	public void extrairDiscOfertadas() throws IOException{
		//Executar ite.next() 17 vezes
		Document doc = Jsoup.connect("https://matriculaweb.unb.br/graduacao/oferta_dis.aspx?cod="+getCod()).get();
		Element table = doc.select("TD").first();
		Iterator<Element> ite2 = table.select("tr").iterator();
		while(!(ite2.next().text()).equals("Código Denominação Ementa"));
		String stringMW = ite2.next().text();
		String comp ="© 2016 CPD - Centro de Informática UnB - Universidade de Brasília"; 
		while(!(stringMW.equals(comp))){
			String[] fraseSeparada = stringMW.split(" ");
			codDiscOfertadas.add(fraseSeparada[0]);
			String subfrase = "";
			for(int j = 1; j < fraseSeparada.length; j++){
				subfrase += fraseSeparada[j] + " ";
			}
			discOfertadas.add(subfrase);
			stringMW = ite2.next().text();
		}
		//System.out.print(stringMW);
	}
	*/
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codEnviar) {
		this.codigo = codEnviar;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

}
