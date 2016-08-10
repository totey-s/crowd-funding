package com.neu.kickstarter_experimental.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.neu.kickstarter_experimental.pojo.User;


public class SampleInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
//		response.getWriter().write("Website is closed");
		/*System.out.println("Interceptor");
		HttpSession userSession = request.getSession(false);	
		User user = (User)userSession.getAttribute("user");
		if(user != null){
			return true;
		}else{
			response.getWriter().write("Unauthorized access");
		}*/
		
		return true;
	}

	
}
