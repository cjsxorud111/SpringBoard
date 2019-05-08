package com.example.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.dto.DefineSubVO;
import com.example.dto.FileContentVO;
import com.example.dto.FileContentVO2;
import com.example.dto.MainDefineContentVO;
import com.example.dto.NewwordVO;
import com.example.dto.SubVO;

@Repository
public class DefineDAOImpl implements DefineDAO {

	@Inject
	private SqlSession sqlSession;
	private static final String Namespace = "com.example.mapper.defineMapper";

	@Override
	public void newwordWriting(NewwordVO vo) throws Exception {
		sqlSession.insert(Namespace + ".newwordWriting", vo);
	}

	@Override
	public List<MainDefineContentVO> selectMainDefCon() throws Exception {
		List<MainDefineContentVO> MainDefineList = sqlSession.selectList(Namespace + ".mainDefineList");
		return MainDefineList;
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
	public void DeleteAllSub() throws Exception {
		sqlSession.delete(Namespace + ".deleteAllSub");
	}

	//댓글삭제를 위한 셀렉트
	@Override
	public DefineSubVO getDefinSub(int num) throws Exception {
		return sqlSession.selectOne(Namespace + ".getDefineSub", num);
	}
	
	//댓글삭제
	@Override
	public void deleteDefineSub(int num) throws Exception {
		sqlSession.delete(Namespace + ".deleteDefineSub", num);
	}
	//추천+1
	@Override
	public void recommendUp(String upNumber, String conNum) {
		sqlSession.update(Namespace + ".recommendUp", conNum);
	}
	//추천-1
	@Override
	public void recommendDown(String downNumber, String conNum) {
		sqlSession.update(Namespace + ".recommendDown", conNum);
	}
}
