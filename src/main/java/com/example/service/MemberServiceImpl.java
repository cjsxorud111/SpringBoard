package com.example.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.MemberDAO;
import com.example.dto.MemberjoinVO;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDAO dao;
	
	@Override
	public String logining(HttpServletRequest request) throws Exception {
		String a = request.getParameter("ID");
		String b = request.getParameter("PW");
		String c = dao.selectpw(a);
		System.out.println("요기"+c);
		if (c == null) {
			return "noId";
		}
		if (b.equals(c)) {
			return "true";
		} else {
			return "notPw";
		}
	}
	
	
	@Override
	public void memberjoining(HttpServletRequest request) throws Exception {
		MemberjoinVO vo = new MemberjoinVO();
		vo.setId(request.getParameter("ID"));
		vo.setPw(request.getParameter("PW"));
		vo.setName(request.getParameter("NAME"));
		vo.setEmail(request.getParameter("EMAIL"));
		dao.memberjoining(vo);
	}
	
}
