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
public class HomeDAOImpl implements HomeDAO {

	@Inject
	private SqlSession sqlSession;
	private static final String Namespace = "com.example.mapper.homeMapper";

	@Override
	public List<HomeContentVO> selectContent() throws Exception {
		List<HomeContentVO> ccc = sqlSession.selectList(Namespace + ".selectContent");
		HomeContentVO vvv = ccc.get(0);
		return ccc;
	}

	@Override
	public List<GetContentVO> getcontent(String num) throws Exception {
		List<GetContentVO> aaa = sqlSession.selectList(Namespace + ".getcontent", num);
		return aaa;
	}

	@Override
	public List<SubVO> getsub(String num) throws Exception {
		List<SubVO> ccc = sqlSession.selectList(Namespace + ".getsub", num);
		return ccc;
	}


	@Override
	public void inserting(MemberVO vo) throws Exception {
		System.out.println(vo.getId());

		sqlSession.insert(Namespace + ".insertingMember", vo);
	}

	@Override
	public void writing(ContentsVO vo) throws Exception {
		System.out.println(vo.getId());
		System.out.println(vo.getTitle());
		System.out.println(vo.getContents());

		sqlSession.insert(Namespace + ".writing", vo);
		sqlSession.update(Namespace + ".plus_homenum");
	}

	@Override
	public void writesub(SubVO vo) throws Exception {
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
				
				if(vvvo[i].getUid().equals(uuid)) {
					sqlSession.insert(Namespace + ".writesub", vo);
				}
			}
			
		}else {
			sqlSession.insert(Namespace + ".writesub", vo);
		}
	}


	@Override
	public void updating(MemberVO vo) throws Exception {
		System.out.println(vo.getId());
		System.out.println(vo.getName());
		sqlSession.update(Namespace + ".updatingMember", vo);
	}

	@Override
	public void deleting(NewupdatingVO vo) throws Exception {

		sqlSession.delete(Namespace + ".deletingMember", vo);
		sqlSession.update(Namespace + ".minus_homenum");
	}
	
	
	@Override
	public void newupdating(NewupdatingVO NVO) throws Exception {
		sqlSession.update(Namespace + ".newupdating", NVO);
	}

	@Override
	public int Cnum() throws Exception {
		return Integer.parseInt((String) sqlSession.selectOne(Namespace + ".Cnum"));
	}
}
