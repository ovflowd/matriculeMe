package com.unb.matriculeme.domain;

import com.google.gson.Gson;
import com.unb.matriculeme.dao.*;
import com.unb.matriculeme.helpers.ClientUtils;
import com.unb.matriculeme.helpers.PersistenceHelper;
import com.unb.matriculeme.messages.AllRightMessage;
import com.unb.matriculeme.messages.BaseMessage;
import com.unb.matriculeme.messages.NotFoundMessage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/alunos")
public class AlunosController {
    /****************************************************
     *
     *  MATRICULE-ME SERVER V1.0 - NOIS E FODA OU NUM E
     *  NICOLAS, BRUNO, CLAUDIO, MATHEUS
     *  SE VC FOR ENCARREGADO DE PRESTAR MANUTENÇÃO NESTE CÓDIGO
     *  DEUS TENHA PIEDADE DE TI KKKKKKKKKKKKKKKKKKKKKKKK
     *
     *  V1.1 - MELHOROU UM POUCO MAS STILL SHIT
     *   *SQLi corrigido com prepst
     *   *Get geral para indices primarios (/rest/Tabela/Parametro/OQueBuscar/)
     *
     * ***************************************************/

    @Path("/updateAluno/disciplinasCursadas")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateAlunoFull(Aluno aluno) {
        List alunos = PersistenceHelper.queryCustom("Aluno", "matricula", aluno.getMatricula());
        List cursos = PersistenceHelper.queryCustom("Curso", "codigo", aluno.getCurso().getCodigo());
        for (int i = 0; i < aluno.getDisciplinasCursadas().size(); i++) {
            List mencoes = PersistenceHelper.queryCustom("Mencao", "codigo", aluno.getDisciplinasCursadas().get(i).getMencao().getCodigo());
            List disciplinas = PersistenceHelper.queryCustom("Disciplina", "codigo", aluno.getDisciplinasCursadas().get(i).getOferta().getDisciplina().getCodigo());
            List semestres = PersistenceHelper.queryCustom("Semestre", "codigo", aluno.getDisciplinasCursadas().get(i).getOferta().getSemestre().getCodigo());
            if (disciplinas.size() < 1) {
                System.out.println("ERRO DISCIPLINA: -1");
                List departamentos = PersistenceHelper.queryCustom("Departamento", "codigo", aluno.getDisciplinasCursadas().get(i).getOferta().getDisciplina().getDepartamento().getCodigo());
                aluno.getDisciplinasCursadas().get(i).getOferta().getDisciplina().setDepartamento((Departamento) departamentos.get(0));
                PersistenceHelper.Persist(aluno.getDisciplinasCursadas().get(i).getOferta().getDisciplina());
                disciplinas = PersistenceHelper.queryCustom("Disciplina", "codigo", aluno.getDisciplinasCursadas().get(i).getOferta().getDisciplina().getCodigo());
            }
            List ofertas = PersistenceHelper.queryCustomTurma("Oferta", "disciplina_id", ((Disciplina) disciplinas.get(0)).getId(), "semestre_id", ((Semestre) semestres.get(0)).getId());
            aluno.getDisciplinasCursadas().get(i).setMencao((Mencao) mencoes.get(0));
            if (ofertas.size() > 0) {
                aluno.getDisciplinasCursadas().get(i).setOferta((Oferta) ofertas.get(0));
            } else {
                Oferta o1 = new Oferta();
                o1.setDisciplina((Disciplina) disciplinas.get(0));
                o1.setSemestre((Semestre) semestres.get(0));
                PersistenceHelper.Persist(o1);
                List ofertas_else = PersistenceHelper.queryCustomTurma("Oferta", "disciplina_id", ((Disciplina) disciplinas.get(0)).getId(), "semestre_id", ((Semestre) semestres.get(0)).getId());
                aluno.getDisciplinasCursadas().get(i).setOferta((Oferta) ofertas_else.get(0));
            }
        }

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDB");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Aluno a1 = (Aluno) em.merge((Aluno) alunos.get(0));
        System.out.println(new Gson().toJson(aluno));
        a1.setDisciplinasCursadas(aluno.getDisciplinasCursadas());
        a1.setIra(aluno.getIra());
        a1.setSemestreAtual(aluno.getSemestreAtual());
        a1.setCurso((Curso) cursos.get(0));
        em.getTransaction().commit();
        em.close();
        emf.close();

        List alunos_2 = PersistenceHelper.queryCustom("Aluno", "matricula", aluno.getMatricula());

        Trigger thread = new Trigger(new Gson().toJson((Aluno) alunos_2.get(0)), "http://homol.redes.unb.br/ptr022016-b/ML/rest/MachineLearn/loadAluno", true);
        thread.start();

        return ClientUtils.sendMessage(new AllRightMessage("Student updated succesffully."));
    }

