package com.amirtha.model;

public class User {
	
	private String username;
	private String role;
	private boolean enabled;
	
	public User(String username, String role, boolean enabled) {
		super();
		this.username = username;
		this.role = role;
		this.enabled = enabled;
	}
	
	public User() {

	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
}
