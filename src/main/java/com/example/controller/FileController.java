package com.example.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.example.dto.FileContentVO;
import com.example.dto.FileContentVO2;
import com.example.service.FileService;


@Controller
public class FileController {

	@Inject
	private FileService service;
	
	@RequestMapping(value = "/file_content", method = RequestMethod.GET)
	public String file_content(HttpServletRequest request, Model model) throws Exception {

		String num = request.getParameter("num");

		List<FileContentVO2> GetContentList = service.getfile(num);
		model.addAttribute("GetContentList", GetContentList);
		FileContentVO2 a = GetContentList.get(0);
		// 서비스에서 스트링형 배열로 sql실행결과 받고 홈컨트롤러에서 모델에 넣어서 전달

		return "file_content";
	}
	
	@RequestMapping(value = "/fileupdate", method = RequestMethod.GET)
	public String fileupdate(HttpServletRequest request, Model model) throws Exception {
		String num = request.getParameter("num");

		List<FileContentVO2> GetContentList = service.getfile(num);
		model.addAttribute("GetContentList", GetContentList);
		FileContentVO2 a = GetContentList.get(0);

		return "fileupdate";
	}

	@RequestMapping(value = "/file_write", method = RequestMethod.GET)
	public String file_write(Model model) throws Exception {
		return "file_write";
	}
	
	@RequestMapping(value = "/filedelete", method = RequestMethod.GET)
	public String filedeleting(HttpServletRequest request, Model model) throws Exception {
		service.filedeleting(request);
		return "redirect:file";
	}

	@RequestMapping(value = "/file", method = RequestMethod.GET)
	public String file(HttpServletRequest request, Model model) throws Exception {
		List<FileContentVO> FileCList = service.selectFile();
		int Fnum = service.Fnum();
		model.addAttribute("FileCList", FileCList);
		model.addAttribute("Fnum", Fnum);

		return "file";
	}

	@RequestMapping(value = "/fileupload", method = RequestMethod.POST)
	public String upload(MultipartFile uploadfile, HttpServletRequest request, Model model) throws Exception {

		String result = saveFile(uploadfile);

		String save_file_name = result; // 저장하고 받은 결과

		request.setAttribute("save_file_name", save_file_name);

		// 이하로 db에 저장할 거 다 request 담아서 db에 저장후 게시판에 사진출력 만들기

		service.filewriting(request);

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
	} 

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
}
