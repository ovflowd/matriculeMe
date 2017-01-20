package com.unb.matriculeme.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.unb.matriculeme.dao.Curso;
import com.unb.matriculeme.dao.Horario;
import com.unb.matriculeme.dao.Disciplina;
import com.unb.matriculeme.dao.Oferta;
import com.unb.matriculeme.dao.Semestre;
import com.unb.matriculeme.dao.Turma;
import com.unb.matriculeme.helpers.ClientUtils;
import com.unb.matriculeme.helpers.PersistenceHelper;
import com.unb.matriculeme.messages.NotFoundMessage;

@Path("/turmas/")
public class TurmaController {
	/*
	 * POST
	 * Professor - Persistir(Professor)
	 * Oferta  - Persistir(Oferta) Vai mandar oferta (Disciplina e Semestre ja vao existir)
	 * Horarios - Persistir (Horarios) 
	 */
	@Path("/getTurmas/dia={dia}&horarioInicio={horarioInicio}")
	@GET
	@Produces(MediaType.APPLICATION_JSON) 
	public Response getAllCoisas(@PathParam("dia") String dia, @PathParam("horarioInicio") String horarioInicio) throws Exception{
		List<Object> Turmas = PersistenceHelper.queryCustomJoin(dia, horarioInicio);  
		
		List<Disciplina> disciplinas = new ArrayList<Disciplina>();
		
		Iterator itr = Turmas.iterator();
		
		while(itr.hasNext()){
		   Object[] obj = (Object[]) itr.next(); 
		   Turma t1 = (Turma)obj[0]; 
		   disciplinas.add(t1.getOferta().getDisciplina()); 
		}  
		Set<Disciplina> disciplinasUnique = new HashSet<Disciplina>(disciplinas);
		
		return Turmas.size() > 0 ? ClientUtils.sendResponse(disciplinasUnique) : 
            ClientUtils.sendMessage(new NotFoundMessage("This User wasn't found on our system.")); 
	}   
 
	
	@Path("/getTurmas/dia={dia}&horarioInicio={horarioInicio}&disciplina={nome}")
	@GET
	@Produces(MediaType.APPLICATION_JSON) 
	public Response getAllCoisas(@PathParam("dia") String dia, @PathParam("horarioInicio") String horarioInicio,
								 @PathParam("nome") String nome) throws Exception{
		List<Object> Turmas = PersistenceHelper.queryCustomJoin(dia, horarioInicio);  
		
		List<Disciplina> disciplinas = new ArrayList<Disciplina>();
		
		Iterator itr = Turmas.iterator();
		
		while(itr.hasNext()){
		   Object[] obj = (Object[]) itr.next(); 
		   Turma t1 = (Turma)obj[0]; 
		   disciplinas.add(t1.getOferta().getDisciplina()); 
		}  
		
		Set<Disciplina> disciplinasUnique = new HashSet<Disciplina>(disciplinas);
		
		List disciplinas_LIKE = PersistenceHelper.queryCustomLike("Disciplina", "nome", nome); 
		  
		Disciplina d1 = null;
		
		List<Disciplina> disciplinas_toSend = new ArrayList<Disciplina>();  
		 
		for (int i = 0; i < disciplinas.size(); i++){ 
			for (int j = 0; j < disciplinas_LIKE.size(); j++){ 
				if (((Disciplina)disciplinas_LIKE.get(j)).getNome().equals(((Disciplina)disciplinas.get(i)).getNome())){
					System.out.println("entrou"); 
					d1 = (Disciplina)disciplinas_LIKE.get(j); 
					disciplinas_toSend.add(d1);
					break;   
				} 
			} 
		}     
		      
		if (disciplinas_toSend != null){  
			return ClientUtils.sendResponse(disciplinas_toSend); 
		}   
		else{ 
			return ClientUtils.sendMessage(new NotFoundMessage("NOT POSSIBLE")); 
		}
	}
	
	
	@Path("/getTurmas/codigo={codigo}") 
	@GET 
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllCoisas(@PathParam("codigo") String codigo) throws Exception {
		List Turmas = PersistenceHelper.queryCustom("Turma", "codigo", codigo, true);
		 return Turmas.size() > 0 ? ClientUtils.sendResponse(Turmas) :
	            ClientUtils.sendMessage(new NotFoundMessage("This User wasn't found on our system."));
	}
	
