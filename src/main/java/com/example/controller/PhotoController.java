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
		// ���񽺿��� ��Ʈ���� �迭�� sql������ �����㿡 Ȩ��Ʈ�ѷ����� �𵨿� �־ ����
		
		return "photopage";
	}

	@RequestMapping(value = "/photoupload", method = RequestMethod.POST)
	public String photoupload(MultipartFile uploadfile, HttpServletRequest request, Model model) throws Exception {
		
		String result = saveFile(uploadfile);
		String save_file_name = result; // �����ϰ� ���� ���
		request.setAttribute("save_file_name", save_file_name);
		service.photowriting(request);

		if (result != null) { // ���� ���� ����
			model.addAttribute("result", result);
		} else { // ���� ���� ����
			model.addAttribute("result", "fail");
		}

		return "redirect:photo";
	}
   
	private String saveFile(MultipartFile file) {
		// ���� �̸� ����
		UUID uuid = UUID.randomUUID();
		String saveName = uuid + "_" + file.getOriginalFilename();
		File saveFile = new File("C:\\Users\\õ�°�\\eclipse-workspace05\\Portfolio\\src\\main\\webapp\\resources\\img",
				saveName);// ������ ���� �̸�, ������ ���� �̸�
		/*File saveFile = new File("/tomcat/webapps/Portfolio/resources/img",
				saveName); */
		try {
			file.transferTo(saveFile); // ���ε� ���Ͽ� saveFile�̶�� ������ ����
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return saveName;
	} // end saveFile(
}
