package com.example.controller;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.dto.GetContentVO;
import com.example.dto.HomeContentVO;
import com.example.dto.MemberVO;
import com.example.dto.SubVO;
import com.example.service.HomeService;

@Controller
public class HomeController {
	
	//�������� �ڵ�����
	@Inject
	private HomeService service;
	
	//��û URL���� method�Ӽ��� ����
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	//Model ��ü�� �Ķ���ͷ� �޾Ƽ� �����͸� ��� �ѱ�
	public String home(Locale locale, Model model) throws Exception {
		
		List<HomeContentVO> HomeCList = service.selectContent();
		int Cnum = service.Cnum();
		model.addAttribute("HomeCList", HomeCList);
		model.addAttribute("Cnum", Cnum);

		return "home";
	}
	
	@RequestMapping(value = "/content", method = RequestMethod.GET)
	public String content(HttpServletRequest request, Locale locale, Model model) throws Exception {
		String num = request.getParameter("num");

		List<GetContentVO> GetContentList = service.getcontent(num);
		model.addAttribute("GetContentList", GetContentList);
		
		List<SubVO> GetSubList = service.getsub(num);

		model.addAttribute("GetSubList", GetSubList);

		// ���񽺿��� ��Ʈ���� �迭�� sql������ �������� Ȩ��Ʈ�ѷ����� �𵨿� �־ ����
		return "content";
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insert(Locale locale, Model model) throws Exception {
		return "insert";
	}

	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write(Locale locale, Model model) throws Exception {
		return "write";
	}

	@RequestMapping(value = "/inserting", method = RequestMethod.GET)
	public String inserting(HttpServletRequest request, Model model) throws Exception {
		service.inserting(request);
		return "redirect:home";
	}

	@RequestMapping(value = "/writing", method = RequestMethod.POST)
	public String writing(HttpServletRequest request, Model model) throws Exception {
		service.writing(request);
		return "redirect:home";
	}

	@RequestMapping(value = "/newupdate", method = RequestMethod.GET)
	public String newupdate(HttpServletRequest request, Locale locale, Model model) throws Exception {
		
		String num = request.getParameter("num");
		
		List<GetContentVO> GetContentList = service.getcontent(num);
		model.addAttribute("GetContentList", GetContentList);
		
		return "newupdate";
	}
	
	@RequestMapping(value = "/newupdating", method = RequestMethod.POST)
	public String newupdating(HttpServletRequest request, Locale locale, Model model) throws Exception {

		service.newupdating(request);
		return "redirect:home";
	}
	
	@RequestMapping(value = "/update")
	public String update(HttpServletRequest request, Locale locale, Model model) throws Exception {
		
		return "update";
	}
	
	@RequestMapping(value = "/updating", method = RequestMethod.GET)
	public String updating(HttpServletRequest request, Model model) throws Exception {
		service.updating(request);
		return "redirect:home";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleting(HttpServletRequest request, Model model) throws Exception {
		service.deleting(request);
		return "redirect:home";
	}
	
	@RequestMapping(value = "/writesub", method = RequestMethod.POST)
	public String writesub(HttpServletRequest request, Model model) throws Exception {
		String num = (String) request.getParameter("num");
		String url = "redirect:content?num=" + num;
		service.writesub(request);

		return url;
	}
}
