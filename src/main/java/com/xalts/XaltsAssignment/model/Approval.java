package com.xalts.XaltsAssignment.model;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "approvals")
public class Approval {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "task_id", nullable = false)
	private Task task;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	private boolean approved;
	
	private String comment;

	private LocalDateTime approvalDate = LocalDateTime.now();
}