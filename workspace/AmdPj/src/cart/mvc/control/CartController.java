package cart.mvc.control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cart.mvc.model.CartService;
import amd.domain.Cart;
import amd.domain.Member;

@WebServlet("/cart/cart.do")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	private String m = "";
	public void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		m = request.getParameter("m");
		if(m != null) {
			m = m.trim();
			switch(m){
				case "insert": insert(request,response); break;
				case "list" :list(request, response); break;
				case "del" : del(request, response); break;
				case "update" : update(request,response); break;
				default: list(request,response);
			}
		}else {
			list(request,response);
			
		}
	}
	private void list(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{	
	
		//2 m_email
		HttpSession session = request.getSession();
		
		Object loginUserObj = session.getAttribute("loginUser");
		Member loginUser = null;
		if(loginUserObj != null) {
			loginUser = (Member)loginUserObj;
			String m_email = loginUser.getM_email();
			if(m_email != null) {
				m_email = m_email.trim();
			}
			session.setAttribute("m_email", m_email);
			
			CartService service = CartService.getInstance();
			ArrayList<Cart> list = service.listS(m_email);
			request.setAttribute("list", list);
			
			String view = "cart.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(view);
			rd.forward(request, response);
			
		}else {
			String view = "../login/login.do";
			response.sendRedirect(view);
		}
		
	}
	
		
	private void insert(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		
		//2 m_email
		HttpSession session = request.getSession();
		
		Object loginUserObj = session.getAttribute("loginUser");
		Member loginUser = null;
		if(loginUserObj != null) {
			loginUser = (Member)loginUserObj;
			String m_email = loginUser.getM_email();
			if(m_email != null) {
				m_email = m_email.trim();
			}
			session.setAttribute("m_email", m_email);
			
			
			//6 c_amount
			String c_amountStr= request.getParameter("c_amount");
			System.out.println("장바구니담을때 :"+c_amountStr);
			int c_amount = 0;
			if(c_amountStr == null) {
				c_amount = -1;
			}else {
				c_amountStr = c_amountStr.trim();
				try {
					c_amount = Integer.parseInt(c_amountStr);
				}catch(NumberFormatException ne) {
					c_amount = -1;
				}
			}
			
			//3 p_code
			String p_codeStr= request.getParameter("p_code");
			int p_code = 0;
			if(p_codeStr == null) {
				p_code = -1;
			}else {
				p_codeStr = p_codeStr.trim();
				try {
					p_code = Integer.parseInt(p_codeStr);
				}catch(NumberFormatException ne) {
					p_code = -1;
				}
			}
			
			
			Cart cart = new Cart(-1, m_email, p_code, null,-1, null, c_amount, null);
			CartService service = CartService.getInstance();
			service.insertS(cart);
			String view = "cart.do";
			response.sendRedirect(view);
		}else {
			String view = "../login/login.do";
			response.sendRedirect(view);	
		}
	}
		
	private void del(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		String c_seqStr = request.getParameter("c_seq");
		int c_seq = 0;
		if(c_seqStr == null) {
			c_seq = -1;
		}else {
			c_seqStr = c_seqStr.trim();
			try {
				c_seq = Integer.parseInt(c_seqStr);
			}catch(NumberFormatException ne) {
				c_seq = -1;
			}
		}
		if(c_seq != -1) {
			CartService service = CartService.getInstance();
			service.delS(c_seq);
		}
		
		String view = "cart.do";
		response.sendRedirect(view);
	}
	
	private void update(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		String c_seqStr = request.getParameter("c_seq");
		int c_seq = 0;
		if(c_seqStr == null) {
			c_seq = -1;
		}else {
			c_seqStr = c_seqStr.trim();
			try {
				c_seq = Integer.parseInt(c_seqStr);
			}catch(NumberFormatException ne) {
				c_seq = -1;
			}
		}
		String c_amountStr = request.getParameter("c_amount");
		System.out.println("카트에서 수량을 변경할떄 :"+c_amountStr);
		int c_amount = 0;
		if(c_amountStr == null) {
			c_amount = -1;
		}else {
			c_amountStr = c_amountStr.trim();
			try {
				c_amount = Integer.parseInt(c_amountStr);
			}catch(NumberFormatException ne) {
				c_amount = -1;
			}
		}
		
		
		Cart cart = new Cart(c_seq, null, -1, null,-1, null, c_amount, null);
		CartService service = CartService.getInstance();
		service.updateS(cart);
		
		String view = "../order/order.do";
		response.sendRedirect(view);
	}
	
}
