package com.ruiliang.management.mvcconfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.ruiliang.management.constants.Constants;
import com.ruiliang.management.pojo.Manager;

@Component
public class SupervisorInterceptor extends HandlerInterceptorAdapter{

	Logger logger = LoggerFactory.getLogger(getClass());
	public boolean preHandle(HttpServletRequest request,    
			HttpServletResponse response, Object handler) throws Exception { 
		
		String uri = request.getRequestURI();
		logger.info("request url:"+uri);
		/****获取session判断用户是否登录****/
		Manager m = (Manager) request.getSession().getAttribute(Constants.SESSION_KEY);
		if(null==m){
			//**没有登录就跳转到登录界面**//
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath+"/management/goLogin");
			return false ;
		}
		return true ;
	}	
}
