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
 		/* 주문페이지에 표시할 내역 	
 	 	리스팅 : VO로 묶어서 전달하자
 			1)세션에 저장된 장바구니 email >> 이미지이름가격/수량,  
 				>> 여러 개  Cart cart
 			2)세션에 저장된 m_email >> 이름 메일 전화번호 주소
 				: select * from Member where M_EMAIL = ?
 				>> 한 개 Member member , pwd는 널로.
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
 		/* 결제버튼을 누를 떄
 			//1. 인서트 주문테이블 >>> 테이블 설계 오류
 			//insert into ORD values(ORD_SEQ.nextval, ?, SYSDATE, ?, ?, ?, ?, ?)";
 			
 			1. 업데이트 카트 (유효성 변경)
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
 		/* 구매내역을 누를 때
 		 리스트
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
