package login.mvc.model;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

import amd.domain.Member;

class LoginDAO {
	private DataSource ds;
	LoginDAO(){
		try {
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/myoracle");
		}catch(NamingException ne) {
			System.out.println("Apache DBCP °´Ã¼(jdbc/myoracle)¸¦ Ã£Áö ¸øÇÔ");
		}
	}
	Member getMember(String email){
		Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String sql = LoginSQL.CONTENT;
	    try {
	    	con = ds.getConnection();
	    	pstmt = con.prepareStatement(sql);
	    	pstmt.setString(1, email);
	    	rs	= pstmt.executeQuery();
	    	if(rs.next()) {
	    		String m_email = rs.getString("M_EMAIL");
	    		String m_pwd = rs.getString("M_PWD");
	    		String m_name = rs.getString("M_NAME");
	    		String m_phone = rs.getString("M_PHONE");
	    		String m_addr = rs.getString("M_ADDR");
	    		String m_addr2 = rs.getString("M_ADDR2");
	    		String m_admin = rs.getString("M_ADMIN");

	    		return new Member(m_email, m_pwd, m_name, m_phone, m_addr, m_addr2, m_admin);
	    	}else {
	    		return null;
	    	}
	    }catch(SQLException se) {
	    	System.out.println(se);
	    	return null;
	    }finally {
	    	try {
	    		if(rs != null) rs.close();
	    		if(pstmt != null) pstmt.close();
	    		if(con != null) con.close();
	    	}catch(SQLException se) {}
	    }
	}
}