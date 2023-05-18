package pdes.c1.universitypanel.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity(name = "students")
public class Student {
	@Id
	private Integer dni;
	private String name;
	private String mail;
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "student_group", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
	private List<Group> groups = new ArrayList<>();

	public Student(Integer dni, String name, String mail) {
		this.dni = dni;
		this.name = name;
		this.mail = mail;
	}

	public Student() {
	}

	public Integer getDni() {
		return this.dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public List<Group> getGroups() {
		return this.groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}
}
