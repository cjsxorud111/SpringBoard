package com.example.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.dto.DefineSubVO;
import com.example.dto.FileContentVO;
import com.example.dto.FileContentVO2;
import com.example.dto.GetContentVO;
import com.example.dto.HomeContentVO;
import com.example.dto.MainDefineContentVO;
import com.example.dto.MemberVO;
import com.example.dto.SubVO;

public interface DefineService {
	
	public void newword_writing(HttpServletRequest request) throws Exception;

	public List<MainDefineContentVO> selectMainDefCon()throws Exception;

	public void define_sub(HttpServletRequest request)throws Exception;

	public List<DefineSubVO> getDefinSubList()throws Exception;

}
