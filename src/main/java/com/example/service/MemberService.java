package com.example.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.dto.FileContentVO;
import com.example.dto.FileContentVO2;
import com.example.dto.GetContentVO;
import com.example.dto.HomeContentVO;
import com.example.dto.MemberVO;
import com.example.dto.SubVO;

public interface MemberService {
	
	public void memberjoining(HttpServletRequest request) throws Exception;
	public String logining(HttpServletRequest request) throws Exception;

}
