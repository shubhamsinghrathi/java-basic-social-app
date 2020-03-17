package com.indi.basicsocialapp.model;

import java.util.Date;

import com.indi.basicsocialapp.entity.User;
import com.indi.basicsocialapp.entity.posts.Post;

public class SecPost {

	private int id;
	
	private String value;
	
	private SecuredUser user;
	
	private Date createdAt;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public SecuredUser getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = new SecuredUser(user);
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public SecPost() {
		super();
	}

	public SecPost(int id, User fullUser, String value) {
		super();
		this.id = id;
		this.value = value;
		this.user = new SecuredUser(fullUser);
	}
	
	public SecPost(Post post) {
		this.id = post.getId();
		this.value = post.getValue();
		this.user = new SecuredUser(post.getUser());
		this.createdAt = post.getCreatedAt();
	}
	
}
