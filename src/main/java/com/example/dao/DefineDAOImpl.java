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
	public void newword_writing(NewwordVO vo) throws Exception {
		System.out.println("2222");
		sqlSession.insert(Namespace + ".newword_writing", vo);
		System.out.println("3333----");
	}

	@Override
	public List<MainDefineContentVO> selectMainDefCon() throws Exception {
		List<MainDefineContentVO> MainDefineList = sqlSession.selectList(Namespace + ".mainDefineList");
		return MainDefineList;
	}

	@Override
	public void define_sub(DefineSubVO vo) throws Exception {
		
		sqlSession.insert(Namespace + ".define_sub", vo);
	}

	@Override
	public List<DefineSubVO> getDefinSubList() throws Exception {
		List<DefineSubVO> getDefinSubList = sqlSession.selectList(Namespace + ".getDefinSubList");
		return getDefinSubList;
		
	}
}
