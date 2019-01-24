package com.example.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.dto.FileContentVO;
import com.example.dto.FileContentVO2;
import com.example.dto.GetContentVO;
import com.example.dto.HomeContentVO;
import com.example.dto.MemberVO;

public interface MemberService {
	
	public List<HomeContentVO> selectContent() throws Exception;
	public void inserting(HttpServletRequest request) throws Exception;
	public void writing(HttpServletRequest request) throws Exception;
	public void memberjoining(HttpServletRequest request) throws Exception;
	public void updating(HttpServletRequest request) throws Exception;
	public void deleting(HttpServletRequest request) throws Exception;
	public boolean logining(HttpServletRequest request) throws Exception;
	public List<GetContentVO> getcontent(String num) throws Exception;
	public List<FileContentVO2> getfile(String num) throws Exception;
	public void newupdating(HttpServletRequest request) throws Exception;
	public int Cnum() throws Exception;
	public void filewriting(HttpServletRequest request) throws Exception;
	public List<FileContentVO> selectFile() throws Exception;
	public int Fnum() throws Exception;
	public void photowriting(HttpServletRequest request) throws Exception;
	public List<FileContentVO> selectPhoto()throws Exception;
	public int Pnum()throws Exception;
	
	

}
