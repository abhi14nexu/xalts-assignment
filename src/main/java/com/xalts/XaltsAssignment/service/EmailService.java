package com.xalts.XaltsAssignment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.xalts.XaltsAssignment.model.User;
import com.xalts.XaltsAssignment.model.Task;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public void sendTaskAssignmentEmail(User user, Task task) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(user.getEmail());
		message.setSubject("New Task Approval Request");
		message.setText("You have been assigned to approve task: " + task.getTitle());
		mailSender.send(message);
	}
	
	public void sendTaskApprovalNotification(Task task, User approver) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(task.getCreatedBy().getEmail());
		message.setSubject("Task Approval Update");
		message.setText("Task: " + task.getTitle() + " has been approved by " + approver.getUsername());
		mailSender.send(message);
	}
	
	public void sendTaskCompletionNotification(Task task) {
		// Send to all involved parties
		SimpleMailMessage message = new SimpleMailMessage();
		message.setSubject("Task Fully Approved");
		message.setText("Task: " + task.getTitle() + " has received all required approvals");
		
		task.getApprovals().forEach(approval -> {
			message.setTo(approval.getApprover().getEmail());
			mailSender.send(message);
		});
		
		// Send to creator
		message.setTo(task.getCreatedBy().getEmail());
		mailSender.send(message);
	}
}