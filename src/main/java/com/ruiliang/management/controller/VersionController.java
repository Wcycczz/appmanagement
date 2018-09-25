package com.ruiliang.management.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruiliang.management.constants.Constants;
import com.ruiliang.management.pojo.Manager;
import com.ruiliang.management.pojo.UserInfo;
import com.ruiliang.management.pojo.Version;
import com.ruiliang.management.service.VersionService;
import com.ruiliang.management.upload.FileUploadService;
import com.ruiliang.management.util.CharUtils;
import com.ruiliang.management.util.JSON;
import com.ruiliang.management.util.MD5Util;


@Controller
@RequestMapping("version")
public class VersionController {
	
	private Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private FileUploadService fs;
	@Autowired
	private VersionService vs;
	
	@RequestMapping("toVersionList")
	public String toVersionList(){
		return "admin/version/versionList";
	}
	
	@RequestMapping("getVersion")
	@ResponseBody
	public String getVersion(HttpServletRequest request){
		String key = request.getParameter("key");
		String pageIndex = request.getParameter("pageIndex");
		String pageSize = request.getParameter("pageSize");
		int count = vs.getCount(key);
		List<Version> users = vs.selectVersion(key,Integer.parseInt(pageIndex),Integer.parseInt(pageSize));
		for (Version version : users) {
			if(version.getForceflag() == 1){
				version.setFf("否");
			}
			if(version.getForceflag() == 2){
				version.setFf("是");
			}
			if(version.getPlatForm()==1){
				version.setPf("安卓");
			}
			if(version.getPlatForm()==2){
				version.setPf("苹果");
			}
		}
		Map<String,Object> map  = new HashMap<String, Object>();
		map.put("data", users);
		map.put("total",count);
		String string = JSON.encode(map);
		LOG.info("版本数据=====>"+string);
		return string;
	}
	
	@RequestMapping("toVersion")
	public String toUser(){
		return "admin/version/version";
	}
	
	/*@RequestMapping("getCus")
	@ResponseBody
	public ArrayList<Customer> getCus(){
		ArrayList<Customer> list = (ArrayList<Customer>)cs.selectAllCustomer();
		return list;
	}
	*/
	@RequestMapping("saveVersion")
	public String saveVersion(@RequestParam("cid") String cid,@RequestParam("ui") String content,Model model,HttpServletRequest request,@RequestParam("file") MultipartFile file,@ModelAttribute Version v){
		if(StringUtils.isNoneBlank(file.getOriginalFilename())){
			String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
			if(!Objects.equals("apk", suffix)){
				model.addAttribute("msg", "必须是APK格式");
				model.addAttribute("code",-1);
				return "admin/frame/result";
			}
			String apk = "";
			try {
				InputStream	inputStream = new ByteArrayInputStream(file.getBytes());
				
				try {
					 apk = fs.copy2Apk(inputStream, suffix, "",cid);
				} catch (Exception e) {
					model.addAttribute("msg", "APK上传错误");
					model.addAttribute("code",-1);
					return "admin/frame/result";
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			v.setDownUrl(apk);
		}
		v.setUpdateInfo(content);
		v.setcTime(new Date());
		v.setCid(cid);
		if(v.getId() == null){
			
			int i = vs.insert(v);
			if(i != 1){
				model.addAttribute("msg", "保存错误");
				model.addAttribute("code",-1);
				return "admin/frame/result";
			}
		}else{
			int i = vs.update(v);
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
	
	@RequestMapping("getVersionByCid")
	@ResponseBody
	public HashMap getUserByCid(HttpServletRequest request) throws Exception{
		String id = request.getParameter("id");
		Version ui = vs.selectVersionByid(id);
		HashMap<String, Object> toMap = convertToMap(ui);
		return toMap;
	}
	
	@RequestMapping("deleteVersionByids")
	@ResponseBody
	public void deleteUserByids(HttpServletRequest request) throws Exception{
		String id = request.getParameter("id");
		String[] ids = id.split(",");
		int[] arr = new int[ids.length];
		for(int i=0; i< ids.length; i++){
			arr[i] = Integer.parseInt(ids[i]);
		}
		vs.deleteVersion(arr);
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
