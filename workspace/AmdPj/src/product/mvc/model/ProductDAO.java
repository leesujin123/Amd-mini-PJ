package product.mvc.model;

import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import amd.domain.Product;

import java.sql.*;

import static product.mvc.model.ProductSQL.*;

class ProductDAO {
	private DataSource ds;
	ProductDAO(){
		try {
			Context initContext = new InitialContext();
		    Context envContext  = (Context)initContext.lookup("java:/comp/env");
		    ds = (DataSource)envContext.lookup("jdbc/myoracle");
		}catch(NamingException ne) {
		}
	}
	ArrayList<Product> list(int cp, int ps){
		ArrayList<Product> list = new ArrayList<Product>();
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
				int p_code = rs.getInt(2);
				String p_type= rs.getString(3);
				String p_name= rs.getString(4);
				int p_price= rs.getInt(5);
				String p_img =rs.getString(6);
				String p_info= rs.getString(7);
				
				Product p = new Product(p_code, p_type, p_name, p_price, p_img, p_info);
				list.add(p);
			}
		}catch(SQLException se) {
			System.out.println("ProductDAO list(cp, ps) se: " + se);
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se) {}
		}
		
		return list;
	}
	
	ArrayList<Product> list(int cp, int ps, String p_type){
		ArrayList<Product> list = new ArrayList<Product>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		String sql = LISTC_PAGE;
		int startRow = (cp-1) * ps; //0, 3, 6
		int endRow = cp * ps;       //3, 6, 9
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,p_type);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int p_code = rs.getInt(2);
				//p_type= rs.getString(3);
				String p_name= rs.getString(4);
				int p_price= rs.getInt(5);
				String p_img =rs.getString(6);
				String p_info= rs.getString(7);
				
				Product p = new Product(p_code, p_type, p_name, p_price, p_img, p_info);
				list.add(p);
			}
		}catch(SQLException se) {
			System.out.println("ProductDAO list(cp, ps) se: " + se);
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
			System.out.println("getTotalCount() se: " + se);
			return -1L;
		}finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(con != null) con.close();
			}catch(SQLException se) {}
		}
	}
	long getTotalCount(String p_type) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		String sql = COUNTC;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,p_type);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				long count = rs.getLong(1);
				return count;
			}else {
				return -1L;
			}
		}catch(SQLException se) {
			System.out.println("getTotalCount() se: " + se);
			return -1L;
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se) {}
		}
	}
	Product getProduct(int p_code) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = CONTENT;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, p_code);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				//int p_code = rs.getInt(1);
				String p_type= rs.getString(2);
				String p_name= rs.getString(3);
				int p_price= rs.getInt(4);
				String p_img =rs.getString(5);
				String p_info= rs.getString(6);
				
				Product p = new Product(p_code, p_type, p_name, p_price, p_img, p_info);
				return p;
			}else {
				return null;
			}
		}catch(SQLException se) {
			System.out.println("Product getProduct(int p_code) se: " + se);
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
