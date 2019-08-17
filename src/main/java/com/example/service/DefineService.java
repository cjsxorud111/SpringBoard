package com.example.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.dto.memberRankingVO;
import com.example.dto.DefineSubVO;
import com.example.dto.GetModifyContentVO;
import com.example.dto.MainDefineContentVO;

public interface DefineService {
	
	public void newwordWriting(HttpServletRequest request) throws Exception;

	public List<MainDefineContentVO> selectMainDefCon()throws Exception;
	  
	public List<MainDefineContentVO> linkCon(HttpServletRequest request)throws Exception;

	public void defineWriteSub(HttpServletRequest request)throws Exception;

	public List<DefineSubVO> getDefinSubList()throws Exception;

	public void defineSecondSub(HttpServletRequest request)throws Exception;

	public String deleteDefineSub(String pw, String num)throws Exception;

	public String recommendUp(HttpServletRequest request, String upNumber, String conNum)throws Exception;

	public String recommendDown(HttpServletRequest request, String downNumber, String conNum)throws Exception;

	public void deleteDefineContent(HttpServletRequest request)throws Exception;

	public String searchWord(HttpServletRequest request)throws Exception;

	public GetModifyContentVO defineContentModify(HttpServletRequest request)throws Exception;

	public void modifyWriting(HttpServletRequest request)throws Exception;

	public List<memberRankingVO> memberRanking()throws Exception;

}
