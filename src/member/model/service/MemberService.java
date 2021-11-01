package member.model.service;

import member.model.dao.MemberDao;
import member.model.vo.Member;

public class MemberService {

	public MemberService() {
	}

	public Member selectMember(Member m) {
		return new MemberDao().selectMember(m);
	}

	public Member checkIdDup(String userid) {
		return new MemberDao().checkIdDup(userid);
	}
	
	public int insertMember(Member m) {
		return new MemberDao().insertMember(m);
	}

	public int updateMember(Member m) {
		return new MemberDao().updateMember(m);
	}

	public int deleteMember(String userid) {
		return new MemberDao().deleteMember(userid);
	}

}