    @Path("/updateAluno/alunoSugestao")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateAlunoSugestao(Aluno aluno) {
        List alunos = PersistenceHelper.queryCustom("Aluno", "matricula", aluno.getMatricula());

        for (int i = 0; i < aluno.getSugestoes().size(); i++) {
            List disciplinas = PersistenceHelper.queryCustom("Disciplina", "codigo", aluno.getSugestoes().get(i).getDisciplina().getCodigo());
            aluno.getSugestoes().get(i).setDisciplina(((Disciplina) disciplinas.get(0)));
            PersistenceHelper.Persist((Sugestao) aluno.getSugestoes().get(i));
        }

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDB");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Aluno a1 = (Aluno) em.merge(alunos.get(0));
        a1.setSugestoes(aluno.getSugestoes());
        em.getTransaction().commit();
        em.close();
        emf.close();

        return ClientUtils.sendMessage(new AllRightMessage());
    }

    @Path("/getAluno/nome={nome}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response example(@PathParam("nome") String nome) {
        List students = PersistenceHelper.queryCustom("Aluno", "nome", nome);

        return students.size() > 0 ? ClientUtils.sendResponse(students.get(0)) : ClientUtils.sendMessage(new NotFoundMessage("This User wasn't found on our system."));
    }

    @Path("/updateAlunoCurso/matricula={matricula}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response UpdateCurso(Curso curso, @PathParam("matricula") int matricula) {
        List alunos = PersistenceHelper.queryCustom("Aluno", "matricula", matricula);
        List cursos = PersistenceHelper.queryCustom("Curso", "codigo", curso.getCodigo());

        if (alunos.size() > 0 && cursos.size() > 0) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDB");
            EntityManager em = emf.createEntityManager();

            em.getTransaction().begin();

            Aluno a1 = (Aluno) em.merge(alunos.get(0));
            a1.setCurso((Curso) cursos.get(0));
            em.getTransaction().commit();
            em.close();
            emf.close();

            return ClientUtils.sendMessage(new AllRightMessage());
        } else
            return ClientUtils.sendMessage(new NotFoundMessage("Student not Found!"));
    }


    @Path("/getAluno/login={login}&senha={senha}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response example(@PathParam("login") String accessKey, @PathParam("senha") String senha) {
        List students = PersistenceHelper.queryCustom("Login", "accessKey", accessKey, "password", senha);
        List alunos = null;

        if (students.size() > 0) {
            alunos = PersistenceHelper.queryCustom("Aluno", "login_id", ((Login) students.get(0)).getId());
            return ClientUtils.sendResponse(alunos.get(0));
        }

        return ClientUtils.sendMessage(new NotFoundMessage("This User wasn't found on our system."));
    }

    @Path("/setAluno/")
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public Response setHorarios(String mandatory) throws Exception {
        Aluno student = new Gson().fromJson(mandatory, Aluno.class);

        List students = PersistenceHelper.queryCustom("Login", "accessKey", student.getLogin().getAccessKey());

        if (students.size() == 0) {
            student.setCurso((Curso) (PersistenceHelper.queryCustom("Curso", "codigo", 0).get(0)));
            PersistenceHelper.Persist(student.getLogin());
            PersistenceHelper.Persist(student);
        }

        return ClientUtils.sendMessage(students.size() > 0 ? new BaseMessage(483, "User Already Exists. Creation not Allowed.") : new BaseMessage(200, "User Doesn't exists. Creation Allowed."));
    }
}
