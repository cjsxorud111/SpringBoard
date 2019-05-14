package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.example.dto.RecommendVO;
import com.example.dto.ReturnRecommendVO;
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
		dao.newwordWriting(vo);
	}

	@Override
	public List<MainDefineContentVO> selectMainDefCon() throws Exception {
		List<MainDefineContentVO> MainDefineList = dao.selectMainDefCon();
		
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
		List<DefineSubVO> allSubList = dao.getDefinSubList(); // 전체테이블 가져옴
		dao.DeleteAllSub(); // 테이블내용 전체삭제
		List<DefineSubVO> newSubList = new ArrayList<DefineSubVO>(); // 중간에댓글 새로 삽입할 리스트
		for (int i = 0; i < allSubList.size(); i++) {
			DefineSubVO temp = new DefineSubVO();

			temp = allSubList.get(i);
			newSubList.add(temp);
			int a = 1;
			int b = 1;
			if (temp.getNum().equals(request.getParameter("subnum"))) {
				newSubList.add(vo);
			}
		}
		for (int i = 0; i < newSubList.size(); i++) {
			dao.defineWriteSub(newSubList.get(i));
		}
	}

	// 댓글삭제
	@Override
	public String deleteDefineSub(String pw, String num) throws Exception {
		int numb = Integer.parseInt(num);
		DefineSubVO defineSub = dao.getDefinSub(numb);
		String isDelete = "no";

		if (defineSub.getPw() != null && defineSub.getPw().equals(pw)) {
			dao.deleteDefineSub(numb);
			isDelete = "yes";
		}
		return isDelete;
	}

	@Override
	public String recommendUp(HttpServletRequest request, String upNumber, String conNum) throws Exception {
		HttpSession session = request.getSession();
		String sessionId = (String) session.getAttribute("ID");
		List<ReturnRecommendVO> recommendList = dao.recommendSelect(conNum);
		String isId = "no";
		//db에서이미추천한적있는지검사
		for (int i = 0; i < recommendList.size(); i++) {
			ReturnRecommendVO recom = recommendList.get(i);
			if (recommendList.get(i).getId().equals(sessionId)) {
				 
				isId = "yes";
			}
		}
		//db에추천이저장된적없다면추천+1
		if (isId.equals("no")) {
			dao.recommendUp(upNumber, conNum);
			RecommendVO recommendVO = new RecommendVO();
			recommendVO.setConNum(Integer.parseInt(conNum));
			recommendVO.setSessionId((String) session.getAttribute("ID"));
			dao.recommendWrite(recommendVO);
		}
		return isId;
	}

	@Override
	public String recommendDown(HttpServletRequest request, String downNumber, String conNum) throws Exception {
		HttpSession session = request.getSession();
		String sessionId = (String) session.getAttribute("ID");
		List<ReturnRecommendVO> recommendList = dao.recommendSelect(conNum);
		String isId = "no";
		for (int i = 0; i < recommendList.size(); i++) {
			if (recommendList.get(i).getId().equals(sessionId)) {
				isId = "yes";
			}
		}
		if (isId.equals("no")) {

			dao.recommendDown(downNumber, conNum);

			RecommendVO recommendVO = new RecommendVO();

			recommendVO.setConNum(Integer.parseInt(conNum));
			recommendVO.setSessionId((String) session.getAttribute("ID"));

			dao.recommendWrite(recommendVO);
		}
		return isId;
	}
	
	@Override
	public void deleteDefineContent(HttpServletRequest request) throws Exception {
		String conNum = request.getParameter("conNum");
		dao.deleteDefineContent(conNum);
	}

}
