package boardN.mvc.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import boardN.mvc.model.BoardNService;
import boardN.mvc.vo.BoardNVO;
import amd.domain.BoardN;

@WebServlet("/board_n/board_n.do")
public class BoardNController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private String m = "";
	public void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		m = request.getParameter("m");
		if(m != null) {
			m = m.trim();
			switch (m) {
				case "list": list(request, response); break;
				case "write": write(request,response); break;
				case "insert": insert(request,response); break;
				case "content": getBoardN(request,response); break;
				case "update" : getBoardN(request,response); break;
				case "updateOk": updateOk(request,response); break;
				case "delete": del(request, response); break;
				default: list(request, response); break;
			}
		}else {
			list(request, response); 
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
		int ps = 10;
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
		
		BoardNService service = BoardNService.getInstance();
		BoardNVO boardNVO = service.getboardNVO(cp, ps); //listResult
		request.setAttribute("boardNVO", boardNVO );
		
		if(boardNVO.getList().size() == 0 && cp>1) {
			response.sendRedirect("board_n.do?m=list&cp="+(cp-1));
		}else {
			String view = "list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(view);
			rd.forward(request, response);
		}
	}
	
	private void write(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
	
		String view = "write.jsp";
		response.sendRedirect(view);
	}
	
	private void insert(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		String bn_subject = request.getParameter("bn_subject");
		String bn_content = request.getParameter("bn_content");
		BoardN boardN = new BoardN(-1, null, bn_subject, bn_content, null, -1);
		
		BoardNService service = BoardNService.getInstance();
		service.insertS(boardN);
		
		String view = "board_n.do";
		response.sendRedirect(view);
	}
	
	private void getBoardN(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		long bn_seq = getBn_seq(request);
		if(bn_seq != -1L) {
			BoardNService service = BoardNService.getInstance();
			BoardN boardN = service.getBoardNS(bn_seq);
			request.setAttribute("boardN", boardN);
			
			String view = "";
			if(m.equals("content")) {
				view = "content.jsp";
			}else if(m.equals("update")) {
				view = "update.jsp";
			}
			RequestDispatcher rd = request.getRequestDispatcher(view);
			rd.forward(request, response);
		}else {
			String view = "board_n.do";
			response.sendRedirect(view);
		}
	}
	
	private void updateOk(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		long bn_seq = getBn_seq(request);
		String bn_subject = request.getParameter("bn_subject");
		String bn_content = request.getParameter("bn_content");
		BoardN boardN = new BoardN(bn_seq, null, bn_subject, bn_content, null, -1);
		
		BoardNService service = BoardNService.getInstance();
		service.updateS(boardN);
		
		
		String view = "board_n.do";
		response.sendRedirect(view);
	}
	
	private void del(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		long bn_seq = getBn_seq(request);
		if(bn_seq != -1L) {
			BoardNService service = BoardNService.getInstance();
			service.delS(bn_seq);
		}
		
		String view = "board_n.do";
		response.sendRedirect(view);
	}
	
	private long getBn_seq(HttpServletRequest request) {
		String bn_seqStr = request.getParameter("bn_seq");
		long bn_seq = 0L;
		if(bn_seqStr == null) {
			return -1L;
		}else {
			bn_seqStr = bn_seqStr.trim();
			try {
				bn_seq = Integer.parseInt(bn_seqStr);
				return bn_seq;
			}catch(NumberFormatException ne) {
				return -1L;
			}
		}
	}
	
}
