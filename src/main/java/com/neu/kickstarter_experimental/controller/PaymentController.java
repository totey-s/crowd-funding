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

import com.neu.kickstarter_experimental.dao.PaymentDao;
import com.neu.kickstarter_experimental.dao.ProjectDao;
import com.neu.kickstarter_experimental.dao.UserDaoImpl;
import com.neu.kickstarter_experimental.pojo.User;
import com.neu.kickstarter_experimental.pojo.CreatedProject;
import com.neu.kickstarter_experimental.pojo.PaymentDetails;

@Controller
public class PaymentController {

	@RequestMapping(value = {"/payment.htm"}, method = RequestMethod.POST)
	protected ModelAndView doSubmitAction(@ModelAttribute("paymentDetails") PaymentDetails paymentDetails, BindingResult result, HttpServletRequest request) throws Exception {
//		System.out.println("In Controller POST");	
		System.out.println("LoginController - POST for path 'payment.htm'");
		
		HttpSession userSession = request.getSession(false);
		ModelAndView mv = new ModelAndView();
		if(userSession!=null){
			User user = (User)userSession.getAttribute("user");
			if(user != null){
				System.out.println("ProjectId: "+request.getParameter("projectId"));
				System.out.println("Street Address: "+paymentDetails.getStreetAddress());
				System.out.println("First Name: "+request.getParameter("first-name"));
				
				paymentDetails.setCardNumber(request.getParameter("number"));
				String cvcStr = request.getParameter("cvc");
				int cvc = Integer.parseInt(cvcStr);
				paymentDetails.setCvc(cvc);
				paymentDetails.setExpiry(request.getParameter("expiry"));
				paymentDetails.setLastName(request.getParameter("last-name"));
				paymentDetails.setFirstName(request.getParameter("first-name"));
				paymentDetails.setCreatedBy(user.getUserId());
				
				String projectIdStr = request.getParameter("projectId");
				int projcectId = Integer.parseInt(projectIdStr);
				String amt = request.getParameter("amount");
				double amount = Integer.parseInt(amt);
				paymentDetails.setProjectId(projcectId);
				paymentDetails.setFundAmount(amount);		
				PaymentDao paymentDao = new PaymentDao();
				paymentDao.create(paymentDetails);
				mv.setViewName("paymentGateway");
				return mv;
//				ModelAndView mv = new ModelAndView("paymentGateway");
			}			
		}			
		mv.setViewName("login");
		return mv;
			
	}
	
	@RequestMapping(value = {"/payment.htm"}, method = RequestMethod.GET)
	public ModelAndView paymentRedirect(@ModelAttribute("paymentDetails") PaymentDetails paymentDetails, BindingResult result,HttpServletRequest request){
		System.out.println("ProjectId: "+request.getParameter("projectId"));
		HttpSession userSession = request.getSession(false);
		try {
			if(userSession != null){
				System.out.println("Check session");
				User user = (User)userSession.getAttribute("user");
				System.out.println("Check session user");
				if(user == null){
					return new ModelAndView("redirect:login.htm");	
				}else{
					ModelAndView mv = new ModelAndView("paymentGateway");
					mv.addObject("projectId", request.getParameter("projectId"));
					return mv;
				}
			}
		} catch (Exception e) {
//			e.printStackTrace();
			return new ModelAndView("login");
		}
		
		return new ModelAndView("login");
	}
}
