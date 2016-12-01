package projeto.matriculeme.REST;
import java.util.LinkedList;

public class Grades /////Classe Manipula as informa��es do algoritmo
	{
		LinkedList<Curriculo> listaOrdenada = new LinkedList<Curriculo>();
		String pertencentes = "";
		LinkedList<Curriculo> listaPertence = new LinkedList<Curriculo>();
		String[][] SLOT = new String[7][24];
		String horarios = "";
		double metricaTotal = 0;
		int totalCreditos = 0;

		
		
		
		public Grades(LinkedList<Curriculo> l,String p, double d, int t,LinkedList<Curriculo> lp,String[][] S)
		{
		this.SLOT = S;
		this.listaOrdenada = l;
		this.pertencentes = p; //disciplinas "com" da �rvore
		this.metricaTotal = d; //m�trica resultante das pertencentes
		this.totalCreditos = t; //total de cr�ditos
		this.listaPertence = lp;
		try{
		System.out.print("Lista comeco "+this.listaOrdenada.getFirst().getDisciplina().getNome()+"\n");}
		catch(Exception e)
		{}
		System.out.print("Metrica "+this.metricaTotal+"\n");
		}
		
		
		public Grades(LinkedList<Curriculo> l)
		{
			this.listaOrdenada = l;
		System.out.print("Lista comeco"+pertencentes+"\n");
			
		}
		
		
		public Grades(String[][] S)
		{
			this.SLOT = S;
		}
		
		
		
	}
