package com.xalts.XaltsAssignment.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "task_approvals")
@Data
public class TaskApproval {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "task_id", nullable = false)
	private Task task;
	
	@ManyToOne
	@JoinColumn(name = "approver_id", nullable = false)
	private User approver;
	
	private LocalDateTime approvedAt;
	private String comments;
	
	@PrePersist
	protected void onCreate() {
		approvedAt = LocalDateTime.now();
	}
}