package com.neu.kickstarter_experimental.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import com.neu.kickstarter_experimental.dao.UserDaoImpl;
import com.neu.kickstarter_experimental.pojo.User;

@Controller
@RequestMapping("/login.htm")
public class LoginController {

	@RequestMapping(method = RequestMethod.POST)
	protected ModelAndView doSubmitAction(@ModelAttribute("user") User user, BindingResult result, HttpServletRequest request) throws Exception {
//		System.out.println("In Controller POST");	
		System.out.println("LoginController - POST for path 'login.htm'");
		System.out.println("User: "+user.getUsername());	
		UserDaoImpl userDao = new UserDaoImpl();
		User authUser = userDao.get(user.getUsername(),user.getPassword());
		ModelAndView mv = new ModelAndView();
		boolean flag = false;
		
		if(authUser!=null && (!authUser.getStatus().equals("inactive"))){
			System.out.println("Status: "+authUser.getStatus());
			System.out.println("Got User: "+authUser.getFirstName()+" "+authUser.getProfilepic());			
						
			if(authUser.getStatus().equals("active")){
				mv.addObject("user", authUser);
				mv.setViewName("redirect:home.htm");
				HttpSession userSession = request.getSession(true);
				userSession.setAttribute("user", authUser);
				flag = true;
			}else{
				mv.addObject("userId", authUser.getUserId());
				mv.setViewName("activate");
				return mv;
			}			
			
//			return mv;
		}else{
			mv.addObject("error", "Incorrect Username or Password");
			mv.setViewName("login");
			
//			return mv;
		}	
		if(flag == false){
			mv.addObject("access", "blocked");
			mv.setViewName("login");
		}	
		return mv;	
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView login(@ModelAttribute("user") User user, BindingResult result, HttpServletRequest request) {
		System.out.println("LoginController - GET for path 'login.htm'");
		HttpSession userSession = request.getSession(false);		
		
		if(userSession != null){
			User authUser = (User)userSession.getAttribute("user");
			if(authUser != null)
			return new ModelAndView("redirect:home.htm");
		}
		return new ModelAndView("login");
	}
}
