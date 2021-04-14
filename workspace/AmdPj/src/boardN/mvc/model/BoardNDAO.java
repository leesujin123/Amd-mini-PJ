package boardN.mvc.model;

import amd.domain.BoardN;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.sql.Date;

import static boardN.mvc.model.BoardNSQL.*;

class BoardNDAO {
	private DataSource ds;
	BoardNDAO(){
		try {
			Context initContext = new InitialContext();
		    Context envContext  = (Context)initContext.lookup("java:/comp/env");
		    ds = (DataSource)envContext.lookup("jdbc/myoracle");
		}catch(NamingException ne) {
		}
	}
	
	ArrayList<BoardN> list(int cp, int ps){
		ArrayList<BoardN> list = new ArrayList<BoardN>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		String sql = LIST_PAGE;
		
		int startRow = (cp-1) * ps; //0, 3, 6
		int endRow = cp * ps;       //3, 6, 9
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				long bn_seq = rs.getLong(2);
				String m_email = rs.getString(3);
				String bn_subject = rs.getString(4);
				String bn_content = rs.getString(5);
				Date rdate = rs.getDate(6);
				int bn_count = rs.getInt(7);
				
				
				BoardN bn = new BoardN(bn_seq, m_email, bn_subject, bn_content, rdate, bn_count);
				list.add(bn);
			}
		}catch(SQLException se) {
			System.out.println("ArrayList<BoardN> list(int cp, int ps) se: " + se);
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se) {}
		}
		
		return list;
	}
	long getTotalCount() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;		
		String sql = COUNT;
		try {
			con = ds.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				long count = rs.getLong(1);
				return count;
			}else {
				return -1L;
			}
		}catch(SQLException se) {
			System.out.println("long getTotalCount() se: " + se);
			return -1L;
		}finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(con != null) con.close();
			}catch(SQLException se) {}
		}
	}
	void insert(BoardN boardN) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = INSERT;
		String m_email= "admin@gmail.com";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, m_email);
			pstmt.setString(2, boardN.getBn_subject());
			pstmt.setString(3, boardN.getBn_content());
			pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println("void insert(BoardN boardN): "+se);
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se) {}
		}
	}
	
	BoardN getBoardN(long bn_seq) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = CONTENT;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, bn_seq);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				//long seq = rs.getLong(1);
				String m_email = rs.getString(2);
				String bn_subject = rs.getString(3);
				String bn_content = rs.getString(4);
				Date rdate = rs.getDate(5);
				int bn_count = rs.getInt(6);
				
				BoardN bn = new BoardN(bn_seq, m_email,bn_subject, bn_content, rdate, bn_count);
				return bn;
			}else {
				return null;
			}
		}catch(SQLException se) {
			System.out.println("BoardDAO getBoard() se: " + se);
			return null;
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se) {}
		}
	}
	
	void update(BoardN boardN) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = UPDATE;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, boardN.getBn_subject());
			pstmt.setString(2, boardN.getBn_content());
			pstmt.setLong(3, boardN.getBn_seq());
			pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println("BoardDAO update() se: " + se);
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se) {}
		}
	}
	
	void del(long bn_seq) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = DEL;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, bn_seq);
			pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println("void del(long bn_seq) se: "+se);
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se) {}
		}
	}
	
	void updateCnt(long bq_seq){
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = UPDATECNT;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, bq_seq);
			pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println(se);
		}finally{
			try{
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se){
			}
		}
	}
		

}
