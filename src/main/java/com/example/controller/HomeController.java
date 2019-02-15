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

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	public static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Inject
	private MemberService service;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Locale locale, Model model) throws Exception {
		
		logger.info("home");
		List<HomeContentVO> HomeCList = service.selectContent();
		int Cnum = service.Cnum();
		model.addAttribute("HomeCList", HomeCList);
		model.addAttribute("Cnum", Cnum);

		return "home";
	}

	@RequestMapping(value = "/content", method = RequestMethod.GET)
	public String content(HttpServletRequest request, Locale locale, Model model) throws Exception {
		System.out.println("ㅣㅅ1111");
		String num = request.getParameter("num");

		List<GetContentVO> GetContentList = service.getcontent(num);
		System.out.println("ㅣ넫11111");
		model.addAttribute("GetContentList", GetContentList);
		List<SubVO> GetSubList = service.getsub(num);
		/*List<SubVO> TestSubList = GetSubList;
		List<SubVO> NewSubList = new ArrayList<SubVO>();
		List<String> Data1 = new ArrayList<String>();
		List<String> Data2 = new ArrayList<String>();

		System.out.println(TestSubList.get(1));
		SubVO vvo = TestSubList.get(1);
		SubVO[] vvvo = new SubVO[TestSubList.size()];
		SubVO[] vvvo2 = new SubVO[TestSubList.size()];
		for (int i = 0; i < TestSubList.size(); i++) {
			vvvo[i] = TestSubList.get(i);
		}
		System.out.println("ㅣㅅ발뭥넫111112222222");
		for (int i = 0; i < vvvo.length; i++) {
			System.out.println("개밧");
			Data1.add(vvvo[i].getUid());
			System.out.println("ㅣㅅㄴㅇ");
			
			
			Data2.add(vvvo[i].getUuid());
			System.out.println("시잘");
		}
		
		for (int i = 0; i < Data1.size(); i++) {System.out.println(vvvo[i].getUid());}
		for (int i = 0; i < Data2.size(); i++) {System.out.println(vvvo[i].getUuid());}
		
		
		System.out.println("112222222");
		
		
		for (int i = 0; i < Data1.size(); i++) {
			String ssub = Data1.get(i);
		
			
			NewSubList.add(vvvo[i]);
			System.out.println(ssub+"tl시ㄹㄹ라ㅏ라라라라ㅏ라");
            for (int j = 0; j < Data2.size(); j++) {
	            String sub = Data2.get(j);
	            
	            
	            System.out.println(sub+"tl시ㄹㅇㄴㄴㄴㅇ라라라ㅏ라");
	            
	            String test = null;
                if (ssub.equals(sub)) {
                	System.out.println(vvvo[j].getUuid()+"하");
                	NewSubList.add(vvvo[j]);
                	test = Data2.get(j);
                	System.out.println(test+"여기야익");
                	
                	
                	Data2.remove(j);
                	Data1.remove(j);
                	
                	
                	for (int k = 0; k < Data2.size(); k++) {
						System.out.println(Data2.get(k)+"여기야여기");
						
					}
                	
                	
                	
                	//리무브 왜 안되는지 해결!!!
                	
                	
                	test = Data2.get(j);
                	System.out.println(test+"역이익");
                	
                } 
            }
            
            Data1.remove(i);
        }
		
		System.out.println("12222222333333");
		System.out.println(vvo.getNum());*/

		model.addAttribute("GetSubList", GetSubList);
		//model.addAttribute("NewSubList", NewSubList);

		// 서비스에서 스트링형 배열로 sql실행결과 받은담에 홈컨트롤러에서 모델에 넣어서 전달
		System.out.println("112222222333333");
		return "content";
	}

	@RequestMapping(value = "/file_content", method = RequestMethod.GET)
	public String file_content(HttpServletRequest request, Locale locale, Model model) throws Exception {

		String num = request.getParameter("num");

		List<FileContentVO2> GetContentList = service.getfile(num);
		model.addAttribute("GetContentList", GetContentList);
		System.out.println("HomeController4");
		FileContentVO2 a = GetContentList.get(0);
		System.out.println(GetContentList.get(0));
		System.out.println(a.getTitle());
		System.out.println(a.getContent());
		// 서비스에서 스트링형 배열로 sql실행결과 받은담에 홈컨트롤러에서 모델에 넣어서 전달

		return "file_content";
	}
	
	
	
	@RequestMapping(value = "/fileupdate", method = RequestMethod.GET)
	public String fileupdate(HttpServletRequest request, Locale locale, Model model) throws Exception {

		String num = request.getParameter("num");

		List<FileContentVO2> GetContentList = service.getfile(num);
		model.addAttribute("GetContentList", GetContentList);
		System.out.println("HomeController4");
		FileContentVO2 a = GetContentList.get(0);
		System.out.println(GetContentList.get(0));
		System.out.println(a.getTitle());
		System.out.println(a.getContent());
		// 서비스에서 스트링형 배열로 sql실행결과 받은담에 홈컨트롤러에서 모델에 넣어서 전달

		return "fileupdate";
	}

	@RequestMapping(value = "/newupdate", method = RequestMethod.GET)
	public String newupdate(HttpServletRequest request, Locale locale, Model model) throws Exception {

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
	public String login(Locale locale, Model model) throws Exception {
		System.out.println("i1");
		logger.info("login");
		return "login";
	}

	@RequestMapping(value = "/logining", method = RequestMethod.GET)
	public String logining(HttpServletRequest request, Model model) throws Exception {
		System.out.println("loginingining");
		logger.info("loginning");
		boolean aa = service.logining(request);
		System.out.println(aa);

		// 반환값 트루면 로그인 ok 아니면 false로
		if (aa == true) {
			String a = request.getParameter("ID");
			HttpSession session = request.getSession();
			session.setAttribute("ID", a);
			System.out.println("ddd" + session.getAttribute("ID"));
			return "redirect:home";

		} else {
			return "redirect:memberjoin";
		}

	}

	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insert(Locale locale, Model model) throws Exception {
		System.out.println("insert11");
		logger.info("insert");
		return "insert";
	}

	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write(Locale locale, Model model) throws Exception {
		System.out.println("write1");
		logger.info("write");
		return "write";
	}

	@RequestMapping(value = "/file_write", method = RequestMethod.GET)
	public String file_write(Locale locale, Model model) throws Exception {
		System.out.println("write1");
		logger.info("write");
		return "file_write";
	}

	@RequestMapping(value = "/memberjoin", method = RequestMethod.GET)
	public String memberjoin(Locale locale, Model model) throws Exception {
		System.out.println("i1");
		logger.info("imberjoi");
		return "memberjoin";
	}

	@RequestMapping(value = "/memberjoining", method = RequestMethod.GET)
	public String memberjoining(HttpServletRequest request, Model model) throws Exception {
		System.out.println("memberjoining");
		logger.info("memberjoining");
		service.memberjoining(request);
		System.out.println("memberjoining2");
		return "redirect:home";
	}

	@RequestMapping(value = "/inserting", method = RequestMethod.GET)
	public String inserting(HttpServletRequest request, Model model) throws Exception {
		System.out.println("inserting11");
		logger.info("inserting");
		service.inserting(request);
		System.out.println("inserting12");
		return "redirect:home";
	}

	@RequestMapping(value = "/writing", method = RequestMethod.POST)
	public String writing(HttpServletRequest request, Model model) throws Exception {
		System.out.println("ritin11");
		logger.info("iritin");
		service.writing(request);
		System.out.println("inserting12");
		return "redirect:home";
	}

	@RequestMapping(value = "/writesub", method = RequestMethod.POST)
	public String writesub(HttpServletRequest request, Model model) throws Exception {
		String num = (String) request.getParameter("num");
		String url = "redirect:content?num=" + num;
		logger.info("iritin");
		service.writesub(request);

		return url;
	}

	@RequestMapping(value = "/newupdating", method = RequestMethod.POST)
	public String newupdating(HttpServletRequest request, Locale locale, Model model) throws Exception {

		logger.info("newupdateing");

		service.newupdating(request);

		return "redirect:home";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(HttpServletRequest request, Locale locale, Model model) throws Exception {
		System.out.println("HomeController1");
		logger.info("update");
		System.out.println("HomeController2");
		String test = request.getParameter("ID");
		String test1 = request.getParameter("PW");
		String test2 = request.getParameter("NAME");

		/*
		 * List<MemberVO> memberList = service.selectMember();
		 * System.out.println("HomeController3"); model.addAttribute("memberList",
		 * memberList);
		 */
		System.out.println(test);
		System.out.println(test1);
		System.out.println(test2);

		MemberVO vv = new MemberVO();

		return "update";
	}

	@RequestMapping(value = "/updating", method = RequestMethod.GET)
	public String updating(HttpServletRequest request, Model model) throws Exception {
		System.out.println("updating11");
		logger.info("updating");
		service.updating(request);
		System.out.println("iupdating12");
		return "redirect:home";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleting(HttpServletRequest request, Model model) throws Exception {
		System.out.println("deletting11");
		logger.info("deleting");
		service.deleting(request);
		System.out.println("deletting12");
		return "redirect:home";
	}
	
	@RequestMapping(value = "/filedelete", method = RequestMethod.GET)
	public String filedeleting(HttpServletRequest request, Model model) throws Exception {
		System.out.println("deletting11");
		logger.info("deleting");
		service.filedeleting(request);
		System.out.println("deletting12");
		return "redirect:home";
	}

	@RequestMapping(value = "/photo_write")
	public String photo_write() throws Exception {

		return "photo_write";
	}
	
	

	@RequestMapping(value = "/file", method = RequestMethod.GET)
	public String file(HttpServletRequest request, Model model) throws Exception {
		System.out.println("11111?????????????");
		List<FileContentVO> FileCList = service.selectFile();
		System.out.println("2222?????????????");
		int Fnum = service.Fnum();
		System.out.println("33333?????????????");
		model.addAttribute("FileCList", FileCList);
		model.addAttribute("Fnum", Fnum);
		System.out.println("44444444?????????????");

		return "file";
	}

	@RequestMapping(value = "/photo", method = RequestMethod.GET)
	public String photo(HttpServletRequest request, Model model) throws Exception {
		System.out.println("11111?????????????");
		List<FileContentVO> PhotoCList = service.selectPhoto();
		System.out.println("2222?????????????");
		int Pnum = service.Pnum();
		System.out.println("33333?????????????");
		model.addAttribute("PhotoCList", PhotoCList);
		model.addAttribute("Pnum", Pnum);
		System.out.println("44444444?????????????");

		return "photo";
	}
	
	@RequestMapping(value = "/photopage")
	public String photopage(HttpServletRequest request, Locale locale, Model model) throws Exception {

		String num = request.getParameter("num");

		List<FileContentVO> GetContentList = service.page_photo(num);
		model.addAttribute("GetContentList", GetContentList);
		System.out.println("HomeController4");
		FileContentVO a = GetContentList.get(0);
		System.out.println(GetContentList.get(0));
		System.out.println(a.getTitle());
		System.out.println(a.getContents());
		// 서비스에서 스트링형 배열로 sql실행결과 받은담에 홈컨트롤러에서 모델에 넣어서 전달
		
		return "photopage";
	}

	@RequestMapping(value = "/fileupload", method = RequestMethod.POST)
	public String upload(MultipartFile uploadfile, HttpServletRequest request, Model model) throws Exception {
		logger.info("upload() POST 호출");
		logger.info("파일 이름: {}", uploadfile.getOriginalFilename());
		logger.info("파일 크기: {}", uploadfile.getSize());

		String result = saveFile(uploadfile);

		String save_file_name = result; // 저장하고 받은 결과

		request.setAttribute("save_file_name", save_file_name);

		// 이하로 db에 저장할 거 다 request 담아서 db에 저장후 게시판에 사진출력 만들기
		System.out.println("??111111");

		service.filewriting(request);

		System.out.println("??666666");

		System.out.println("dididiid" + result);

		if (result != null) { // 파일 저장 성공
			model.addAttribute("result", result);
		} else { // 파일 저장 실패
			model.addAttribute("result", "fail");
		}

		return "redirect:file";
	}
	
	@RequestMapping(value = "/fileupdating", method = RequestMethod.POST)
	public String fileupdating(MultipartFile uploadfile, HttpServletRequest request, Model model) throws Exception {
		
		String result = saveFile(uploadfile);

		String save_file_name = result; // 저장하고 받은 결과

		request.setAttribute("save_file_name", save_file_name);

		service.fileupdating(request);

		if (result != null) { // 파일 저장 성공
			model.addAttribute("result", result);
		} else { // 파일 저장 실패
			model.addAttribute("result", "fail");
		}

		return "redirect:file";
	}

	@RequestMapping(value = "/photoupload", method = RequestMethod.POST)
	public String photoupload(MultipartFile uploadfile, HttpServletRequest request, Model model) throws Exception {
		logger.info("upload() POST 호출");
		logger.info("파일 이름: {}", uploadfile.getOriginalFilename());
		logger.info("파일 크기: {}", uploadfile.getSize());
		
		String result = saveFile(uploadfile);
		String save_file_name = result; // 저장하고 받은 결과
		request.setAttribute("save_file_name", save_file_name);
		service.photowriting(request);

		if (result != null) { // 파일 저장 성공
			model.addAttribute("result", result);
		} else { // 파일 저장 실패
			model.addAttribute("result", "fail");
		}

		return "redirect:photo";
	}
   
	private String saveFile(MultipartFile file) {
		// 파일 이름 변경
		UUID uuid = UUID.randomUUID();
		String saveName = uuid + "_" + file.getOriginalFilename();
		logger.info("saveName: {}", saveName);
		/*File saveFile = new File("C:\\Users\\천태경\\eclipse-workspace05\\Portfolio\\src\\main\\webapp\\resources\\img",
				saveName);*/ // 저장할 폴더 이름, 저장할 파일 이름
		File saveFile = new File("/tomcat/webapps/Portfolio/resources/img",
				saveName); 
		
		try {
			file.transferTo(saveFile); // 업로드 파일에 saveFile이라는 껍데기 입힘
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return saveName;
	} // end saveFile(

	@RequestMapping(value = "/download")
	public void download(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String filename = request.getParameter("filename");

		byte fileByte[] = FileUtils.readFileToByteArray(new File("C:\\Study\\fileupload\\" + filename));

		response.setContentType("application/octet-stream");
		response.setContentLength(fileByte.length);
		response.setHeader("Content-Disposition",
				"attachment; fileName=\"" + URLEncoder.encode(filename, "UTF-8") + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.getOutputStream().write(fileByte);

		response.getOutputStream().flush();
		response.getOutputStream().close();
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "test";
	}


}
