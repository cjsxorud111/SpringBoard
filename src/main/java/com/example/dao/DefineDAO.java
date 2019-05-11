package com.example.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.dto.ContentsVO;
import com.example.dto.DefineSubVO;
import com.example.dto.FileContentVO;
import com.example.dto.FileContentVO2;
import com.example.dto.GetContentVO;
import com.example.dto.HomeContentVO;
import com.example.dto.MainDefineContentVO;
import com.example.dto.MemberVO;
import com.example.dto.MemberjoinVO;
import com.example.dto.NewupdatingVO;
import com.example.dto.NewwordVO;
import com.example.dto.RecommendVO;
import com.example.dto.ReturnRecommendVO;
import com.example.dto.SubVO;

public interface DefineDAO {
	public void newwordWriting(NewwordVO vo) throws Exception;

	public List<MainDefineContentVO> selectMainDefCon() throws Exception;

	public void defineWriteSub(DefineSubVO vo) throws Exception;

	public List<DefineSubVO> getDefinSubList()throws Exception;

	public void DeleteAllSub() throws Exception;

	public DefineSubVO getDefinSub(int numb) throws Exception;

	public void deleteDefineSub(int numb) throws Exception;

	public void recommendUp(String upNumber, String conNum) throws Exception;

	public void recommendDown(String downNumber, String conNum) throws Exception;

	public void recommendWrite(RecommendVO recommendVO) throws Exception;

	public List<ReturnRecommendVO> recommendSelect(String conNum) throws Exception;

	public void deleteDefineContent(String conNum) throws Exception;

}
