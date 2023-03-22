package pdes.c1.universitypanel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "students")
public class Student {
	@Id
	private String name;

	public Student(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
