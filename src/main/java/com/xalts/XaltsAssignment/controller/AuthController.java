package com.xalts.XaltsAssignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.xalts.XaltsAssignment.dto.SignupRequest;
import com.xalts.XaltsAssignment.model.User;
import com.xalts.XaltsAssignment.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private UserService userService;

	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody SignupRequest request) {
		try {
			User user = userService.signup(request);
			return ResponseEntity.ok(user);
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}