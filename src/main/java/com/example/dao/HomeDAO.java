package com.example.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.dto.ContentsVO;
import com.example.dto.FileContentVO;
import com.example.dto.FileContentVO2;
import com.example.dto.GetContentVO;
import com.example.dto.HomeContentVO;
import com.example.dto.MemberVO;
import com.example.dto.MemberjoinVO;
import com.example.dto.NewupdatingVO;
import com.example.dto.SubVO;

public interface HomeDAO {
	public List<HomeContentVO> selectContent() throws Exception;
	public void inserting(MemberVO vo) throws Exception;
	public void writing(ContentsVO vo) throws Exception;
	public void updating(MemberVO vo) throws Exception;
	public void deleting(NewupdatingVO vo) throws Exception;
	public List<GetContentVO> getcontent(String num) throws Exception;
	public void newupdating(NewupdatingVO NVO) throws Exception;
	public int Cnum() throws Exception;
	public void writesub(SubVO vo)throws Exception;
	public List<SubVO> getsub(String num)throws Exception;
}