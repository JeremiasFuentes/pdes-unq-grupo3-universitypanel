package pdes.c1.universitypanel.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
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
import pdes.c1.universitypanel.exceptions.ResourceNotFoundException;
import pdes.c1.universitypanel.model.Course;
import pdes.c1.universitypanel.model.Subject;
import pdes.c1.universitypanel.repositories.CourseRepository;
import pdes.c1.universitypanel.repositories.ProfessorRepository;
import pdes.c1.universitypanel.repositories.StudentRepository;
import pdes.c1.universitypanel.repositories.SubjectRepository;
import pdes.c1.universitypanel.service.CourseService;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
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
    public void testGetCourseById_NotFound() throws Exception {
        Long courseId = 1L;

        when(courseService.getCourseById(courseId)).thenThrow(new ResourceNotFoundException("Course", "id", courseId));

        mockMvc.perform(get("/courses/1"))
                .andExpect(status().isNotFound());

        verify(courseService, times(1)).getCourseById(courseId);
    }

    @Test
    public void testCreateCourse() throws Exception {
        Course course = new Course("Test Course", 2021, 2, new Subject());

        Mockito.when(courseService.createCourse(Mockito.any(Course.class))).thenReturn(course);

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