package com.indi.basicsocialapp.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "users")
public class SecUser {

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
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date createdAt = new Date();

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

	public SecUser() {
		//
	}
	
	public SecUser(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.mobileNumber = user.getMobileNumber();
		this.createdAt = user.getCreatedAt();
	}

	public SecUser(String name, String email, String mobileNumber, String password) {
		this.name = name;
		this.email = email;
		this.mobileNumber = mobileNumber;
	}
	
}
