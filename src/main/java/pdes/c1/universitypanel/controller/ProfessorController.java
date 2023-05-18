package pdes.c1.universitypanel.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdes.c1.universitypanel.model.Professor;
import pdes.c1.universitypanel.service.ProfessorService;

@RestController
@RequestMapping("/professors")
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> getProfessorById(@PathVariable Long id) {
        Professor professor = professorService.getProfessorById(id);
        return ResponseEntity.ok(professor);
    }

    @PostMapping("/")
    public ResponseEntity<Professor> createProfessor(@RequestBody Professor professor) {
        Professor savedProfessor = professorService.createProfessor(professor);
        return new ResponseEntity<>(savedProfessor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Professor> updateProfessor(@PathVariable Long id, @RequestBody Professor professor) {
        Professor updatedProfessor = professorService.updateProfessor(id, professor);
        return ResponseEntity.ok(updatedProfessor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfessor(@PathVariable Long id) {
        professorService.deleteProfessor(id);
        return ResponseEntity.noContent().build();
    }
}