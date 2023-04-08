package pdes.c1.universitypanel.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pdes.c1.universitypanel.model.Student;
import pdes.c1.universitypanel.service.StudentService;
import pdes.c1.universitypanel.utils.ResponseBody;

@RestController
@RequestMapping("students")
public class StudentController {
	@Autowired
	private StudentService studentService;

	@GetMapping("/")
	public List<Student> getAllStudents() {
		return studentService.getAllStudents();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable(value = "id") Long studentId) {
		Student student = studentService.getStudentById(studentId);
		return ResponseEntity.ok().body(student);
	}

	@PostMapping("/")
	public Student createStudent(@RequestBody Student student) {
		return studentService.createStudent(student);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable(value = "id") Long studentId, @RequestBody Student studentDetails) {
		Student updatedStudent = studentService.updateStudent(studentId, studentDetails);
		return ResponseEntity.ok(updatedStudent);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteStudent(@PathVariable(value = "id") Long studentId) {
		studentService.deleteStudent(studentId);
		return ResponseEntity.ok().build();
	}
}
