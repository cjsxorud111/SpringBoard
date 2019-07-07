package com.example.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.service.MemberService;

@Controller
public class LoginController {

	@Inject
	private MemberService service;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) throws Exception {
		return "login";
	}

	@RequestMapping(value = "/logining", method = RequestMethod.GET)
	@ResponseBody
	public String logining(HttpServletRequest request,HttpServletRequest response, Model model) throws Exception {
		String aa = service.logining(request);
		if (aa.equals("true")) {
			String a = request.getParameter("ID");
			String pw = request.getParameter("PW");
			HttpSession session = request.getSession();
			session.setAttribute("ID", a);
			session.setAttribute("PW", pw);
			return "success";
		} else if(aa.equals("noId")){
			return "noId";
		} else {
			return "notPw";
		}
	}
}
