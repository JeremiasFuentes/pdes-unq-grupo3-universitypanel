package pdes.c1.universitypanel.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pdes.c1.universitypanel.service.StudentService;
import pdes.c1.universitypanel.utils.ResponseBody;

@RestController
@RequestMapping("/student")
public class StudentController {
	@Autowired
	private StudentService studentService;
	
	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}
	
	@CrossOrigin
	@GetMapping(path = "/helloworld")
	public ResponseEntity<Map<String, String>> getById() {
		String string = this.studentService.helloWorld();
		return ResponseEntity.ok().body(ResponseBody.create(string));
	}
}
