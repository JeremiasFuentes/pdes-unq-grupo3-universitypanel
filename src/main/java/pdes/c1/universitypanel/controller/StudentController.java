package pdes.c1.universitypanel.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pdes.c1.universitypanel.model.Student;
import pdes.c1.universitypanel.service.StudentService;

@RestController
@RequestMapping("students")
public class StudentController {
	private StudentService studentService;

	@GetMapping("/")
	public List<Student> getAllStudents() {
		return studentService.getAllStudents();
	}

	@GetMapping("/{dni}")
	public ResponseEntity<Student> getStudentById(@PathVariable(value = "dni") Integer studentDni) {
		Student student = studentService.getStudentById(studentDni);
		return ResponseEntity.ok().body(student);
	}

	@PostMapping("/")
	public Student createStudent(@RequestBody Student student) {
		return studentService.createStudent(student);
	}

	@PutMapping("/{dni}")
	public ResponseEntity<Student> updateStudent(@PathVariable(value = "dni") Integer studentDni,
			@RequestBody Student studentDetails) {
		Student updatedStudent = studentService.updateStudent(studentDni, studentDetails);
		return ResponseEntity.ok(updatedStudent);
	}

	@DeleteMapping("/{dni}")
	public ResponseEntity<?> deleteStudent(@PathVariable(value = "dni") Integer studentDni) {
		studentService.deleteStudent(studentDni);
		return ResponseEntity.ok().build();
	}
}
