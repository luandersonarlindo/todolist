package br.com.luanderson.todolist.task.controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.luanderson.todolist.dto.task.TaskRequest;
import br.com.luanderson.todolist.dto.task.TaskResponse;
import br.com.luanderson.todolist.dto.task.TaskUpdateRequest;
import br.com.luanderson.todolist.task.entity.TaskModel;
import br.com.luanderson.todolist.task.service.TaskService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tasks")
public class TaskController {

	@Autowired
	private TaskService taskService;

	@PostMapping
	public ResponseEntity<Void> create(@RequestBody TaskRequest taskRequest, HttpServletRequest request) {
		var taskModel = new TaskModel();
		BeanUtils.copyProperties(taskRequest, taskModel);

		taskService.save(taskModel, request);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping
	public ResponseEntity<List<TaskResponse>> getAll() {
		var tasks = taskService.findAll();
		var responseList = tasks.stream()
				.map(this::toResponse)
				.collect(Collectors.toList());

		return ResponseEntity.ok(responseList);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable UUID id, @RequestBody TaskUpdateRequest taskRequest) {
		taskService.update(id, taskRequest);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable UUID id) {
		var task = taskService.findById(id);
		taskService.delete(task);

		return ResponseEntity.noContent().build();
	}

	private TaskResponse toResponse(TaskModel task) {
		return new TaskResponse(task.getId(), task.getDescription(), task.getTitle(), task.getPriority(),
				task.getStartAt(),
				task.getEndAt(), task.getIdUser());
	}
}
