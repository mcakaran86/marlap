package com.marlap.bean;

import java.sql.Timestamp;

public class SecurityBean {
	private String question;
	private Integer ans;
	private Timestamp createdOn;
	private String uniqueId;

	SecurityBean() {
	}

	public SecurityBean(String question, Integer ans) {
		this.question = question;
		this.ans = ans;
		this.createdOn = new Timestamp(System.currentTimeMillis());
		this.uniqueId = java.util.UUID.randomUUID().toString();
	}

	public String getQuestion() {
		return question;
	}

	public Integer getAns() {
		return ans;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public String getUniqueId() {
		return uniqueId;
	}

}