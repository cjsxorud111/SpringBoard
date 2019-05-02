package com.example.service;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.DefineDAO;
import com.example.dao.FileDAO;
import com.example.dao.PhotoDAO;
import com.example.dto.DefineSubVO;
import com.example.dto.FileContentVO;
import com.example.dto.FileContentVO2;
import com.example.dto.HomeContentVO;
import com.example.dto.MainDefineContentVO;
import com.example.dto.NewwordVO;
import com.example.dto.SubVO;

@Service
public class DefineServiceImpl implements DefineService {

	@Autowired
	private DefineDAO dao;

	@Override
	public void newword_writing(HttpServletRequest request) throws Exception {
		NewwordVO vo = new NewwordVO();
		System.out.println("0000");
		vo.setWord(request.getParameter("WORD"));
		vo.setId(request.getParameter("ID"));
		vo.setPw(request.getParameter("PW"));
		vo.setEditor1(request.getParameter("editor1"));	
		System.out.println("1111");
		dao.newword_writing(vo);
	}

	@Override
	public List<MainDefineContentVO> selectMainDefCon() throws Exception {
		List<MainDefineContentVO> MainDefineList = dao.selectMainDefCon();
		//		for (int i = 0; i < MainDefineList.size(); i++) {
//			if(MainDefineList.get(i).getUp() == null ) {
//				
//			}
//			
//		}
		System.out.println("ㅅㅅㄷ");
		return MainDefineList;
	}

	@Override
	public void define_sub(HttpServletRequest request) throws Exception {
		DefineSubVO vo = new DefineSubVO();
		
		vo.setContent(request.getParameter("subcon"));
		vo.setConnum(request.getParameter("num"));
		vo.setSpace(request.getParameter("space"));

		dao.define_sub(vo);
		
	}

	@Override
	public List<DefineSubVO> getDefinSubList() throws Exception {
		
		return dao.getDefinSubList();
	}

	
	
}
