package com.unb.matriculeme.dao;

import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


@Entity
public class Student {

	@Id
	@GeneratedValue
	private int id;	

	@Column(nullable = false, unique = true)
	private int registerId;

	@Column(nullable = false)
	private String name;

	@Column
	private int ira;

	@Column
	private int actualSemester;

	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH, CascadeType.ALL })
	@JoinColumn
	private Course course;

	@Column
	private String interest;

	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE, CascadeType.ALL })
	@JoinColumn
	private Profile profile;

	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE, CascadeType.PERSIST }, fetch = FetchType.EAGER)
	@JoinTable(name = "disciplinasCursadas")
	private List<CoursedDisciplines> CoursedDisciplines;

	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE })
	@JoinColumn 
	private Login login;

	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE, CascadeType.PERSIST })
	@JoinTable(name = "aluno_sugestoes")
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
    	return this.CoursedDisciplines;
    }
    
    public void setCoursedDisciplines (List<CoursedDisciplines> CoursedDisciplines){
    	this.CoursedDisciplines = CoursedDisciplines;
    }
}
