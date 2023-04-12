package pdes.c1.universitypanel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pdes.c1.universitypanel.exceptions.ResourceNotFoundException;
import pdes.c1.universitypanel.model.Course;
import pdes.c1.universitypanel.model.Professor;
import pdes.c1.universitypanel.model.Student;
import pdes.c1.universitypanel.model.Subject;
import pdes.c1.universitypanel.repositories.CourseRepository;
import pdes.c1.universitypanel.repositories.ProfessorRepository;
import pdes.c1.universitypanel.repositories.StudentRepository;
import pdes.c1.universitypanel.repositories.SubjectRepository;

@Service
public class CourseService {
	private final CourseRepository courseRepository;
	private final StudentRepository studentRepository;
	private final ProfessorRepository professorRepository;
	private final SubjectRepository subjectRepository;

	@Autowired
	public CourseService(CourseRepository courseRepository, StudentRepository studentRepository,
			ProfessorRepository professorRepository, SubjectRepository subjectRepository) {
		this.courseRepository = courseRepository;
		this.studentRepository = studentRepository;
		this.professorRepository = professorRepository;
		this.subjectRepository = subjectRepository;
	}

	public Course getCourseById(Long id) {
		return this.courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course", "id", id));
	}

	public Course createCourse(Course course) {
		Subject subject = subjectRepository.findById(course.getSubject().getId())
				.orElseThrow(() -> new ResourceNotFoundException("Subject", "id", course.getSubject().getId()));

		course.setSubject(subject);
		Course savedCourse = courseRepository.save(course);

		subject.getCourses().add(savedCourse);
		subjectRepository.save(subject);

		return savedCourse;
	}

	public Course updateCourse(Long id, Course course) {
		Course existingCourse = courseRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Course", "id", id));

		existingCourse.setYear(course.getYear());
		existingCourse.setSemester(course.getSemester());
		existingCourse.setSubject(course.getSubject());
		existingCourse.setStudents(course.getStudents());
		existingCourse.setProfessors(course.getProfessors());
		existingCourse.setName(course.getName());

		return this.courseRepository.save(existingCourse);
	}

	public void deleteCourse(Long id) {
		this.courseRepository.deleteById(id);
	}

	public void addStudentToCourse(Long courseId, Student student) {
		Course course = getCourseById(courseId);
		Student existingStudent = studentRepository.findById(student.getDni())
				.orElseThrow(() -> new ResourceNotFoundException("Student", "dni", student.getDni()));
		course.getStudents().add(existingStudent);

		this.courseRepository.save(course);
	}

	public void addProfessorToCourse(Long courseId, Professor professor) {
		Course course = getCourseById(courseId);
		Professor existingProfessor = professorRepository.findById(professor.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Professor", "id", professor.getId()));
		course.getProfessors().add(existingProfessor);

		this.courseRepository.save(course);
	}

	public List<Course> getAllCourses() {
		return (List<Course>) this.courseRepository.findAll();
	}
}