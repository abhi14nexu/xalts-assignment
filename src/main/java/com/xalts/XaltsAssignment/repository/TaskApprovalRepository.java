package com.xalts.XaltsAssignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.xalts.XaltsAssignment.model.TaskApproval;
import com.xalts.XaltsAssignment.model.Task;
import com.xalts.XaltsAssignment.model.User;

public interface TaskApprovalRepository extends JpaRepository<TaskApproval, Long> {
	boolean existsByTaskAndApprover(Task task, User approver);
	int countByTask(Task task);
}