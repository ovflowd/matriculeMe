package com.unb.matriculeme.domain;

//TODO: fazer o get do perfil; alem disso, nao esquecer das perguntas salvas no notepad
//R: nao aparentemente

import com.unb.matriculeme.dao.Aluno;
import com.unb.matriculeme.dao.Disciplina;
import com.unb.matriculeme.dao.Perfil;
import com.unb.matriculeme.helpers.ClientUtils;
import com.unb.matriculeme.helpers.PersistenceHelper;
import com.unb.matriculeme.messages.NotFoundMessage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/perfil")
public class PerfilController {
    @Path("/setPerfil/matricula={matricula}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response sayPlainTextHello(@PathParam("matricula") int matricula, Perfil profile) throws Exception {
        List alunos = PersistenceHelper.queryCustom("Aluno", "matricula", String.valueOf(matricula), false);
        if (alunos.size() < 0) {
            //TRATANDO SE PASSAR MATRICULA INVALIDA
            return ClientUtils.sendMessage(new NotFoundMessage("This user wasn't found on our system."));
        }

        //Abaixo segue o algol p poder linkar sugestao com disciplina ja presente no BD

        for (int i = 0; i < profile.getAluno().getSugestoes().size(); i++) {
            List disciplinas = PersistenceHelper.queryCustom("Disciplina", "codigo", String.valueOf(profile.getAluno().getSugestoes().get(i).getDisciplina().getCodigo()), false);
            profile.getAluno().getSugestoes().get(i).setDisciplina(((Disciplina) disciplinas.get(0)));
            //PersistenceHelper.Persist(profile.getAluno().getSugestoes().get(i));
        }

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDB");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Aluno a1 = (Aluno) em.merge(alunos.get(0));
        a1.setSugestoes(profile.getAluno().getSugestoes());
        em.getTransaction().commit();
        em.close();
        emf.close();

        return ClientUtils.sendMessage(new AllRightMessage("Profile set successfully."));
    }


    @Path("/getPerfil/Aluno={matricula}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response alterPerfil(@PathParam("matricula") int matricula) throws IllegalAccessException {
        List Alunos = PersistenceHelper.queryCustom("Aluno", "matricula", String.valueOf(matricula), false);
        if (Alunos.size() > 0) {
            List perfis = PersistenceHelper.queryCustom("Perfil", "aluno_id", String.valueOf(((Aluno) Alunos.get(0)).getId()), false);
            return perfis.size() > 0 ? ClientUtils.sendResponse(perfis.get(0)) :
                    ClientUtils.sendMessage(new NotFoundMessage("This Profile wasn't found on our system."));
        }
        return ClientUtils.sendMessage(new NotFoundMessage("This Profile wasn't found on our system."));
    }
}
