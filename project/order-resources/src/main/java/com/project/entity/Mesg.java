package com.project.entity;


public class Mesg {

	private Integer id;
	private String content;
	private Integer state;

	@Override
	public String toString() {
		return "Mes [id=" + id + ", content=" + content + ", state=" + state + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}