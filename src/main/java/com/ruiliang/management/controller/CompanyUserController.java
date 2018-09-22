package com.ruiliang.management.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ruiliang.management.constants.Constants;
import com.ruiliang.management.pojo.Customer;
import com.ruiliang.management.pojo.Manager;
import com.ruiliang.management.pojo.UserInfo;
import com.ruiliang.management.service.CustomerService;
import com.ruiliang.management.service.UserInfoService;
import com.ruiliang.management.upload.FileUploadService;
import com.ruiliang.management.util.Base64;
import com.ruiliang.management.util.CharUtils;
import com.ruiliang.management.util.DateUtil;
import com.ruiliang.management.util.JSON;
import com.ruiliang.management.util.MD5Util;
import com.ruiliang.management.util.RandomUtil;


@Controller
@RequestMapping("company")
public class CompanyUserController {
	
	private Logger LOG = LoggerFactory.getLogger(getClass());
	
	private static final String dateTimePath = DateUtil.getFormatDateString(new Date(), "yyyyMMdd") ;
	
	private static final String[] imgSuffix = {"gif","jpg","png","jpeg","bmp"} ;
	
	private static long maxSize = 20480;
	
	@Value("${image.server}")
	private String imageServer ;
	
	@Autowired
	private UserInfoService us;
	
	@Autowired
	private CustomerService cs;
	
	@Autowired
	private FileUploadService fs;
	
	@RequestMapping("toUserList")
	public String toCustomerList(){
		return "admin/company/userList";
	}
	
	@RequestMapping("getUser")
	@ResponseBody
	public String getCustomer(HttpServletRequest request){
		String key = request.getParameter("key");
		String pageIndex = request.getParameter("pageIndex");
		String pageSize = request.getParameter("pageSize");
		int count = us.getCount(key);
		List<UserInfo> users = us.selectUserInfo(key,Integer.parseInt(pageIndex),Integer.parseInt(pageSize));
		Map<String,Object> map  = new HashMap<String, Object>();
		map.put("data", users);
		map.put("total",count);
		String string = JSON.encode(map);
		LOG.info("公司数据=====>"+string);
		return string;
	}
	
	@RequestMapping("toUser")
	public String toUser(){
		return "admin/company/user";
	}
	
	@RequestMapping("getCus")
	@ResponseBody
	public ArrayList<Customer> getCus(){
		ArrayList<Customer> list = (ArrayList<Customer>)cs.selectAllCustomer();
		return list;
	}
	
	@RequestMapping("saveUser")
	public String saveUser(Model model,@RequestParam("file") MultipartFile file,@ModelAttribute UserInfo ui,HttpServletRequest request){
		if(StringUtils.isNoneBlank(file.getOriginalFilename())){
			String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
			if(!CharUtils.contains(imgSuffix, suffix)){
				model.addAttribute("msg", "必须是图片格式");
				model.addAttribute("code",-1);
				return "admin/frame/result";
			}
			long size=(long)file.getSize()/1024;
			if(size > maxSize){
				model.addAttribute("msg", "图片大于20M");
				model.addAttribute("code",-1);
				return "admin/frame/result";
		    }
			String chat = "";
			try {
				InputStream	inputStream = new ByteArrayInputStream(file.getBytes());
				
				try {
					 chat = fs.copy2Avatar(inputStream, suffix, "");
				} catch (Exception e) {
					model.addAttribute("msg", "图片上传错误");
					model.addAttribute("code",-1);
					return "admin/frame/result";
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			ui.setAvatar(chat);
			/*String imgurl = imageServer+"/"+dateTimePath+"/"+ui.getIdCard()+"."+suffix;
			ui.setAvatar(dateTimePath+"/"+ui.getIdCard()+"."+suffix);
		    try {
		    	File f = new File(imageServer+"/"+dateTimePath);
		    	if(!f.exists()){
		    		f.mkdir();
		    	}
		    	OutputStream out = new FileOutputStream(imgurl);
				out.write(file.getBytes());
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
				model.addAttribute("msg", "图片上传错误");
				model.addAttribute("code",-1);
				return "admin/frame/result";
			}*/
			
		   
		}
		Manager creator = (Manager) request.getSession().getAttribute(Constants.SESSION_KEY);
		if(null != creator){
			ui.setCreator(creator.getUsername());
		}
		ui.setPassword(MD5Util.MD5Encode(MD5Util.MD5Encode(ui.getPassword())));
		ui.setcTime(new Date());
		ui.setuId(generateUserid());
		if(ui.getId() == null){
			int check = us.check(ui.getName(),"","");
			if(check >= 1){
				model.addAttribute("msg", "用户名已存在");
				model.addAttribute("code",-1);
				return "admin/frame/result";
			}
			int check1 = us.check("",ui.getIdCard(),"");
			if(check1 >= 1){
				model.addAttribute("msg", "身份证号已存在");
				model.addAttribute("code",-1);
				return "admin/frame/result";
			}
			int check2 = us.check("","",ui.getMobile());
			if(check2 >= 1){
				model.addAttribute("msg", "手机号已存在");
				model.addAttribute("code",-1);
				return "admin/frame/result";
			}
			int check3 = us.selectManager(ui.getCid());
			if(check3 >= 1 && ui.getType() == 2){
				model.addAttribute("msg", "超管最多一个");
				model.addAttribute("code",-1);
				return "admin/frame/result";
			}
			ui.setLoginTimes(0);
			int i = us.insert(ui);
			if(i != 1){
				model.addAttribute("msg", "保存错误");
				model.addAttribute("code",-1);
				return "admin/frame/result";
			}
		}else{
			int check3 = us.selectManager(ui.getCid());
			if(check3 >= 1 && ui.getType() == 2){
				model.addAttribute("msg", "超管最多一个");
				model.addAttribute("code",-1);
				return "admin/frame/result";
			}
			int i = us.update(ui);
			if(i != 1){
				model.addAttribute("msg", "更新错误");
				model.addAttribute("code",-1);
				return "admin/frame/result";
			}
		}
		
		model.addAttribute("msg", "保存成功");
		model.addAttribute("code",0);
		return "admin/frame/result";
	}
	
	@RequestMapping("getUserByCid")
	@ResponseBody
	public HashMap getUserByCid(HttpServletRequest request) throws Exception{
		String id = request.getParameter("id");
		UserInfo ui = us.selectUserInfoById(id);
		HashMap<String, Object> toMap = convertToMap(ui);
		LOG.info("getUserByCid=====>"+toMap);
		return toMap;
	}
	
	@RequestMapping("deleteUserByids")
	@ResponseBody
	public void deleteUserByids(HttpServletRequest request) throws Exception{
		String id = request.getParameter("id");
		String[] ids = id.split(",");
		int[] arr = new int[ids.length];
		for(int i=0; i< ids.length; i++){
			arr[i] = Integer.parseInt(ids[i]);
		}
		us.deleteUser(arr);
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
	
	private String generateUserid() {
		String uid = null;
		while (true) {
			uid = RandomUtil.getRandomString(10);
			// 查询是否占用
			UserInfo userid = us.selectByUserid(uid);
			if (null == userid){
				return uid;
			}
		}
	}
}
