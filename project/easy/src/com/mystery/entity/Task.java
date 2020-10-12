package com.mystery.entity;

import java.io.Serializable;

public class Task implements Serializable {
	private Integer id;

	private String email;

	private String content;

	private Integer state;

	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "Task [id=" + id + ", email=" + email + ", content=" + content + ", state=" + state + "]";
	}

	/**
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return state
	 */
	public Integer getState() {
		return state;
	}

	/**
	 * @param state
	 */
	public void setState(Integer state) {
		this.state = state;
	}
}