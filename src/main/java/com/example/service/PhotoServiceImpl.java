package com.example.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.PhotoDAO;
import com.example.dto.FileContentVO;

@Service
public class PhotoServiceImpl implements PhotoService {

	/* 여기 왜 오토와이어드 쓰는지 공부 */
	@Autowired
	private PhotoDAO dao;

	@Override
	public void photowriting(HttpServletRequest request) throws Exception {
		FileContentVO vo = new FileContentVO();
		String save_file_name = (String) request.getAttribute("save_file_name");

		vo.setId(request.getParameter("ID"));
		vo.setTitle(request.getParameter("TITLE"));
		vo.setContents(request.getParameter("CONTENTS"));
		vo.setSave_file_name(save_file_name);

		dao.photowriting(vo);
	}

	@Override
	public List<FileContentVO> selectPhoto() throws Exception {
		List<FileContentVO> selcon = dao.selectPhoto();

		return selcon;
	}
	
	@Override
	public List<FileContentVO> page_photo(String num) throws Exception {
		// TODO Auto-generated method stub
		return dao.page_photo(num);
	}
	
	@Override
	public int Pnum() throws Exception {
		return dao.Pnum();
	}
	
}
