package com.example.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.example.dto.MemberjoinVO;

@Repository
public class MemberDAOImpl implements MemberDAO {

	@Inject
	private SqlSession sqlSession;
	private static final String Namespace = "com.example.mapper.memberMapper";
	final static Logger logger = LoggerFactory.getLogger(MemberDAOImpl.class);
	
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
