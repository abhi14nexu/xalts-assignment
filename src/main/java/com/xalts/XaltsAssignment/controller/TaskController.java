package com.xalts.XaltsAssignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.xalts.XaltsAssignment.dto.TaskRequest;
import com.xalts.XaltsAssignment.model.Task;
import com.xalts.XaltsAssignment.service.TaskService;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

	@Autowired
	private TaskService taskService;

	@PostMapping
	public ResponseEntity<?> createTask(@RequestBody TaskRequest request, @RequestParam Long userId) {
		try {
			Task task = taskService.createTask(request, userId);
			return ResponseEntity.ok(task);
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/{taskId}")
	public ResponseEntity<?> getTask(@PathVariable Long taskId) {
		try {
			Task task = taskService.getTask(taskId);
			return ResponseEntity.ok(task);
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}