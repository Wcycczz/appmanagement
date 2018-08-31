package com.ruiliang.management.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author LinJian.Liu
 * 客户类
 */
public class Customer implements Serializable{

	/**
	 * Serializable
	 */
	private static final long serialVersionUID = -4180419574386237287L;

	/**
	 * 主键
	 */
	private Integer id;
	
	/**
	 * 公司编码
	 */
	private String cId;
	
	/**
	 * 公司名称
	 */
	private String name;
	
	/**
	 * 公司APIKEY
	 */
	private String apiKey;
	
	/**
	 * 管理员数量
	 */
	private Integer mgrQua;
	
	/**
	 * 创建时间
	 */
	private Date cTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getcId() {
		return cId;
	}

	public void setcId(String cId) {
		this.cId = cId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public Integer getMgrQua() {
		return mgrQua;
	}

	public void setMgrQua(Integer mgrQua) {
		this.mgrQua = mgrQua;
	}

	public Date getcTime() {
		return cTime;
	}

	public void setcTime(Date cTime) {
		this.cTime = cTime;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", cId=" + cId + ", name=" + name
				+ ", apiKey=" + apiKey + ", mgrQua=" + mgrQua + ", cTime="
				+ cTime + "]";
	}
	
	
}