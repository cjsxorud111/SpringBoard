package com.example.dao;

import java.util.List;

import com.example.dto.DefineSubVO;
import com.example.dto.GetModifyContentVO;
import com.example.dto.MainDefineContentVO;
import com.example.dto.NewwordVO;
import com.example.dto.RecommendVO;
import com.example.dto.ReturnRecommendVO;
import com.example.dto.memberRankingVO;

public interface DefineDAO {
	public void newwordWriting(NewwordVO vo) throws Exception;

	public List<MainDefineContentVO> selectMainDefCon() throws Exception;
	
	public List<MainDefineContentVO> linkCon(String linkWord) throws Exception;

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

	public GetModifyContentVO defineContentModify(String conNum) throws Exception;

	public void modifyWriting(GetModifyContentVO vo)throws Exception;

	public List<MainDefineContentVO> selectRecommendMainDefCon()throws Exception;

	public List<memberRankingVO> memberRanking()throws Exception;

}