	@Path("/getTurmas/disciplina={nome}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTurmas(@PathParam("nome") String nome){
		List disciplinas = PersistenceHelper.queryCustom("Disciplina", "nome", nome, true);
		if (disciplinas.size() > 0)
		{
			List ofertas	 = PersistenceHelper.queryCustom("Oferta", "disciplina_id", String.valueOf(((Disciplina)disciplinas.get(0)).getId()), false);
			if (ofertas.size() > 0)
			{
				System.out.println(((Oferta)ofertas.get(0)).getId());
				List turmas		 = PersistenceHelper.queryCustom("Turma", "oferta_id", String.valueOf(((Oferta)ofertas.get(0)).getId()), false);
				return turmas.size() > 0 ? ClientUtils.sendResponse(turmas) :
		            ClientUtils.sendMessage(new NotFoundMessage("This User wasn't found on our system."));
			}
			
		}
		return ClientUtils.sendMessage(new NotFoundMessage("This disciplina or oferta wasn't found on our system. "));
	} 
	
	@Path("/getTurmas/codDisciplina={codigo}") 
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTurmas(@PathParam("codigo") int codigo){
		List disciplinas = PersistenceHelper.queryCustom("Disciplina", "codigo", String.valueOf(codigo), true);
		if (disciplinas.size() > 0) 
		{
			List ofertas	 = PersistenceHelper.queryCustom("Oferta", "disciplina_id", String.valueOf(((Disciplina)disciplinas.get(0)).getId()), false);
			if (ofertas.size() > 0)
			{
				System.out.println(((Oferta)ofertas.get(0)).getId());
				List turmas		 = PersistenceHelper.queryCustom("Turma", "oferta_id", String.valueOf(((Oferta)ofertas.get(0)).getId()), false);
				return turmas.size() > 0 ? ClientUtils.sendResponse(turmas) :
		            ClientUtils.sendMessage(new NotFoundMessage("This User wasn't found on our system."));
			} 
		}
		return ClientUtils.sendMessage(new NotFoundMessage("This disciplina or oferta wasn't found on our system. "));
	} 
		 
	@Path("/setTurmas/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setAllCoisas(List<Turma> allTurmas) throws Exception {
		
        for (int i = 0; i < allTurmas.size(); i++)
        {
        	PersistenceHelper.Persist(allTurmas.get(i).getProfessor());
        	
        	Disciplina d1 = (Disciplina)(PersistenceHelper.queryCustom("Disciplina", "codigo", String.valueOf(allTurmas.get(i).getOferta().getDisciplina().getCodigo()), false).get(0));
        	Semestre s1 = (Semestre)(PersistenceHelper.queryCustom("Semestre", "codigo", allTurmas.get(i).getOferta().getSemestre().getCodigo(), true).get(0));
        	
        	List o1 = PersistenceHelper.queryCustomTurma("Oferta", "semestre_id", s1.getId() , "disciplina_id", d1.getId());
        	if (o1.size() > 0) //Existe a oferta, setar;
        	{
        		allTurmas.get(i).setOferta((Oferta)o1.get(0));
        	}
        	else
        	{
        		allTurmas.get(i).getOferta().setDisciplina(d1);
            	allTurmas.get(i).getOferta().setSemestre(s1);
            	PersistenceHelper.Persist(allTurmas.get(i).getOferta());  
        	}
        	for (int j = 0 ; j < allTurmas.get(i).getHorario().size(); j++)
        	{
        		PersistenceHelper.Persist(allTurmas.get(i).getHorario().get(j));
        	}  
        	PersistenceHelper.Persist(allTurmas.get(i)); 
       	}

        return Response.status(200).build();
    }
	
}
