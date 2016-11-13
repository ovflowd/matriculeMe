public class HorarioAula
	{	
		int diaInt;
		int horario_inicioInt ;
		int horario_finalInt;
		
		char[] dia = new char[10];
		char[] horario_inicio = new char[10];
		char[] horario_final = new char[10];
		public HorarioAula()
		{
			diaInt = Integer.valueOf(String.valueOf(dia));
			horario_inicioInt = Integer.valueOf(String.valueOf(horario_inicio));
			horario_finalInt = Integer.valueOf(String.valueOf(horario_final));
		}
		
	}