package com.indi.basicsocialapp.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.indi.basicsocialapp.entity.follows.Follow;
import com.indi.basicsocialapp.entity.posts.Post;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	@Column(name = "mobile_number", nullable = false, unique = true)
	private String mobileNumber;
	
	@Column(name = "password")
	private String password;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date createdAt = new Date();
	
	@OneToMany(mappedBy = "following", cascade = {
			CascadeType.DETACH,
			CascadeType.MERGE,
			CascadeType.PERSIST,
			CascadeType.REFRESH,
			CascadeType.REMOVE
	}, fetch = FetchType.LAZY)
	private List<Follow> followers;
	
	@OneToMany(mappedBy = "follower", cascade = {
			CascadeType.DETACH,
			CascadeType.MERGE,
			CascadeType.PERSIST,
			CascadeType.REFRESH,
			CascadeType.REMOVE
	}, fetch = FetchType.LAZY)
	private List<Follow> followings;
	
	@OneToMany(mappedBy = "user", cascade = {
		CascadeType.ALL
	}, fetch = FetchType.LAZY)
	private List<Post> posts;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public List<Follow> getFollowers() {
		return followers;
	}

	public void setFollowers(List<Follow> followers) {
		this.followers = followers;
	}

	public List<Follow> getFollowings() {
		return followings;
	}

	public void setFollowings(List<Follow> followings) {
		this.followings = followings;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", mobileNumber=" + mobileNumber
				+ ", password=" + password + ", createdAt=" + createdAt + "]";
	}

	public User() {
		//
	}

	public User(String name, String email, String mobileNumber, String password) {
		this.name = name;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.password = password;
	}
	
}
