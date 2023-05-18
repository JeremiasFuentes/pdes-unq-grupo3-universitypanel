package pdes.c1.universitypanel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pdes.c1.universitypanel.model.Group;

@Repository
public interface GroupRepository extends CrudRepository<Group, Long> {

	@Query(nativeQuery = true, value = "SELECT g.* FROM groups g JOIN course_groups cg ON g.id = cg.groups_id WHERE cg.course_id = ?1")
	List<Group> findGroupsOfCourse(Long id);
}
