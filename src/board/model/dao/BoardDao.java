package board.model.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import board.model.vo.Board;

public class BoardDao {

	public BoardDao() {
	}

	private SqlSessionFactory getSqlSessionFactory() {
		String resource = "mybatis/mybatis-config.xml";
		SqlSessionFactory factory = null;
		try {
			InputStream inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			factory = builder.build(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return factory;
	}

	public int getListCount() {
		// 총 게시글 개수를 리턴하는 메소드
		int listCount = 0;
		SqlSession session = null;
		try {
			session = getSqlSessionFactory().openSession(false);
			listCount = session.selectOne("Board.listCount");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return listCount;
	}

	public ArrayList<Board> selectListRownum(int currentPage, int limit) {
		// 페이지 단위로 게시글 목록 조회
		ArrayList<Board> list = null;
		SqlSession session = null;
		// # 1. ROWNUM을 사용하는 경우
		// 직접 시작 행과 끝나는 행을 계산하여 Map의 형태로 값을 전달한다.
		int startRow = (currentPage - 1) * limit + 1; // 시작 행
		int endRow = startRow + limit - 1; // 끝나는 행
		HashMap<String, Integer> boardPage = new HashMap<String, Integer>();
		boardPage.put("start", startRow);
		boardPage.put("end", endRow);
		try {
			session = getSqlSessionFactory().openSession(false);
			// 생성한 HaspMap을 selectList() 메소드의 인자로 전달한다
			list = new ArrayList<Board>(session.selectList("Board.selectAll", boardPage));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

	public ArrayList<Board> selectList(int currentPage, int limit) {
		// 페이지 단위로 게시글 목록 조회
		ArrayList<Board> list = null;
		// # 2. Rowbounds 를 사용하는 경우
		// 대상의 시작 행과 제한 개수를 인자로 Rowbounds 객체를 생성하여 전달한다.
		SqlSession session = null;
		int startRow = (currentPage - 1) * limit;
		try {
			session = getSqlSessionFactory().openSession(false);
			RowBounds row = new RowBounds(startRow, limit);
			// Parameter Object가 없으므로, 가운데 인자를 null로 하여 전달한다.
			list = new ArrayList<Board>(session.selectList("Board.selectAll", null, row));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
	
	public ArrayList<Board> selectListJoinMember(int currentPage, int limit) {
		// 페이지 단위로 게시글 목록 조회
		ArrayList<Board> list = null;
		// # 2. Rowbounds 를 사용하는 경우
		// 대상의 시작 행과 제한 개수를 인자로 Rowbounds 객체를 생성하여 전달한다.
		SqlSession session = null;
		int startRow = (currentPage - 1) * limit;
		try {
			session = getSqlSessionFactory().openSession(false);
			RowBounds row = new RowBounds(startRow, limit);
			// Parameter Object가 없으므로, 가운데 인자를 null로 하여 전달한다.
			list = new ArrayList<Board>(session.selectList("Board.selectAllJoinMember", null, row));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

}
