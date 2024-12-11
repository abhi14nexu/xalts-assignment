package com.xalts.XaltsAssignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.xalts.XaltsAssignment.model.Task;
import com.xalts.XaltsAssignment.model.User;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
	List<Task> findByCreatedBy(User user);
}
