package order.mvc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



import amd.domain.Cart;
import amd.domain.Member;
import amd.domain.Ord;
import amd.domain.Product;
import static order.mvc.model.OrderSQL.*;

class OrderDAO {
	private DataSource ds;
	private Connection con;
	private PreparedStatement pstmt;
	private Statement stmt;
	
	OrderDAO(){	
		try {
			Context initContext = new InitialContext();
		    Context envContext  = (Context)initContext.lookup("java:/comp/env");
		    ds = (DataSource)envContext.lookup("jdbc/myoracle");
		}catch(NamingException ne) {
		}
	}
	
	//조인한 테이블 컬럼 불러오기 showCartInfo
	Product showProductInCart(String m_email){
		String sql2 = PRODUCT_IN_CART;
		ResultSet rs2 = null;
		Product product = null;
		Connection con2 = null;
		PreparedStatement pstmt2 = null;
		try {
			con2 = ds.getConnection();
			pstmt2 = con2.prepareStatement(sql2);
			pstmt2.setString(1, m_email);
			rs2 = pstmt2.executeQuery();
			if(rs2.next()) {
				int p_code = rs2.getInt("P_CODE");
				String p_type = rs2.getString("P_TYPE");
				String p_name = rs2.getString("P_NAME");
				int p_price = rs2.getInt("P_PRICE");
				String p_img = rs2.getString("P_IMG");
				String p_info = rs2.getString("P_INFO");
				product = new Product(p_code, p_type, p_name, p_price, p_img, p_info);
			}
			return product;
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
	
	ArrayList<Cart> showCartInfo(String m_email){
		String sql = INFO_CART;
		ArrayList<Cart> listC = new ArrayList<Cart>();
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, m_email);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int c_seq = rs.getInt("C_SEQ");
				int c_amount = rs.getInt("C_AMOUNT");
				int p_code = rs.getInt("P_CODE");
				String c_valid = rs.getString("C_VALID");
				//참조
				Product product = showProductInCart(m_email);
				String p_name = product.getP_name();
				int p_price = product.getP_price();
				String p_img = product.getP_img();
				
				Cart cart = new Cart(c_seq, m_email, p_code, p_name, p_price, p_img, c_amount, c_valid);
				listC.add(cart);
			}
			return listC;
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
	
	Member showOrderer(String m_email) {
		String sql = INFO_MEMBER;
		ResultSet rs = null;
		Member member= null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, m_email);
			rs = pstmt.executeQuery();
			if(rs.next()){
				String m_name  = rs.getString("M_NAME");
				String m_phone = rs.getString("M_PHONE");
				String m_addr = rs.getString("M_ADDR");
				member = new Member(m_email, null, m_name, m_phone, m_addr, null, "n");
			}
			return member;
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
	
	void insertOrd(Ord order) {
		String sql = INSERT_ORD; 
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, order.getC_seq());
			pstmt.setString(2, order.getO_oName());
			pstmt.setString(3, order.getO_oAddr());
			pstmt.setString(4, order.getO_msg());
			pstmt.setString(5, order.getO_oValid());
			pstmt.setInt(6, order.getO_total());
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
	
	void updateCartValid(String m_email) {
		String sql = UPDATE_CART;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, m_email);
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
	
	ArrayList<Cart> showOrderInfo(String m_email){
		String sql = SELECT_CART_NO;
		ArrayList<Cart> listC = new ArrayList<Cart>();
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, m_email);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int c_seq = rs.getInt("C_SEQ");
				int c_amount = rs.getInt("C_AMOUNT");
				String p_name = rs.getString("P_NAME");
				int p_price = rs.getInt("P_PRICE");
				String p_img = rs.getString("P_IMG");
				
				Cart cart = new Cart(c_seq, m_email, -1, p_name, p_price, p_img, c_amount, null);
				listC.add(cart);
			}
			return listC;
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
	
}
