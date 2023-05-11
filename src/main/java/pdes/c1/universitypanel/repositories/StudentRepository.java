package pdes.c1.universitypanel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pdes.c1.universitypanel.model.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {
	
	@Query(nativeQuery = true, value = "SELECT s.* FROM students s JOIN groups_students gs ON s.dni = gs.students_dni WHERE gs.groups_id = ?1")
	List<Student> findStudentsOfGroup(Long groupId);
}
