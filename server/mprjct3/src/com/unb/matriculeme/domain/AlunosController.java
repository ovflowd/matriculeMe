package com.unb.matriculeme.domain;

import com.mysema.commons.lang.Pair;
import com.unb.matriculeme.dao.Aluno;
import com.unb.matriculeme.dao.Curso;
import com.unb.matriculeme.dao.Login;
import com.unb.matriculeme.helpers.ClientUtils;
import com.unb.matriculeme.helpers.Persistence;
import com.unb.matriculeme.messages.AllRightMessage;
import com.unb.matriculeme.messages.BaseMessage;
import com.unb.matriculeme.messages.NotFoundMessage;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/alunos")
public class AlunosController {

    /******************************ABAIXO SEGUE O TRIGGER DO MACHINE LEARNING***************
     *                                                                                     *    
     *                                      ¯\_('')_/¯                                     *    
     *                                                                                     *
     ***************************************************************************************/
    /*
    @Path("/updateAluno/disciplinasCursadas")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateAlunoFull(Aluno aluno){
        List alunos = PersistenceHelper.queryCustom("Aluno", "matricula", String.valueOf(aluno.getMatricula()), false);
        List cursos = PersistenceHelper.queryCustom("Curso", "codigo", String.valueOf(aluno.getCurso().getCodigo()), false);
        for(int i = 0; i < aluno.getDisciplinasCursadas().size(); i++)
        {
            List mencoes     = PersistenceHelper.queryCustom("Mencao", "codigo",aluno.getDisciplinasCursadas().get(i).getMencao().getCodigo(), true);
            List disciplinas = PersistenceHelper.queryCustom("Disciplina", "codigo", String.valueOf(aluno.getDisciplinasCursadas().get(i).getOferta().getDisciplina().getCodigo()), false);
            List semestres   = PersistenceHelper.queryCustom("Semestre", "codigo", aluno.getDisciplinasCursadas().get(i).getOferta().getSemestre().getCodigo(), true);
            List ofertas = PersistenceHelper.queryCustomTurma("Oferta", "disciplina_id", ((Disciplina)disciplinas.get(0)).getId(), "semestre_id", ((Semestre)semestres.get(0)).getId());
            aluno.getDisciplinasCursadas().get(i).setMencao((Mencao)mencoes.get(0));
            aluno.getDisciplinasCursadas().get(i).setOferta((Oferta)ofertas.get(0));
        }   
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDB");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Aluno a1 = (Aluno)em.merge(alunos.get(0));  
        a1.setDisciplinasCursadas(aluno.getDisciplinasCursadas());
        a1.setIra(aluno.getIra());
        a1.setSemestreAtual(aluno.getSemestreAtual());
        a1.setCurso((Curso)cursos.get(0));
        em.getTransaction().commit();
        em.close();
        emf.close();
        //TRIGGER
        return Response.status(200).build();  
    }*/

    @Path("/getAluno/nome={nome}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAlunoByNome(@PathParam("nome") String nome) {
        List<Aluno> alunos = Persistence.select(Aluno.class, Persistence.createExpression(new Pair<>("nome", nome)), true);

        return alunos.size() > 0 ? ClientUtils.sendResponse(alunos.get(0)) : ClientUtils.sendMessage(new NotFoundMessage("This User wasn't found on our system."));
    }

    @Path("/updateAlunoCurso/matricula={matricula}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCurso(Curso curso, @PathParam("matricula") int matricula) throws IllegalAccessException {
        List<Aluno> alunos = Persistence.select(Aluno.class, Persistence.createExpression(new Pair<>("matricula", matricula)), true);
        List<Curso> cursos = Persistence.select(Curso.class, Persistence.createExpression(new Pair<>("codigo", curso.getCodigo())), true);

        alunos.get(0).setCurso(cursos.get(0));

        if (alunos.size() > 0 && cursos.size() > 0) {
            Persistence.update(alunos.get(0));
        }

        return ClientUtils.sendMessage(alunos.size() > 0 && cursos.size() > 0 ? new AllRightMessage("The meta data from the course was updated successfully.") :
                new NotFoundMessage("This User wasn't found on our system."));
    }

    @Path("/getAluno/login={login}&senha={senha}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAlunoByLogin(@PathParam("login") String accessKey, @PathParam("senha") String senha) {
        List<Login> logins = Persistence.select(Login.class, Persistence.createExpression(new Pair<>("accessKey", accessKey), new Pair<>("password", senha)), true);

        return logins.size() > 0 ? ClientUtils.sendResponse(Persistence.select(Aluno.class, Persistence.createExpression(new Pair<>("login", logins.get(0).getId())), true).get(0)) : ClientUtils.sendMessage(new NotFoundMessage("This User wasn't found on our system."));
    }

    @Path("/setAluno/")
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public Response setHorarios(String alunoRaw) throws Exception {
        Aluno aluno = ClientUtils.getGson(alunoRaw, Aluno.class);
        List students = Persistence.select(Login.class, Persistence.createExpression(new Pair<>("accessKey", aluno.getLogin().getAccessKey())), true);

        // User Doesn't Exists
        // Profile & Course set by default and can be changed latter.
        if (students.size() == 0) {
            aluno.setCurso((Curso) Persistence.select(Curso.class, Persistence.createExpression(new Pair<>("codigo", 0)), true).get(0));
            Persistence.insert(aluno.getLogin());
            Persistence.insert(aluno);
        }

        // If User Exists Return 403. If not, Return 200.
        return ClientUtils.sendMessage(students.size() > 0 ? new BaseMessage(483, "User Already Exists. Creation not Allowed.") : new AllRightMessage("User created successfully in the system."));
    }
}
