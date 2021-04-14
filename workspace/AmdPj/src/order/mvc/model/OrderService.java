package order.mvc.model;

import java.util.List;

import amd.domain.Cart;
import amd.domain.Member;
import amd.domain.Ord;
import amd.domain.Product;
import order.mvc.vo.OrderVO;

public class OrderService {
	private OrderDAO dao;
	private static final OrderService instance = new OrderService();
	private OrderService() {
		dao = new OrderDAO();
	}
	public static OrderService getInstance() {
		return instance;
	}
	
	public OrderVO showOrderPage(String m_email) {
		List<Cart> listC = dao.showCartInfo(m_email);
		Member member = dao.showOrderer(m_email);
		return new OrderVO(listC, member);
	}
	
	public void insertOrderS(String m_email) {
		//dao.insertOrd(order);
		dao.updateCartValid(m_email);
	}
	
	public List<Cart> showOrderInfoS(String m_email) {
		return dao.showOrderInfo(m_email);
	}
}
