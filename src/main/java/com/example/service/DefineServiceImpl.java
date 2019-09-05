package com.example.service;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		logger.info("");
		
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
		logger.info("");
		
		List<MainDefineContentVO> MainDefineList = dao.selectMainDefCon();
		return MainDefineList;
	}
	
	@Override
	public List<memberRankingVO> memberRanking() throws Exception {
		logger.info("");
		
		List<memberRankingVO> memberRanking = dao.memberRanking();
		return memberRanking;
	}
	
	@Override
	public List<MainDefineContentVO> linkCon(HttpServletRequest request) throws Exception {
		logger.info("");
		
		String linkWord = request.getParameter("linkWord");
		List<MainDefineContentVO> linkCon = dao.linkCon(linkWord);
		return linkCon;
	}
	      
	@Override
	public void defineWriteSub(HttpServletRequest request) throws Exception {
		logger.info("");
		
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
		logger.info("");
		
		return dao.getDefinSubList();
	}

	@Override
	public void defineSecondSub(HttpServletRequest request) throws Exception {
		logger.info("");
		
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
		logger.info("");
		
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
		logger.info("");
		
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
		logger.info("");
		
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
		logger.info("");
		
		String conNum = request.getParameter("conNum");
		dao.deleteDefineContent(conNum);
	}

	@Override
	public String searchWord(HttpServletRequest request) throws Exception {
		logger.info("");

		HangulDivide handiv = new HangulDivide();
		
		List<MainDefineContentVO> MainDefineList = dao.selectRecommendMainDefCon();
		String inputText = request.getParameter("inputText");
		String inputTextDiv = handiv.toKoJasoAtom(inputText);
		String show = "";
		inputTextDiv.charAt(inputTextDiv.length()-1);
		int num = 0;
		for (int i = 0; i < MainDefineList.size(); i++) {
			if(MainDefineList.get(i).getSplitWord() != null) {
				if(MainDefineList.get(i).getSplitWord().length() >= inputTextDiv.length()&& inputTextDiv.equals(MainDefineList.get(i).getSplitWord().substring(0,inputTextDiv.length()))) {
					num++;
					show += "<div id='num";
					show += num;
					show += "' class='recommendSection' onmouseover='mover("+num+")' onmouseout='mout("+num+")' style='width:310px; padding-left:8px; padding-top:5px; box-shadow:1px 1px 1px 1px gray;'>";
					show += "<div style=\" cursor: pointer;\" onclick=\"wordClick('";
					show += MainDefineList.get(i).getWord();
					show += "');\">";
					show += MainDefineList.get(i).getWord();
					show += "</div></div>";
				}
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
		logger.info("");
		
		String conNum = request.getParameter("conNum");
		return dao.defineContentModify(conNum);
	}

}
