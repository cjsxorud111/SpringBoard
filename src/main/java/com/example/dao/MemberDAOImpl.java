package com.example.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.dto.MemberjoinVO;

@Repository
public class MemberDAOImpl implements MemberDAO {

	@Inject
	private SqlSession sqlSession;
	private static final String Namespace = "com.example.mapper.memberMapper";

	@Override
	public void memberjoining(MemberjoinVO vo) throws Exception {
		System.out.println(vo.getId());
		System.out.println(vo.getPw());
		System.out.println(vo.getName());
		System.out.println(vo.getEmail());
		sqlSession.insert(Namespace + ".memberjoining", vo);
	}
	
	@Override
	public String selectpw(String a) throws Exception {
		return sqlSession.selectOne(Namespace + ".selectpw", a);
	}

}
