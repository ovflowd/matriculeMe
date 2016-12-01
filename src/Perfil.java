package projeto.matriculeme.REST;

import java.util.ArrayList;
import java.util.List;
public class Perfil 
{
	private int id;
	public ArrayList<Departamento> departamentos;
	transient private double[] metrica;
	transient public int[] suporte;
	public String metricaString;
	public Aluno aluno;
	
	
	public double PerfilporDepartamento(int dDep){return this.getMetrica(dDep);}
    
	
	public Perfil() 
	{
		this.departamentos = new ArrayList<Departamento>();
		this.metrica = new double[90];
		this.suporte = new int[90];
		this.aluno = new Aluno();
		this.metricaString ="";
	}
	
	public void GeraPerfil(List<DisciplinasCursadas> histDisciplinas)
	{
    	
		
		for(DisciplinasCursadas disciplina : histDisciplinas)
		{
			
			if(this.getDepartamento(disciplina.getId())==null)
			{
			//disciplina ainda nao mapeada
				this.departamentos.add(disciplina.getOferta().getDisciplina().getDepartamento());
			}
			if(disciplina.Nota(aluno.getIra())>0)
			{//
				this.setMetricaDep(disciplina.Nota(aluno.getIra()),disciplina.getOferta().getDisciplina().getDepartamento().getCodigo());
			}
		}
		
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
	public double[] getMetrica()
	{
		return metrica;
	}
	public double getMetrica(int Id)
	{
		
		double result;
		try{
			result = (metrica[this.departamentos.indexOf(this.getDepartamento(Id))]/suporte[this.departamentos.indexOf(this.getDepartamento(Id))]);
		}catch(Exception e)
		{result = 0;}
		
		
		return result;
	}
	public void setMetricaDep(int Value,int Dep)
	{
		this.metrica[this.departamentos.indexOf(this.getDepartamento(Dep))] = (this.metrica[this.departamentos.indexOf(this.getDepartamento(Dep))] 
																				* this.suporte[this.departamentos.indexOf(this.getDepartamento(Dep))]+ Value) /( 1+ this.suporte[this.departamentos.indexOf(this.getDepartamento(Dep))]);
		this.suporte[this.departamentos.indexOf(this.getDepartamento(Dep))] += 1;
	}
	public void setMetrica(double[] metrica)
	{
		this.metrica = metrica;
	}
}