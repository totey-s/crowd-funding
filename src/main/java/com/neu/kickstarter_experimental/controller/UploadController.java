package com.neu.kickstarter_experimental.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.neu.kickstarter_experimental.dao.UserDaoImpl;
import com.neu.kickstarter_experimental.pojo.User;
import com.neu.kickstarter_experimental.validator.UserValidator;

@Controller
@RequestMapping(value = "/upload.htm")
public class UploadController implements  ServletContextAware{
	
	private ServletContext servletContext;
	
	@Autowired
	@Qualifier("userValidator")
	UserValidator validator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {		
		binder.setValidator(validator);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String updateDetails(@ModelAttribute("user") User user, BindingResult result, 
			@RequestParam(value = "profilepic", required = false) MultipartFile image, 
			HttpServletRequest request){
		System.out.println("UploadController - POST for path 'upload.htm'");
		if (!image.isEmpty()) {
			try {
				System.out.println("Validating Image...");
				validateImage(image);
			 
			} catch (RuntimeException re) {
				System.out.println("RuntimeException");
			result.reject(re.getMessage());
			return "redirect:userDash.htm";
			}
		try {
			System.out.println("Saving Image...");
			saveImage(user.getUsername() + ".jpg", image);
//			user.setProfilepic(servletContext.getRealPath("/") + "/"+user.getUsername() + ".jpg");
			user.setProfilepic("resources/img/dashboard/"+user.getUsername() + ".jpg");
			} catch (IOException e) {
				System.out.println("IOException");
			result.reject(e.getMessage());
			return "redirect:userDash.htm";
			}
		}else{
			user.setProfilepic("NA");
		}
		UserDaoImpl userDao = new UserDaoImpl();
			try {
				System.out.println("updating other data...");
				
				HttpSession userSession = request.getSession();
				User user1 = (User)userSession.getAttribute("user");
				user.setUserRole(user1.getUserRole());
				user.setCreatedOn(user1.getCreatedOn());
				System.out.println("Profile Pic Path: "+user.getProfilepic());
				user1 = userDao.update(user);
				
				userSession.setAttribute("user", user1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		return "redirect:userDash.htm"; 
	}
	
	private void validateImage(MultipartFile image) {
		if (!image.getContentType().equals("image/jpeg")) {
		throw new RuntimeException("Only JPG images are accepted");
		}
		}
	
	private void saveImage(String filename, MultipartFile image)
			throws RuntimeException, IOException {
			try {
			File file = new File(servletContext.getRealPath("/")+"/resources/img/dashboard/"
			+ filename);
			 
			FileUtils.writeByteArrayToFile(file, image.getBytes());
			System.out.println("Go to the location:  " + file.toString()
			+ " on your computer and verify that the image has been stored.");
			} catch (IOException e) {
				e.printStackTrace();
			}
			}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;		
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView updateDetailsGet(@ModelAttribute("user") User user, BindingResult result, 
//			@RequestParam(value = "profilepic", required = false) MultipartFile image, 
			HttpServletRequest request){
		System.out.println("UploadController - GET for path 'upload.htm'");
		HttpSession userSession = request.getSession(false);
		User user1 = (User)userSession.getAttribute("user");
		ModelAndView mv = new ModelAndView();
		mv.addObject("user", user1);		
		if(user1.getUserRole().getRole().equals("ROLE_USER")){
//			mv.setViewName("user_Dashboard");
			mv.setViewName("userProfile");
		}else if(user1.getUserRole().getRole().equals("ROLE_ADMIN")){
			mv.setViewName("admin_Dashboard");			
		}else{
			mv.setViewName("home");
		}
		return mv;
		
	}
	
}
