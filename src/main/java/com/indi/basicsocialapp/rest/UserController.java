package com.indi.basicsocialapp.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.indi.basicsocialapp.entity.SecUser;
import com.indi.basicsocialapp.entity.User;
import com.indi.basicsocialapp.model.SecuredUser;
import com.indi.basicsocialapp.service.UserService;
import com.indi.basicsocialapp.util.SecurityRelated;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private SecurityRelated securityRelated;

	@Autowired
	private UserService userService;
	
	@GetMapping({ "/get-list" })
	public ResponseEntity<?> GetUserList(
		@RequestParam(required = false, value = "pageNumber", defaultValue = "2") int pageNumber,
		@RequestParam(required = false, value = "limit", defaultValue = "1") int limit
	) {
		List<SecUser> users = userService.getUserList(pageNumber, limit);
		return ResponseEntity.ok(users);
	}
	
	@PostMapping
	public ResponseEntity<?> UserSignup(@RequestBody User user) {
		user.setPassword(securityRelated.encryptPassword(user.getPassword()));
		userService.addUser(user);
		return ResponseEntity.ok(new SecuredUser(user));
	}
	
	@GetMapping
	public ResponseEntity<?> ProfileInfo(Authentication authentication) {
		String email = authentication.getName();
		User user = userService.findUserByEmail(email);
		return ResponseEntity.ok(new SecuredUser(user));
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<?> AnyUserInfo(@PathVariable("userId") int userId) {
		User user = userService.findUserById(userId);
		return ResponseEntity.ok(new SecUser(user));
	}
	
}
