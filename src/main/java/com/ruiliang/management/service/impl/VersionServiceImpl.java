package com.ruiliang.management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruiliang.management.dao.VersionDAO;
import com.ruiliang.management.pojo.Version;
import com.ruiliang.management.service.VersionService;


/**
 * @author LinJian.Liu
 *
 */
@Service
public class VersionServiceImpl implements VersionService{

	@Autowired
	private VersionDAO vDao;
	

	@Override
	public int insert(Version v) {
		return vDao.insert(v);
	}

	@Override
	public int update(Version v) {
		return vDao.update(v);
	}

	@Override
	public int getCount(String key) {
		return vDao.getCount(key);
	}

	@Override
	public void deleteVersion(int[] ids) {
		vDao.deleteVersion(ids);
	}

	@Override
	public List<Version> selectVersion(String key, int pageindex, int pagesize) {
		pageindex = pageindex+1;
		return vDao.selectVersion(key,(pageindex-1)*pagesize,pagesize);
	}

	@Override
	public Version selectVersionByid(String id) {
		return vDao.selectVersionByid(id);
	}

}
