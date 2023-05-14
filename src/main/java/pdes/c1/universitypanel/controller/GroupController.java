package pdes.c1.universitypanel.controller;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pdes.c1.universitypanel.model.dto.GroupDTO;
import pdes.c1.universitypanel.service.GroupService;
import pdes.c1.universitypanel.utils.ResponseBody;

@RestController
@RequestMapping("/groups")
public class GroupController {
	private final GroupService groupService;

	@Autowired
	public GroupController(GroupService groupService) {
		this.groupService = groupService;
	}

	@CrossOrigin
	@GetMapping("/getAll")
	public ResponseEntity<Map<String, Object>> getGroupsByCourseId(@RequestParam(name = "courseId") Long courseId) {
		return ResponseEntity.ok().body(ResponseBody.create(groupService.getGroupsByCourseId(courseId).stream().map(group -> new GroupDTO(group)).collect(Collectors.toList())));
	}

	@CrossOrigin
	@GetMapping("/getAllStudents")
	public ResponseEntity<Map<String, Object>> getAllStudents(@RequestParam(name = "groupId") Long groupId) {
		return ResponseEntity.ok().body(ResponseBody.create(groupService.getGroupStudents(groupId)));
	}

	@CrossOrigin
	@PostMapping("/create")
	public ResponseEntity<Void> create(@RequestParam(name = "courseId") Long courseId) {
		this.groupService.addGroup(courseId);
		return ResponseEntity.noContent().build();
	}

	@CrossOrigin
	@PutMapping("{id}/addStudent")
	public ResponseEntity<Void> addStudentToGroup(@PathVariable Long id, @RequestParam(name = "studentDni") Integer studentDni) {
		this.groupService.addStudentToGroup(id, studentDni);
		return ResponseEntity.noContent().build();
	}
}
