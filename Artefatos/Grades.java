import java.util.LinkedList;

public class Grades /////Classe Manipula as informa��es do algoritmo
	{
		LinkedList<Disciplina> listaOrdenada = null;
		String pertencentes = "";
		String[] horario = new String[108];
		int metricaTotal = 0;
		int totalCreditos = 0;

		public Grades(LinkedList<Disciplina> l,String p,String[] h, int m, int t)
		{
		listaOrdenada = l;
		pertencentes = p; //disciplinas "com" da �rvore
		horario = h;
		metricaTotal = m; //m�trica resultante das pertencentes
		totalCreditos = t; //total de cr�ditos
		}
		
		public Grades(LinkedList<Disciplina> l)
		{
		listaOrdenada = l;
		}
		
		
		public Grades(String[] h)
		{
		horario = h;
		}
		
		
		
	}