package com.xalts.XaltsAssignment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.xalts.XaltsAssignment.dto.TaskApprovalRequest;
import com.xalts.XaltsAssignment.model.*;
import com.xalts.XaltsAssignment.repository.*;

@Service
public class TaskApprovalService {

	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private TaskApprovalRepository taskApprovalRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Transactional
	public TaskApproval approveTask(Long taskId, Long approverId, TaskApprovalRequest request) {
		Task task = taskRepository.findById(taskId)
			.orElseThrow(() -> new RuntimeException("Task not found"));
			
		User approver = userRepository.findById(approverId)
			.orElseThrow(() -> new RuntimeException("Approver not found"));
			
		if (task.getCreatedBy().getId().equals(approverId)) {
			throw new RuntimeException("Task creator cannot approve their own task");
		}
		
		if (taskApprovalRepository.existsByTaskAndApprover(task, approver)) {
			throw new RuntimeException("User has already approved this task");
		}
		
		TaskApproval approval = new TaskApproval();
		approval.setTask(task);
		approval.setApprover(approver);
		approval.setComments(request.getComments());
		
		TaskApproval savedApproval = taskApprovalRepository.save(approval);
		
		// Update task approval count and status
		task.setCurrentApprovals(task.getCurrentApprovals() + 1);
		if (task.getCurrentApprovals() >= task.getRequiredApprovals()) {
			task.setStatus(Task.TaskStatus.APPROVED);
		}
		taskRepository.save(task);
		
		return savedApproval;
	}