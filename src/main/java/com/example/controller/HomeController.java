package com.example.controller;


import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.dto.GetContentVO;
import com.example.dto.HomeContentVO;
import com.example.dto.MemberVO;
import com.example.service.MemberService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	//git pull test
	//branch test1
	//branch test2
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
		List<HomeContentVO> HomeCList = service.selectContent();
		int Cnum = service.Cnum();
		System.out.println("HomeController3");
		model.addAttribute("HomeCList", HomeCList);
		System.out.println("HomeController4");		
		System.out.println("Cnum ==" + Cnum);
		System.out.println(HomeCList.get(1));
		model.addAttribute("Cnum",Cnum);
		return "home";
	}
	
	
	
	@RequestMapping(value = "/content", method = RequestMethod.GET)
	public String content(HttpServletRequest request, Locale locale, Model model) throws Exception{
		
		String num = request.getParameter("num");
		
		List<GetContentVO> GetContentList = service.getcontent(num);		
		System.out.println("HomeController3");
		model.addAttribute("GetContentList", GetContentList);
		System.out.println("HomeController4");		
		GetContentVO a = GetContentList.get(0);
		System.out.println(GetContentList.get(0));
		System.out.println(a.getTitle());
		System.out.println(a.getContent());
		// 서비스에서 스트링형 배열로 sql실행결과 받은담에 홈컨트롤러에서 모델에 넣어서 전달
		
		return "content";
	}
	

	@RequestMapping(value = "/newupdate", method = RequestMethod.GET)
	public String newupdate(HttpServletRequest request, Locale locale, Model model) throws Exception{

		logger.info("newupdate");
		String num = request.getParameter("num");
					
		List<GetContentVO> GetContentList = service.getcontent(num);		
		System.out.println("HomeController3");
		model.addAttribute("GetContentList", GetContentList);
		System.out.println("HomeController4");		
		GetContentVO a = GetContentList.get(0);
		System.out.println(GetContentList.get(0));
		System.out.println(a.getTitle());
		System.out.println(a.getContent());
		
		return "newupdate";
	}
	
	
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Locale locale, Model model) throws Exception{
		System.out.println("i1");
		logger.info("login");
		return "login";
	}
	
	@RequestMapping(value = "/logining", method = RequestMethod.GET)
	public String logining(HttpServletRequest request, Model model) throws Exception{
		System.out.println("loginingining");
		logger.info("loginning");
		boolean aa = service.logining(request);
		System.out.println(aa);
		
		
		//반환값 트루면 로그인 ok 아니면 false로
		if (aa == true) {
			String a = request.getParameter("ID");
			HttpSession session = request.getSession();
			session.setAttribute("ID", a);
			System.out.println("ddd"+session.getAttribute("ID"));		
			return "redirect:home";
			
		}else {
			return "redirect:memberjoin";
		}
		
	}
	
	
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insert(Locale locale, Model model) throws Exception{
		System.out.println("insert11");
		logger.info("insert");
		return "insert";
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write(Locale locale, Model model) throws Exception{
		System.out.println("write1");
		logger.info("write");
		return "write";
	}
	
	@RequestMapping(value = "/memberjoin", method = RequestMethod.GET)
	public String memberjoin(Locale locale, Model model) throws Exception{
		System.out.println("i1");
		logger.info("imberjoi");
		return "memberjoin";
	}
	
	@RequestMapping(value = "/memberjoining", method = RequestMethod.GET)
	public String memberjoining(HttpServletRequest request, Model model) throws Exception{
		System.out.println("memberjoining");
		logger.info("memberjoining");
		service.memberjoining(request);
		System.out.println("memberjoining2");
		return "redirect:home";
	}
	
	@RequestMapping(value = "/inserting", method = RequestMethod.GET)
	public String inserting(HttpServletRequest request, Model model) throws Exception{
		System.out.println("inserting11");
		logger.info("inserting");
		service.inserting(request);
		System.out.println("inserting12");
		return "redirect:home";
	}
	
	@RequestMapping(value = "/writing", method = RequestMethod.POST)
	public String writing(HttpServletRequest request, Model model) throws Exception{
		System.out.println("ritin11");
		logger.info("iritin");
		service.writing(request);
		System.out.println("inserting12");
		return "redirect:home";
	}
	
	@RequestMapping(value = "/newupdating", method = RequestMethod.POST)
	public String newupdating(HttpServletRequest request, Locale locale, Model model) throws Exception{

		logger.info("newupdateing");
		
					
		service.newupdating(request);
		
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
