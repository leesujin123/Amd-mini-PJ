package order.mvc.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import amd.domain.Cart;
import amd.domain.Member;
import order.mvc.model.OrderService;
import order.mvc.vo.OrderVO;


@WebServlet("/order/order.do")
public class OrderContoller extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private OrderService service = OrderService.getInstance();
	private String m ="";
 	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		m = request.getParameter("m");
		if(m != null) {
			m=m.trim();
			switch(m) {
			case "insertOrd" : insertOrd(request,response);break;
			case "listOrd" : listOrd(request,response);break;
			case "content" : showContent(request,response);break;
			default: moveOrdPage(request, response);
			}
		}else {
			moveOrdPage(request, response);
		}
	}
 	private void moveOrdPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		/* �ֹ��������� ǥ���� ���� 	
 	 	������ : VO�� ��� ��������
 			1)���ǿ� ����� ��ٱ��� email >> �̹����̸�����/����,  
 				>> ���� ��  Cart cart
 			2)���ǿ� ����� m_email >> �̸� ���� ��ȭ��ȣ �ּ�
 				: select * from Member where M_EMAIL = ?
 				>> �� �� Member member , pwd�� �η�.
 		*/
 		HttpSession session = request.getSession();
 		Member member = (Member)session.getAttribute("loginUser");
 		String m_email = member.getM_email();
 		
 		OrderVO orderVO = service.showOrderPage(m_email);
 		request.setAttribute("orderVO", orderVO);
 		
 		String view = "order.jsp";
 		RequestDispatcher rd = request.getRequestDispatcher(view);
 		rd.forward(request, response);
 	}
 	
 	private void insertOrd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		/* ������ư�� ���� ��
 			//1. �μ�Ʈ �ֹ����̺� >>> ���̺� ���� ����
 			//insert into ORD values(ORD_SEQ.nextval, ?, SYSDATE, ?, ?, ?, ?, ?)";
 			
 			1. ������Ʈ īƮ (��ȿ�� ����)
 			update CART set C_VALID ='N' where C_SEQ = any(select C_SEQ from CART c join Member m on c.M_EMAIL = ? and c.C_VALID = 'Y')
 		*/
 		HttpSession session = request.getSession();
 		Member member = (Member)session.getAttribute("loginUser");
 		service.insertOrderS(member.getM_email());
 		
 		String view = "order.do?m=listOrd";
 		RequestDispatcher rd = request.getRequestDispatcher(view);
 		rd.forward(request, response);
 	}
 	
 	private void listOrd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		/* ���ų����� ���� ��
 		 ����Ʈ
 		 select p.P_NAME, p.P_PRICE, p.P_IMG, c.C_SEQ, c.C_AMOUNT from PRODUCT p join CART c on p.P_CODE = any(select P_CODE from CART where C_SEQ = any(select C_SEQ from CART c join Member m on c.M_EMAIL = ? and c.C_VALID='Y'))
 		*/
 		HttpSession session = request.getSession();
 		Member member = (Member)session.getAttribute("loginUser");
 		
 		List<Cart> listC = service.showOrderInfoS(member.getM_email());
 		Cart cart = null;
 		if(listC.size()!=0) cart = listC.get(0);
 		
 		request.setAttribute("listC", listC);
 		request.setAttribute("Cart", cart);
 		
 		String view = "list.jsp";
 		RequestDispatcher rd = request.getRequestDispatcher(view);
 		rd.forward(request, response);
 	}
 	
 	private void showContent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		
 		HttpSession session = request.getSession();
 		Member member = (Member)session.getAttribute("loginUser");
 		
 		List<Cart> listC = service.showOrderInfoS(member.getM_email());
 		
 		request.setAttribute("listC", listC);
 		String view = "content.jsp";
 		RequestDispatcher rd = request.getRequestDispatcher(view);
 		rd.forward(request, response);
 	}
 	
}
