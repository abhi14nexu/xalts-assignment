package com.xalts.XaltsAssignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.xalts.XaltsAssignment.repository.UserRepository;
import com.xalts.XaltsAssignment.model.User;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/available-approvers")
	public ResponseEntity<?> getAvailableApprovers(@RequestParam Long excludeUserId) {
		List<User> users = userRepository.findAll().stream()
			.filter(user -> !user.getId().equals(excludeUserId))
			.toList();
		return ResponseEntity.ok(users);
	}
}