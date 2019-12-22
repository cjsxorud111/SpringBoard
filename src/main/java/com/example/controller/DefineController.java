package com.example.controller;

import com.example.dto.*;
import com.example.service.DefineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;

@Controller
public class DefineController {

	int refreshNum = 0;

	@Autowired
	private DefineService service;

	final static Logger logger = LoggerFactory.getLogger(DefineController.class);

	// 글수정
	@RequestMapping(value = "/defineContentModify", method = RequestMethod.POST)
	public String defineContentModify(HttpServletRequest request,HttpServletRequest response) {
		GetModifyContentVO modifyContentVO = null;
		try {
			modifyContentVO = service.defineContentModify(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String textStatus = request.getParameter("textStatus");
		textStatusVO textStatusVO = new textStatusVO();
		
		response.setAttribute("modifyContentVO", modifyContentVO);
		textStatusVO.setTextStatus(textStatus);
		response.setAttribute("textStatusVO", textStatusVO );

		return "define_modify";
	}
	
	@RequestMapping(value = "/inform", method = RequestMethod.GET)
	public String inform() {
		return "inform";
	}

	//단어눌렀을때 검색기능
	@RequestMapping(value = "/linkWord", method = RequestMethod.GET)
	public String linkWord(HttpServletRequest request, HttpServletRequest response, Model model) {
		List<MainDefineContentVO> linkCon = null;
		try {
			linkCon = service.linkCon(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		HttpSession session = request.getSession();
		model.addAttribute("MainDefineList", linkCon);
		model.addAttribute("Cnum", linkCon.size());
		List<DefineSubVO> getDefinSubList = null;

		try {
			getDefinSubList = service.getDefinSubList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("getDefinSubList", getDefinSubList);

		//어떤페이지인지구분
		String linkWord = request.getParameter("linkWord");
		List<memberRankingVO> memberRanking = null;

		try {
			memberRanking = service.memberRanking();
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

		model.addAttribute("memberRanking", memberRanking);
		model.addAttribute("totalPageNum", pageCount(linkCon.size()));
		textStatusVO textStatusVO = new textStatusVO();
		textStatusVO.setTextStatus(linkWord);
		response.setAttribute("textStatusVO", textStatusVO);
		refreshNum++;
		response.setAttribute("lastGroupNum", getDefinSubList.get(getDefinSubList.size()-1));
		response.setAttribute("word", getDefinSubList.get(getDefinSubList.size()-1));
		session.setAttribute("refreshNum", refreshNum);
		return "define";
	}

	@RequestMapping(value = "/newwordwrite", method = RequestMethod.GET)
	public String newword_write() {
		return "newwordwrite";
	}

	static public int pageCount(int cNum) {
		//총 페이지수 소수로계산
		double pNum = (double)cNum / 10;
		//나머지 페이지가 있는지 확인
		double isDecimal = pNum - (int)pNum;
		//총 페이지 개수
		int totalPageNum;
		if (isDecimal == 0) {
			totalPageNum = (int)pNum;
		} else {
			totalPageNum = (int)pNum + 1;
		}
		return totalPageNum;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	// Model 객체를 파라미터로 받아서 데이터를 뷰로 넘김 컨트롤러에서 뷰에 데이터를 전달하기 위해 사용하는 객체
	public String define(Locale locale, HttpServletRequest request, HttpServletRequest response, Model model) {
		List<MainDefineContentVO> MainDefineList = null;

		try {
			MainDefineList = service.selectMainDefCon(locale);
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<memberRankingVO> memberRanking;
		try {
			memberRanking = service.memberRanking();
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

		model.addAttribute("totalPageNum", pageCount(MainDefineList.size()));
		model.addAttribute("MainDefineList", MainDefineList);
		model.addAttribute("memberRanking", memberRanking);

		List<DefineSubVO> getDefinSubList = null;

		try {
			getDefinSubList = service.getDefinSubList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("getDefinSubList", getDefinSubList);
		//어떤페이지인지구분
		textStatusVO textStatusVO = new textStatusVO();
		textStatusVO.setTextStatus("main(*)");
		response.setAttribute("textStatusVO", textStatusVO);
		refreshNum++;
		response.setAttribute("lastGroupNum", getDefinSubList.get(getDefinSubList.size()-1));
		HttpSession session = request.getSession();
		session.setAttribute("refreshNum", refreshNum);
		return "define";
	}

	@RequestMapping(value = "/define_write", method = RequestMethod.GET)
	public String define_write() {
		return "define_write";
	}

	@RequestMapping(value = "/thiswordwrite", method = RequestMethod.GET)
	public String thisword_write() {
		return "thiswordwrite";
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() {
		return "test";
	}
	
	@RequestMapping(value = "/newwordWriting", method = RequestMethod.POST)
	public String newwordWriting(HttpServletRequest request) {
		try {
			service.newwordWriting(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/";
	}
}