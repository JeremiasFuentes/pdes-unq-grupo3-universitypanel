package pdes.c1.universitypanel.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pdes.c1.universitypanel.model.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

}
