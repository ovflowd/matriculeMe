import java.util.LinkedList;

public class Grades /////Classe Manipula as informa��es do algoritmo
	{
		LinkedList<Curriculo> listaOrdenada = null;
		String pertencentes = "";
		String[] horario = new String[108];
		String horarios;
		int metricaTotal = 0;
		int totalCreditos = 0;

		public Grades(LinkedList<Curriculo> l,String p,String[] h, int m, int t)
		{
		listaOrdenada = l;
		pertencentes = p; //disciplinas "com" da �rvore
		horario = h;
		metricaTotal = m; //m�trica resultante das pertencentes
		totalCreditos = t; //total de cr�ditos
		}
		
		public Grades(LinkedList<Curriculo> l)
		{
		listaOrdenada = l;
		}
		
		
		public Grades(String[] h)
		{
		horario = h;
		}
		
		
		
	}
