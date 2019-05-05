package com.example.service;

import java.util.ArrayList;
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
import com.example.dto.NewupdatingVO;
import com.example.dto.NewwordVO;
import com.example.dto.SubVO;

@Service
public class DefineServiceImpl implements DefineService {

	@Autowired
	private DefineDAO dao;

	@Override
	public void newwordWriting(HttpServletRequest request) throws Exception {
		NewwordVO vo = new NewwordVO();
		vo.setWord(request.getParameter("WORD"));
		vo.setId(request.getParameter("ID"));
		vo.setPw(request.getParameter("PW"));
		vo.setEditor1(request.getParameter("editor1"));	
		System.out.println("1111");
		dao.newwordWriting(vo);
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
	public void defineWriteSub(HttpServletRequest request) throws Exception {
		DefineSubVO vo = new DefineSubVO();
		
		vo.setContent(request.getParameter("subcon"));
		vo.setPw(request.getParameter("pw"));
		vo.setConnum(request.getParameter("num"));
		vo.setSpace(request.getParameter("space"));

		dao.defineWriteSub(vo);
		
	}

	@Override
	public List<DefineSubVO> getDefinSubList() throws Exception {
		
		return dao.getDefinSubList();
	}

	@Override
	public void defineSecondSub(HttpServletRequest request) throws Exception {
		DefineSubVO vo = new DefineSubVO();
		
		vo.setContent(request.getParameter("subcon"));
		vo.setConnum(request.getParameter("connum"));
		vo.setSpace(request.getParameter("space"));
		vo.setNum(request.getParameter("subnum"));
		vo.setPw(request.getParameter("pw"));
		List<DefineSubVO> allSubList = dao.getDefinSubList(); //전체테이블 가져옴
		System.out.println("테스트1");
		dao.DeleteAllSub(); //테이블내용 전체삭제
		System.out.println("테스트2");
		List<DefineSubVO> newSubList = new ArrayList<DefineSubVO>(); //중간에댓글 새로 삽입할 리스트
		System.out.println();
		for (int i = 0; i < allSubList.size(); i++) {
			DefineSubVO temp = new DefineSubVO();
			
			temp = allSubList.get(i);
			newSubList.add(temp);
			int a = 1;
			int b =1;
			if(temp.getNum().equals(request.getParameter("subnum"))) {
				System.out.println("kokokoko");
				newSubList.add(vo);
			}
		}
		System.out.println("테스트3");
		for (int i = 0; i < newSubList.size(); i++) {
			dao.defineWriteSub(newSubList.get(i));
		}
		System.out.println("테스트4");
	}
    
	// 댓글삭제
	@Override
	public boolean deleteDefineSub(HttpServletRequest request) throws Exception {
		String pw = request.getParameter("PW");
		String num = request.getParameter("NUM");
		DefineSubVO subVo = dao.getDefinSub(num);
		if(subVo.getPw().equals(pw)) {
			dao.deleteDefineSub(num);
			return true;
		}
		return false;
	}
}
