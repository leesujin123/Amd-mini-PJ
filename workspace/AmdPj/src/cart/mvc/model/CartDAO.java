package cart.mvc.model;

import amd.domain.Cart;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

import static cart.mvc.model.CartSQL.*;

class CartDAO {
	private DataSource ds;
	CartDAO(){
		try {
			Context initContext = new InitialContext();
		    Context envContext  = (Context)initContext.lookup("java:/comp/env");
		    ds = (DataSource)envContext.lookup("jdbc/myoracle");
		}catch(NamingException ne) {
		}
	}
	
	ArrayList<Cart> list(String m_email){
		ArrayList<Cart> list = new ArrayList<Cart>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		String sql = LIST;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, m_email);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int c_seq = rs.getInt(1);
				//m_email = rs.getString(2);
				int p_code = rs.getInt(3);
				String p_name = rs.getString(4);
				int p_price = rs.getInt(5);
				String p_img = rs.getString(6);
				int c_amount = rs.getInt(7);
				String c_valid = rs.getString(8);
				
				Cart c = new Cart(c_seq, m_email, p_code, p_name, p_price, p_img, c_amount, c_valid);
				list.add(c);
			}
		}catch(SQLException se) {
			System.out.println("ArrayList<Cart> list(String m_email) se: " + se);
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se) {}
		}
		
		return list;
	}
	
	void insert(Cart cart) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = INSERT;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cart.getM_email());
			pstmt.setInt(2, cart.getP_code());
			pstmt.setInt(3, cart.getC_amount());
			pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println("insert(Cart cart) se= "+se);
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se) {}
		}
	}
	
	void del(int c_seq) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = DEL;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, c_seq);
			pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println("void del(int c_seq) se: "+se);
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se) {}
		}
	}
	
	void update(Cart cart) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = UPDATE;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cart.getC_seq());
			pstmt.setInt(2, cart.getC_amount());
			pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println("void update(Cart cart) se: " + se);
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se) {}
		}
	}
	
	

}
