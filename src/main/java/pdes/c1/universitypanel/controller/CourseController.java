package pdes.c1.universitypanel.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pdes.c1.universitypanel.model.Course;
import pdes.c1.universitypanel.model.Professor;
import pdes.c1.universitypanel.service.CourseService;
import pdes.c1.universitypanel.utils.ResponseBody;

@RestController
@RequestMapping("/courses")
public class CourseController {
	private final CourseService courseService;

	@Autowired
	public CourseController(CourseService courseService) {
		this.courseService = courseService;
	}

	@CrossOrigin
	@GetMapping("/")
	public ResponseEntity<Map<String, Object>> getAllCourses() {
		return ResponseEntity.ok().body(ResponseBody.create(courseService.getAllCourses()));
	}

	@CrossOrigin
	@GetMapping("/{id}")
	public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
		Course course = courseService.getCourseById(id);
		return ResponseEntity.ok(course);
	}

	@CrossOrigin
	@PostMapping("/")
	public ResponseEntity<Course> createCourse(@RequestBody Course course) {
		Course savedCourse = courseService.createCourse(course);
		return ResponseEntity.ok(savedCourse);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course course) {
		Course updatedCourse = courseService.updateCourse(id, course);
		return ResponseEntity.ok(updatedCourse);
	}

	@CrossOrigin
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
		courseService.deleteCourse(id);
		return ResponseEntity.noContent().build();
	}

	@CrossOrigin
	@PostMapping("/{id}/professors")
	public ResponseEntity<Void> addProfessorToCourse(@PathVariable Long id, @RequestBody Professor professor) {
		courseService.addProfessorToCourse(id, professor);
		return ResponseEntity.noContent().build();
	}
}
