package com.example.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.dto.FileContentVO;
import com.example.dto.FileContentVO2;
import com.example.dto.GetContentVO;
import com.example.dto.HomeContentVO;
import com.example.dto.MemberVO;
import com.example.dto.SubVO;

public interface PhotoService {
	
	public void photowriting(HttpServletRequest request) throws Exception;
	public List<FileContentVO> selectPhoto()throws Exception;
	public int Pnum()throws Exception;
	public List<FileContentVO> page_photo(String num)throws Exception;

}
