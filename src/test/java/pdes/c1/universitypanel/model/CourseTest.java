package pdes.c1.universitypanel.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CourseTest {

    @Test
    public void testGetSubject() {
        Subject subject = new Subject("Test Subject");
        Course course = new Course("Test Course", 2021,2, subject);

        assertEquals(subject, course.getSubject());
    }

    @Test
    public void testGetName() {
        String name = "Test Course";
        Course course = new Course(name, 2021, 2,new Subject());

        assertEquals(name, course.getName());
    }

    @Test
    public void testGetYear() {
        int year = 2021;
        Course course = new Course("Test Course", year,1, new Subject());

        assertEquals(year, course.getYear());
    }

    @Test
    public void testGetId() {
        Course course = new Course();

        assertEquals(null, course.getId());
    }

    @Test
    public void testSetName() {
        Course course = new Course();
        String name = "Test Course";
        course.setName(name);
        assertEquals(name, course.getName());
    }

    @Test
    public void testSetYear() {
        Course course = new Course();
        int year = 2021;
        course.setYear(year);
        assertEquals(year, course.getYear());
    }

    @Test
    public void testSetSubject() {
        Course course = new Course();
        Subject subject = new Subject("Test Subject");
        course.setSubject(subject);
        assertEquals(subject, course.getSubject());
    }
}