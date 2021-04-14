package member.mvc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import amd.domain.Member;

public class MemberDAO {

	private DataSource ds;
	MemberDAO(){
		try {
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/myoracle");
		}catch(NamingException ne) {
			System.out.println("");
		}
	}
	
	Boolean join(Member m){
		Connection con = null;
	    PreparedStatement pstmt = null;
	    String sql = MemberSQL.JOIN;
	    try {
	    	con = ds.getConnection();
	    	pstmt = con.prepareStatement(sql);
	    	pstmt.setString(1, m.getM_email());
	    	pstmt.setString(2, m.getM_pwd());
	    	pstmt.setString(3, m.getM_name());
	    	pstmt.setString(4, m.getM_phone());
	    	pstmt.setString(5, m.getM_addr());
	    	pstmt.setString(6, m.getM_addr2());
	    	int i = pstmt.executeUpdate();
	    	if(i>0) return true;
			else return false;
	    }catch(SQLException se) {
	    	System.out.println(se);
	    	return false;
	    }finally {
	    	try {
	    		if(pstmt != null) pstmt.close();
	    		if(con != null) con.close();
	    	}catch(SQLException se) {}
	    }
	}
	    Member getMember(String email){
			Connection con = null;
		    PreparedStatement pstmt = null;
		    ResultSet rs = null;
		    String sql = MemberSQL.CHECK;
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
	    Boolean edit(Member m){
			Connection con = null;
		    PreparedStatement pstmt = null;
		    String sql = MemberSQL.EDIT;
		    try {
		    	con = ds.getConnection();
		    	pstmt = con.prepareStatement(sql);
		    	pstmt.setString(1, m.getM_name());
		    	pstmt.setString(2, m.getM_pwd());
		    	pstmt.setString(3, m.getM_phone());
		    	pstmt.setString(4, m.getM_addr());
		    	pstmt.setString(5, m.getM_addr2());
		    	pstmt.setString(6, m.getM_email());
		    	System.out.println(1);
		    	int i = pstmt.executeUpdate();
		    	if(i>0) return true;
				else return false;
		    }catch(SQLException se) {
		    	System.out.println(se);
		    	return false;
		    }finally {
		    	try {
		    		if(pstmt != null) pstmt.close();
		    		if(con != null) con.close();
		    	}catch(SQLException se) {}
		    }
		}
	}