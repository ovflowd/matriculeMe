package com.unb.matriculeme.dao;

import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


@Entity
@Table(name = "aluno")
public class Student {

	@Id
	@GeneratedValue
	private int id; // Chave primária da classe Alunos

	@Column(nullable = false, unique = true)
	private int registerId; // Matricula do aluno na Universidade

	@Column(nullable = false)
	private String name; // Nome do Aluno

	@Column
	private int ira; // Indice de Rendimento Acadmico do aluno

	@Column
	private int actualSemester;

	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH, CascadeType.ALL })
	@JoinColumn
	private Course course;// 'FK'

	@Column
	private String interest;

	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE, CascadeType.ALL })
	@JoinColumn
	@Fetch(FetchMode.SELECT)
	private Profile profile;

	@OneToMany
	@JoinTable(name = "disciplinasCursadas") // dar uma olhada no
	private List<CoursedDisciplines> coursedisciplines;  

	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE })
	@JoinColumn 
	@Fetch(FetchMode.SELECT)   
	private Login login;

	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE, CascadeType.PERSIST })
	@JoinTable(name = "aluno_sugestoes") // dar uma olhadad em sugestoes
	private List<Suggestion> suggestions; 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIra() {
        return ira;
    }

    public void setIra(int ira) {
        this.ira = ira;
    }

    public int getRegisterId() {
        return registerId;
    }

    public void setRegisterId(int registerId) {
        this.registerId = registerId;
    }

    public int getActualSemester() {
        return actualSemester;
    }

    public void setActualSemester(int actualSemester) {
        this.actualSemester = actualSemester;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public Login getLogin() {
        return login;
    }
    public void setProfile(Profile profile){
    	this.profile = profile;
    }
    
    public Profile getProfile(){
    	return this.profile;
    }
    
    public void setLogin(Login login) {
        this.login = login;
    }
    
    public List<Suggestion> getSuggestions(){
    	return this.suggestions;
    }
    
    public void setSuggestions(List<Suggestion> suggestion){
    	this.suggestions = suggestion;
    }
    
    public List<CoursedDisciplines> getCoursedDisciplines(){
    	return this.coursedisciplines;
    }
    
    public void setCoursedDisciplines (List<CoursedDisciplines> coursedDisciplines){
    	this.coursedisciplines = coursedDisciplines;
    }
}
