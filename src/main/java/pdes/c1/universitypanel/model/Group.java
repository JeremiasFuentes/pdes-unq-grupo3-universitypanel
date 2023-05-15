package pdes.c1.universitypanel.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity(name = "groups")
public class Group {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToMany
	private List<Student> students = new ArrayList<>();

	@OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<GitRepository> repositories = new ArrayList<>();

	public Group(Long id, List<Student> students, List<GitRepository> repositories) {
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

	public List<GitRepository> getRepositories() {
		return repositories;
	}

	public void setRepositories(List<GitRepository> repositories) {
		this.repositories = repositories;
	}

	public void addRepositorie(GitRepository repositorie) {
		this.repositories.add(repositorie);
		repositorie.setGroup(this);
	}

	public void removeRepositorie(GitRepository repositorie) {
		this.repositories.remove(repositorie);
		repositorie.setGroup(null);
	}
}
