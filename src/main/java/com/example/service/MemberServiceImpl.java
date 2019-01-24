package com.example.service;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.MemberDAO;
import com.example.dto.MemberVO;
import com.example.dto.ContentsVO;
import com.example.dto.FileContentVO;
import com.example.dto.FileContentVO2;
import com.example.dto.GetContentVO;
import com.example.dto.HomeContentVO;
import com.example.dto.MemberjoinVO;
import com.example.dto.NewupdatingVO;

@Service
public class MemberServiceImpl implements MemberService {
	
	
	/*여기 왜 오토와이어드 쓰는지 공부*/
	@Autowired
	private MemberDAO dao;
	
	@Override
	public List<HomeContentVO> selectContent() throws Exception {
		System.out.println("MemberService");
		List<HomeContentVO> selcon = dao.selectContent();
		
		System.out.println();
		
		return selcon;
	}
	
	@Override
	public List<FileContentVO> selectFile() throws Exception {
		List<FileContentVO> selcon = dao.selectFile();
		
		System.out.println();
		
		return selcon;
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
		NewupdatingVO vo= new NewupdatingVO();
		vo.setNum(request.getParameter("num"));
		
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

	@Override
	public List<GetContentVO> getcontent(String num) throws Exception {
		
		return dao.getcontent(num);
	}
	
	@Override
	public List<FileContentVO2> getfile(String num) throws Exception {
		// TODO Auto-generated method stub
		return dao.getfile(num);
	}

	@Override
	public void newupdating(HttpServletRequest request) throws Exception {
		NewupdatingVO NVO = new NewupdatingVO();
		NVO.setNum(request.getParameter("NUM"));
		NVO.setTitle(request.getParameter("TITLE"));
		NVO.setContent(request.getParameter("CONTENT"));
		System.out.println("??????  "+NVO.getNum());
		System.out.println(NVO.getTitle());
		System.out.println(NVO.getContent());
		dao.newupdating(NVO);
		
	}

	@Override
	public int Cnum() throws Exception {
		
		return dao.Cnum();
	}
	
	@Override
	public int Fnum() throws Exception {
		// TODO Auto-generated method stub
		return dao.Fnum();
	}

	
	@Override
	public void filewriting(HttpServletRequest request) throws Exception {
		FileContentVO vo = new FileContentVO();
		System.out.println(request.getAttribute("save_file_name"));
		String save_file_name = (String)request.getAttribute("save_file_name");
		
		vo.setId(request.getParameter("ID"));
		vo.setTitle(request.getParameter("TITLE"));
		vo.setContents(request.getParameter("CONTENTS"));
		vo.setSave_file_name(save_file_name);
		
		System.out.println("??444444");
		dao.filewriting(vo);
		
		
	}

	

	

	
	

}
