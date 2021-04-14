package amd.domain;

public class Member {
	private String m_email;
	private String m_pwd;
	private String m_name;
	private String m_phone;
	private String m_addr;
	private String m_addr2;
	private String m_admin;

	public Member() {}

	public Member(String m_email, String m_pwd, String m_name, String m_phone, String m_addr, String m_addr2,
			String m_admin) {
		super();
		this.m_email = m_email;
		this.m_pwd = m_pwd;
		this.m_name = m_name;
		this.m_phone = m_phone;
		this.m_addr = m_addr;
		this.m_addr2 = m_addr2;
		this.m_admin = m_admin;
	}

	public String getM_email() {
		return m_email;
	}

	public void setM_email(String m_email) {
		this.m_email = m_email;
	}

	public String getM_pwd() {
		return m_pwd;
	}

	public void setM_pwd(String m_pwd) {
		this.m_pwd = m_pwd;
	}

	public String getM_name() {
		return m_name;
	}

	public void setM_name(String m_name) {
		this.m_name = m_name;
	}

	public String getM_phone() {
		return m_phone;
	}

	public void setM_phone(String m_phone) {
		this.m_phone = m_phone;
	}

	public String getM_addr() {
		return m_addr;
	}

	public void setM_addr(String m_addr) {
		this.m_addr = m_addr;
	}

	public String getM_addr2() {
		return m_addr2;
	}

	public void setM_addr2(String m_addr2) {
		this.m_addr2 = m_addr2;
	}

	public String getM_admin() {
		return m_admin;
	}

	public void setM_admin(String m_admin) {
		this.m_admin = m_admin;
	}
}



