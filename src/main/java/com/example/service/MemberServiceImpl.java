package com.example.service;

import com.example.dao.MemberDAO;
import com.example.dto.MemberjoinVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class MemberServiceImpl implements MemberService {

    final static Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

	private MemberDAO memberDAO;

	@Autowired
	public void memberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	@Override
	public String idValidCheck(String inputId) throws Exception {
		return memberDAO.idValidCheck(inputId);
	}

	@Override
	public String logining(HttpServletRequest request) throws Exception {
		String a = request.getParameter("ID");
		String b = request.getParameter("PW");
		String c = memberDAO.selectpw(a);
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
		memberDAO.memberjoining(vo);
	}
	
}
