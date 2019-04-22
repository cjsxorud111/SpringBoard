package com.example.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.dto.GetContentVO;
import com.example.dto.HomeContentVO;
import com.example.dto.SubVO;
import com.example.service.HomeService;

@Controller
public class HomeController {
	
	//의존관계 자동연결
	@Inject
	private HomeService service;
	
	//요청 URL매핑 method속성값 지정
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	//Model 객체를 파라미터로 받아서 데이터를 뷰로 넘김 컨트롤러에서 뷰에 데이터를 전달하기 위해 사용하는 객체
	public String home(Model model) throws Exception {
		
		List<HomeContentVO> HomeCList = service.selectContent();
		int Cnum = service.Cnum();
		model.addAttribute("HomeCList", HomeCList);
		model.addAttribute("Cnum", Cnum);
		
		return "home";
	}
	
	
	
	@RequestMapping(value = "/content", method = RequestMethod.GET)
	public String content(HttpServletRequest request, Model model) throws Exception {
		String num = request.getParameter("num");
		
		List<GetContentVO> GetContentList = service.getcontent(num);
		model.addAttribute("GetContentList", GetContentList);
		
		List<SubVO> GetSubList = service.getsub(num);
		
		model.addAttribute("GetSubList", GetSubList);

		// 서비스에서 스트링형 배열로 sql실행결과 받은다음 홈컨트롤러에서 모델에 넣어서 전달
		return "content";
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insert(Model model) throws Exception {
		return "insert";
	}

	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write(Model model) throws Exception {
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
	public String newupdate(HttpServletRequest request, Model model) throws Exception {
		
		String num = request.getParameter("num");
		
		List<GetContentVO> GetContentList = service.getcontent(num);
		model.addAttribute("GetContentList", GetContentList);
		
		return "newupdate";
	}
	
	@RequestMapping(value = "/newupdating", method = RequestMethod.POST)
	public String newupdating(HttpServletRequest request, Model model) throws Exception {

		service.newupdating(request);
		return "redirect:home";
	}
	
	@RequestMapping(value = "/update")
	public String update(HttpServletRequest request, Model model) throws Exception {
		
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
