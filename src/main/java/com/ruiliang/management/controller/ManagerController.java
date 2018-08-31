package com.ruiliang.management.controller;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruiliang.management.constants.Constants;
import com.ruiliang.management.exception.SendCodeFailureException;
import com.ruiliang.management.pojo.Manager;
import com.ruiliang.management.service.ManagerService;
import com.ruiliang.management.service.UserVerifyService;
import com.ruiliang.management.util.JSON;
import com.ruiliang.management.util.MD5Util;

/**
 * @author LinJian.Liu
 * 
 */
@Controller
@RequestMapping("management")
public class ManagerController {

	private static final Logger LOG = LoggerFactory
			.getLogger(ManagerController.class);

	@Autowired
	private ManagerService ms;

	@Autowired
	private UserVerifyService uv;

	/**
	 * 去登录页面
	 * 
	 * @return
	 */
	@RequestMapping("goLogin")
	public String goLogin(HttpSession session) {
		session.invalidate();
		return "admin/frame/login";
	}

	/**
	 * 忘记密码页面
	 * 
	 * @return
	 */
	@RequestMapping("goForget")
	public String goForget() {
		return "admin/frame/foget";
	}

	/**
	 * 修改密码页面
	 * 
	 * @return
	 */
	@RequestMapping("goRest")
	public String goRest(HttpSession session) {
		Manager m = (Manager) session.getAttribute(Constants.SESSION_KEY);
		if (null == m) {
			return "redirect:/management/goLogin";

		} else {
			session.setAttribute("admin", m);
		}
		return "admin/frame/rest";
	}

	/**
	 * 修改密码 方法
	 * 
	 * @param request
	 * @return
	 */
	
	@RequestMapping("updatePwd")
	@ResponseBody
	public JSONObject updatePwd(HttpServletRequest request) {
		JSONObject result = new JSONObject();
		String string = request.getParameter("data");
		if (StringUtils.isBlank(string)) {
			result.put("msg", "数据为空");
			result.put("code", -1);
			return result;
		}
		JSONArray object = JSONArray.parseArray(string);
		Iterator<Object> it = object.iterator();
		while (it.hasNext()) {
			JSONObject object2 = (JSONObject) it.next();
			String name = object2.getString("name");
			String phone = object2.getString("phone");
			String code = object2.getString("code");
			String pwd = object2.getString("pwd");
			String newpwd = object2.getString("newpwd");
			String confpwd = object2.getString("confpwd");

			if(StringUtils.isBlank(name)){//忘记密码
				if(StringUtils.isNoneBlank(phone) && StringUtils.isNoneBlank(code)){
					//验证验证码
					boolean checkCode;
					try {
						checkCode = uv.checkCode(phone, code, 1, 2);
					} catch (SendCodeFailureException e) {
						LOG.error(e.getMessage(),e);
						result.put("code", -1);
						result.put("msg", "短信验证码错误");
						return result;
					}
					if(checkCode){//验证码正确
						if (!Objects.equals(newpwd, confpwd)) {
							result.put("msg", "密码不一致");
							result.put("code", -1);
							return result;
						}
						int i = ms.updatePwdByPhone(MD5Util.MD5Encode(newpwd), phone);
						if(i != 1){
							result.put("msg", "更新密码失败");
							result.put("code", -1);
							return result;
						}
					}
				}
			}else{//更新密码
				Manager manager = ms.loginManager(name, MD5Util.MD5Encode(pwd));
				if (null == manager) {
					result.put("msg", "原密码错误");
					result.put("code", -1);
					return result;
				}

				if (!Objects.equals(newpwd, confpwd)) {
					result.put("msg", "密码不一致");
					result.put("code", -1);
					return result;
				}
				int i = ms.check(name);
				if (i == 0) {
					result.put("msg", "用户不存在");
					result.put("code", -1);
					return result;
				}
				int p = ms.updatePwd(MD5Util.MD5Encode(newpwd), name);
				if (p != 1) {
					result.put("msg", "更新错误");
					result.put("code", -1);
					return result;
				}
			}
		}
		result.put("msg", "更新成功");
		result.put("code", 1);
		return result;
	}

