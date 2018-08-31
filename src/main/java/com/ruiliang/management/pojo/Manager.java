package com.ruiliang.management.pojo;

import java.util.Date;

public class Manager {
	
	private Integer id;
	
	private String username;
	
	private String password;
	
	private Byte status;
	
	private Date ctime;
	
	private String phone;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Manager [id=" + id + ", username=" + username + ", password="
				+ password + ", status=" + status + ", ctime=" + ctime
				+ ", phone=" + phone + "]";
	}

	
	
	
}
