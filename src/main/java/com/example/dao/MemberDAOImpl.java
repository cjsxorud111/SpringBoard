package com.example.dao;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.dto.ContentsVO;
import com.example.dto.GetContentVO;
import com.example.dto.HomeContentVO;
import com.example.dto.MemberVO;
import com.example.dto.MemberjoinVO;

@Repository
public class MemberDAOImpl implements MemberDAO {
 
	
	@Inject
	private SqlSession sqlSession;
	
	private static final String Namespace = "com.example.mapper.memberMapper";
	
	@Override
	public List<HomeContentVO> selectContent() throws Exception {
		System.out.println("MemberDAOImpl");
		return sqlSession.selectList(Namespace+".selectContent");
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
	public void writing(ContentsVO vo) throws Exception {
		System.out.println("Memberinserting");
		System.out.println(vo.getId());
		System.out.println("Memberinserting1");
		
		sqlSession.insert(Namespace+".writing", vo);
		System.out.println("Memberinserting2");
		
	}
	@Override
	public void memberjoining(MemberjoinVO vo) throws Exception {
		System.out.println("??");
		System.out.println(vo.getId());
		System.out.println(vo.getPw());
		System.out.println(vo.getName());
		System.out.println(vo.getEmail());
		sqlSession.insert(Namespace+".memberjoining", vo);
		System.out.println("???");
		
	}

	@Override
	public void updating(MemberVO vo) throws Exception {
		System.out.println("Memberupdating");
		System.out.println(vo.getId());
		System.out.println("Memberupdating1");
		System.out.println(vo.getName());
		sqlSession.update(Namespace+".updatingMember", vo);
		System.out.println("Membeting2");
	}
	
	@Override
	public void deleting(MemberVO vo) throws Exception {
		
		sqlSession.delete(Namespace+".deletingMember", vo);
		System.out.println("Membeting2");
	}
	@Override
	public String selectpw(String a) throws Exception {
		System.out.println("M?2");
		return sqlSession.selectOne(Namespace+".selectpw", a);
		
	}
	@Override
	public List<GetContentVO> getcontent(String num) throws Exception {
		
		return sqlSession.selectList(Namespace+".getcontent", num);
	}
	
}
