package com.example.dao;

import com.example.dto.MemberjoinVO;

public interface MemberDAO {
	
	public void memberjoining(MemberjoinVO vo);
	public String selectpw(String a);
}
