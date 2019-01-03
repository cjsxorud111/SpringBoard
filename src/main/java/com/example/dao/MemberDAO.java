package com.example.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.dto.MemberVO;

public interface MemberDAO {
	
	public List<MemberVO> selectMember() throws Exception;
	public void inserting(MemberVO vo) throws Exception;
	public void updating(MemberVO vo) throws Exception;
	public void deleting(MemberVO vo) throws Exception;
	
}
