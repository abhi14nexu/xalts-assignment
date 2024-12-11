package com.xalts.XaltsAssignment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xalts.XaltsAssignment.dto.TaskRequest;
import com.xalts.XaltsAssignment.model.Task;
import com.xalts.XaltsAssignment.model.User;
import com.xalts.XaltsAssignment.repository.TaskRepository;
import com.xalts.XaltsAssignment.repository.UserRepository;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private UserRepository userRepository;

	public Task createTask(TaskRequest request, Long userId) {
		User user = userRepository.findById(userId)
			.orElseThrow(() -> new RuntimeException("User not found"));

		Task task = new Task();
		task.setTitle(request.getTitle());
		task.setDescription(request.getDescription());
		task.setStatus(request.getStatus());
		task.setCreatedBy(user);
		
		return taskRepository.save(task);
	}

	public Task getTask(Long taskId) {
		return taskRepository.findById(taskId)
			.orElseThrow(() -> new RuntimeException("Task not found"));
	}
}