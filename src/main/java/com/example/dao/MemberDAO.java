package com.example.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.dto.ContentsVO;
import com.example.dto.GetContentVO;
import com.example.dto.HomeContentVO;
import com.example.dto.MemberVO;
import com.example.dto.MemberjoinVO;

public interface MemberDAO {
	
	
	public List<HomeContentVO> selectContent() throws Exception;
	public void inserting(MemberVO vo) throws Exception;
	public void memberjoining(MemberjoinVO vo) throws Exception;
	public void updating(MemberVO vo) throws Exception;
	public void deleting(MemberVO vo) throws Exception;
	public String selectpw(String a) throws Exception;
	public void writing(ContentsVO vo) throws Exception;
	public List<GetContentVO> getcontent(String num) throws Exception;
	
}
