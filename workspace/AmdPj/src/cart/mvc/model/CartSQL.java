package cart.mvc.model;

public class CartSQL {
	final static String LIST = "select c.C_SEQ, c.M_EMAIL, c.P_CODE, p.P_NAME, p.P_PRICE, p.P_IMG, c.C_AMOUNT, c.C_VALID from CART c, PRODUCT p where c.P_CODE = p.P_CODE and c.M_EMAIL=? order by c.P_CODE"; 
	final static String INSERT="insert into CART values(CART_SEQ.nextval,?,?,?,'Y')";
	final static String DEL = "delete from CART where C_SEQ = ?";
	final static String UPDATE="update CART set C_AMOUNT=? where C_SEQ=?";
}
