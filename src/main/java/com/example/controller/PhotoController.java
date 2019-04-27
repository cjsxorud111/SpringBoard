package com.example.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
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

import com.example.dto.FileContentVO;
import com.example.service.PhotoService;
import com.google.gson.JsonObject;

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
	
	
//	@RequestMapping(value = "img")
//	public void ckeditorImageUpload(MultipartFile upload, HttpServletRequest request, Model model) throws Exception {
//
//		System.out.println("1");
//		
//		String result = saveFile(upload);System.out.println("여기되나0");
//		String save_file_name = result; 
//		request.setAttribute("save_file_name", save_file_name);System.out.println("여기되나1");
//		System.out.println("여기되나2");
//		
//
//		
//		
//	}
	
	
	
	
	/**
     * 이미지 업로드
     * @param request
     * @param response
     * @param upload
     */
    @RequestMapping(value = "img", method = RequestMethod.POST)
    public void communityImageUpload(HttpServletRequest request, HttpServletResponse response, @RequestParam MultipartFile upload) {
 
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
            out = new FileOutputStream(new File(uploadPath));
            out.write(bytes);
            String callback = request.getParameter("CKEditorFuncNum");
            System.out.println(callback);
            printWriter = response.getWriter();
            String fileUrl = "/Users/taeky/eclipse-workspace/SpringBoard/src/main/webapp/resources/img/" + fileName; //url경로
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
		System.out.println("여긴?1");
		UUID uuid = UUID.randomUUID();
		System.out.println(file.getOriginalFilename());
		String saveName = uuid + "_" + file.getOriginalFilename();
		System.out.println("여긴?2");
		
//		Macintosh HD⁩ ▸ ⁨사용자⁩ ▸ ⁨taeky⁩ ▸ ⁨eclipse-workspace⁩ ▸ ⁨SpringBoard⁩ ▸ ⁨src⁩ ▸ ⁨main⁩ ▸ ⁨webapp⁩ ▸ ⁨resources⁩
		File saveFile = new File("/Users/taeky/eclipse-workspace/SpringBoard/src/main/webapp/resources/img",
				saveName);System.out.println("여긴?3");// ������ ���� �̸�, ������ ���� �̸�
		/*File saveFile = new File("/tomcat/webapps/Portfolio/resources/img",
				saveName); Users/taeky/eclipse-workspace/SpringBoard/src/main/webapp*/
				System.out.println("여긴?4");
		try {
			file.transferTo(saveFile); // ���ε� ���Ͽ� saveFile�̶�� ������ ����
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return saveName;
	} // end saveFile(
}
