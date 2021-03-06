package com.indi.basicsocialapp.dao.posts;

import java.util.List;

import com.indi.basicsocialapp.entity.posts.Post;
import com.indi.basicsocialapp.model.SecPost;

public interface PostDao {

	public Post addPost(Post post);
	
	public void removePost(int id, int userId);

	public List<SecPost> getPosts(int userId, int pageNumber, int limit);

	public List<SecPost> getWallPosts(List<Integer> followingUsers, int pageNumber, int limit);
	
}
