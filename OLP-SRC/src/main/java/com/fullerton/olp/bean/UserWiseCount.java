package com.fullerton.olp.bean;

public class UserWiseCount extends BaseBean {
	
	private String user;
	private String count;
	
	public UserWiseCount(String user, String count) {
		super();
		this.user = user;
		this.count = count;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}
	 
	 
	
}
