package com.neu.kickstarter_experimental.controller;

import javax.servlet.ServletRequestWrapper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.neu.kickstarter_experimental.codegenerator.CodeGenertor;
import com.neu.kickstarter_experimental.dao.UserDaoImpl;
import com.neu.kickstarter_experimental.email.EmailSend;
import com.neu.kickstarter_experimental.pojo.User;
import com.neu.kickstarter_experimental.validator.UserValidator;

@Controller
@RequestMapping("/register.htm")
public class RegisterController {
	@Autowired
	@Qualifier("userValidator")
	UserValidator validator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {		
		binder.setValidator(validator);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	protected ModelAndView doSubmitAction(@ModelAttribute("user") User user, BindingResult result, HttpServletRequest request) throws Exception {
		System.out.println("RegisterController - POST for path 'register.htm'");
		System.out.println("Email: "+user.getEmail());
		System.out.println("Username: "+user.getUsername());
		System.out.println("Password: "+user.getPassword());
		validator.validate(user, result);
		System.out.println(result.hasErrors());
		if (result.hasErrors()) {
			return new ModelAndView("register");
		}
		UserDaoImpl userDao = new UserDaoImpl();
		ModelAndView mv = new ModelAndView();
		try {
			System.out.println("test");
						
			System.out.print("test1");
			String code = CodeGenertor.randomString(6);
			System.out.println("Code: "+code);
			user.setStatus(code);
			String from = "we@kickstarter.org";
			String message = "Hi "+user.getFirstName()+", "
					+ "Your unique code is "+code+". Kindly login again and enter the code to activate your account.";
			
			String htmlMessage = "<html>Hi "+user.getFirstName()+"<br/>"
					+ "Your unique code is "+code+". Kindly login again and enter the code to activate your account."
							+ "<a href=\"login.htm\">KickStarter</a></html>";
			EmailSend.emailSend(from, user.getEmail(), "Account Verification", htmlMessage, message);
			userDao.create(user);	
			mv.setViewName("activate");
			mv.addObject("userId", user.getUserId());
			// DAO.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception: " + e.getMessage());
		}
//		User authUser = userDao.get(user.getUsername(),user.getPassword());
//		System.out.println("User Just Created: "+authUser);
////		ModelAndView mv = new ModelAndView();		
//		HttpSession userSession = request.getSession();
//		userSession.setAttribute("user", authUser);
//		mv.addObject("user", authUser);
//		mv.addObject("success", "success");
//		mv.setViewName("register");
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView initializeForm(@ModelAttribute("user") User user, BindingResult result) {
		System.out.println("RegisterController - GET for path 'register.htm'");
		ModelAndView mv = new ModelAndView("register");
		mv.addObject("access", "blocked");
		return mv;
	}

}
