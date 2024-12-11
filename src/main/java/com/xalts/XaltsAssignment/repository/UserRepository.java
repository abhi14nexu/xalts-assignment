package com.xalts.XaltsAssignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.xalts.XaltsAssignment.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	boolean existsByEmail(String email);
	boolean existsByUsername(String username);
}
	boolean existsByEmail(String email);
}