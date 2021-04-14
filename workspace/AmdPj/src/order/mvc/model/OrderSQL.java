package order.mvc.model;



class OrderSQL {
	final static String PRODUCT_IN_CART = "select p.* from PRODUCT p"
			+ " join CART c on p.P_CODE = any(select P_CODE from CART where C_SEQ = "
			+ "any(select C_SEQ from CART c join Member m on c.M_EMAIL = ? and c.C_VALID='Y'))";
	final static String INFO_CART = "select * from CART where M_EMAIL=? and C_VALID='Y'";
	final static String INFO_MEMBER = "select * from Member where M_EMAIL = ?";
	final static String INSERT_ORD = "insert into ORD "
			+ "values(ORD_SEQ.nextval, ?, SYSDATE, ?, ?, ?, ?, ?)";
	final static String UPDATE_CART = "update CART set C_VALID ='N' where C_SEQ = "
			+ "any(select C_SEQ from CART c join Member m on c.M_EMAIL = ? and c.C_VALID = 'Y')";
	final static String SELECT_CART_NO = "select p.P_NAME, p.P_PRICE, p.P_IMG, c.C_SEQ, c.C_AMOUNT from PRODUCT p join CART c on p.P_CODE = any(select P_CODE from CART where C_SEQ = any(select C_SEQ from CART c join Member m on c.M_EMAIL = ? and c.C_VALID='N'))";
}
