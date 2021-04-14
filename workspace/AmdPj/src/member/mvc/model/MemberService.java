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
			return MemberSet.PASS; //�ߺ� ���̵� ����!
		}else {
			return MemberSet.YES_ID; // �ߺ����̵� ����!
		}
	}
	public int checkPwd(String email, String pwd) {
		Member m = dao.getMember(email);
		if(m != null) {
			String pwdDb = m.getM_pwd();
			if(pwdDb != null) pwdDb = pwdDb.trim();
			
			if(!pwd.equals(pwdDb)) {
				return MemberSet.NO_PWD; //PWD����ġ 
			}else {
				return MemberSet.YES_ID; //PWD ��ġ 
			}
		}else { 
			return MemberSet.PASS; // ID�� ����
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
