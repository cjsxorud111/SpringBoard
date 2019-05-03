package com.example.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.dto.DefineSubVO;
import com.example.dto.GetContentVO;
import com.example.dto.HomeContentVO;
import com.example.dto.MainDefineContentVO;
import com.example.dto.SubVO;
import com.example.service.DefineService;
import com.example.service.HomeService;

@Controller
public class DefineController {
	
	//의존관계 자동연결
	@Inject
	private DefineService service;

	@RequestMapping(value = "/define_sub", method = RequestMethod.POST)
	public String define_sub(HttpServletRequest request, Model model) throws Exception {
		
		service.define_sub(request);
		return "redirect:define";
	}
	
	@RequestMapping(value = "/defineSecondSub", method = RequestMethod.POST)
	public String defineSecondSub(HttpServletRequest request, Model model) throws Exception {
		
		service.defineSecondSub(request);
		return "redirect:define";
	}
	
	@RequestMapping(value = "/define", method = RequestMethod.GET)
	//Model 객체를 파라미터로 받아서 데이터를 뷰로 넘김 컨트롤러에서 뷰에 데이터를 전달하기 위해 사용하는 객체
	public String define(Model model) throws Exception {
		List<MainDefineContentVO> MainDefineList = service.selectMainDefCon();
		model.addAttribute("MainDefineList", MainDefineList);
	
		List<DefineSubVO> getDefinSubList = service.getDefinSubList();
		model.addAttribute("getDefinSubList", getDefinSubList);
		
		System.out.println(getDefinSubList.get(0).getContent()+"여기되나?");
		
		
		return "define";
	}
	
	@RequestMapping(value = "/define_write", method = RequestMethod.GET)
	public String define_write(Model model) throws Exception {
		
		
		return "define_write";
	}
	
	@RequestMapping(value = "/newword_write", method = RequestMethod.GET)
	public String newword_write(Model model) throws Exception {
		
		return "newword_write";
	}
	
	@RequestMapping(value = "/newword_writing", method = RequestMethod.POST)
	public String newword_writing(HttpServletRequest request, Model model) throws Exception {
		service.newword_writing(request);
		return "redirect:define";
	}
	
	@RequestMapping(value = "img1", method = RequestMethod.POST)
    public void communityImageUpload1(HttpServletRequest request, HttpServletResponse response, @RequestParam MultipartFile upload) {
 
        OutputStream out = null;
        PrintWriter printWriter = null;
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
 
        try{
        	System.out.println("1");
        	UUID uuid = UUID.randomUUID();
    		String fileName = uuid + "_" + upload.getOriginalFilename();
    		System.out.println(fileName);
            byte[] bytes = upload.getBytes();
            String uploadPath = "/Users/taeky/eclipse-workspace/SpringBoard/src/main/webapp/resources/img/" + fileName;//저장경로
//            String uploadPath = "http://192.168.0.28:8090/Users/taeky/eclipse-workspace/SpringBoard/src/main/webapp/resources/img/" + fileName;
            ///Users/taeky/eclipse-workspace/SpringBoard/src/main/webapp/resources/img/" + fileName;
            
            out = new FileOutputStream(new File(uploadPath));
            out.write(bytes);
            String callback = request.getParameter("CKEditorFuncNum");
            System.out.println(callback);
            printWriter = response.getWriter();
            String fileUrl = "img/" + fileName; //url경로
            System.out.println("3");
//            JsonObject json = new JsonObject();
//
//            json.addProperty("uploaded", 1);
//            json.addProperty("fileName", fileName);
//            json.addProperty("url", fileUrl);
    		
            printWriter.println("<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction("
                    + callback
                    + ",'"
                    + fileUrl
                    + "','이미지를 업로드 하였습니다.'"
                    + ")</script>");
//            printWriter.println("{\"filename\" : \"cjsxorud111.jpg\", \"uploaded\" : 1, \"url\":\"/Users/taeky/eclipse-workspace/SpringBoard/src/main/webapp/resources/img/cjsxorud111.jpg\"}");
            printWriter.flush();
 
        }catch(IOException e){
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
        System.out.println("4");
        return;
    }
	
//	@RequestMapping(value = "/content", method = RequestMethod.GET)
//	public String content(HttpServletRequest request, Model model) throws Exception {
//		String num = request.getParameter("num");
//		
//		List<GetContentVO> GetContentList = service.getcontent(num);
//		model.addAttribute("GetContentList", GetContentList);
//		
//		List<SubVO> GetSubList = service.getsub(num);
//		
//		model.addAttribute("GetSubList", GetSubList);
//
//		// 서비스에서 스트링형 배열로 sql실행결과 받은다음 홈컨트롤러에서 모델에 넣어서 전달
//		return "content";
//	}
//	
//	@RequestMapping(value = "/insert", method = RequestMethod.GET)
//	public String insert(Model model) throws Exception {
//		return "insert";
//	}
//
//	@RequestMapping(value = "/write", method = RequestMethod.GET)
//	public String write(Model model) throws Exception {
//		return "write";
//	}
//
//	@RequestMapping(value = "/inserting", method = RequestMethod.GET)
//	public String inserting(HttpServletRequest request, Model model) throws Exception {
//		service.inserting(request);
//		return "redirect:home";
//	}
//
//	@RequestMapping(value = "/writing", method = RequestMethod.POST)
//	public String writing(HttpServletRequest request, Model model) throws Exception {
//		service.writing(request);
//		return "redirect:home";
//	}
//
//	@RequestMapping(value = "/newupdate", method = RequestMethod.GET)
//	public String newupdate(HttpServletRequest request, Model model) throws Exception {
//		
//		String num = request.getParameter("num");
//		
//		List<GetContentVO> GetContentList = service.getcontent(num);
//		model.addAttribute("GetContentList", GetContentList);
//		
//		return "newupdate";
//	}
//	
//	@RequestMapping(value = "/newupdating", method = RequestMethod.POST)
//	public String newupdating(HttpServletRequest request, Model model) throws Exception {
//
//		service.newupdating(request);
//		return "redirect:home";
//	}
//	
//	@RequestMapping(value = "/update")
//	public String update(HttpServletRequest request, Model model) throws Exception {
//		
//		return "update";
//	}
//	
//	@RequestMapping(value = "/updating", method = RequestMethod.GET)
//	public String updating(HttpServletRequest request, Model model) throws Exception {
//		service.updating(request);
//		return "redirect:home";
//	}
//	
//	@RequestMapping(value = "/delete", method = RequestMethod.GET)
//	public String deleting(HttpServletRequest request, Model model) throws Exception {
//		service.deleting(request);
//		return "redirect:home";
//	}
//	
//	@RequestMapping(value = "/writesub", method = RequestMethod.POST)
//	public String writesub(HttpServletRequest request, Model model) throws Exception {
//		String num = (String) request.getParameter("num");
//		
//		String url = "redirect:content?num=" + num;
//		service.writesub(request);
//		return url;
//	}
}
