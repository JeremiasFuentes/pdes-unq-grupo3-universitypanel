package pdes.c1.universitypanel.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdes.c1.universitypanel.model.GitRepository;
import pdes.c1.universitypanel.model.Group;
import pdes.c1.universitypanel.service.RepositoryService;

@RestController
@RequestMapping("/repositories")
public class RepositoryController {
    private final RepositoryService repositoryService;

    @Autowired
    public RepositoryController(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }


    @CrossOrigin
    @PostMapping("{id}/createRepository")
    public ResponseEntity<Group> createRepositoryInGroup(@PathVariable Long id, @RequestBody GitRepository gitRepository) {
        Group savedGroup = repositoryService.createRepositoryInGroup(id, gitRepository);
        return ResponseEntity.ok(savedGroup);
    }

    @CrossOrigin
    @GetMapping("/{idRepo}")
    public ResponseEntity<GitRepository> getRepoById(@PathVariable(value = "idRepo") Long idRepo) {
        GitRepository gitRepository = repositoryService.getRepoById(idRepo);
        return ResponseEntity.ok().body(gitRepository);
    }

    @CrossOrigin
    @PutMapping("/{idGroup}/update/{idRepo}")
    public ResponseEntity<GitRepository> updateRepository(@PathVariable(value = "idGroup") Long groupId,
                                                    @PathVariable(value = "idRepo") Long idRepo,
                                                 @RequestBody GitRepository repositoryDetails) {
        GitRepository updatedRepository = repositoryService.updateRepository(groupId, idRepo,repositoryDetails);
        return ResponseEntity.ok(updatedRepository);
    }

    @CrossOrigin
    @DeleteMapping("/{idGroup}/delete/{idRepo}")
    public ResponseEntity<?> deleteStudent(@PathVariable(value = "idGroup") Long groupId,
                                           @PathVariable(value = "idRepo") Long idRepo) {
        repositoryService.deleteRepository(idRepo);
        return ResponseEntity.ok().build();
    }
}
