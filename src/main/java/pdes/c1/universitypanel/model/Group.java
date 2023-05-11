package pdes.c1.universitypanel.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity(name = "groups")
public class Group {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToMany
	private List<Student> students = new ArrayList<>();
	
	@ElementCollection
	private List<String> repositories = new ArrayList<>();

	public Group(Long id, List<Student> students, List<String> repositories) {
		super();
		this.id = id;
		this.students = students;
		this.repositories = repositories;
	}

	public Group() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public List<String> getRepositories() {
		return repositories;
	}

	public void setRepositories(List<String> repositories) {
		this.repositories = repositories;
	}

	public void addRepositorie(String repositorie) {
		this.repositories.add(repositorie);
	}
}
