package member.mvc.model;

import amd.domain.Member;

public class MemberService {
	private MemberDAO dao;
	private static final MemberService instance = new MemberService();
	private MemberService() {
		dao = new MemberDAO();
	}
	public static MemberService getInstance() {
		return instance;
		
	}
	public int checkMember(String email) {
		Member m = dao.getMember(email);
		
		if(m == null) {
			return MemberSet.PASS; //중복 아이디가 없음!
		}else {
			return MemberSet.YES_ID; // 중복아이디가 있음!
		}
	}
	public int checkPwd(String email, String pwd) {
		Member m = dao.getMember(email);
		if(m != null) {
			String pwdDb = m.getM_pwd();
			if(pwdDb != null) pwdDb = pwdDb.trim();
			
			if(!pwd.equals(pwdDb)) {
				return MemberSet.NO_PWD; //PWD불일치 
			}else {
				return MemberSet.YES_ID; //PWD 일치 
			}
		}else { 
			return MemberSet.PASS; // ID가 없음
		}
	}
	public Member getMember(String email) {
		return dao.getMember(email);
	}
	public Boolean join(Member m) {
		Boolean flag = dao.join(m);
		return flag;
	}
	public Boolean edit(Member m) {
		Boolean flag = dao.edit(m);
		return flag;
	}
}
