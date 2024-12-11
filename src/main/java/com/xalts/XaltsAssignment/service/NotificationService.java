package com.xalts.XaltsAssignment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import com.xalts.XaltsAssignment.model.Task;
import com.xalts.XaltsAssignment.model.User;
import java.util.Map;

@Service
public class NotificationService {
	
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	public void notifyTaskCreator(Task task, User approver) {
		String destination = "/topic/task/" + task.getId() + "/approval";
		messagingTemplate.convertAndSend(destination, 
			Map.of(
				"message", "Task approved by " + approver.getUsername(),
				"taskId", task.getId(),
				"approverId", approver.getId()
			));
	}
	
	public void notifyTaskCompletion(Task task) {
		String destination = "/topic/task/" + task.getId() + "/completed";
		messagingTemplate.convertAndSend(destination, 
			Map.of(
				"message", "Task has received all approvals",
				"taskId", task.getId()
			));
	}
}