package com.example.service;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.HomeDAO;
import com.example.dto.ContentsVO;
import com.example.dto.GetContentVO;
import com.example.dto.HomeContentVO;
import com.example.dto.MemberVO;
import com.example.dto.NewupdatingVO;
import com.example.dto.SubVO;

@Service
public class HomeServiceImpl implements HomeService {

	
	@Autowired
	private HomeDAO dao;

	@Override
	public List<HomeContentVO> selectContent() throws Exception {
		List<HomeContentVO> selcon = dao.selectContent();

		return selcon;
	}

	@Override
	public void inserting(HttpServletRequest request) throws Exception {
		MemberVO vo = new MemberVO();
		vo.setId(request.getParameter("ID"));
		vo.setPw(request.getParameter("PW"));
		vo.setName(request.getParameter("ID"));
		dao.inserting(vo);
	}

	@Override
	public void writing(HttpServletRequest request) throws Exception {
		ContentsVO vo = new ContentsVO();
		vo.setId(request.getParameter("ID"));
		vo.setTitle(request.getParameter("TITLE"));
		vo.setContents(request.getParameter("CONTENTS"));
		dao.writing(vo);

	}
	
	@Override
	public int Cnum() throws Exception {
		return dao.Cnum();
	}
	
	@Override
	public void writesub(HttpServletRequest request) throws Exception {
		SubVO vo = new SubVO();
		String uid = null;
		for (int i=0;i<10;i++){
            uid = UUID.randomUUID().toString();
		}
		
		vo.setContent(request.getParameter("subcon"));
		vo.setConnum(request.getParameter("num"));
		vo.setSpace(request.getParameter("space"));
		vo.setUid(uid);
		vo.setUuid(request.getParameter("uuid"));
		
		dao.writesub(vo);
	}

	@Override
	public void deleting(HttpServletRequest request) throws Exception {
		NewupdatingVO vo = new NewupdatingVO();
		vo.setNum(request.getParameter("num"));
		dao.deleting(vo);
	}

	@Override
	public List<GetContentVO> getcontent(String num) throws Exception {
		return dao.getcontent(num);
	}
	
	@Override
	public List<SubVO> getsub(String num) throws Exception {
		int a =1;
		return dao.getsub(num);
	}
	
	@Override
	public void updating(HttpServletRequest request) throws Exception {
		MemberVO vo = new MemberVO();
		vo.setId(request.getParameter("ID"));
		vo.setPw(request.getParameter("PW"));
		vo.setName(request.getParameter("NAME"));
		dao.updating(vo);
	}
	
	
	@Override
	public void newupdating(HttpServletRequest request) throws Exception {
		NewupdatingVO NVO = new NewupdatingVO();
		NVO.setNum(request.getParameter("NUM"));
		NVO.setTitle(request.getParameter("TITLE"));
		NVO.setContent(request.getParameter("CONTENT"));
		dao.newupdating(NVO);
	}

	
}
