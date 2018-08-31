package com.ruiliang.management.service;

import java.util.List;

import com.ruiliang.management.pojo.Manager;

/**
 * @author LinJian.Liu
 * 管理接口
 */
public interface ManagerService {
	
	int updatePwd(String pwd,String name);
	
	int updatePwdByPhone(String pwd,String p);
 
	int insert(Manager manager);
	
	int update(Manager manager);
	
	List<Manager> selectAllManager();
	
	List<Manager> selectManager(String key,int pageindex, int pagesize);
	
	Manager selectManagerById(String id);
	
	void deleteManager(int[] ids);
	
	int getCount( String key);
	
	int check( String n);
	
	Manager loginManager(String username,String password);
}
