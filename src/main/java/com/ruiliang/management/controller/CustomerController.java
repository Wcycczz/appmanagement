package com.ruiliang.management.controller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruiliang.management.constants.Constants;
import com.ruiliang.management.pojo.Customer;
import com.ruiliang.management.service.CustomerService;
import com.ruiliang.management.util.JSON;


@Controller
@RequestMapping("customer")
public class CustomerController {
	
	private Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private CustomerService cs;
	
	@RequestMapping("toCustomerList")
	public String toCustomerList(){
		return "admin/customer/customerList";
	}
	
	@RequestMapping("getCustomer")
	@ResponseBody
	public String getCustomer(HttpServletRequest request){
		String key = request.getParameter("key");
		String pageIndex = request.getParameter("pageIndex");
		String pageSize = request.getParameter("pageSize");
		int count = cs.getCount(key);
		List<Customer> customers = cs.selectCustomer(key,Integer.parseInt(pageIndex),Integer.parseInt(pageSize));
		Map<String,Object> map  = new HashMap<String, Object>();
		map.put("data", customers);
		map.put("total",count);
		String string = JSON.encode(map);
		LOG.info("公司数据=====>"+string);
		return string;
	}
	
	@RequestMapping("toCustomer")
	public String toAddCustomer(){
		return "admin/customer/customer";
	}
	
	@RequestMapping("getCustomerByCid")
	@ResponseBody
	public HashMap getCustomerByCid(HttpServletRequest request) throws Exception{
		String id = request.getParameter("id");
		Customer customer = cs.selectCustomerById(id);
		HashMap<String, Object> toMap = convertToMap(customer);
		LOG.info("getCustomerByCid=====>"+toMap);
		return toMap;
	}
	
	
	@RequestMapping("deleteCustomerByids")
	@ResponseBody
	public void deleteCustomerByids(HttpServletRequest request) throws Exception{
		String id = request.getParameter("id");
		String[] ids = id.split(",");
		int[] arr = new int[ids.length];
		for(int i=0; i< ids.length; i++){
			arr[i] = Integer.parseInt(ids[i]);
		}
		cs.deleteCustomer(arr);
	}
	
	@RequestMapping("saveCustomer")
	@ResponseBody
	public JSONObject saveCustomer(HttpServletRequest request){
		JSONObject result = new JSONObject();
		String string = request.getParameter("data");
		if(StringUtils.isBlank(string)){
			result.put("msg", "数据为空");
			result.put("code",-1);
			return result;
		}
		JSONArray object = JSONArray.parseArray(string);
		Iterator<Object> it = object.iterator();
		while(it.hasNext()){
			JSONObject object2 = (JSONObject)it.next();
			String id = object2.getString("id");
			String name = object2.getString("name");
			String cid = object2.getString("cId");
			String cou = object2.getString("mgrQua");
			String key = object2.getString("apiKey");
			Customer ct = new Customer();
			if(!StringUtils.isBlank(id)){
				ct.setId(Integer.parseInt(id));
			}
			ct.setName(name);
			ct.setMgrQua(Integer.parseInt(cou));
			ct.setcId(cid);
			ct.setApiKey(key);
			Customer customer = cs.selectCustomerById(id);
			
			if(null == customer){
				int check2 = cs.check("",name,"");
				if(check2 >= 1){
					result.put("msg", "公司名已存在");
					result.put("code",-1);
					return result;
				}
				int check3 = cs.check("","",cid);
				if(check3 >= 1){
					result.put("msg", "公司编码已存在");
					result.put("code",-1);
					return result;
				}
				int check1 = cs.check(key,"","");
				if(check1 >= 1){
					result.put("msg", "apiKey已存在");
					result.put("code",-1);
					return result;
				}
				int i = cs.create(ct);
				if(i != 1){
					result.put("msg", "保存错误");
					result.put("code",-1);
					return result;
				}
			}else{
				int i = cs.update(ct);
				if(i != 1){
					result.put("msg", "更新错误");
					result.put("code",-1);
					return result;
				}
			}
		}
		result.put("msg", "保存成功");
		result.put("code", 0);
		return result;
	}
	
	
	
	private  HashMap<String, Object> convertToMap(Object obj)
            throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (int i = 0, len = fields.length; i < len; i++) {
            String varName = fields[i].getName();
            boolean accessFlag = fields[i].isAccessible();
            fields[i].setAccessible(true);

            Object o = fields[i].get(obj);
            if (o != null)
                map.put(varName, o.toString());

            fields[i].setAccessible(accessFlag);
        }
        return map;
    }
}
