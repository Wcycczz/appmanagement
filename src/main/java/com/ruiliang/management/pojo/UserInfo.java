package com.ruiliang.management.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author LinJian.Liu 用户类
 */
public class UserInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5974076256526032038L;

	/**
	 * 主键
	 */
	private Integer id;

	/**
	 * 用户ID
	 */
	private String uId;

	/**
	 * 用户密码
	 */
	private String password;

	/**
	 * 用户姓名
	 */
	private String name;

	/**
	 * 身份证号码
	 */
	private String idCard;

	/**
	 * 手机号码
	 */
	private String mobile;

	/**
	 * 性别
	 */
	private Byte gender;

	/**
	 * 邮件地址
	 */
	private String email;

	/**
	 * 所在区域
	 */
	private String area;

	/**
	 * 详细地址
	 */
	private String address;

	/**
	 * 头像路径
	 */
	private String avatar;

	/**
	 * 创建者ID
	 */
	private String creator;

	/**
	 * 用户类型（0 普通用户，1 管理员，2超管）
	 */
	private Byte type;

	/**
	 * 用户状态(0正常，1 限制登录 )
	 */
	private Byte status;

	/**
	 * 最后通话记录上传时间
	 */
	private Long upTimeCalllog;

	/**
	 * 最后短信上传时间
	 */
	private Long upTimeSms;

	/**
	 * 最后通讯录上传时间
	 */
	private Long upTimePim;

	/**
	 * 创建时间
	 */
	private Date cTime;

	private int loginTimes;
	private Date lastLoginTime;
	private String cid;

	private String token;
	private String cname;
	
	
	public String getCname() {
		return cname;
	}


	public void setCname(String cname) {
		this.cname = cname;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getuId() {
		return uId;
	}


	public void setuId(String uId) {
		this.uId = uId;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getIdCard() {
		return idCard;
	}


	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public Byte getGender() {
		return gender;
	}


	public void setGender(Byte gender) {
		this.gender = gender;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getArea() {
		return area;
	}


	public void setArea(String area) {
		this.area = area;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getAvatar() {
		return avatar;
	}


	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}


	public String getCreator() {
		return creator;
	}


	public void setCreator(String creator) {
		this.creator = creator;
	}


	public Byte getType() {
		return type;
	}


	public void setType(Byte type) {
		this.type = type;
	}


	public Byte getStatus() {
		return status;
	}


	public void setStatus(Byte status) {
		this.status = status;
	}


	public Long getUpTimeCalllog() {
		return upTimeCalllog;
	}


	public void setUpTimeCalllog(Long upTimeCalllog) {
		this.upTimeCalllog = upTimeCalllog;
	}


	public Long getUpTimeSms() {
		return upTimeSms;
	}


	public void setUpTimeSms(Long upTimeSms) {
		this.upTimeSms = upTimeSms;
	}


	public Long getUpTimePim() {
		return upTimePim;
	}


	public void setUpTimePim(Long upTimePim) {
		this.upTimePim = upTimePim;
	}


	public Date getcTime() {
		return cTime;
	}


	public void setcTime(Date cTime) {
		this.cTime = cTime;
	}


	public int getLoginTimes() {
		return loginTimes;
	}


	public void setLoginTimes(int loginTimes) {
		this.loginTimes = loginTimes;
	}


	public Date getLastLoginTime() {
		return lastLoginTime;
	}


	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}


	public String getCid() {
		return cid;
	}


	public void setCid(String cid) {
		this.cid = cid;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", uid=" + uId + ", password=" + password + ", name=" + name + ", idcard="
				+ idCard + ", mobile=" + mobile + ", gender=" + gender + ", email=" + email + ", area=" + area
				+ ", address=" + address + ", avatar=" + avatar + ", creator=" + creator + ", type=" + type
				+ ", status=" + status + ", upTimeCalllog=" + upTimeCalllog + ", upTimeSms=" + upTimeSms
				+ ", upTimePim=" + upTimePim + ", ctime=" + cTime + "]";
	}

}
