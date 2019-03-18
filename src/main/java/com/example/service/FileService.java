package com.example.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.dto.FileContentVO;
import com.example.dto.FileContentVO2;
import com.example.dto.GetContentVO;
import com.example.dto.HomeContentVO;
import com.example.dto.MemberVO;
import com.example.dto.SubVO;

public interface FileService {
	
	public List<FileContentVO2> getfile(String num) throws Exception;
	public void filewriting(HttpServletRequest request) throws Exception;
	public List<FileContentVO> selectFile() throws Exception;
	public int Fnum() throws Exception;

	public void fileupdating(HttpServletRequest request)throws Exception;
	public void filedeleting(HttpServletRequest request)throws Exception;

}
