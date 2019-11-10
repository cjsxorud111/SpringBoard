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
		logger.info("");
		
		try {
			sqlSession.insert(Namespace + ".newwordWriting", vo);
		} catch (Exception e) {
			logger.error("sqlError : ",e);
		}
	}
	
	@Override
	public List<MainDefineContentVO> selectMainDefCon() {
		logger.info("");
		
		List<MainDefineContentVO> MainDefineList = null; 
		
		try {
			MainDefineList = sqlSession.selectList(Namespace + ".mainDefineList");
		} catch (Exception e) {
			logger.error("sqlError : ",e);
		} 
		
		return MainDefineList;
	}    
	
	@Override
	public List<memberRankingVO> memberRanking() {
		
		List<memberRankingVO> memberRanking = null;
		
		try {
			memberRanking = sqlSession.selectList(Namespace + ".memberRanking");
		} catch (Exception e) {
			logger.error("sqlError : ",e);
		} 
		
		return memberRanking;
	}
	
	@Override
	public List<MainDefineContentVO> selectRecommendMainDefCon(String inputText) {
		
		List<MainDefineContentVO> MainDefineList = null;
		
		try {
			MainDefineList = sqlSession.selectList(Namespace + ".recommendDefineList", inputText);
		} catch (Exception e) {
			logger.error("sqlError : ",e);
		}
		
		return MainDefineList;
	}
	
	@Override
	public List<MainDefineContentVO> linkCon(String linkWord) {
		logger.info("");
		
		List<MainDefineContentVO> linkCon = null;
		
		try {
			linkCon = sqlSession.selectList(Namespace + ".linkCon", linkWord);
		} catch (Exception e) {
			logger.error("sqlError : ",e);
		}
		
		return linkCon;
	}
	
	//기존 추천,비추천 여부를 확인하기위한 셀렉트
	@Override
	public List<ReturnRecommendVO> recommendSelect(String conNum) {
		logger.info("");
		
		List<ReturnRecommendVO> recommendSelect = null;
		
		try {
			recommendSelect = sqlSession.selectList(Namespace + ".recommendSelect", conNum);
		} catch (Exception e) {
			logger.error("sqlError : ",e);
		}
		
		return recommendSelect;
	}
	
	@Override
	public void defineWriteSub(DefineSubVO vo) {
		logger.info("");
		
		try {
			sqlSession.insert(Namespace + ".defineWriteSub", vo);
		} catch (Exception e) {
			logger.error("sqlError : ",e);
		}
	}

	@Override
	public List<DefineSubVO> getDefinSubList() {
		logger.info("");
		
		List<DefineSubVO> getDefinSubList = null; 
		
		try {
			getDefinSubList = sqlSession.selectList(Namespace + ".getDefinSubList");
		} catch (Exception e) {
			logger.error("sqlError : ",e);
		}
		return getDefinSubList;
	}

	@Override
	public void DeleteAllSub() {
		logger.info("");
		
		try {
			sqlSession.delete(Namespace + ".deleteAllSub");
		} catch (Exception e) {
			logger.error("sqlError : ",e);
		}
	}

	//댓글삭제를 위한 셀렉트
	@Override
	public DefineSubVO getDefinSub(int num) {
		logger.info("");

		DefineSubVO defineSub = null;
		
		try {
			 defineSub = sqlSession.selectOne(Namespace + ".getDefineSub", num);
		} catch (Exception e) {
			logger.error("sqlError : ",e);
		}
		
		return defineSub;
	}
	
	@Override
	public GetModifyContentVO defineContentModify(String conNum) {
		logger.info("");
		
		GetModifyContentVO modifyContent = null;
		
		try {
			modifyContent = sqlSession.selectOne(Namespace + ".getDefineModifyCon", conNum);
		} catch (Exception e) {
			logger.error("sqlError : ",e);
		}
		return modifyContent;
	}
	
	//댓글삭제
	@Override
	public void deleteDefineSub(int num) {
		logger.info("");
		
		try {
			sqlSession.delete(Namespace + ".deleteDefineSub", num);
		} catch (Exception e) {
			logger.error("sqlError : ",e);
		}
	}
	
	//추천+1
	@Override
	public void recommendUp(String upNumber, String conNum) {
		logger.info("");
		
		try {
			sqlSession.update(Namespace + ".recommendUp", conNum);
		} catch (Exception e) {
			logger.error("sqlError : ",e);
		}
	}
	
	//추천-1
	@Override
	public void recommendDown(String downNumber, String conNum) {
		logger.info("");
		
		try {
			sqlSession.update(Namespace + ".recommendDown", conNum);
		} catch (Exception e) {
			logger.error("sqlError : ",e);
		}
	}
	
	@Override
	public void modifyWriting(GetModifyContentVO vo) {
		logger.info("");
		
		try {
			sqlSession.update(Namespace + ".modifyWriting", vo);
		} catch (Exception e) {
			logger.error("sqlError : ",e);
		}
	}

	@Override
	public void recommendWrite(RecommendVO recommendVO) {
		logger.info("");
		
		try {
			sqlSession.insert(Namespace + ".recommendWriting", recommendVO);
		} catch (Exception e) {
			logger.error("sqlError : ",e);
		}
	} 

	@Override
	public void deleteDefineContent(String conNum) {
		logger.info("");
		
		//글,글에연관된댓글,추천삭제
		try {
			sqlSession.delete(Namespace + ".deleteDefineContent", conNum);
		} catch (Exception e) {
			logger.error("sqlError : ",e);
		}
		
		try {
			sqlSession.delete(Namespace + ".deleteDefineAllConSub", conNum);
		} catch (Exception e) {
			logger.error("sqlError : ",e);
		}

		try {
			sqlSession.delete(Namespace + ".deleteDefineRecommend", conNum);
		} catch (Exception e) {
			logger.error("sqlError : ",e);
		}
	}

}
