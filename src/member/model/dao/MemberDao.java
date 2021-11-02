package member.model.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import member.model.vo.Member;

public class MemberDao {

	public MemberDao() {
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

	public Member selectMember(Member m) {
		Member member = null;
		SqlSession session = null;
		try {
			session = getSqlSessionFactory().openSession(false); // DB 연결 코드

			member = session.selectOne("Member.loginCheck", m); // JSP -> Servlet -> Service를 거쳐서 실려온 것 중 하나를 member에
																// 실어줌.
			System.out.println(session);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return member;
	}

	public List<Member> selectMembers(int offset, int pageSize) {
		List<Member> members = null;
		SqlSession session = null;
		try {
			session = getSqlSessionFactory().openSession(false); // DB 연결 코드
			RowBounds rbounds = new RowBounds(offset, pageSize);
			members = session.selectList("Member.listMember", null, rbounds); // JSP -> Servlet -> Service를 거쳐서 실려온 것 중 하나를 member에 실어줌.
			System.out.println(session);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return members;
	}

	
	public Member checkIdDup(String userid) {
		Member member = null;
		SqlSession session = null;
		try {
			session = getSqlSessionFactory().openSession(false); // DB 연결 코드

			member = session.selectOne("Member.memberInfo", userid); // JSP -> Servlet -> Service를 거쳐서 실려온 것 중 하나를
																		// member에 실어줌.
			System.out.println(session);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return member;
	}

	public int insertMember(Member m) {
		int result = 0;
		SqlSession session = null;
		try {
			session = getSqlSessionFactory().openSession(false); // DB 연결 코드

			result = session.insert("Member.insertMember", m); // JSP -> Servlet -> Service를 거쳐서 실려온 것 중 하나를 member에
																	// 실어줌.
			System.out.println(session);
			if (result > 0) {
				session.commit();
			} else {
				session.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

	public int updateMember(Member m) {
		int result = 0;
		SqlSession session = null;
		try {
			session = getSqlSessionFactory().openSession(false); // DB 연결 코드

			result = session.update("Member.updateMember", m); // JSP -> Servlet -> Service를 거쳐서 실려온 것 중 하나를 member에
																	// 실어줌.
			System.out.println(session);
			if (result > 0) {
				session.commit();
			} else {
				session.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

	public int deleteMember(String userid) {
		int result = 0;
		SqlSession session = null;
		try {
			session = getSqlSessionFactory().openSession(false); // DB 연결 코드

			result = session.delete("Member.deleteMember", userid); // JSP -> Servlet -> Service를 거쳐서 실려온 것 중 하나를
																		// member에 실어줌.
			System.out.println(session);
			if (result > 0) {
				session.commit();
			} else {
				session.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}
}
