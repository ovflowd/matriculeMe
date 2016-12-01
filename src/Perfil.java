package projeto.matriculeme.REST;

import java.util.ArrayList;
import java.util.List;
public class Perfil 
{
	private int id;
	public ArrayList<Departamento> departamentos;
	transient public double[] metrica;
	public String metricaString;
	public Aluno aluno;
	
	
	public double PerfilporDepartamento(int dDep){return this.getMetrica(dDep);}
    
	
	public Perfil() 
	{
		this.departamentos = new ArrayList<Departamento>();
		this.metrica = new double[90];
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
			if(disciplina.Nota()>0)
			{//
				this.setMetricaDep(disciplina.Nota(),disciplina.getOferta().getDisciplina().getDepartamento().getCodigo());
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
		return metrica[this.departamentos.indexOf(this.getDepartamento(Id))];
	}
	public void setMetricaDep(int Value,int Dep)
	{
		this.metrica[this.departamentos.indexOf(this.getDepartamento(Dep))] += Value;
		this.metrica[this.departamentos.indexOf(this.getDepartamento(Dep))] /= 2;
	}
	public void setMetrica(double[] metrica)
	{
		this.metrica = metrica;
	}
}