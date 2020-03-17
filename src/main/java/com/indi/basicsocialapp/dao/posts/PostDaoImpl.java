package com.indi.basicsocialapp.dao.posts;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.indi.basicsocialapp.entity.posts.Post;
import com.indi.basicsocialapp.model.SecPost;

@Repository
public class PostDaoImpl implements PostDao {
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public Post addPost(Post post) {
		entityManager.persist(post);
		return post;
	}

	@Override
	public void removePost(int id, int userId) {
		Query deletePostQuery = entityManager.createQuery("delete from Post where id=:id and userId=:userId");
		deletePostQuery.setParameter("id", id);
		deletePostQuery.setParameter("userId", userId);
		deletePostQuery.executeUpdate();
	}

	@Override
	public List<SecPost> getPosts(int userId, int pageNumber, int limit) {
		Query postsQuery = entityManager.createQuery("from Post where userId=:userId", Post.class);
		postsQuery.setParameter("userId", userId);
		postsQuery.setFirstResult((pageNumber - 1) * limit);
		postsQuery.setMaxResults(limit);
		
		@SuppressWarnings("unchecked")
		List<Post> posts = postsQuery.getResultList();
		
		ArrayList<SecPost> secPosts = new ArrayList<SecPost>();
		for (Post post : posts) {
			secPosts.add(new SecPost(post));
		}
		
		return secPosts;
	}

	@Override
	public List<SecPost> getWallPosts(List<Integer> followingUsers, int pageNumber, int limit) {
		Query postsQuery = entityManager.createQuery("from Post where userId in (:userIds)", Post.class);
		postsQuery.setParameter("userIds", followingUsers);
		postsQuery.setFirstResult((pageNumber - 1) * limit);
		postsQuery.setMaxResults(limit);
		
		@SuppressWarnings("unchecked")
		List<Post> posts = postsQuery.getResultList();
		
		ArrayList<SecPost> secPosts = new ArrayList<SecPost>();
		posts.forEach(post -> secPosts.add(new SecPost(post)));
		
		return secPosts;
	}

}
