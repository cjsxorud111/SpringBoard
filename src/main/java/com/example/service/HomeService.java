package com.example.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.dto.FileContentVO;
import com.example.dto.FileContentVO2;
import com.example.dto.GetContentVO;
import com.example.dto.HomeContentVO;
import com.example.dto.MemberVO;
import com.example.dto.SubVO;

public interface HomeService {
	
	public List<HomeContentVO> selectContent() throws Exception;
	public void inserting(HttpServletRequest request) throws Exception;
	public void writing(HttpServletRequest request) throws Exception;
	public void updating(HttpServletRequest request) throws Exception;
	public void deleting(HttpServletRequest request) throws Exception;
	
	public List<GetContentVO> getcontent(String num) throws Exception;
	public void newupdating(HttpServletRequest request) throws Exception;
	public int Cnum() throws Exception;

	public void writesub(HttpServletRequest request)throws Exception;
	public List<SubVO> getsub(String num)throws Exception;

}
