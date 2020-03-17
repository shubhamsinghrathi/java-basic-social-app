package com.indi.basicsocialapp.entity.posts;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.indi.basicsocialapp.entity.User;

@Entity
@Table(name = "posts")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@ManyToOne(cascade = {
			CascadeType.DETACH,
			CascadeType.MERGE,
			CascadeType.PERSIST,
			CascadeType.REFRESH
	}, fetch = FetchType.LAZY)
	@JoinColumn(name="userId")
	private User user;
	
	@Column(name = "value")
	private String value;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date createdAt = new Date();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", value=" + value + ", createdAt=" + createdAt + "]";
	}

	public Post(User user, String value) {
		super();
		this.user = user;
		this.value = value;
	}

	public Post(String value) {
		super();
		this.value = value;
	}

	
	public Post() {
		super();
	}
	
}
