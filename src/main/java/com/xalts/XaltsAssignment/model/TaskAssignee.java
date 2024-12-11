package com.xalts.XaltsAssignment.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "task_assignees")
@Data
public class TaskAssignee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "task_id", nullable = false)
	private Task task;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	private boolean hasApproved = false;
	private boolean notified = false;
}