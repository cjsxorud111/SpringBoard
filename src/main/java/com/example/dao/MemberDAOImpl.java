package com.example.dao;

import com.example.dto.MemberjoinVO;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;

@Repository
public class MemberDAOImpl implements MemberDAO {

	@Inject
	private SqlSession sqlSession;
	private static final String Namespace = "com.example.mapper.memberMapper";
	final static Logger logger = LoggerFactory.getLogger(MemberDAOImpl.class);

	@Override
	public String idValidCheck(String inputId) {
		String test = null;
		try {
			test = sqlSession.selectOne(Namespace + ".idValidCheck", inputId);
		} catch (Exception e){
			logger.error("error: {}", e);
		}
		return test;
	}

	@Override
	public void memberjoining(MemberjoinVO vo) {
		logger.info("");
		
		try {
			sqlSession.insert(Namespace + ".memberjoining", vo);
		} catch (Exception e) {
			logger.error("sqlError", e);
		}
	}
	
	@Override
	public String selectpw(String a) {
		logger.info("");
		
		String session = null;
		
		try {
			session = sqlSession.selectOne(Namespace + ".selectpw", a);
		} catch (Exception e) {
			logger.error("sqlError", e);
		}
		
		return session;
	}

}
