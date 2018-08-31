package com.ruiliang.management.service;

import java.util.List;

import com.ruiliang.management.pojo.Customer;


public interface CustomerService {

	int create(Customer customer);

	int update(Customer customer);

	Customer selectCustomerByCid(String cid);
	
	List<Customer> selectAllCustomer();
	
	List<Customer> selectCustomer(String key,int pageindex,int pagesize);
	
	Customer selectCustomerById(String id);
	
	void deleteCustomer(int[] ids);
	
	int getCount(String key);
	
	int check(String name,String cid,String key);
}