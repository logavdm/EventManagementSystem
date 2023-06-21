package com.amirtha.model;

import java.util.Date;

public class User {
	
	private Long id;
	private String username;
	private String password;
	private String role;
	private boolean enabled;
	
	private Date createdAt;
	private Date updatedAt;
	
	public User(Long id,String username,String password, String role, boolean enabled,Date createdAt,Date updatedAt) {
		this.id=id;
		this.username = username;
		this.password=password;
		this.role = role;
		this.enabled = enabled;
		this.createdAt=createdAt;
		this.updatedAt=updatedAt;
	}
	
	public User() {

	}
		
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
}
