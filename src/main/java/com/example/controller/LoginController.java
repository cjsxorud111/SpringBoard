package com.example.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.dto.FileContentVO;
import com.example.dto.FileContentVO2;
import com.example.dto.GetContentVO;
import com.example.dto.HomeContentVO;
import com.example.dto.MemberVO;
import com.example.dto.SubVO;
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
		boolean aa = service.logining(request);
		System.out.println("오류1"+aa);
		if (aa == true) {
			String a = request.getParameter("ID");
			String pw = request.getParameter("PW");
			HttpSession session = request.getSession();
			session.setAttribute("ID", a);
			session.setAttribute("PW", pw);
			System.out.println("오류2" + a + pw);
			return "success";
		} else {
			//request.setAttribute("notMembe", "notMembe");
			model.addAttribute("notMember", "일치하는 ID가 없습니다. <br> 회원가입을 해주세요.");
			System.out.println("오류3");
			return "fail";
		}
	}
}
