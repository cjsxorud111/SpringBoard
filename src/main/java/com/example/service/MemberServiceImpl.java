package com.example.service;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.MemberDAO;
import com.example.dto.MemberVO;
import com.example.dto.ContentsVO;
import com.example.dto.MemberjoinVO;

@Service
public class MemberServiceImpl implements MemberService {
	
	
	@Inject
	private MemberDAO dao;
	
	@Override
	public List<MemberVO> selectMember() throws Exception {
		System.out.println("MemberService");
		return dao.selectMember();
	}
	
	@Override
	public void inserting(HttpServletRequest request) throws Exception {
		System.out.println("MembeSERVICE INSERTING");
		MemberVO vo= new MemberVO();
		vo.setId(request.getParameter("ID"));
		vo.setPw(request.getParameter("PW"));
		vo.setName(request.getParameter("ID"));
		dao.inserting(vo);
	}
	
	@Override
	public void writing(HttpServletRequest request) throws Exception {
		System.out.println("MembeING");
		ContentsVO vo= new ContentsVO();
		vo.setId(request.getParameter("ID"));
		vo.setTitle(request.getParameter("TITLE"));
		vo.setContents(request.getParameter("CONTENTS"));
		dao.writing(vo);
		
	}
	
	@Override
	public void memberjoining(HttpServletRequest request) throws Exception {
		System.out.println("MembING");
		MemberjoinVO vo= new MemberjoinVO();
		vo.setId(request.getParameter("ID"));
		vo.setPw(request.getParameter("PW"));
		vo.setName(request.getParameter("NAME"));
		vo.setEmail(request.getParameter("EMAIL"));
		dao.memberjoining(vo);
		
	}
	
	@Override
	public void updating(HttpServletRequest request) throws Exception {
		System.out.println("MembeSERVICE updaTING");
		MemberVO vo= new MemberVO();
		vo.setId(request.getParameter("ID"));
		vo.setPw(request.getParameter("PW"));
		vo.setName(request.getParameter("NAME"));
		dao.updating(vo);
	}
	
	@Override
	public void deleting(HttpServletRequest request) throws Exception {
		System.out.println("MembeSERVICE updaTING");
		MemberVO vo= new MemberVO();
		vo.setId(request.getParameter("ID"));
		
		dao.deleting(vo);
	}

	@Override
	public boolean logining(HttpServletRequest request) throws Exception {
		String a = request.getParameter("ID");
		String b = request.getParameter("PW");
		String c = dao.selectpw(a);
		System.out.println("test  "+b);
		System.out.println("test  "+c);
		if (b.equals(c)) {
			return true;
		}else {
			return false;
		}
		
	}

	

}
