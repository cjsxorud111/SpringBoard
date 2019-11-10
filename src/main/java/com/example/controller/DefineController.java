package com.example.controller;

import com.example.dto.*;
import com.example.service.DefineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Controller
public class DefineController {

	int refreshNum = 0;
	
	@Inject
	private DefineService service;
	final static Logger logger = LoggerFactory.getLogger(DefineController.class);
	
	// 글삭제하기
	@RequestMapping(value = "/deleteDefineContent", method = RequestMethod.POST)
	public String deleteDefineContent(HttpServletRequest request) {
		try {
			service.deleteDefineContent(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String textStatus = request.getParameter("textStatus");
		String returnUrl = "redirect:linkWord?linkWord="+textStatus;
		
		return "success";
	}
	
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
		
		String returnUrl = "redirect:define_modify?textStatus="+textStatus;
		response.setAttribute("modifyContentVO", modifyContentVO);
		textStatusVO.setTextStatus(textStatus);
		response.setAttribute("textStatusVO", textStatusVO );

		return "define_modify";
	}
	
	@RequestMapping(value = "/inform", method = RequestMethod.GET)
	public String inform(HttpServletRequest request,HttpServletRequest response, Model model) {
		return "inform";
	}
	
	// 추천수증가
	@RequestMapping(value = "/recommendUp", method = RequestMethod.POST)
	@ResponseBody
	public String recommendUp(HttpServletRequest request) {
		System.out.println("추천테스트11");

		
		String upNumber = request.getParameter("upNumber");
		String conNum = request.getParameter("conNum");
		System.out.println(conNum+"추천테스트1");
		String result = null;
		try {
			System.out.println(conNum+"추천테스트2");
			result = service.recommendUp(request, upNumber, conNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// 추천수감소
	@RequestMapping(value = "/recommendDown", method = RequestMethod.POST)
	@ResponseBody
	public String recommendDown(HttpServletRequest request) {
		String downNumber = request.getParameter("downNumber");
		String conNum = request.getParameter("conNum");
		String result = null;
		try {
			result = service.recommendDown(request, downNumber, conNum);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping(value = "/deleteDefineSub", method = RequestMethod.POST)
	@ResponseBody
	public String deleteDefineSub(HttpServletRequest request) {
		String pw = request.getParameter("pw");
		String num = request.getParameter("num");
		String result = null;
		try {
			result = service.deleteDefineSub(pw, num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	@ResponseBody
	public void logout(HttpServletRequest request, Model model) {
		try {
			HttpSession session = request.getSession();
			session.removeAttribute("ID");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/defineWriteSub", method = RequestMethod.POST)
	@ResponseBody
	public String defineWriteSub(HttpServletRequest request, Model model) {
		try {
			service.defineWriteSub(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	@RequestMapping(value = "/defineSecondSub", method = RequestMethod.POST)
	@ResponseBody
	public String defineSecondSub(HttpServletRequest request, HttpServletRequest response, Model model) {
		try {
			service.defineSecondSub(request); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
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
	
	//define메인페이지
	@RequestMapping(value = "/", method = RequestMethod.GET)
	// Model 객체를 파라미터로 받아서 데이터를 뷰로 넘김 컨트롤러에서 뷰에 데이터를 전달하기 위해 사용하는 객체
	public String define(Locale locale, HttpServletRequest request, HttpServletRequest response, Model model) {
		List<MainDefineContentVO> MainDefineList = null;
		try {
			MainDefineList = service.selectMainDefCon(locale);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<memberRankingVO> memberRanking = null;
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
	public String define_write(Model model) {
		return "define_write";
	}

	@RequestMapping(value = "/thiswordwrite", method = RequestMethod.GET)
	public String thisword_write(Model model) {
		return "thiswordwrite";
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(Model model) {
		return "test";
	}
	
	@RequestMapping(value = "/newwordWriting", method = RequestMethod.POST)
	public String newwordWriting(HttpServletRequest request, Model model) {
		try {
			service.newwordWriting(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/";
	}
	//추후 img업로드 추가개발에 사용 
	@RequestMapping(value = "img1", method = RequestMethod.POST)
	public void communityImageUpload1(HttpServletRequest request, HttpServletResponse response,
			@RequestParam MultipartFile upload) {
		OutputStream out = null;
		PrintWriter printWriter = null;
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		try {
			UUID uuid = UUID.randomUUID();
			String fileName = uuid + "_" + upload.getOriginalFilename();
			byte[] bytes = upload.getBytes();
			String uploadPath = "/Users/taeky/eclipse-workspace/SpringBoard/src/main/webapp/resources/img/" + fileName;// 저장경로

			out = new FileOutputStream(new File(uploadPath));
			out.write(bytes);
			String callback = request.getParameter("CKEditorFuncNum");
			printWriter = response.getWriter();
			String fileUrl = "img/" + fileName; // url경로

			printWriter.println("<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction(" + callback
					+ ",'" + fileUrl + "','이미지를 업로드 하였습니다.'" + ")</script>");
			printWriter.flush();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (printWriter != null) {
					printWriter.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return;
	}
}