package com.ruiliang.management.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ruiliang.management.pojo.UserInfo;

@Mapper
public interface UserInfoDAO {
	int selectManager(String cid);
	
	int insert(UserInfo userinfo);
	
	void deleteUser(int[] ids);
	
	UserInfo selectByUserid(String uid);

	int update(UserInfo userinfo);
	
	int getCount(@Param("key") String key);
	
	UserInfo selectUserInfoById(String id);
	
	int check(@Param("n") String n,@Param("idcar") String idcar,@Param("mo") String mo);
	
	List<UserInfo> selectUserInfo(@Param("key") String key,@Param("cid") String cid,@Param("pageindex") int pageindex,@Param("pagesize") int pagesize);
}
