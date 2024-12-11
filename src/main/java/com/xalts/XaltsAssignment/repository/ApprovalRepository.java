package com.xalts.XaltsAssignment.repository;

import com.xalts.XaltsAssignment.model.Approval;
import com.xalts.XaltsAssignment.model.Task;
import com.xalts.XaltsAssignment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import javax.persistence.GeneratedValue;

import java.util.Optional;

public interface ApprovalRepository extends JpaRepository<Approval, Long> {
	Optional<Approval> findByTaskAndUser(Task task, User user);
	long countByTaskAndApprovedTrue(Task task);
}