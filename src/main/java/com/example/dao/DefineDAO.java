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
	public void newwordWriting(NewwordVO vo);

	public List<MainDefineContentVO> selectMainDefCon();
	
	public List<MainDefineContentVO> linkCon(String linkWord);

	public void defineWriteSub(DefineSubVO vo);

	public List<DefineSubVO> getDefinSubList();

	public void DeleteAllSub();

	public DefineSubVO getDefinSub(int numb);

	public void deleteDefineSub(int numb);

	public void recommendUp(String upNumber, String conNum);

	public void recommendDown(String downNumber, String conNum);

	public void recommendWrite(RecommendVO recommendVO);

	public List<ReturnRecommendVO> recommendSelect(String conNum);

	public void deleteDefineContent(String conNum);

	public GetModifyContentVO defineContentModify(String conNum);

	public void modifyWriting(GetModifyContentVO vo);

	public List<MainDefineContentVO> selectRecommendMainDefCon();

	public List<memberRankingVO> memberRanking();
}
