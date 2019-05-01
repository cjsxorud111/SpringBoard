package com.example.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.dto.ContentsVO;
import com.example.dto.FileContentVO;
import com.example.dto.FileContentVO2;
import com.example.dto.GetContentVO;
import com.example.dto.HomeContentVO;
import com.example.dto.MainDefineContentVO;
import com.example.dto.MemberVO;
import com.example.dto.MemberjoinVO;
import com.example.dto.NewupdatingVO;
import com.example.dto.NewwordVO;
import com.example.dto.SubVO;

public interface DefineDAO {
	public void newword_writing(NewwordVO vo) throws Exception;

	public List<MainDefineContentVO> selectMainDefCon() throws Exception;

	public void define_sub(SubVO vo) throws Exception;
}
