package pdes.c1.universitypanel.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pdes.c1.universitypanel.model.Course;
import pdes.c1.universitypanel.model.Subject;
import pdes.c1.universitypanel.repositories.CourseRepository;
import pdes.c1.universitypanel.repositories.ProfessorRepository;
import pdes.c1.universitypanel.repositories.StudentRepository;
import pdes.c1.universitypanel.repositories.SubjectRepository;
import pdes.c1.universitypanel.service.CourseService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@WebMvcTest(CourseController.class)
@ContextConfiguration(classes = {CourseController.class, CourseService.class})
public class CourseControllerTest {

    @Mock
    private CourseService courseService;

    @MockBean
    private CourseRepository courseRepository;

    @MockBean
    private StudentRepository studentRepository;

    @MockBean
    private ProfessorRepository professorRepository;

    @MockBean
    private SubjectRepository subjectRepository;

    @InjectMocks
    private CourseController courseController;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(courseController).build();
    }

    @Test
    public void testGetCourseById() throws Exception {
        Course course = new Course("Test Course", 2021, 2, new Subject());
        course.setId(1L);

        when(courseService.getCourseById(1L)).thenReturn(course);

        mockMvc.perform(get("/courses/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is(course.getName())))
                .andExpect(jsonPath("$.year", is(course.getYear())))
                .andExpect(jsonPath("$.semester", is(course.getSemester())))
                .andExpect(jsonPath("$.subject.name", is(course.getSubject().getName())))
                .andExpect(jsonPath("$.id", is(course.getId().intValue())));

        verify(courseService, times(1)).getCourseById(1L);
    }

    @Test
    public void testGetAllCourses() throws Exception {
        Course course1 = new Course("Course 1", 2022, 1, new Subject());
        Course course2 = new Course("Course 2", 2022, 1, new Subject());

        List<Course> courses = new ArrayList<>();
        courses.add(course1);
        courses.add(course2);

        when(courseService.getAllCourses()).thenReturn(courses);

        mockMvc.perform(get("/courses/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name", is(course1.getName())))
                .andExpect(jsonPath("$[0].year", is(course1.getYear())))
                .andExpect(jsonPath("$[0].semester", is(course1.getSemester())))
                .andExpect(jsonPath("$[0].subject.name", is(course1.getSubject().getName())))
                .andExpect(jsonPath("$[1].name", is(course2.getName())))
                .andExpect(jsonPath("$[1].year", is(course2.getYear())))
                .andExpect(jsonPath("$[1].semester", is(course2.getSemester())))
                .andExpect(jsonPath("$[1].subject.name", is(course2.getSubject().getName())));

        verify(courseService, times(1)).getAllCourses();
    }

    @Test
    public void testCreateCourse() throws Exception {
        // Creamos un objeto Course para enviar en la petición POST
        Course course = new Course("Test Course", 2021, 2, new Subject());

        // Configuramos el comportamiento del mock de CourseService para devolver el objeto Course creado
        Mockito.when(courseService.createCourse(Mockito.any(Course.class))).thenReturn(course);

        // Enviamos la petición POST con el objeto Course como JSON
        String courseJson = "{\"name\":\"Test Course\",\"year\":2021,\"semester\":2,\"subject\":{\"id\":1}}";
        mockMvc.perform(MockMvcRequestBuilders.post("/courses/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(courseJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Test Course"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.year").value(2021))
                .andExpect(MockMvcResultMatchers.jsonPath("$.semester").value(2));
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}