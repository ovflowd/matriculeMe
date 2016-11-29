import java.util.ArrayList;

public class Perfil 
{
	private int id;
	public ArrayList<Departamento> departamentos;
	public int[] metrica;
	
	
	
	public int PerfilporDepartamento(int dDep){return this.getMetrica(dDep);}
    
	
	public Perfil() 
	{
		this.departamentos = new ArrayList<Departamento>();
		
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public ArrayList<Departamento> getDepartamentos()
	{
		return departamentos;
	}
	
	
	public Departamento getDepartamento(int Code)
	{
		for(Departamento Dep : departamentos)
		{
			if(Dep.getCodigo()== Code){return Dep;}
		}
		return null;
	}
	
	public void setDepartamentos(ArrayList<Departamento> departamentos)
	{
		this.departamentos = departamentos;
	}
	public int[] getMetrica()
	{
		return metrica;
	}
	public int getMetrica(int Id)
	{
		return metrica[this.departamentos.indexOf(this.getDepartamento(Id))];
	}
	public void setMetricaDep(int Value,int Dep)
	{
		this.metrica[this.departamentos.indexOf(this.getDepartamento(Dep))] += Value;
		this.metrica[this.departamentos.indexOf(this.getDepartamento(Dep))] /= 2;
	}
	public void setMetrica(int[] metrica)
	{
		this.metrica = metrica;
	}
}