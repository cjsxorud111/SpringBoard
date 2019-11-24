package com.example.dao;

import com.example.dto.MemberjoinVO;

public interface MemberDAO {

	public String idValidCheck(String inputId);

	public void memberjoining(MemberjoinVO vo);

	public String selectpw(String a);

}
