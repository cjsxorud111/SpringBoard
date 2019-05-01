package com.example.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.dto.FileContentVO;
import com.example.dto.FileContentVO2;
import com.example.dto.MainDefineContentVO;
import com.example.dto.NewwordVO;
import com.example.dto.SubVO;

@Repository
public class DefineDAOImpl implements DefineDAO {

	@Inject
	private SqlSession sqlSession;
	private static final String Namespace = "com.example.mapper.defineMapper";

	@Override
	public void newword_writing(NewwordVO vo) throws Exception {
		System.out.println("2222");
		sqlSession.insert(Namespace + ".newword_writing", vo);
		System.out.println("3333----");
	}

	@Override
	public List<MainDefineContentVO> selectMainDefCon() throws Exception {
		List<MainDefineContentVO> MainDefineList = sqlSession.selectList(Namespace + ".mainDefineList");
		return MainDefineList;
	}

	@Override
	public void define_sub(SubVO vo) throws Exception {
		if (vo.getUuid() != null) {
			String uuid = vo.getUuid();
			String num = vo.getConnum();
			List<SubVO> soso = sqlSession.selectList(Namespace + ".getsub3", vo);
			List<SubVO> test = new ArrayList<SubVO>();
			SubVO[] vvvo = new SubVO[soso.size()];
			for (int i = 0; i < soso.size(); i++) {
				vvvo[i] = soso.get(i);

			}
			sqlSession.delete(Namespace + ".deletsub", vo);
			for (int i = 0; i < soso.size(); i++) {
				sqlSession.insert(Namespace + ".writesub", vvvo[i]);

				if (vvvo[i].getUid().equals(uuid)) {
					sqlSession.insert(Namespace + ".writesub", vo);
				}
			}

		} else {
			sqlSession.insert(Namespace + ".writesub", vo);
		}
	}

}
