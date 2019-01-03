package com.example.service;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.example.dao.MemberDAO;
import com.example.dto.MemberVO;

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

}
