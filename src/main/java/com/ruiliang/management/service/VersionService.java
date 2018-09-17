package com.ruiliang.management.service;

import java.util.List;

import com.ruiliang.management.pojo.Version;


/**
 * @author LinJian.Liu
 *
 */
public interface VersionService {
	
	
	Version selectVersionByid(String id);
	
	int insert(Version v);
	
	int update(Version v);
	
	int getCount(String key);
	
	void deleteVersion(int[] ids);
	
	List<Version> selectVersion(String key,int pageindex, int pagesize);
}
