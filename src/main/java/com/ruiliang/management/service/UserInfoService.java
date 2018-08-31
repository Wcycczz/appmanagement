package com.ruiliang.management.service;

import java.util.List;

import com.ruiliang.management.pojo.UserInfo;

public interface UserInfoService {

	int insert(UserInfo userinfo);
	
	UserInfo selectByUserid(String uid);
	
	int update(UserInfo userinfo);
	
	void deleteUser(int[] ids);
	
	int getCount(String key);
	
	UserInfo selectUserInfoById(String id);
	
	int check(String n,String idcar,String mo);
	
	List<UserInfo> selectUserInfo(String key,int pageindex,int pagesize);
}
