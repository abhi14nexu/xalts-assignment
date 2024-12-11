package com.xalts.XaltsAssignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.xalts.XaltsAssignment.dto.TaskApprovalRequest;
import com.xalts.XaltsAssignment.model.TaskApproval;
import com.xalts.XaltsAssignment.service.TaskApprovalService;

@RestController
@RequestMapping("/api/tasks")
public class TaskApprovalController {

	@Autowired
	private TaskApprovalService taskApprovalService;

	@PostMapping("/{taskId}/approve")
	public ResponseEntity<?> approveTask(
			@PathVariable Long taskId,
			@RequestParam Long approverId,
			@RequestBody TaskApprovalRequest request) {
		try {
			TaskApproval approval = taskApprovalService.approveTask(taskId, approverId, request);
			return ResponseEntity.ok(approval);
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}