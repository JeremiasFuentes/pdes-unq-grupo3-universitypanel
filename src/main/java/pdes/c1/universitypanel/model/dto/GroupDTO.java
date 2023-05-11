package pdes.c1.universitypanel.model.dto;

import pdes.c1.universitypanel.model.Group;

public class GroupDTO {
	private Long id;
	private Integer studentsAmount;
	private Integer repositoriesAmount;

	public GroupDTO(Long id, Integer studentsAmount) {
		this.id = id;
		this.studentsAmount = studentsAmount;
	}

	public GroupDTO(Group group) {
		this.id = group.getId();
		this.studentsAmount = group.getStudents().size();
		this.repositoriesAmount = group.getRepositories().size();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getStudentsAmount() {
		return studentsAmount;
	}

	public void setStudentsAmount(Integer studentsAmount) {
		this.studentsAmount = studentsAmount;
	}

	public Integer getRepositoriesAmount() {
		return repositoriesAmount;
	}

	public void setRepositoriesAmount(Integer repositoriesAmount) {
		this.repositoriesAmount = repositoriesAmount;
	}
}
