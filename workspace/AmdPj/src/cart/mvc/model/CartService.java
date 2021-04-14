package cart.mvc.model;

import java.util.ArrayList;

import amd.domain.Cart;

public class CartService {
	
	private CartDAO dao;
	
	private static final CartService instance = new CartService();
	private CartService(){
		dao = new CartDAO();
	} 
	public static CartService getInstance() {
		return instance;
	}
	
	public ArrayList<Cart> listS(String m_email) {
		return dao.list(m_email);
	} 
	
	public void insertS(Cart cart) {
		dao.insert(cart);
	}
	
	public void delS(int c_seq) {
		dao.del(c_seq);
	}
	
	public void updateS(Cart cart) {
		dao.update(cart);
	}

}
