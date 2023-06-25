package pdes.c1.universitypanel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pdes.c1.universitypanel.exceptions.ResourceNotFoundException;
import pdes.c1.universitypanel.model.GitRepository;
import pdes.c1.universitypanel.model.Group;
import pdes.c1.universitypanel.model.Student;
import pdes.c1.universitypanel.repositories.GroupRepository;
import pdes.c1.universitypanel.repositories.StudentRepository;

@Service
public class GroupService {
	private final CourseService courseService;
	private final StudentService studentService;
	private final GroupRepository groupRepository;
	private final StudentRepository studentRepository;

	@Autowired
	public GroupService(CourseService courseService, StudentService studentService, GroupRepository groupRepository,
			StudentRepository studentRepository) {
		this.courseService = courseService;
		this.groupRepository = groupRepository;
		this.studentRepository = studentRepository;
		this.studentService = studentService;
	}

	public Group getGroupById(Long id) {
		return this.groupRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Group", "id", id));
	}

	public List<Student> getGroupStudents(Long id) {
		return this.studentRepository.findStudentsOfGroup(id);
	}

	public List<Group> getGroupsByCourseId(Long id) {
		return this.groupRepository.findGroupsOfCourse(id);
	}

	public void addStudentToGroup(Long groupId, Integer studentDni) {
		if(this.studentRepository.findStudentByGroupIdAndUserId(groupId, studentDni).isPresent())
			throw new RuntimeException("El estudiante ya se encuentra en el grupo");
			
		Group group = this.getGroupById(groupId);
		Student existingStudent = this.studentService.getStudentById(studentDni);

		group.getStudents().add(existingStudent);

		this.groupRepository.save(group);
	}

	public void addGroup(Long courseId) {
		Group group = new Group();
		this.groupRepository.save(group);
		
		this.courseService.addGroupToCourse(courseId, group);
	}


	public void save(Group group) {
		groupRepository.save(group);
	}

	public List<GitRepository> getGroupRepositories(Long groupId) {
		Group group = this.getGroupById(groupId);
		return  group.getRepositories();

	}

	public void deleteGroup(Long groupId) {
		Group group = this.getGroupById(groupId);
		this.groupRepository.delete(group);
	}

	public Group addNoteToGroup(Long groupId, String note) {
		Group group = groupRepository.findById(groupId).orElseThrow(() -> new ResourceNotFoundException("Group", "id", groupId));
		group.getNotes().add(note);
		return groupRepository.save(group);
	}

	public Group updateNoteInGroup(Long groupId, int noteIndex, String updatedNote) {
		Group group = groupRepository.findById(groupId).orElseThrow(() -> new ResourceNotFoundException("Group", "id", groupId));
		List<String> notes = group.getNotes();
		if (noteIndex >= 0 && noteIndex < notes.size()) {
			notes.set(noteIndex, updatedNote);
		} else {
			throw new IllegalArgumentException("Invalid index");
		}
		return groupRepository.save(group);
	}


	public Group deleteNoteFromGroup(Long groupId, int noteIndex) {
		Group group = groupRepository.findById(groupId).orElseThrow(() -> new ResourceNotFoundException("Group", "id", groupId));
		List<String> notes = group.getNotes();
		if (noteIndex >= 0 && noteIndex < notes.size()) {
			notes.remove(noteIndex);
		} else {
			throw new IllegalArgumentException("Invalid index");
		}
		return groupRepository.save(group);
	}


	public List<String> getGroupNotes(Long groupId) {
		Group group = groupRepository.findById(groupId).orElseThrow(() -> new ResourceNotFoundException("Group", "id", groupId));
		return group.getNotes();
	}
}
