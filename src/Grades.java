package projeto.matriculeme.REST;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Grades /////Classe Manipula as informa��es do algoritmo
	{
		List<Curriculo> listaOrdenada = new ArrayList<Curriculo>();
		String pertencentes = "";
		LinkedList<Curriculo> listaPertence = new LinkedList<Curriculo>();
		int[][] SLOT = new int[7][24];
		String horarios = "";
		double metricaTotal = 0;
		int totalCreditos = 0;

		
		
		
		public Grades(List<Curriculo> listaOrdenada2,String p, double d, int t,LinkedList<Curriculo> lp,int[][] S)
		{
		this.SLOT = S;
		this.listaOrdenada = listaOrdenada2;
		this.pertencentes = p; //disciplinas "com" da �rvore
		this.metricaTotal = d; //m�trica resultante das pertencentes
		this.totalCreditos = t; //total de cr�ditos
		this.listaPertence = lp;
		try{
		System.out.print("Metrica "+this.listaOrdenada.get(0).getDisciplina().metrica+"\n");
		}catch(Exception e){}}
		
		
		public Grades(List<Curriculo> disciplineList)
		{
			this.listaOrdenada = disciplineList;
		System.out.print("Lista comeco"+pertencentes+"\n");
			
		}
		
		
		public Grades(int[][] S)
		{
			this.SLOT = S;
		}
		
		
		
	}