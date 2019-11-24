package com.example.service;

import javax.servlet.http.HttpServletRequest;

public interface MemberService {

	public String idValidCheck(String inputId) throws Exception;

	public void memberjoining(HttpServletRequest request) throws Exception;

	public String logining(HttpServletRequest request) throws Exception;

}
