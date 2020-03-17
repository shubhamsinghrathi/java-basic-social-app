package com.indi.basicsocialapp.rest.follows;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.indi.basicsocialapp.entity.User;
import com.indi.basicsocialapp.entity.follows.Follow;
import com.indi.basicsocialapp.model.SecuredUser;
import com.indi.basicsocialapp.service.UserService;
import com.indi.basicsocialapp.service.follows.FollowService;

@RestController
@RequestMapping("/api/follow")
public class FollowController {

	@Autowired
	private FollowService followService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/{userId}")
	public ResponseEntity<?> followUser(Authentication authentication, @PathVariable("userId") int userId) throws Exception {
		String email = authentication.getName();
		User user = userService.findUserByEmail(email);
		int followerId = user.getId();
		if (userId == followerId) {
			throw new Exception("can't follow yourself");
		}
		
		Follow follow = new Follow(userService.findUserById(followerId), userService.findUserById(userId));
		followService.addFollow(follow);
		return ResponseEntity.ok("followed successfully");
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> unfollowUser(Authentication authentication, @PathVariable("userId") int userId) {
		String email = authentication.getName();
		User user = userService.findUserByEmail(email);
		int followerId = user.getId();
		followService.removeFollowByUserAndFollowerId(userId, followerId);
		return ResponseEntity.ok("user unfollowed successfully");
	}
	
	@GetMapping("/followers")
	public ResponseEntity<?> getFollowerList(
		Authentication authentication,
		@RequestParam(required = false, value = "pageNumber", defaultValue = "1") int pageNumber,
		@RequestParam(required = false, value = "limit", defaultValue = "2") int limit
	) {
		String email = authentication.getName();
		User user = userService.findUserByEmail(email);
		List<SecuredUser> users = userService.getFollowerList(user.getId(), pageNumber, limit);
		return ResponseEntity.ok(users);
	}
	
	@GetMapping("/followings")
	public ResponseEntity<?> getFollowingList(
		Authentication authentication,
		@RequestParam(required = false, value = "pageNumber", defaultValue = "1") int pageNumber,
		@RequestParam(required = false, value = "limit", defaultValue = "2") int limit
	) {
		String email = authentication.getName();
		User user = userService.findUserByEmail(email);
		List<SecuredUser> users = userService.getFollowingList(user.getId(), pageNumber, limit);
		return ResponseEntity.ok(users);
	}
	
}