	/**
	 * @param request
	 * @return
	 */
	@RequestMapping("login")
	public String login(HttpServletRequest request,
			RedirectAttributes redirectAttributes, HttpSession session) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
			redirectAttributes.addFlashAttribute("code", -1);
			redirectAttributes.addFlashAttribute("msg", "账号或密码为空");
			// 跳转到去登录页面方法
			return "redirect:/management/goLogin";
		}
		Manager manager = ms
				.loginManager(username, MD5Util.MD5Encode(password));
		if (null == manager) {
			redirectAttributes.addFlashAttribute("code", -1);
			redirectAttributes.addFlashAttribute("msg", "账号或密码错误");
			// 跳转到去登录页面方法
			return "redirect:/management/goLogin";
		}
		if (manager.getStatus() != 0) {
			redirectAttributes.addFlashAttribute("code", -1);
			redirectAttributes.addFlashAttribute("msg", "账号登录状态错误");
			// 跳转到去登录页面方法
			return "redirect:/management/goLogin";
		}
		session.setAttribute(Constants.SESSION_KEY, manager);
		return "redirect:/management/index";
	}

	@RequestMapping("index")
	public String toMain(HttpSession session) {
		Manager m = (Manager) session.getAttribute(Constants.SESSION_KEY);
		if (null == m) {
			return "redirect:/management/goLogin";
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = sdf.format(new Date());
			session.setAttribute("admin", m);
			session.setAttribute("time", time);
		}
		return "admin/frame/index";
	}

	@RequestMapping("toManagerList")
	public String toManagerList() {
		return "admin/manager/adminList";
	}

	@RequestMapping("getManager")
	@ResponseBody
	public String getManager(HttpServletRequest request) {
		String key = request.getParameter("key");
		String pageIndex = request.getParameter("pageIndex");
		String pageSize = request.getParameter("pageSize");
		int count = ms.getCount(key);
		List<Manager> managers = ms.selectManager(key,
				Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", managers);
		map.put("total", count);
		String string = JSON.encode(map);
		LOG.info("用户数据=====>" + string);
		return string;
	}

	@RequestMapping("toManage")
	public String toManage() {
		return "admin/manager/admin";
	}

	@RequestMapping("getManagerByCid")
	@ResponseBody
	public HashMap getManagerByCid(HttpServletRequest request) throws Exception {
		String id = request.getParameter("id");
		Manager man = ms.selectManagerById(id);
		HashMap<String, Object> toMap = convertToMap(man);
		LOG.info("getManagerByCid=====>" + toMap);
		return toMap;
	}

	@RequestMapping("deleteManagerByids")
	@ResponseBody
	public void deleteManagerByids(HttpServletRequest request) throws Exception {
		String id = request.getParameter("id");
		String[] ids = id.split(",");
		int[] arr = new int[ids.length];
		for (int i = 0; i < ids.length; i++) {
			arr[i] = Integer.parseInt(ids[i]);
		}
		ms.deleteManager(arr);
	}

	@RequestMapping("saveManager")
	@ResponseBody
	public JSONObject saveManager(HttpServletRequest request) {
		JSONObject result = new JSONObject();
		String string = request.getParameter("data");
		if (StringUtils.isBlank(string)) {
			result.put("msg", "数据为空");
			result.put("code", -1);
			return result;
		}
		JSONArray object = JSONArray.parseArray(string);
		Iterator<Object> it = object.iterator();
		while (it.hasNext()) {
			JSONObject object2 = (JSONObject) it.next();
			String id = object2.getString("id");
			String username = object2.getString("username");
			String password = object2.getString("password");
			String phone =  object2.getString("phone");
			String status = object2.getString("status");
			Manager ct = new Manager();
			if (!StringUtils.isBlank(id)) {
				ct.setId(Integer.parseInt(id));
			}
			ct.setUsername(username);
			ct.setPassword(MD5Util.MD5Encode(password));
			ct.setPhone(phone);
			ct.setStatus(Byte.parseByte(status));
			Manager manager = ms.selectManagerById(id);
			if (null == manager) {
				int check2 = ms.check(username);
				if (check2 >= 1) {
					result.put("msg", "用户名已存在");
					result.put("code", -1);
					return result;
				}
				int i = ms.insert(ct);
				if (i != 1) {
					result.put("msg", "保存错误");
					result.put("code", -1);
					return result;
				}
			} else {
				int i = ms.update(ct);
				if (i != 1) {
					result.put("msg", "更新错误");
					result.put("code", -1);
					return result;
				}
			}
		}
		result.put("msg", "保存成功");
		result.put("code", 0);
		return result;
	}

	@RequestMapping("sendCode")
	@ResponseBody
	public JSONObject sendCode(HttpServletRequest request) {
		JSONObject res = new JSONObject();
		String phone = request.getParameter("phone");
		if (StringUtils.isBlank(phone)) {
			res.put("code", -1);
			res.put("msg", "手机号码不能为空");
			return res;
		}
		// 发送验证码
		try {
			uv.sendCode(null, phone, 1, 2);
		} catch (SendCodeFailureException e) {
			LOG.error(e.getMessage(), e);
			res.put("code", -1);
			res.put("msg", "验证码发送失败");
			return res;
		}
		return res;
	}

	private HashMap<String, Object> convertToMap(Object obj) throws Exception {
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
