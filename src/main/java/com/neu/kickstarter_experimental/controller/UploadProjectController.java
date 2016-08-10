package com.neu.kickstarter_experimental.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import com.neu.kickstarter_experimental.dao.CategoryDao;
import com.neu.kickstarter_experimental.dao.UserDaoImpl;
import com.neu.kickstarter_experimental.pojo.Category;
import com.neu.kickstarter_experimental.pojo.CreatedProject;
import com.neu.kickstarter_experimental.pojo.User;

@Controller
@RequestMapping(value = "/uploadProject.htm")
public class UploadProjectController implements  ServletContextAware{

	private ServletContext servletContext;
	
	@RequestMapping(method = RequestMethod.POST)
	public String updateDetails(@ModelAttribute("createdProject") CreatedProject createdProject, BindingResult result, 
			@RequestParam(value = "projectPic", required = false) MultipartFile image, 
			HttpServletRequest request){
		System.out.println("UploadController - POST for path 'uploadProject.htm'");
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
			saveImage(createdProject.getProjectName() + ".jpg", image);
//			user.setProfilepic(servletContext.getRealPath("/") + "/"+user.getUsername() + ".jpg");
			CategoryDao cat = new CategoryDao();
			
			createdProject.setProjectPic("resources/img/project/"+createdProject.getProjectName() + ".jpg");
			UserDaoImpl u = new UserDaoImpl();
			
			HttpSession userSession = request.getSession(false);
			User user = (User)userSession.getAttribute("user");
			System.out.println("Insert user: "+user);
			
//			CreatedProject newProject = new CreatedProject(createdProject.getProjectName(), createdProject.getLocation(), user1, createdProject.getCategory(), createdProject.getDescription());
//			newProject.setProjectPic(createdProject.getProjectPic());
			
			createdProject.setOwner(user);
			System.out.println("Project: "+createdProject);
			Category c = cat.get(createdProject.getCategory());			
//			c.addProjects(createdProject);
//			cat.save(c);
			u.createProject(createdProject);
			} catch (Exception e) {
				System.out.println("Exception");
			result.reject(e.getMessage());
			return "redirect:userDash.htm";
			}
		}else{
			createdProject.setProjectPic("NA");
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
			File file = new File(servletContext.getRealPath("/")+"/resources/img/project/"
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
}
