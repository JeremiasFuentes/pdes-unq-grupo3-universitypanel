package pdes.c1.universitypanel.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pdes.c1.universitypanel.model.Course;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
}
