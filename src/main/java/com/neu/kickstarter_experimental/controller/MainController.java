package com.neu.kickstarter_experimental.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.neu.kickstarter_experimental.dao.AuthorizeProjectDao;
import com.neu.kickstarter_experimental.dao.CategoryDao;
import com.neu.kickstarter_experimental.dao.ProjectDao;
import com.neu.kickstarter_experimental.dao.SearchDao;
import com.neu.kickstarter_experimental.dao.UserDaoImpl;
import com.neu.kickstarter_experimental.pojo.Category;
import com.neu.kickstarter_experimental.pojo.CreatedProject;
import com.neu.kickstarter_experimental.pojo.PaymentDetails;
import com.neu.kickstarter_experimental.pojo.User;

@Controller
public class MainController {

	@RequestMapping(value = {"/home.htm"}, method = RequestMethod.GET)
	public String homeRedirect(){
		System.out.println("MainController - GET for path 'home.htm'");
		return "home";
	}
	
	@RequestMapping(value = {"/logout.htm"}, method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request){
		System.out.println("MainController - GET for path 'logout.htm'");
		HttpSession userSession = request.getSession(false);
		userSession.invalidate();
		return new ModelAndView("redirect:home.htm");
	}
		
	@RequestMapping(value = {"/userDash.htm"}, method = RequestMethod.GET)
//	public ModelAndView userRedirect(@ModelAttribute("user") User user, BindingResult result,HttpServletRequest request){
	public String userRedirect(ModelMap model, HttpServletRequest request){
		System.out.println("MainController - GET for path 'userDash.htm'");
		User user = new User();		
		HttpSession userSession = request.getSession(false);		
			User user1 = (User)userSession.getAttribute("user");		
			ModelAndView mv = new ModelAndView();
			if(user1!=null){
				System.out.println("User ID: "+user1.getUserId());
//				if(user1.getUserRole().getRole().equals("ROLE_USER")){				
					
					user = user1;
					/*user.setUserId(user1.getUserId());
					user.setActive(user1.isActive());
					user.setCreatedOn(user1.getCreatedOn());
					user.setEmail(user1.getEmail());
					user.setFirstName(user1.getFirstName());
					user.setLastName(user1.getLastName());
					user.setPassword(user1.getPassword());
					user.setProfilepic(user1.getProfilepic());
					user.setUsername(user1.getUsername());
					user.setUserRole(user1.getUserRole());*/
					
					model.addAttribute("user", user);
					mv.addObject("user", user);
					mv.setViewName("user_Dashboard");
					return "user_Dashboard";
//					return "Dashboard";
//				}
			}
			else{
				mv.setViewName("home");
				return "home";
			}	
//		return null;
	}
		
	@RequestMapping(value = {"/adminDash.htm"}, method = RequestMethod.GET)
	public ModelAndView adminRedirect(HttpServletRequest request){
		System.out.println("MainController - GET for path 'adminDash.htm'");
		HttpSession userSession = request.getSession(false);
		User user = (User)userSession.getAttribute("user");
		ModelAndView mv = new ModelAndView();
//		System.out.println("Admin: "+user.getFirstName());
		if(user!=null){
			if(user.getUserRole().getRole().equals("ROLE_ADMIN")){
				mv.addObject("user", user);
				mv.setViewName("admin_Dashboard");
			}
		}
		else{
			mv.setViewName("home");
		}		
		return mv;
	}
	
	@RequestMapping(value = {"/uploadProjectForm.htm"}, method = RequestMethod.GET)
	public ModelAndView projectFormRedirect(@ModelAttribute("createdProject") CreatedProject createdProject, BindingResult result, HttpServletRequest request){		
		
		CategoryDao cdao = new CategoryDao();
		/*cdao.setCategory(new Category("Art"));
		cdao.setCategory(new Category("Games"));
		cdao.setCategory(new Category("Entertainment"));*/
		
		List categories = cdao.getCategories();
		
		Iterator categIterator = categories.iterator();
		while (categIterator.hasNext()){
			Category category = (Category) categIterator.next();
		}
		
		ModelAndView mv = new  ModelAndView("createProject");		
		mv.addObject("categoryList", categories);
		return mv;
	}
	
	@RequestMapping(value = {"/authorizeProject.htm"}, method = RequestMethod.GET)
	public ModelAndView authorizeProjectRedirect(HttpServletRequest request){
		AuthorizeProjectDao a = new AuthorizeProjectDao();
		CategoryDao cdao = new CategoryDao();
		List projects = a.getProjects();
		Iterator it = projects.iterator();
		while(it.hasNext()){
			CreatedProject cp = (CreatedProject)it.next();
			cp.setCategoryName(cdao.get(cp.getCategory()).getCategoryName());
//			System.out.println(cp.getProjectName());
		}
		ModelAndView mv = new  ModelAndView("authorizeProjects");		
		mv.addObject("projects", projects);
		return mv;
	}
	
