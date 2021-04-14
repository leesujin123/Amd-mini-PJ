package boardQ.mvc.model;

import java.sql.*;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import amd.domain.BoardQ;
import static boardQ.mvc.model.BoardQSQL.*;

class BoardQDAO {
	
	private DataSource ds;
	private Connection con;
	private PreparedStatement pstmt;
	private Statement stmt;
	
	BoardQDAO(){	
		try {
			Context initContext = new InitialContext();
		    Context envContext  = (Context)initContext.lookup("java:/comp/env");
		    ds = (DataSource)envContext.lookup("jdbc/myoracle");
		}catch(NamingException ne) {
		}
	}
	
	ArrayList<BoardQ> list(int page, int pageSize){
		ArrayList<BoardQ> list = new ArrayList<BoardQ>();
		String sql = LIST;
		ResultSet rs = null;
		int initRow = (page-1)*pageSize;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, initRow);
			pstmt.setInt(2, initRow + pageSize);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int bq_seq = rs.getInt("BQ_SEQ");
				String m_email = rs.getString("M_EMAIL");
				String bq_subject = rs.getString("BQ_SUBJECT");
				String bq_content = rs.getString("BQ_CONTENT");
				Date bq_rdate = rs.getDate("BQ_RDATE");
				int bq_count = rs.getInt("BQ_COUNT");
				int bq_refer = rs.getInt("BQ_REFER");
				int bq_lev = rs.getInt("BQ_LEV");
				int bq_place = rs.getInt("BQ_PLACE");
				BoardQ boardq = new BoardQ(bq_seq, m_email, bq_subject, bq_content, bq_rdate, bq_count,
						bq_refer, bq_lev, bq_place);
				boardq.setM_name(getName(bq_seq));	//참조부분
				list.add(boardq);
			}
			return list;
		}catch(SQLException se) {
			System.out.println(se);
			return null;
		}finally {
			try{
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se){
			}
		}
	}
	String getName(int bq_seq){
		Connection con2 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs2 = null;
		String sql2 = BQ_NAME;
		String m_name = null;
		try {
			con2 = ds.getConnection();
			pstmt2 = con2.prepareStatement(sql2);
			pstmt2.setInt(1, bq_seq);
			rs2 = pstmt2.executeQuery();
			if(rs2.next()) m_name = rs2.getString(1);
			return m_name;
		}catch(SQLException se) {
			System.out.println(se);
			return null;
		}finally {
			try{
				if(rs2 != null) rs2.close();
				if(pstmt2 != null) pstmt2.close();
				if(con2 != null) con2.close();
			}catch(SQLException se){
			}
		}
		
	}
	long countRow() {
		String sql = COUNT;
		ResultSet rs = null;
		long totalCount = 0L;
		try {
			con = ds.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) totalCount = rs.getLong(1);
			return totalCount;
		}catch(SQLException se) {
			
			System.out.println(se);
			return -1L;
		}finally {
			try{
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(con != null) con.close();
			}catch(SQLException se){
			}
		}
	}
	//insert into BOARDQ values(BOARD_SEQ.nextval,?,?,?,SYSDATE,?,?,?,?)
	void insert(BoardQ boardQ) {
		String sql = INSERT; 
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, boardQ.getM_email());
			pstmt.setString(2, boardQ.getBq_subject());
			pstmt.setString(3, boardQ.getBq_content());
			pstmt.setInt(4, boardQ.getBq_count());
			pstmt.setInt(5, boardQ.getBq_refer());
			pstmt.setInt(6, boardQ.getBq_lev());
			pstmt.setInt(7, boardQ.getBq_place());
			pstmt.executeUpdate();
		}catch(SQLException se) {
			
			System.out.println(se);
		}finally {
			try{
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se){
			}
		}
	}
	
	int getMaxRef() {
		String sql = MAX_REF;
		ResultSet rs = null;
		int maxRef = 0;
		try {
			con = ds.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) maxRef = rs.getInt(1);
			return maxRef;
		}catch(SQLException se) {
			System.out.println(se);
			return -1;
		}finally {
			try{
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(con != null) con.close();
			}catch(SQLException se){
			}
		}
	}
	void updateCnt(int bq_seq){
		String sql = UPDATECNT;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bq_seq);
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
	BoardQ getContent(int bq_seq){
		String sql = CONTENT;
		ResultSet rs = null;
		BoardQ boardq= null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bq_seq);
			rs = pstmt.executeQuery();
			if(rs.next()){
				String m_email = rs.getString("M_EMAIL");
				String bq_subject = rs.getString("BQ_SUBJECT");
				String bq_content = rs.getString("BQ_CONTENT");
				Date bq_rdate = rs.getDate("BQ_RDATE");
				int bq_count = rs.getInt("BQ_COUNT");
				int bq_refer = rs.getInt("BQ_REFER");
				int bq_lev = rs.getInt("BQ_LEV");
				int bq_place = rs.getInt("BQ_PLACE");
				boardq = new BoardQ(bq_seq, m_email, bq_subject, bq_content, bq_rdate, bq_count,
						bq_refer, bq_lev, bq_place);
				boardq.setM_name(getName(bq_seq));
			}
			return boardq;
		}catch(SQLException se) {
			System.out.println(se);
			return null;
		}finally{
			try{
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se){
			}
		}
	}

	void delete(int bq_seq){
		String sql = DELETE;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bq_seq);
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
	
	void update(BoardQ boardQ){
		String sql = UPDATE;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, boardQ.getBq_subject());
			pstmt.setString(2, boardQ.getBq_content());
			pstmt.setInt(3, boardQ.getBq_seq());
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
	////답글작업
	int getNewPLACE(int bq_lev, int place) {
		String sql = MAX_PLACE;
		ResultSet rs = null;
		int newPlace = place + 1;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bq_lev);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				newPlace = 1 + rs.getInt(1);
			}
			return newPlace;
		}catch(SQLException se) {
			System.out.println(se);
			return -1;
		}finally{
			try{
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se){
			}
		}
	}
	void updatePlace(int refer, int place) {
		String sql = UPDATE_PLACE;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, refer);
			pstmt.setInt(2, place);
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
