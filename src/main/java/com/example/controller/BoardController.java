package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardController {
/* 
    @Autowired
    private MemberService boardServiceImpl;
    
    *//**
     * �Խ��� ��ȸ
     * @param boardVO
     * @param model
     * @return
     * @throws Exception
     *//*
    @RequestMapping(value="/board/boardList.do")
    @NoLoginCheck
    public String boardList(@ModelAttribute("boardVO") BoardVO boardVO,
            @RequestParam(defaultValue="1") int curPage,
            HttpServletRequest request,
            Model model) throws Exception{
        
        HttpSession session = request.getSession();
        LoginVO loginVO = (LoginVO)session.getAttribute("loginVO");
 
        // ��ü����Ʈ ����
        int listCnt = boardServiceImpl.selectBoardListCnt(boardVO);
        
        Pagination pagination = new Pagination(listCnt, curPage);
        
        boardVO.setStartIndex(pagination.getStartIndex());
        boardVO.setCntPerPage(pagination.getPageSize());
        // ��ü����Ʈ ���
        List<BoardVO> list = boardServiceImpl.selectBoardList(boardVO);
                
        model.addAttribute("list", list);
        model.addAttribute("listCnt", listCnt);
        model.addAttribute("loginVO", loginVO);
        
        model.addAttribute("pagination", pagination);
        
        return "board/boardList";
    }
    
//...����...//
*/}
