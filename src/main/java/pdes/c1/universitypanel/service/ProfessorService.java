package pdes.c1.universitypanel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdes.c1.universitypanel.exceptions.ResourceNotFoundException;
import pdes.c1.universitypanel.model.Professor;
import pdes.c1.universitypanel.repositories.ProfessorRepository;

@Service
public class ProfessorService {

    @Autowired
    private final ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public Professor getProfessorById(Long id) {
        return professorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Professor", "id", id));
    }

    public Professor createProfessor(Professor professor) {
        return professorRepository.save(professor);
    }

    public Professor updateProfessor(Long id, Professor professor) {
        Professor existingProfessor = professorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Professor", "id", id));

        existingProfessor.setName(professor.getName());
        existingProfessor.setDni(professor.getDni());
        existingProfessor.setMail(professor.getMail());
        existingProfessor.setCourses(professor.getCourses());

        return professorRepository.save(existingProfessor);
    }

    public void deleteProfessor(Long id) {
        professorRepository.deleteById(id);
    }
}