	@RequestMapping(value = {"/approve.htm"}, method = RequestMethod.POST)
	public void approveProject(HttpServletRequest request, HttpServletResponse response){
		try {
			PrintWriter out = response.getWriter();
			String projId = request.getParameter("projectId");
			int projectId = Integer.parseInt(projId);
			AuthorizeProjectDao a = new AuthorizeProjectDao();
			if(a.authorizeProject(projectId)){
				out.print("Project has been approved");
			}else{
				out.print("Project was not approved");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	@RequestMapping(value = {"/getProjects.htm"}, method = RequestMethod.POST)
	public @ResponseBody List<CreatedProject> getProjects(@RequestBody CreatedProject project, HttpServletRequest request){
		List<CreatedProject> projectList = null;
		try {
			System.out.println("MainController - GET for path 'getProjects.htm'");
//			String status = request.getParameter("Status");
			String status = project.getApproved();
			System.out.println("Status: "+project.getApproved());
			
//			PrintWriter out = response.getWriter();
//			String status = request.getParameter("status");
//			System.out.println("Status: "+status);
			
			AuthorizeProjectDao a = new AuthorizeProjectDao();
			CategoryDao cdao = new CategoryDao();
			projectList = new ArrayList<CreatedProject>();
			List projects = a.getProjects(status);
			Iterator it = projects.iterator();
			while(it.hasNext()){
				CreatedProject cp = (CreatedProject)it.next();
				cp.setCategoryName(cdao.get(cp.getCategory()).getCategoryName());
				cp.setOwner_Fname(cp.getOwner().getFirstName());
				cp.setOwner_Lname(cp.getOwner().getLastName());
				projectList.add(cp);
				System.out.println(cp.getProjectName());
				System.out.println(cp.getCreatedDate());
			}
			
//			JSONObject json = new JSONObject();
//			System.out.println("Before JSON put");
//            json.put("projects", projects);
//			json.put("projects", projectList);
//            System.out.println("Json Length: "+json.length());
//            out.println(json);
		} catch (Exception e){
			e.printStackTrace();
		}
		return projectList;
	}
	
	@RequestMapping(value = {"/searchProject.htm"}, method = RequestMethod.GET)
	public ModelAndView finProjectRedirect(HttpServletRequest request){
		System.out.println("MainController - GET for path 'searchProject.htm'");
//		return new ModelAndView("searchProjects");
		List<CreatedProject> projectList = null;
		ModelAndView mv = new ModelAndView("findProjects");
		try {
			System.out.println("MainController - GET for path 'getProjects.htm'");
			
			ProjectDao a = new ProjectDao();
			CategoryDao cdao = new CategoryDao();
			HttpSession userSession = request.getSession(false);
			if(userSession != null){
				User user = (User)userSession.getAttribute("user");
				projectList = new ArrayList<CreatedProject>();
				List projects = a.getProjects(user, false);
				Iterator it = projects.iterator();
				while(it.hasNext()){
					CreatedProject cp = (CreatedProject)it.next();
					cp.setCategoryName(cdao.get(cp.getCategory()).getCategoryName());
					cp.setOwner_Fname(cp.getOwner().getFirstName());
					cp.setOwner_Lname(cp.getOwner().getLastName());
//					cp.setFundReceived(a.getPaymentDetails(cp.getProjectId()));
//					List<PaymentDetails> payments = cp.getFundReceived();
					a.getPaymentDetailsAndBackers(cp.getProjectId(), cp);					
					projectList.add(cp);
					
					System.out.println(cp.getProjectName());
					System.out.println(cp.getCreatedDate());
				}
				mv.addObject("Title", "Projects to fund");
				mv.addObject("projects", projectList);
			}			
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return mv;
	}
	
	@RequestMapping(value = {"/yourProjects.htm"}, method = RequestMethod.GET)
	public ModelAndView yourProjectRedirect(HttpServletRequest request){
		List<CreatedProject> projectList = null;
		ModelAndView mv = new ModelAndView("findProjects");
		System.out.println("MainController - GET for path 'yourProjects.htm'");
		try {
			ProjectDao a = new ProjectDao();
			CategoryDao cdao = new CategoryDao();
			HttpSession userSession = request.getSession(false);
			if(userSession != null){
				User user = (User)userSession.getAttribute("user");
				projectList = new ArrayList<CreatedProject>();
				List projects = a.getProjects(user, true);
				Iterator it = projects.iterator();
				while(it.hasNext()){
					CreatedProject cp = (CreatedProject)it.next();
					cp.setCategoryName(cdao.get(cp.getCategory()).getCategoryName());
					cp.setOwner_Fname(cp.getOwner().getFirstName());
					cp.setOwner_Lname(cp.getOwner().getLastName());
//					cp.setFundReceived(a.getPaymentDetails(cp.getProjectId()));
//					List<PaymentDetails> payments = cp.getFundReceived();
					a.getPaymentDetailsAndBackers(cp.getProjectId(), cp);					
					projectList.add(cp);
					
					System.out.println(cp.getProjectName());
					System.out.println(cp.getCreatedDate());
				}
				mv.addObject("self", "self");
				mv.addObject("Title", "Your Projects");
				mv.addObject("projects", projectList);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
	
	@RequestMapping(value = {"/deactivate.htm"}, method = RequestMethod.GET)
	public ModelAndView deactivateRedirect(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("deactivate");
		HttpSession userSession = request.getSession(false);
		User user1 = (User)userSession.getAttribute("user");
		mv.addObject("user", user1);
		mv.addObject("deactivate", "deactivate");
		return mv;
	}
	
	@RequestMapping(value = {"/deactivate.htm"}, method = RequestMethod.POST)
	public ModelAndView deactivateProcess(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("home");
		HttpSession userSession = request.getSession(false);
		User user1 = (User)userSession.getAttribute("user");
		UserDaoImpl userDao = new UserDaoImpl();
		try {
			userDao.delete(user1);
			userSession.invalidate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
	
	@RequestMapping(value = {"/activate.htm"}, method = RequestMethod.GET)
	public ModelAndView activateRedirect(@ModelAttribute("user") User user, BindingResult result, HttpServletRequest request){
		ModelAndView mv = new ModelAndView("activate");
		HttpSession userSession = request.getSession(false);		
		User user1 = (User)userSession.getAttribute("user");
		user = user1;
		mv.addObject("user", user1);
//		mv.addObject("deactivate", "deactivate");
		return mv;
	}
	
	@RequestMapping(value = {"/activate.htm"}, method = RequestMethod.POST)
	public ModelAndView activateProcess(HttpServletRequest request){
		System.out.println("MainController - POST for path 'activate.htm'");
		ModelAndView mv = new ModelAndView();
		UserDaoImpl userDao = new UserDaoImpl();
		String code = request.getParameter("code");
		System.out.println("code inserted: "+code);
		String userID = request.getParameter("userId");		
		int userId = Integer.parseInt(userID);
		System.out.println("userId: "+userId);
		User user = null;
		try {
			user = userDao.get(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(user.getStatus().equals(code)){
			try {
				userDao.setActive(user);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("Creating Session");
			HttpSession userSession = request.getSession(true);		
			userSession.setAttribute("user",user);
			mv.setViewName("home");
		}else{
			mv.addObject("error","Invalid Code");
			mv.setViewName("login");
		}
		
//		mv.addObject("deactivate", "deactivate");
		return mv;
	}
	
	@RequestMapping(value = {"/search.htm"}, method = RequestMethod.GET)
	public ModelAndView searchProjects(HttpServletRequest request){
		System.out.println("MainController - POST for path 'activate.htm'");
		ModelAndView mv = new ModelAndView("findProjects");
		String searchString = request.getParameter("searchString");
		System.out.println("---"+searchString);
//		SearchDao s = new SearchDao();
		CategoryDao cdao = new CategoryDao();
		ProjectDao a = new ProjectDao();
		List projects = SearchDao.search(searchString);
		List<CreatedProject> projectList = new ArrayList<CreatedProject>();
		System.out.println(projects.size());
		Iterator it = projects.iterator();
		while(it.hasNext()){
			CreatedProject cp = (CreatedProject)it.next();
			cp.setCategoryName(cdao.get(cp.getCategory()).getCategoryName());
			cp.setOwner_Fname(cp.getOwner().getFirstName());
			cp.setOwner_Lname(cp.getOwner().getLastName());
//			cp.setFundReceived(a.getPaymentDetails(cp.getProjectId()));
//			List<PaymentDetails> payments = cp.getFundReceived();
			a.getPaymentDetailsAndBackers(cp.getProjectId(), cp);					
			projectList.add(cp);
			
			System.out.println(cp.getProjectName());
			System.out.println(cp.getCreatedDate());
		}
		mv.setViewName("findProjects");
		mv.addObject("Search", "Search");
		mv.addObject("Title", "Search Results for "+searchString);
		mv.addObject("projects", projectList);
		return mv;
	}

}
