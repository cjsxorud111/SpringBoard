package com.example.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.example.controller.DefineController;
import com.example.dto.DefineSubVO;
import com.example.dto.GetModifyContentVO;
import com.example.dto.MainDefineContentVO;
import com.example.dto.NewwordVO;
import com.example.dto.RecommendVO;
import com.example.dto.ReturnRecommendVO;
import com.example.dto.memberRankingVO;

@Repository
public class DefineDAOImpl implements DefineDAO {

	@Inject
	private SqlSession sqlSession;
	private static final String Namespace = "com.example.mapper.defineMapper";
	final static Logger logger = LoggerFactory.getLogger(DefineDAOImpl.class);

	@Override
	public void newwordWriting(NewwordVO vo) {
		sqlSession.insert(Namespace + ".newwordWriting", vo);
	}
	
	@Override
	public List<MainDefineContentVO> selectMainDefCon() {
		List<MainDefineContentVO> MainDefineList = sqlSession.selectList(Namespace + ".mainDefineList");
		return MainDefineList;
	}    
	
	@Override
	public List<memberRankingVO> memberRanking() throws Exception {
//		List<memberRankingVO> memberRanking = null;
//		
//		try {
//			memberRanking = sqlSession.selectList(Namespace + ".memberRanking");
//		} catch (Exception e) {
//			// TODO: handle exception
//		} finally {
//			logger.error("sql","sql에러발생");
//		}
		List<memberRankingVO> memberRanking = sqlSession.selectList(Namespace + ".memberRanking");
		return memberRanking;
	}
	
	@Override
	public List<MainDefineContentVO> selectRecommendMainDefCon() {
		List<MainDefineContentVO> MainDefineList = sqlSession.selectList(Namespace + ".recommendDefineList");
		return MainDefineList;
	}
	
	@Override
	public List<MainDefineContentVO> linkCon(String linkWord) throws Exception {
		List<MainDefineContentVO> linkCon = sqlSession.selectList(Namespace + ".linkCon", linkWord);
		return linkCon;
	}
	
	//기존 추천,비추천 여부를 확인하기위한 셀렉트
	@Override
	public List<ReturnRecommendVO> recommendSelect(String conNum) throws Exception {
		List<ReturnRecommendVO> recommendSelect = sqlSession.selectList(Namespace + ".recommendSelect", conNum);
		return recommendSelect;
	}
	
	@Override
	public void defineWriteSub(DefineSubVO vo) throws Exception {
		sqlSession.insert(Namespace + ".defineWriteSub", vo);
	}

	@Override
	public List<DefineSubVO> getDefinSubList() throws Exception {
		List<DefineSubVO> getDefinSubList = sqlSession.selectList(Namespace + ".getDefinSubList");
		return getDefinSubList;
	}

	@Override
	public void DeleteAllSub() {
		sqlSession.delete(Namespace + ".deleteAllSub");
	}

	//댓글삭제를 위한 셀렉트
	@Override
	public DefineSubVO getDefinSub(int num) throws Exception {
		return sqlSession.selectOne(Namespace + ".getDefineSub", num);
	}
	
	@Override
	public GetModifyContentVO defineContentModify(String conNum) throws Exception {
		return sqlSession.selectOne(Namespace + ".getDefineModifyCon", conNum);
	}
	
	//댓글삭제
	@Override
	public void deleteDefineSub(int num) throws Exception {
		sqlSession.delete(Namespace + ".deleteDefineSub", num);
	}
	
	//추천+1
	@Override
	public void recommendUp(String upNumber, String conNum) throws Exception {
		sqlSession.update(Namespace + ".recommendUp", conNum);
	}
	
	//추천-1
	@Override
	public void recommendDown(String downNumber, String conNum) throws Exception {
		sqlSession.update(Namespace + ".recommendDown", conNum);
	}
	
	@Override
	public void modifyWriting(GetModifyContentVO vo) throws Exception {
		sqlSession.update(Namespace + ".modifyWriting", vo);
	}

	@Override
	public void recommendWrite(RecommendVO recommendVO) throws Exception {
		sqlSession.insert(Namespace + ".recommendWriting", recommendVO);
	} 

	@Override
	public void deleteDefineContent(String conNum) throws Exception {
		//글,글에연관된댓글,추천삭제
		sqlSession.delete(Namespace + ".deleteDefineContent", conNum);
		sqlSession.delete(Namespace + ".deleteDefineAllConSub", conNum);
		sqlSession.delete(Namespace + ".deleteDefineRecommend", conNum);
	}

}
