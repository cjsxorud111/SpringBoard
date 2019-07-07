package com.example.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.service.MemberService;

@Controller
public class MemberjoinController {

	@Inject
	private MemberService service;

	@RequestMapping(value = "/memberjoin", method = RequestMethod.GET)
	public String memberjoin(Model model) throws Exception {
		return "memberjoin";
	}

	@RequestMapping(value = "/memberjoining", method = RequestMethod.GET)
	public String memberjoining(HttpServletRequest request, Model model) throws Exception {
		String str = request.getParameter("ID");
		
		 for(int i=0;i<str.length();i++){
		     if(Character.getType(str.charAt(i)) == 5) {
			     return "redirect:memberjoin?han=true";
		     }     
		}
		service.memberjoining(request);
		return "redirect:/";
	}
}
