package com.fullerton.olp.bean;

public class StateWiseCount extends BaseBean {
	
	private String state;
	private String count;
	
	public StateWiseCount(String state, String count) {
		super();
		this.state = state;
		this.count = count;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	 
	
}
