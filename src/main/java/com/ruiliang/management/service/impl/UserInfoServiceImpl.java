package com.ruiliang.management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruiliang.management.dao.UserInfoDAO;
import com.ruiliang.management.pojo.UserInfo;
import com.ruiliang.management.service.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService{

	@Autowired
	private UserInfoDAO uDao;
	
	@Override
	public int insert(UserInfo userinfo) {
		return uDao.insert(userinfo);
	}

	@Override
	public int getCount(String key) {
		return uDao.getCount(key);
	}

	@Override
	public List<UserInfo> selectUserInfo(String key, int pageindex, int pagesize) {
		pageindex = pageindex+1;
		return uDao.selectUserInfo(key,(pageindex-1)*pagesize,pagesize);
	}

	@Override
	public UserInfo selectUserInfoById(String id) {
		return uDao.selectUserInfoById(id);
	}

	@Override
	public int check(String n,String idcar,String mo) {
		return uDao.check(n,idcar,mo);
	}

	@Override
	public int update(UserInfo userinfo) {
		return uDao.update(userinfo);
	}

	@Override
	public UserInfo selectByUserid(String uid) {
		return uDao.selectByUserid(uid);
	}

	@Override
	public void deleteUser(int[] ids) {
		uDao.deleteUser(ids);
	}

	@Override
	public int selectManager(String cid) {
		return uDao.selectManager(cid);
	}

}
