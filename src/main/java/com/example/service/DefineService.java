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
	
	public void newwordWriting(HttpServletRequest request) throws Exception;

	public List<MainDefineContentVO> selectMainDefCon()throws Exception;

	public void defineWriteSub(HttpServletRequest request)throws Exception;

	public List<DefineSubVO> getDefinSubList()throws Exception;

	public void defineSecondSub(HttpServletRequest request)throws Exception;

	public String deleteDefineSub(String pw, String num)throws Exception;

	public String recommendUp(HttpServletRequest request, String upNumber, String conNum)throws Exception;

	public String recommendDown(HttpServletRequest request, String downNumber, String conNum)throws Exception;

}
