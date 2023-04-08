package pdes.c1.universitypanel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pdes.c1.universitypanel.exceptions.ResourceNotFoundException;
import pdes.c1.universitypanel.model.Student;
import pdes.c1.universitypanel.repositories.StudentRepository;

import java.util.List;

@Service
public class StudentService {	
	private StudentRepository studentRepository;
	
	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public List<Student> getAllStudents() {
		return (List<Student>) studentRepository.findAll();
	}

	public Student getStudentById(Long studentId) {
		return studentRepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("Student", "id", studentId));
	}

	public Student createStudent(Student student) {
		return studentRepository.save(student);
	}

	public Student updateStudent(Long studentId, Student studentDetails) {
		Student student = studentRepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("Student", "id", studentId));
		student.setName(studentDetails.getName());
		student.setDni(studentDetails.getDni());
		student.setMail(studentDetails.getMail());
		student.setCourses(studentDetails.getCourses());
		return studentRepository.save(student);
	}

	public void deleteStudent(Long studentId) {
		Student student = studentRepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("Student", "id", studentId));
		studentRepository.delete(student);
	}
}
