package com.ruiliang.management.mvcconfig;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ruiliang.management.constants.Constants;
import com.ruiliang.management.pojo.Manager;

@Component
public class SingleAdminLoginInterceptor extends HandlerInterceptorAdapter{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private static Map<String, String> cahe = new HashMap<String, String>(); 
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpServletRequest request2=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		
		//获取SESSION里的用户信息
		Manager m = (Manager) request.getSession().getAttribute(Constants.SESSION_KEY);
		if(null== m){
			return false;
		}
		//得到用户名
		String name = m.getUsername();
		if(name==null || name.equals("")){
			return true ;
		}
		//获取sessionid
		String reqestessionId = request.getSession().getId();
		
		String sessionId = null;
		String ip=null;
			if(cahe.get(name) != null){
			sessionId =  cahe.get(name);
			}
		if(reqestessionId==null || reqestessionId.equals("")){
			return true ;
		}
		if(sessionId==null || sessionId.equals("")){
			if(name != null){
				cahe.put(name, reqestessionId);
			}
			return true ;
		}
		
		if(sessionId.equals(reqestessionId)){
			return true ;
		}
		request.setAttribute("code", "-1");
		request.setAttribute("msg", "您的帐号在异地登录");
		request.getRequestDispatcher("/WEB-INF/jsp/admin/frame/login.jsp").forward(request, response); 
		return false ;
	}
}
