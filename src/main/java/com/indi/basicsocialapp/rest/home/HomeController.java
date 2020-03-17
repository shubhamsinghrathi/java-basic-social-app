package com.indi.basicsocialapp.rest.home;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.indi.basicsocialapp.entity.User;
import com.indi.basicsocialapp.model.SecPost;
import com.indi.basicsocialapp.service.UserService;
import com.indi.basicsocialapp.service.post.PostService;

@RestController
@RequestMapping("/api/home")
public class HomeController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PostService postService;
	
	@GetMapping
	public ResponseEntity<?> homePagePosts(
		Authentication authentication,
		@RequestParam(required = false, value = "pageNumber", defaultValue = "1") int pageNumber,
		@RequestParam(required = false, value = "limit", defaultValue = "2") int limit
	) {
		String email = authentication.getName();
		User user = userService.findUserByEmail(email);
		List<Integer> ins = userService.getFollowingIds(user.getId());
		
		List<SecPost> posts = postService.getWallPosts(ins, pageNumber, limit);
		return ResponseEntity.ok(posts);
	}
	
}
