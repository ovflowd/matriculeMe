public class HorarioAula {
	int diaInt;
	int horario_inicioInt;
	int horario_finalInt;

	char[] dia;
	char[] horario_inicio;
	char[] horario_final;

	public HorarioAula() {
		diaInt = Integer.valueOf(String.valueOf(dia));
		horario_inicioInt = Integer.valueOf(String.valueOf(horario_inicio));
		horario_finalInt = Integer.valueOf(String.valueOf(horario_final));
	}

}