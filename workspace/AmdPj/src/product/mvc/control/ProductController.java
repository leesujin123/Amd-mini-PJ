package product.mvc.control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import product.mvc.model.ProductService;
import product.mvc.vo.ListResult;
import amd.domain.Product;


@WebServlet("/product/product.do")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	private String m = "";
	public void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		m = request.getParameter("m");
		if(m != null) {
			m=m.trim();
			switch(m) {
			case "list":list(request,response); break;
			case "detail":getBoard(request,response); break;
			case "listC":listC(request, response); break;
			default: list(request,response);
			}
		}else {
			list(request,response);
		}
	}
	private void list(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{	
		String cpStr = request.getParameter("cp");
		String psStr = request.getParameter("ps");
		
		HttpSession session = request.getSession();
		
		//(1) cp 
				int cp = 1;
				if(cpStr == null) {
					Object cpObj = session.getAttribute("cp");
					if(cpObj != null) {
						cp = (Integer)cpObj;
					}
				}else {
					cpStr = cpStr.trim();
					cp = Integer.parseInt(cpStr);
				}
				session.setAttribute("cp", cp);
				
				//(2) ps 
				int ps = 16;
				if(psStr == null) {
					Object psObj = session.getAttribute("ps");
					if(psObj != null) {
						ps = (Integer)psObj;
					}
				}else {
					psStr = psStr.trim();
					int psParam = Integer.parseInt(psStr);
					
					Object psObj = session.getAttribute("ps");
					if(psObj != null) {
						int psSession = (Integer)psObj;
						if(psSession != psParam) {
							cp = 1;
							session.setAttribute("cp", cp);
						}
					}else {
						if(ps != psParam) {
							cp = 1;
							session.setAttribute("cp", cp);
						}
					}
					
					ps = psParam;
				}
				session.setAttribute("ps", ps);
				
				ProductService service = ProductService.getInstance();
				ListResult listResult = service.getListResult(cp, ps);
				request.setAttribute("listResult", listResult);
				
				if(listResult.getList().size() == 0 && cp>1) {
					response.sendRedirect("product.do?m=list&cp="+(cp-1));
				}else {
					String view = "list.jsp";
					RequestDispatcher rd = request.getRequestDispatcher(view);
					rd.forward(request, response);
				}
			}
	private void listC(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{	
		String cpStr = request.getParameter("cp");
		String psStr = request.getParameter("ps");
		String p_type = request.getParameter("p_type");
		HttpSession session = request.getSession();
		
		//(1) cp 
				int cp = 1;
				if(cpStr == null) {
					Object cpObj = session.getAttribute("cp");
					if(cpObj != null) {
						cp = (Integer)cpObj;
					}
				}else {
					cpStr = cpStr.trim();
					cp = Integer.parseInt(cpStr);
				}
				session.setAttribute("cp", cp);
				
				//(2) ps 
				int ps = 16;
				if(psStr == null) {
					Object psObj = session.getAttribute("ps");
					if(psObj != null) {
						ps = (Integer)psObj;
					}
				}else {
					psStr = psStr.trim();
					int psParam = Integer.parseInt(psStr);
					
					Object psObj = session.getAttribute("ps");
					if(psObj != null) {
						int psSession = (Integer)psObj;
						if(psSession != psParam) {
							cp = 1;
							session.setAttribute("cp", cp);
						}
					}else {
						if(ps != psParam) {
							cp = 1;
							session.setAttribute("cp", cp);
						}
					}
					
					ps = psParam;
				}
				session.setAttribute("ps", ps);
				
			//(3) p_type
				if(p_type == null) {
					Object p_tyObj = session.getAttribute("p_type");
					p_type = (String)p_tyObj;
				}else {
					p_type = p_type.trim();
				}
				session.setAttribute("p_type", p_type);
				
				ProductService service = ProductService.getInstance();
				ListResult listResult = service.getListResult(cp, ps, p_type);
				request.setAttribute("listResult", listResult);
				
				if(listResult.getList().size() == 0 && cp>1) {
					response.sendRedirect("product.do?m=listC&cp="+(cp-1)+"&p_type="+p_type);
				}else {
					String view = "listC.jsp";
					RequestDispatcher rd = request.getRequestDispatcher(view);
					rd.forward(request, response);
				}
	}
	
	private void getBoard(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		int p_code = getP_code(request);
		if(p_code != -1) {
			ProductService service = ProductService.getInstance();
			Product product = service.getProductS(p_code);
			request.setAttribute("product", product);
			
			String view = "detail.jsp";
			/*if(m.equals("detail")) {
				view = "detail.jsp";
			}else { //m.equals("update")
				view = "list.jsp";
			}*/
			RequestDispatcher rd = request.getRequestDispatcher(view);
			rd.forward(request, response);
		}else {
			String view = "product.do";
			response.sendRedirect(view);
		}
	}
	
	private int getP_code(HttpServletRequest request) {
		String p_codeStr = request.getParameter("p_code");
		int p_code = 0;
		if(p_codeStr == null) {
			return -1;
		}else {
			p_codeStr = p_codeStr.trim();
			try {
				p_code = Integer.parseInt(p_codeStr);
				
				return p_code;
			}catch(NumberFormatException ne) {
				return -1;
			}
		}
	}
	

}
