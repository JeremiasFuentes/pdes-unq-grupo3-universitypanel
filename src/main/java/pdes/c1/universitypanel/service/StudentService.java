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

	public Student getStudentById(Integer studentDni) {
		return studentRepository.findById(studentDni).orElseThrow(() -> new ResourceNotFoundException("Student", "dni", studentDni));
	}

	public Student createStudent(Student student) {
		if (studentRepository.findById(student.getDni()).isPresent())
			throw new RuntimeException("El dni ingresado ya se encuentra registrado");
			
		return studentRepository.save(student);
	}

	public Student updateStudent(Integer studentDni, Student studentDetails) {
		Student student = this.getStudentById(studentDni);
		student.setName(studentDetails.getName());
		student.setDni(studentDetails.getDni());
		student.setMail(studentDetails.getMail());
		student.setGroups(studentDetails.getGroups());
		
		return studentRepository.save(student);
	}

	public void deleteStudent(Integer studentDni) {
		Student student = this.getStudentById(studentDni);
		studentRepository.delete(student);
	}
}
