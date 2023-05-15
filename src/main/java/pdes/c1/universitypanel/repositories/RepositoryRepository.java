package pdes.c1.universitypanel.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pdes.c1.universitypanel.model.GitRepository;

@Repository
public interface RepositoryRepository extends CrudRepository<GitRepository, Long> {
}