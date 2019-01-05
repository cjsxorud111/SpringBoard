package com.example.dao;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.dto.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO {
 
	
	@Inject
	private SqlSession sqlSession;
	
	private static final String Namespace = "com.example.mapper.memberMapper";
	
	@Override
	public List<MemberVO> selectMember() throws Exception {
		System.out.println("MemberDAOImpl");
		return sqlSession.selectList(Namespace+".selectMember");
	}
	@Override
	public void inserting(MemberVO vo) throws Exception {
		System.out.println("Memberinserting");
		System.out.println(vo.getId());
		System.out.println("Memberinserting1");
		
		sqlSession.insert(Namespace+".insertingMember", vo);
		System.out.println("Memberinserting2");
	}
	
	@Override
	public void updating(MemberVO vo) throws Exception {
		System.out.println("Memberupdating");
		System.out.println(vo.getId());
		System.out.println("Memberupdating1");
		System.out.println(vo.getName());
		sqlSession.insert(Namespace+".updatingMember", vo);
		System.out.println("Membeting2");
	}
	
	@Override
	public void deleting(MemberVO vo) throws Exception {
		
		sqlSession.insert(Namespace+".deletingMember", vo);
		System.out.println("Membeting2");
	}

}
