package com.indi.basicsocialapp.rest.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.indi.basicsocialapp.entity.User;
import com.indi.basicsocialapp.entity.posts.Post;
import com.indi.basicsocialapp.model.SecPost;
import com.indi.basicsocialapp.service.UserService;
import com.indi.basicsocialapp.service.post.PostService;

@RestController
@RequestMapping("/api/post")
public class PostController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PostService postService;
	
	@PostMapping
	public ResponseEntity<?> addPost(Authentication authentication, @RequestBody Post post) {
		String email = authentication.getName();
		User user = userService.findUserByEmail(email);
		post.setUser(user);
		postService.addPost(post);
		return ResponseEntity.ok(new SecPost(post));
	}
	
	@DeleteMapping("/{postId}")
	public ResponseEntity<?> removePost(Authentication authentication, @PathVariable("postId") int postId) {
		String email = authentication.getName();
		User user = userService.findUserByEmail(email);
		int userId = user.getId();
		postService.removePost(postId, userId);
		return ResponseEntity.ok("post deleted successfully");
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<?> getPosts(
		@PathVariable("userId") int userId,
		@RequestParam(required = false, value = "pageNumber", defaultValue = "1") int pageNumber,
		@RequestParam(required = false, value = "limit", defaultValue = "2") int limit
	) {
		List<SecPost> secPosts = postService.getPosts(userId, pageNumber, limit);
		return ResponseEntity.ok(secPosts);
	}
	
}
