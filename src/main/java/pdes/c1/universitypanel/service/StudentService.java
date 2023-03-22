package pdes.c1.universitypanel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pdes.c1.universitypanel.repositories.StudentRepository;

@Service
public class StudentService {	
	private StudentRepository studentRepository;
	
	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
		
		this.studentRepository.generateData();
	}

	public String helloWorld() {
		return this.studentRepository.helloWorld();
	}
}
