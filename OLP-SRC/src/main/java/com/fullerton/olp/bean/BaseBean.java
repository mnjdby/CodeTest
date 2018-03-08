package com.fullerton.olp.bean;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

public abstract class BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ToStringBuilder.reflectionToString(this);
	}
}
