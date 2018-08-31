package com.ruiliang.management.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruiliang.management.dao.CustomerDAO;
import com.ruiliang.management.pojo.Customer;
import com.ruiliang.management.service.CustomerService;
import com.ruiliang.management.util.Base64;


@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDAO customerDao;

	private static Map<String, Customer> CUSTOMER_CACHE = new HashMap<String, Customer>();

	@Override
	public int create(Customer customer) {
		if (null == customer.getApiKey()) {
			customer.setApiKey(generateApiKey(customer.getcId()));
		}
		return customerDao.insert(customer);
	}

	private String generateApiKey(String cid) {
		return Base64.encode((cid + System.currentTimeMillis()).getBytes());
	}

	@Override
	public int update(Customer customer) {
		return customerDao.update(customer);
	}

	@Override
	public Customer selectCustomerByCid(String cid) {
		if (CUSTOMER_CACHE.containsKey(cid))
			return CUSTOMER_CACHE.get(cid);
		else {
			Customer cus = customerDao.selectCustomerByCid(cid);

			if (cus != null) {
				synchronized (CUSTOMER_CACHE) {
					CUSTOMER_CACHE.put(cid, cus);
				}
				return cus;
			}

			return null;
		}

	}

	@Override
	public List<Customer> selectCustomer(String key,int pageindex,int pagesize) {
		pageindex = pageindex+1;
		return customerDao.selectCustomer(key,(pageindex-1)*pagesize,pagesize);
	}

	@Override
	public Customer selectCustomerById(String id) {
		return customerDao.selectCustomerById(id);
	}

	@Override
	public void deleteCustomer(int[] ids) {
		customerDao.deleteCustomer(ids);		
	}

	@Override
	public int getCount(String key) {
		return customerDao.getCount(key);
	}

	@Override
	public int check(String key,String name,String cid) {
		return customerDao.check(key,name,cid);
	}

	@Override
	public List<Customer> selectAllCustomer() {
		return customerDao.selectAllCustomer();
	}

}
