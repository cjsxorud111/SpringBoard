package com.example.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.example.dto.FileContentVO;
import com.example.service.PhotoService;

@Controller
public class PhotoController {

	@Inject
	private PhotoService service;
	
	@RequestMapping(value = "/photo_write")
	public String photo_write() throws Exception {
		return "photo_write";
	}

	@RequestMapping(value = "/photo", method = RequestMethod.GET)
	public String photo(HttpServletRequest request, Model model) throws Exception {
		List<FileContentVO> PhotoCList = service.selectPhoto();
		int Pnum = service.Pnum();
		model.addAttribute("PhotoCList", PhotoCList);
		model.addAttribute("Pnum", Pnum);
		return "photo";
	}
	
	@RequestMapping(value = "/photopage")
	public String photopage(HttpServletRequest request, Model model) throws Exception {
		String num = request.getParameter("num");

		List<FileContentVO> GetContentList = service.page_photo(num);
		model.addAttribute("GetContentList", GetContentList);
		FileContentVO a = GetContentList.get(0);
		// 서비스에서 스트링형 배열로 sql실행결과 받은담에 홈컨트롤러에서 모델에 넣어서 전달
		
		return "photopage";
	}

	@RequestMapping(value = "/photoupload", method = RequestMethod.POST)
	public String photoupload(MultipartFile uploadfile, HttpServletRequest request, Model model) throws Exception {
		
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
		File saveFile = new File("C:\\Users\\천태경\\eclipse-workspace05\\Portfolio\\src\\main\\webapp\\resources\\img",
				saveName);// 저장할 폴더 이름, 저장할 파일 이름
		/*File saveFile = new File("/tomcat/webapps/Portfolio/resources/img",
				saveName); */
		try {
			file.transferTo(saveFile); // 업로드 파일에 saveFile이라는 껍데기 입힘
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return saveName;
	} // end saveFile(
}
