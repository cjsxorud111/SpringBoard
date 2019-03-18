package com.example.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.FileDAO;
import com.example.dto.FileContentVO;
import com.example.dto.FileContentVO2;

@Service
public class FileServiceImpl implements FileService {

	/* 여기 왜 오토와이어드 쓰는지 공부 */
	@Autowired
	private FileDAO dao;

	@Override
	public List<FileContentVO> selectFile() throws Exception {
		List<FileContentVO> selcon = dao.selectFile();

		return selcon;
	}
	
	
	@Override
	public void filedeleting(HttpServletRequest request) throws Exception {
		FileContentVO vo = new FileContentVO();
		vo.setNum(request.getParameter("num"));
		dao.filedeleting(vo);
	}

	

	@Override
	public List<FileContentVO2> getfile(String num) throws Exception {
		return dao.getfile(num);
	}
	

	@Override
	public int Fnum() throws Exception {
		return dao.Fnum();
	}

	@Override
	public void filewriting(HttpServletRequest request) throws Exception {
		FileContentVO vo = new FileContentVO();
		String save_file_name = (String) request.getAttribute("save_file_name");

		vo.setId(request.getParameter("ID"));
		vo.setTitle(request.getParameter("TITLE"));
		vo.setContents(request.getParameter("CONTENTS"));
		vo.setSave_file_name(save_file_name);

		dao.filewriting(vo);
	}
	
	@Override
	public void fileupdating(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		FileContentVO vo = new FileContentVO();
		String save_file_name = (String) request.getAttribute("save_file_name");
		vo.setNum(request.getParameter("num"));
		vo.setTitle(request.getParameter("title"));
		vo.setContents(request.getParameter("content"));
		vo.setSave_file_name(save_file_name);
		dao.fileupdating(vo);
	}
	
}
