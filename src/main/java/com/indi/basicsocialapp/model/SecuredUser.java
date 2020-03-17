package com.indi.basicsocialapp.model;

import java.util.Date;

import com.indi.basicsocialapp.entity.User;

public class SecuredUser {

	private int id;
	
	private String name;
	
	private String email;
	
	private String mobileNumber;
	
	private Date createdAt;
	
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

	public Date getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", mobileNumber=" + mobileNumber
				+ ", createdAt=" + createdAt + "]";
	}
	
	public SecuredUser() {
		//
	}
	
	public SecuredUser(User user) {
		this.setId(user.getId());
		this.setName(user.getName());
		this.setEmail(user.getEmail());
		this.setMobileNumber(user.getMobileNumber());
		this.setCreatedAt(user.getCreatedAt());
	}
	
}
