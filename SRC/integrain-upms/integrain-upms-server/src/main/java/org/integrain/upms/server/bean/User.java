package org.integrain.upms.server.bean;

public class User {
	
	private String userId;
	
	private String password;
	
	public User(String userId,String password) {
		this.password = password;
		this.userId = userId;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
