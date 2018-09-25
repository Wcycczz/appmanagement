package com.ruiliang.management.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author LinJian.Liu
 * 版本
 */
public class Version implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6226058384664244252L;

	/**
	 * 主键
	 */
	private Integer id;
	
	/**
	 * 平台 1安卓 2IOS
	 */
	private Byte platForm;
	
	private String pf;
	
	/**
	 * 版本号
	 */
	private Integer code;
	
	/**
	 * 版本信息
	 */
	private String info;
	
	/**
	 * APK 路径
	 */
	private String downUrl;
	
	/**
	 * 更新内容信息
	 */
	private String updateInfo;
	
	/**
	 * 创建时间
	 */
	private Date cTime;
	
	/**
	 * 是否强更 1 否 2是
	 */
	private Byte forceflag;
	
	private String ff;
	
	private String cid;
	
	

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getPf() {
		return pf;
	}

	public void setPf(String pf) {
		this.pf = pf;
	}

	public Byte getForceflag() {
		return forceflag;
	}

	public void setForceflag(Byte forceflag) {
		this.forceflag = forceflag;
	}

	public String getFf() {
		return ff;
	}

	public void setFf(String ff) {
		this.ff = ff;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Byte getPlatForm() {
		return platForm;
	}

	public void setPlatForm(Byte platForm) {
		this.platForm = platForm;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getDownUrl() {
		return downUrl;
	}

	public void setDownUrl(String downUrl) {
		this.downUrl = downUrl;
	}

	public String getUpdateInfo() {
		return updateInfo;
	}

	public void setUpdateInfo(String updateInfo) {
		this.updateInfo = updateInfo;
	}

	public Date getcTime() {
		return cTime;
	}

	public void setcTime(Date cTime) {
		this.cTime = cTime;
	}

	
	public Byte getForceFlag() {
		return forceflag;
	}

	public void setForceFlag(Byte forceflag) {
		this.forceflag = forceflag;
	}

	@Override
	public String toString() {
		return "Version [id=" + id + ", platForm=" + platForm + ", code="
				+ code + ", info=" + info + ", downUrl=" + downUrl
				+ ", updateInfo=" + updateInfo + ", cTime=" + cTime
				+ ", forceflag=" + forceflag + "]";
	}

	
	
	
}
