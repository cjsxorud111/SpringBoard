package com.example.service;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.MemberDAO;
import com.example.dto.ContentsVO;
import com.example.dto.FileContentVO;
import com.example.dto.FileContentVO2;
import com.example.dto.GetContentVO;
import com.example.dto.HomeContentVO;
import com.example.dto.MemberVO;
import com.example.dto.MemberjoinVO;
import com.example.dto.NewupdatingVO;
import com.example.dto.SubVO;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDAO dao;
	
	@Override
	public boolean logining(HttpServletRequest request) throws Exception {
		String a = request.getParameter("ID");
		String b = request.getParameter("PW");
		System.out.println(a + b + "여기여기ㅗ");
		String c = dao.selectpw(a);
		if (b.equals(c)) {
			return true;
		} else {
			return false;
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
