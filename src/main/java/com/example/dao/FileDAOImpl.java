package com.example.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.dto.FileContentVO;
import com.example.dto.FileContentVO2;

@Repository
public class FileDAOImpl implements FileDAO {

	@Inject
	private SqlSession sqlSession;
	private static final String Namespace = "com.example.mapper.fileMapper";

	@Override
	public List<FileContentVO2> getfile(String num) throws Exception {
		List<FileContentVO2> aaa = sqlSession.selectList(Namespace + ".getfile", num);
		FileContentVO2 ccc = aaa.get(0);
		return aaa;
	}
	
	@Override
	public void filedeleting(FileContentVO vo) throws Exception {
		sqlSession.delete(Namespace + ".filedeleting", vo);
		sqlSession.update(Namespace + ".minus_filenum");
	}
	
	@Override
	public List<FileContentVO> selectFile() throws Exception {
		List<FileContentVO> ccc = sqlSession.selectList(Namespace + ".selectFile");
		FileContentVO vvv = ccc.get(0);
		return ccc;
	}

	@Override
	public int Fnum() throws Exception {
		return Integer.parseInt((String) sqlSession.selectOne(Namespace + ".Fnum"));
	}

	@Override
	public void filewriting(FileContentVO vo) throws Exception {

		System.out.println(vo.getSave_file_name());

		sqlSession.insert(Namespace + ".filewriting", vo);
		sqlSession.update(Namespace + ".plus_filenum");
	}

	@Override
	public void fileupdating(FileContentVO vo) throws Exception {
		sqlSession.update(Namespace + ".fileupdating", vo);
	}
}
