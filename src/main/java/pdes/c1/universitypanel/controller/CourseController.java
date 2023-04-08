package pdes.c1.universitypanel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdes.c1.universitypanel.model.Course;
import pdes.c1.universitypanel.model.Professor;
import pdes.c1.universitypanel.model.Student;
import pdes.c1.universitypanel.service.CourseService;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/")
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        Course course = courseService.getCourseById(id);
        return ResponseEntity.ok(course);
    }

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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/students")
    public ResponseEntity<Void> addStudentToCourse(@PathVariable Long id, @RequestBody Student student) {
        courseService.addStudentToCourse(id, student);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/professors")
    public ResponseEntity<Void> addProfessorToCourse(@PathVariable Long id, @RequestBody Professor professor) {
        courseService.addProfessorToCourse(id, professor);
        return ResponseEntity.noContent().build();
    }
}