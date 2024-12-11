package com.xalts.XaltsAssignment.dto;

import lombok.Data;
import com.xalts.XaltsAssignment.model.Task.TaskStatus;

@Data
public class TaskRequest {
	private String title;
	private String description;
	private TaskStatus status;
}