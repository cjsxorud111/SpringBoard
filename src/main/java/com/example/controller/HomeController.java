package com.example.controller;


import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.dto.MemberVO;
import com.example.service.MemberService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	//git pull test
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Inject
	private MemberService service;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Locale locale, Model model) throws Exception{
		System.out.println("HomeController1");
		logger.info("home");
		System.out.println("HomeController2");
		List<MemberVO> memberList = service.selectMember();
		System.out.println("HomeController3");
		model.addAttribute("memberList", memberList);
		System.out.println("HomeController4");
		return "home";
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insert(Locale locale, Model model) throws Exception{
		System.out.println("insert11");
		logger.info("insert");
		return "insert";
	}
	
	@RequestMapping(value = "/inserting", method = RequestMethod.GET)
	public String inserting(HttpServletRequest request, Model model) throws Exception{
		System.out.println("inserting11");
		logger.info("inserting");
		service.inserting(request);
		System.out.println("inserting12");
		return "redirect:home";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(HttpServletRequest request, Locale locale, Model model) throws Exception{
		System.out.println("HomeController1");
		logger.info("update");
		System.out.println("HomeController2");
		String test = request.getParameter("ID");
		String test1 = request.getParameter("PW");
		String test2= request.getParameter("NAME");
		
		/*List<MemberVO> memberList = service.selectMember();
		System.out.println("HomeController3");
		model.addAttribute("memberList", memberList);*/
		System.out.println(test);
		System.out.println(test1);
		System.out.println(test2);
		
		MemberVO vv = new MemberVO();
		
		
		return "update";
	}
	
	@RequestMapping(value = "/updating", method = RequestMethod.GET)
	public String updating(HttpServletRequest request, Model model) throws Exception{
		System.out.println("updating11");
		logger.info("updating");
		service.updating(request);
		System.out.println("iupdating12");
		return "redirect:home";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleting(HttpServletRequest request, Model model) throws Exception{
		System.out.println("deletting11");
		logger.info("deleting");
		service.deleting(request);
		System.out.println("deletting12");
		return "redirect:home";
	}
	
}
