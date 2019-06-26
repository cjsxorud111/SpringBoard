package com.example.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.dto.DefineSubVO;
import com.example.dto.GetContentVO;
import com.example.dto.GetModifyContentVO;
import com.example.dto.HomeContentVO;
import com.example.dto.MainDefineContentVO;
import com.example.dto.SubVO;
import com.example.dto.textStatusVO;
import com.example.service.DefineService;
import com.example.service.HomeService;
@Controller
public class DefineController {
	int refreshNum = 0;
	// 의존관계 자동연결
	@Inject
	private DefineService service;

	@RequestMapping(value = "/searchWord", produces = "application/text; charset=utf8", method = RequestMethod.POST)
	@ResponseBody
	public String searchWord(HttpServletRequest request, Model model) throws Exception {
		
		return service.searchWord(request);
	}

	@RequestMapping(value = "/modifyWriting", method = RequestMethod.POST)
	@ResponseBody
	public String modifyWriting(HttpServletRequest request, Model model) throws Exception {
		service.modifyWriting(request);
		return "redirect:define";
	}
	
	// 글삭제하기
	@RequestMapping(value = "/deleteDefineContent", method = RequestMethod.POST)
	public String deleteDefineContent(HttpServletRequest request,HttpServletRequest response, Model model) throws Exception {
		service.deleteDefineContent(request);
		
		String textStatus = request.getParameter("textStatus");
		String returnUrl = "redirect:linkWord?linkWord="+textStatus;
		//설정하지 않았는데 왜define으로 가는지 이해못함 이해하고 수정필요
//		if(textStatus.equals("main(*)")) {
//			System.out.println("hsddsloo");
//			return "redirect:define";
//		} else {
//			
//			return "define";
//		}
		System.out.println("helloo");
		return "success";
	}
	
	// 글수정
	@RequestMapping(value = "/defineContentModify", method = RequestMethod.POST)
	public String defineContentModify(HttpServletRequest request,HttpServletRequest response, Model model) throws Exception {
		GetModifyContentVO modifyContentVO = service.defineContentModify(request);
		String textStatus = request.getParameter("textStatus");
		textStatusVO textStatusVO = new textStatusVO();

		String returnUrl = "redirect:define_modify?textStatus="+textStatus;
		response.setAttribute("modifyContentVO", modifyContentVO);
		textStatusVO.setTextStatus(textStatus);
		response.setAttribute("textStatusVO", textStatusVO );
//			설정하지 않았는데 왜define으로 가는지 이해못함 이해하고 수정필요
//			if(textStatus.equals("main(*)")) {
//				System.out.println("hsddsloo");
//				return "";
//			} else {
//				return returnUrl;
//			}
		return "define_modify";
	}

	@RequestMapping(value = "/temp", method = RequestMethod.POST)
	public String temp(HttpServletRequest request, Model model) throws Exception {
		return "temp";
	}
	
	// 추천수증가
	@RequestMapping(value = "/recommendUp", method = RequestMethod.POST)
	@ResponseBody
	public String recommendUp(HttpServletRequest request, Model model) throws Exception {
		String upNumber = request.getParameter("upNumber");
		String conNum = request.getParameter("conNum");
		return service.recommendUp(request, upNumber, conNum);
	}

	// 추천수감소
	@RequestMapping(value = "/recommendDown", method = RequestMethod.POST)
	@ResponseBody
	public String recommendDown(HttpServletRequest request, Model model) throws Exception {
		String downNumber = request.getParameter("downNumber");
		String conNum = request.getParameter("conNum");
		return service.recommendDown(request, downNumber, conNum);
	}

	@RequestMapping(value = "/deleteDefineSub", method = RequestMethod.POST)
	@ResponseBody
	public String deleteDefineSub(HttpServletRequest request, Model model) throws Exception {
		String pw = request.getParameter("pw");
		String num = request.getParameter("num");
		return service.deleteDefineSub(pw, num);
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	@ResponseBody
	public void logout(HttpServletRequest request, Model model) throws Exception {
		HttpSession session = request.getSession();
		session.removeAttribute("ID");
	}
	
	@RequestMapping(value = "/defineWriteSub", method = RequestMethod.POST)
	@ResponseBody
	public String defineWriteSub(HttpServletRequest request, Model model) throws Exception {
		service.defineWriteSub(request);
		return "success";
	}
	
	@RequestMapping(value = "/defineSecondSub", method = RequestMethod.POST)
	@ResponseBody
	public String defineSecondSub(HttpServletRequest request, HttpServletRequest response, Model model) throws Exception {
		System.out.println(request.getParameter("textVal")+"D오류");
		service.defineSecondSub(request); 
		return "success";
	}
	
	//단어눌렀을때 검색기능
	@RequestMapping(value = "/linkWord", method = RequestMethod.GET)
	public String linkWord(HttpServletRequest request, HttpServletRequest response, Model model) throws Exception {
		List<MainDefineContentVO> linkCon = service.linkCon(request);
		
		HttpSession session = request.getSession();
		model.addAttribute("MainDefineList", linkCon);
		model.addAttribute("Cnum", linkCon.size());
		List<DefineSubVO> getDefinSubList = service.getDefinSubList();
		model.addAttribute("getDefinSubList", getDefinSubList);
		//어떤페이지인지구분
		String linkWord = request.getParameter("linkWord");
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
	public String newword_write(HttpServletRequest request, HttpServletRequest response, Model model) throws Exception {
		return "newwordwrite";
	}

	//define메인페이지
	@RequestMapping(value = "/", method = RequestMethod.GET)
	// Model 객체를 파라미터로 받아서 데이터를 뷰로 넘김 컨트롤러에서 뷰에 데이터를 전달하기 위해 사용하는 객체
	public String define(HttpServletRequest request, HttpServletRequest response, Model model) throws Exception {
		List<MainDefineContentVO> MainDefineList = service.selectMainDefCon();
		int Cnum = MainDefineList.size();
		model.addAttribute("Cnum", Cnum);
		
		model.addAttribute("MainDefineList", MainDefineList);

		List<DefineSubVO> getDefinSubList = service.getDefinSubList();
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
	public String define_write(Model model) throws Exception {

		return "define_write";
	}

	@RequestMapping(value = "/thiswordwrite", method = RequestMethod.GET)
	public String thisword_write(Model model) throws Exception {
		return "thiswordwrite";
	}

	@RequestMapping(value = "/newwordWriting", method = RequestMethod.POST)
	public String newwordWriting(HttpServletRequest request, Model model) throws Exception {
		service.newwordWriting(request);
		return "redirect:define";
	}
	
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
			System.out.println(fileName);
			byte[] bytes = upload.getBytes();
			String uploadPath = "/Users/taeky/eclipse-workspace/SpringBoard/src/main/webapp/resources/img/" + fileName;// 저장경로

			out = new FileOutputStream(new File(uploadPath));
			out.write(bytes);
			String callback = request.getParameter("CKEditorFuncNum");
			System.out.println(callback);
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