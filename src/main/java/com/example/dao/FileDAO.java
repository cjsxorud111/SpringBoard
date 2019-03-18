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

public interface FileDAO {
	public List<FileContentVO2> getfile(String num) throws Exception; 
	public void filewriting(FileContentVO vo) throws Exception;
	public List<FileContentVO> selectFile() throws Exception;
	public int Fnum() throws Exception;
	
	public void fileupdating(FileContentVO vo)throws Exception;
	public void filedeleting(FileContentVO vo)throws Exception;
	
}
