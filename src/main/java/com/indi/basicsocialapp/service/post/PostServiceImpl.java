package com.indi.basicsocialapp.service.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.indi.basicsocialapp.dao.posts.PostDao;
import com.indi.basicsocialapp.entity.posts.Post;
import com.indi.basicsocialapp.model.SecPost;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostDao postDao;
	
	@Override
	@Transactional
	public Post addPost(Post post) {
		return postDao.addPost(post);
	}

	@Override
	@Transactional
	public void removePost(int id, int userId) {
		postDao.removePost(id, userId);
	}

	@Override
	@Transactional
	public List<SecPost> getPosts(int userId, int pageNumber, int limit) {
		return postDao.getPosts(userId, pageNumber, limit);
	}

	@Override
	@Transactional
	public List<SecPost> getWallPosts(List<Integer> followingUsers, int pageNumber, int limit) {
		return postDao.getWallPosts(followingUsers, pageNumber, limit);
	}

}
