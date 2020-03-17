package com.indi.basicsocialapp.entity.follows;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.indi.basicsocialapp.entity.User;

@Entity
@Table(name = "follows", uniqueConstraints = { @UniqueConstraint( columnNames = { "followerId", "userId" } ) })
public class Follow {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@ManyToOne(cascade= {
			CascadeType.DETACH,
			CascadeType.MERGE,
			CascadeType.PERSIST,
			CascadeType.REFRESH
	})
	@JoinColumn(name="followerId")
	private User follower;
	
	@ManyToOne(cascade= {
			CascadeType.DETACH,
			CascadeType.MERGE,
			CascadeType.PERSIST,
			CascadeType.REFRESH
	})
	@JoinColumn(name="userId")
	private User following;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getFollower() {
		return follower;
	}

	public void setFollower(User follower) {
		this.follower = follower;
	}

	public User getFollowing() {
		return following;
	}

	public void setFollowing(User following) {
		this.following = following;
	}

	@Override
	public String toString() {
		return "Follow [id=" + id + "]";
	}

	public Follow() {
		super();
	}

	public Follow(User follower, User following) {
		super();
		this.follower = follower;
		this.following = following;
	}
	
}
