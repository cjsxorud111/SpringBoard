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
	public String logining(HttpServletRequest request, Model model) throws Exception {
		boolean aa = service.logining(request);

		// 반환값 트루면 로그인 ok 아니면 false로
		if (aa == true) {
			String a = request.getParameter("ID");
			HttpSession session = request.getSession();
			session.setAttribute("ID", a);
			return "redirect:home";

		} else {
			return "redirect:memberjoin";
		}
	}
}
