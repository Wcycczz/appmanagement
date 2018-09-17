package com.ruiliang.management.pojo;

import java.util.Date;

/**
 * 验证码记录
 * 
 * @author rocky
 *
 */
public class UserVerifyLog {

	/**
	 * 验证码发送方式：短信
	 */
	public static final int TYPE_SMS = 1;
	/**
	 * 验证码发送方式：邮件
	 */
	public static final int TYPE_EMAIL = 2;
	
	/**
	 * 状态：默认（待发送）
	 */
	public static final int FLAG_DEFAULT = 0;
	/**
	 * 状态：发送中
	 */
	public static final int FLAG_SENDING = 1;
	/**
	 * 状态：发送失败
	 */
	public static final int FLAG_FAILURE = 2;
	/**
	 * 状态：发送成功
	 */
	public static final int FLAG_SUCCESS = 4;
	
	/**
	 * 短信验证超时时间：分钟
	 */
	public static final int TIME_OUT_SMS = 10;
	/**
	 * 邮件验证超时时间：分钟
	 */
	public static final int TIME_OUT_EMAIL = 30;

	private Long id;
	private String userid;
	private int type; // 发送方式
	private int verifyType; // 验证类型 1 密码重置
	private String dest;
	private String code;
	private int status; // 状态
	private Date sendTime; // 发送时间
	private int useFlag; // 使用状态 0 未使用 1 已使用

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getVerifyType() {
		return verifyType;
	}

	public void setVerifyType(int verifyType) {
		this.verifyType = verifyType;
	}

	public String getDest() {
		return dest;
	}

	public void setDest(String dest) {
		this.dest = dest;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public int getUseFlag() {
		return useFlag;
	}

	public void setUseFlag(int useFlag) {
		this.useFlag = useFlag;
	}

}
