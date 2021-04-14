package login.mvc.model;

import amd.domain.Member;

public class LoginService {
	private LoginDAO dao;
	private static final LoginService instance = new LoginService();
	private LoginService() {
		dao = new LoginDAO();
	}
	public static LoginService getInstance() {
		return instance;
	}
	
	public int checkMember(String email, String pwd) {
		Member m = dao.getMember(email);
		if(m == null) {
			return LoginSet.NO_EMAIL; //그런 이메일을 가진 회원이 없음
		}else {
			String pwdDb = m.getM_pwd();
			if(pwdDb != null) pwdDb = pwdDb.trim();
			
			if(!pwd.equals(pwdDb)) {
				return LoginSet.NO_PWD; //PWD불일치 
			}else {
				return LoginSet.PASS; //PWD 일치 
			}
		}
	}
	
	public Member getMemberS(String email) {
		Member m = dao.getMember(email);
		m.setM_pwd("");
		
		return m;
	}
}
