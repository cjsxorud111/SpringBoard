package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.controller.DefineController;
import com.example.dao.DefineDAO;
import com.example.dto.DefineSubVO;
import com.example.dto.GetModifyContentVO;
import com.example.dto.MainDefineContentVO;
import com.example.dto.NewwordVO;
import com.example.dto.RecommendVO;
import com.example.dto.ReturnRecommendVO;
import com.example.dto.memberRankingVO;
import com.example.otherclass.HangulDivide;

@Service
public class DefineServiceImpl implements DefineService {

	@Autowired
	private DefineDAO dao;
	
	final static Logger logger = LoggerFactory.getLogger(DefineServiceImpl.class);
	
	@Override
	public void newwordWriting(HttpServletRequest request) throws Exception {
		
		NewwordVO vo = new NewwordVO();
		HangulDivide hanguldivide = new HangulDivide();
		vo.setWord(request.getParameter("WORD"));
		vo.setId(request.getParameter("ID"));
		vo.setPw(request.getParameter("PW"));
		vo.setEditor1(request.getParameter("editor1"));
		String divideCharacter = hanguldivide.toKoJasoAtom(request.getParameter("WORD"));
		vo.setSplitWord(divideCharacter);
		dao.newwordWriting(vo);
	}

	@Override
	public void modifyWriting(HttpServletRequest request) throws Exception {
		logger.info("");
		
		GetModifyContentVO vo = new GetModifyContentVO();
		vo.setWord(request.getParameter("WORD"));
		vo.setNum(request.getParameter("conNum"));
		vo.setInfo(request.getParameter("textValue"));
		dao.modifyWriting(vo);
	}

	@Override
	public List<MainDefineContentVO> selectMainDefCon(Locale locale) throws Exception {
		
		List<MainDefineContentVO> MainDefineList = dao.selectMainDefCon();
		return MainDefineList;
	}
	
	@Override
	public List<memberRankingVO> memberRanking() throws Exception {
		
		List<memberRankingVO> memberRanking = dao.memberRanking();
		return memberRanking;
	}
	
	@Override
	public List<MainDefineContentVO> linkCon(HttpServletRequest request) throws Exception {
		String linkWord = request.getParameter("linkWord");
		List<MainDefineContentVO> linkCon = dao.linkCon(linkWord);
		return linkCon;
	}
	      
	@Override
	public void defineWriteSub(HttpServletRequest request) throws Exception {
		DefineSubVO vo = new DefineSubVO();
		vo.setContent(request.getParameter("subcon"));
		vo.setId(request.getParameter("id"));
		vo.setPw(request.getParameter("pw"));
		vo.setContent(request.getParameter("textValue"));
		vo.setGroupnum(Integer.parseInt(request.getParameter("groupNum"))+1);
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
		vo.setContent("to" + " " + request.getParameter("answerId") + " "+ request.getParameter("textVal"));
		vo.setConnum(request.getParameter("num"));
		vo.setSpace(request.getParameter("space"));
		vo.setId(request.getParameter("id"));
		vo.setPw(request.getParameter("pw"));
		vo.setGroupnum(Integer.parseInt(request.getParameter("groupNum")));
		dao.defineWriteSub(vo);
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

	@Override
	public String searchWord(HttpServletRequest request) throws Exception {
		HangulDivide handiv = new HangulDivide();
		String inputText = request.getParameter("inputText");
		String inputTextDiv = handiv.toKoJasoAtom(inputText);
		
		List<MainDefineContentVO> mainDefineList = dao.selectRecommendMainDefCon(inputTextDiv);
		List<String> tempList = new ArrayList<String>(); 
		List<MainDefineContentVO> distinctMainDefineList = new ArrayList<MainDefineContentVO>(); 
		
		for (int i = 0; i < mainDefineList.size(); i++) {
			if (!tempList.contains(mainDefineList.get(i).getWord())) {
				distinctMainDefineList.add(mainDefineList.get(i));
				tempList.add(mainDefineList.get(i).getWord());
			}
		}
		String show = "";
		int num = 0;
		for (int i = 0; i < distinctMainDefineList.size(); i++) {
			
			if(distinctMainDefineList.get(i).getSplitWord() != null) {
				num++;
				show += "<div id='num";
				show += num;
				show += "' class='recommendSection' onmouseover='mover("+num+")' onmouseout='mout("+num+")' style='width:310px; padding-left:8px; padding-top:5px; box-shadow:1px 1px 1px 1px gray;'>";
				show += "<div style=\" cursor: pointer;\" onclick=\"wordClick('";
				show += distinctMainDefineList.get(i).getWord();
				show += "');\">";
				show += distinctMainDefineList.get(i).getWord();
				show += "</div></div>";
			}
		}

		JSONObject jsonObject = new JSONObject();
        jsonObject.put("show", show);
        jsonObject.put("num", num);
        String jsonStr = jsonObject.toString();
		return jsonStr;
	}
	
	@Override
	public GetModifyContentVO defineContentModify(HttpServletRequest request) throws Exception {
		String conNum = request.getParameter("conNum");
		return dao.defineContentModify(conNum);
	}

}
