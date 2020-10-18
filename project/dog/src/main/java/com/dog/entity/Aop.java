package com.dog.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="aop") 
public class Aop {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String type;
	private String method;
	private Date createtime;
	private String args;

	@Override
	public String toString() {
		return "Aop [id=" + id + ", type=" + type + ", method=" + method + ", createtime=" + createtime + ", args=" + args
				+ "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getArgs() {
		return args;
	}

	public void setArgs(String args) {
		this.args = args;
	}

	

}
