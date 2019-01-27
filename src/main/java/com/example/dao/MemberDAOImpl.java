package com.example.dao;

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
public class MemberDAOImpl implements MemberDAO {

	@Inject
	private SqlSession sqlSession;
	private static final String Namespace = "com.example.mapper.memberMapper";

	@Override
	public List<HomeContentVO> selectContent() throws Exception {
System.out.println("MemberDAOImp????????l");
		List<HomeContentVO> ccc = sqlSession.selectList(Namespace + ".selectContent");
		HomeContentVO vvv = ccc.get(0);
		System.out.println(vvv.getId() + " ??  " + vvv.getTitle() + " ?? " + vvv.getNum());

		return ccc;
	}

	@Override
	public List<FileContentVO> selectFile() throws Exception {
		List<FileContentVO> ccc = sqlSession.selectList(Namespace + ".selectFile");
		FileContentVO vvv = ccc.get(0);
		System.out.println(vvv.getId() + " ??  " + vvv.getTitle() + " ?? ");

		return ccc;
	}
	
	@Override
	public List<FileContentVO> selectPhoto() throws Exception {
		List<FileContentVO> ccc = sqlSession.selectList(Namespace + ".selectPhoto");
		FileContentVO vvv = ccc.get(0);
		System.out.println(vvv.getId() + " ??  " + vvv.getTitle() + " ?? ");

		return ccc;
	}

	@Override
	public List<GetContentVO> getcontent(String num) throws Exception {
		List<GetContentVO> aaa = sqlSession.selectList(Namespace + ".getcontent", num);
		GetContentVO ccc = aaa.get(0);
		System.out.println("????  " + ccc.getContent());
		return aaa;
	}
	
	@Override
	public List<SubVO> getsub(String num) throws Exception {
		
		System.out.println("Memb????????????erDAOImpl");
		List<SubVO> ccc = sqlSession.selectList(Namespace + ".getsub", num);
		System.out.println("Memb????????????erDAOImpl");
		SubVO vvv = ccc.get(0);
		System.out.println("Memb????????????erDAOImpl");
		System.out.println(vvv.getNum());

		System.out.println("Memb????????????erDAOImpl");
		return ccc;
	}
	
	@Override
	public List<FileContentVO2> getfile(String num) throws Exception {
		List<FileContentVO2> aaa = sqlSession.selectList(Namespace + ".getfile", num);
		FileContentVO2 ccc = aaa.get(0);
		System.out.println("????  " + ccc.getContent());
		System.out.println("????  " + ccc.getSave_file_name());
		return aaa;
	}

	@Override
	public void inserting(MemberVO vo) throws Exception {
		System.out.println("Memberinserting");
		System.out.println(vo.getId());
		System.out.println("Memberinserting1");

		sqlSession.insert(Namespace + ".insertingMember", vo);
		System.out.println("Memberinserting2");
	}

	@Override
	public void writing(ContentsVO vo) throws Exception {
		System.out.println("Memberinserting");
		System.out.println(vo.getId());
		System.out.println(vo.getTitle());
		System.out.println(vo.getContents());

		sqlSession.insert(Namespace + ".writing", vo);
		sqlSession.update(Namespace + ".plus_homenum");
		System.out.println("Memberinserting2");

	}
	
	@Override
	public void writesub(SubVO vo) throws Exception {
		sqlSession.insert(Namespace + ".writesub", vo);
		String cccc = vo.getSubnum();
		/*if(cccc == null) {
			List<SubVO> ccc = sqlSession.selectList(Namespace + ".getsub2");
			SubVO vvv = ccc.get(0);
			sqlSession.update(Namespace + ".writesub2", vvv);
		}*/
	}

	@Override
	public void memberjoining(MemberjoinVO vo) throws Exception {
		System.out.println("??");
		System.out.println(vo.getId());
		System.out.println(vo.getPw());
		System.out.println(vo.getName());
		System.out.println(vo.getEmail());
		sqlSession.insert(Namespace + ".memberjoining", vo);
		System.out.println("???");

	}

	@Override
	public void updating(MemberVO vo) throws Exception {
		System.out.println("Memberupdating");
		System.out.println(vo.getId());
		System.out.println("Memberupdating1");
		System.out.println(vo.getName());
		sqlSession.update(Namespace + ".updatingMember", vo);
		System.out.println("Membeting2");
	}

	@Override
	public void deleting(NewupdatingVO vo) throws Exception {

		sqlSession.delete(Namespace + ".deletingMember", vo);
		sqlSession.update(Namespace + ".minus_homenum");
		System.out.println("Membeting2");
	}

	@Override
	public String selectpw(String a) throws Exception {
		System.out.println("M?2");
		return sqlSession.selectOne(Namespace + ".selectpw", a);

	}

	@Override
	public void newupdating(NewupdatingVO NVO) throws Exception {
		sqlSession.update(Namespace + ".newupdating", NVO);

	}

	@Override
	public int Cnum() throws Exception {
		return Integer.parseInt((String) sqlSession.selectOne(Namespace + ".Cnum"));
	}

	@Override
	public int Fnum() throws Exception {
		return Integer.parseInt((String) sqlSession.selectOne(Namespace + ".Fnum"));
	}

	@Override
	public int Pnum() throws Exception {
		// TODO Auto-generated method stub
		return Integer.parseInt((String) sqlSession.selectOne(Namespace + ".Pnum"));
	}

	@Override
	public void filewriting(FileContentVO vo) throws Exception {

		System.out.println(vo.getSave_file_name());

		sqlSession.insert(Namespace + ".filewriting", vo);
		sqlSession.update(Namespace + ".plus_filenum");
	}

	@Override
	public void photowriting(FileContentVO vo) throws Exception {
		System.out.println(vo.getSave_file_name());

		sqlSession.insert(Namespace + ".photowriting", vo);
		sqlSession.update(Namespace + ".plus_photonum");

	}

	
}
