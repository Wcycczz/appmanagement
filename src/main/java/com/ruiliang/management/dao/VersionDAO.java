package com.ruiliang.management.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ruiliang.management.pojo.Version;


/**
 * @author LinJian.Liu
 *
 */
@Mapper
public interface VersionDAO {
	
	Version selectVersionByid(String id);
	
	int insert(Version v);
	
	int update(Version v);
	
	int getCount(@Param("key") String key);
	
	void deleteVersion(int[] ids);
	
	List<Version> selectVersion(@Param("key") String key,@Param("pageindex") int pageindex,@Param("pagesize") int pagesize);
}
