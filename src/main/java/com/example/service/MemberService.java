package com.example.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.dto.MemberVO;

public interface MemberService {
	
	public List<MemberVO> selectMember() throws Exception;
	public void inserting(HttpServletRequest request) throws Exception;
	public void updating(HttpServletRequest request) throws Exception;
	public void deleting(HttpServletRequest request) throws Exception;

}
