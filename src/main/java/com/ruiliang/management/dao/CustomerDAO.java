package com.ruiliang.management.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ruiliang.management.pojo.Customer;


@Mapper
public interface CustomerDAO {

	int insert(Customer customer);
	
	int update(Customer customer);
	
	Customer selectCustomerByCid(String cid);
	
	List<Customer> selectAllCustomer();
	
	List<Customer> selectCustomer(@Param("key") String key,@Param("pageindex") int pageindex,@Param("pagesize") int pagesize);
	
	Customer selectCustomerById(String id);
	
	void deleteCustomer(int[] ids);
	
	int getCount(@Param("key") String key);
	
	int check(@Param("n") String n,@Param("cid") String cid,@Param("key") String key);
}
