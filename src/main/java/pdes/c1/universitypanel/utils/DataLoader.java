package pdes.c1.universitypanel.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pdes.c1.universitypanel.model.Course;
import pdes.c1.universitypanel.model.Professor;
import pdes.c1.universitypanel.model.Student;
import pdes.c1.universitypanel.model.Subject;
import pdes.c1.universitypanel.repositories.CourseRepository;
import pdes.c1.universitypanel.repositories.ProfessorRepository;
import pdes.c1.universitypanel.repositories.StudentRepository;
import pdes.c1.universitypanel.repositories.SubjectRepository;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Override
    public void run(String... args) throws Exception {
        // Carga de subjects
        Subject subject1 = new Subject("Matemáticas");
        Subject subject2 = new Subject("Programación");
        subjectRepository.saveAll(Arrays.asList(subject1, subject2));

        // Carga de courses
        Course course1 = new Course("Curso de matemáticas", 2023, 1, subject1);
        Course course2 = new Course("Curso de programación", 2023, 1, subject2);
        courseRepository.saveAll(Arrays.asList(course1, course2));

        // Carga de students
        Student student1 = new Student(463636364, "Juan Pérez", "juanperez@example.com");
        Student student2 = new Student(32131255, "María González", "mariagonzalez@example.com");
        studentRepository.saveAll(Arrays.asList(student1, student2));

        // Carga de professors
        Professor professor1 = new Professor("Pedro Martínez", "12312321", "pedromartinez@example.com");
        Professor professor2 = new Professor("Ana García", "41424214", "anagarcia@example.com");
        professorRepository.saveAll(Arrays.asList(professor1, professor2));

        // Agregar students y professors a los courses
        course1.getStudents().addAll(Arrays.asList(student1, student2));
        course1.getProfessors().add(professor1);
        course2.getStudents().add(student1);
        course2.getProfessors().add(professor2);
        courseRepository.saveAll(Arrays.asList(course1, course2));
    }
}