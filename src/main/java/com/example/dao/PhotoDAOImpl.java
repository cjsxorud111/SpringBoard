package com.example.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.dto.ContentsVO;
import com.example.dto.FileContentVO;
import com.example.dto.FileContentVO2;
import com.example.dto.GetContentVO;
import com.example.dto.HomeContentVO;
import com.example.dto.MemberVO;
import com.example.dto.MemberjoinVO;
import com.example.dto.NewupdatingVO;
import com.example.dto.SubVO;

@Repository
public class PhotoDAOImpl implements PhotoDAO  {

	@Inject
	private SqlSession sqlSession;
	private static final String Namespace = "com.example.mapper.photoMapper";


	@Override
	public List<FileContentVO> selectPhoto() throws Exception {
		List<FileContentVO> ccc = sqlSession.selectList(Namespace + ".selectPhoto");
		FileContentVO vvv = ccc.get(0);
		return ccc;
	}

	@Override
	public int Pnum() throws Exception {
		// TODO Auto-generated method stub
		return Integer.parseInt((String) sqlSession.selectOne(Namespace + ".Pnum"));
	}

	@Override
	public void photowriting(FileContentVO vo) throws Exception {
		System.out.println(vo.getSave_file_name());

		sqlSession.insert(Namespace + ".photowriting", vo);
		sqlSession.update(Namespace + ".plus_photonum");

	}

	@Override
	public List<FileContentVO> page_photo(String num) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace + ".page_photo", num);
	}
}
