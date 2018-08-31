package com.ruiliang.management.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ruiliang.management.pojo.Manager;

/**
 * @author LinJian.Liu
 *
 */
@Mapper
public interface ManagerDAO {
	
	int updatePwd(@Param("pwd") String pwd,@Param("name") String name);
	
	int updatePwdByPhone(@Param("pwd") String pwd,@Param("p") String p);
	
	int insert(Manager manager);
	
	int update(Manager manager);
	
	List<Manager> selectAllManager();
	
	List<Manager> selectManager(@Param("key") String key,@Param("pageindex") int pageindex,@Param("pagesize") int pagesize);
	
	Manager selectManagerById(String id);
	
	void deleteManager(int[] ids);
	
	int getCount(@Param("key") String key);
	
	int check(@Param("n") String n);
	
	Manager loginManager(@Param("username") String username,@Param("password") String password);
}
