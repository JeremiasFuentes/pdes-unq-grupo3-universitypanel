package pdes.c1.universitypanel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdes.c1.universitypanel.exceptions.ResourceNotFoundException;
import pdes.c1.universitypanel.model.Group;
import pdes.c1.universitypanel.model.GitRepository;
import pdes.c1.universitypanel.model.Student;
import pdes.c1.universitypanel.repositories.RepositoryRepository;
import pdes.c1.universitypanel.repositories.StudentRepository;

@Service
public class RepositoryService {


    private final RepositoryRepository repositoryRepository;
    private final CourseService courseService;
    private final StudentService studentService;
    private final GroupService groupService;
    private final StudentRepository studentRepository;

    @Autowired
    public RepositoryService(RepositoryRepository repositoryRepository,CourseService courseService, StudentService studentService, GroupService groupService,
                        StudentRepository studentRepository) {
        this.repositoryRepository = repositoryRepository;
        this.courseService = courseService;
        this.groupService = groupService;
        this.studentRepository = studentRepository;
        this.studentService = studentService;
    }

    public Group createRepositoryInGroup(Long id, GitRepository gitRepository) {
        Group group = groupService.getGroupById(id);
        group.addRepositorie(gitRepository);
        gitRepository.setGroup(group);
        this.repositoryRepository.save(gitRepository);
        this.groupService.save(group);
        return group;
    }

    public GitRepository getRepoById(Long idRepo) {
        return this.repositoryRepository.findById(idRepo).orElseThrow(() -> new ResourceNotFoundException("Repository", "id", idRepo));
    }

    public GitRepository updateRepository(Long groupId, Long idRepo, GitRepository repositoryDetails) {
        GitRepository repository = this.getRepoById(idRepo);
        repository.setOwner(repositoryDetails.getOwner());
        repository.setName(repositoryDetails.getName());
        repository.setToken(repositoryDetails.getToken());

        return repositoryRepository.save(repository);
    }

    public void deleteRepository(Long idRepo) {
        GitRepository repository = this.getRepoById(idRepo);
        repositoryRepository.delete(repository);
    }
}
