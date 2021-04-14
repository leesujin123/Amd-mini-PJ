package boardQ.mvc.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import amd.domain.BoardQ;
import boardQ.mvc.model.BoardQService;
import boardQ.mvc.vo.BoardQVO;


@WebServlet("/board_q/board_q.do")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;   
	
	private BoardQService service = BoardQService.getInstance();
	
	public void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String m = request.getParameter("m");
		if(m != null) {
			m = m.trim();
			if(m.equals("write")) {
				write(request, response);
			}else if(m.equals("insert")) {
				insert(request,response);
			}else if(m.equals("content")){
				showContent(request,response);
			}else if(m.equals("moveUPage")){
				moveUPage(request,response);
			}else if(m.equals("update")){
				update(request, response);
			}else if(m.equals("delete")){
				delete(request,response);
			}else if(m.equals("moveRePage")){
				moveRePage(request,response);
			}else if(m.equals("rewrite")){
				rewrite(request, response);
			}else{
				list(request, response);
			}
		}else {
			list(request, response);
		}
	}
	
	
	
	private void list(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		
		HttpSession session = request.getSession();
		String cpStr = request.getParameter("cp");
		String psStr = request.getParameter("ps");
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
		
		int ps = 20;

		session.setAttribute("ps", ps);
		
		BoardQVO boardQVO = service.list(cp, ps);
		
		request.setAttribute("boardQVO", boardQVO);
		if(boardQVO.getList().size() == 0 && cp>1) {
			response.sendRedirect("board_q.do?cp="+(cp-1));
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
		String email = request.getParameter("email");//로그인하면 email,name은 세션에서 자동으로 입력되게 할겅미
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		BoardQ boardq = new BoardQ(-1,email,subject,content,null,0,-1,0,0);
		
		service.insertS(boardq);
		
		//에러처리추가
		//입력한 글내용으로 이동 고치자
		String view = "board_q.do";	
		response.sendRedirect(view);
	}
	private void showContent(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		int bq_seq = 0;
		String seqStr = request.getParameter("seq");
		bq_seq = check(seqStr);
						
		BoardQ boardQ = service.showContentS(bq_seq);
		request.setAttribute("boardQ", boardQ);
		String view = "content.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);

	}

	private void moveUPage(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		String seqStr = request.getParameter("seq");
		int bq_seq = check(seqStr);
		
		String justFor = "overloading";
		BoardQ boardQ = service.showContentS(bq_seq, justFor);
		request.setAttribute("boardQ", boardQ);

		String view = "update.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	private void update(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		String seqStr = request.getParameter("seq");
		int bq_seq = check(seqStr);
		
		String m_email = request.getParameter("email");
		String bq_subject = request.getParameter("subject");
		String bq_content = request.getParameter("content");
		
		BoardQ boardQ = new BoardQ(bq_seq, m_email, bq_subject, bq_content, null, -1,-1,-1,-1);
		service.updateS(boardQ);
		
		String view = "board_q.do?m=content";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		String seqStr = request.getParameter("seq");
		int bq_seq = check(seqStr);
		
		service.deleteS(bq_seq);

		String view = "board_q.do";
		response.sendRedirect(view);
	}
	private void moveRePage(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		String ref = request.getParameter("ref");
		String lev = request.getParameter("lev");
		String place = request.getParameter("place");
		
		//System.out.println(ref+lev+place);
		String view = "rewrite.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	
	/////////////////////
	private void rewrite(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		String email = request.getParameter("email");//로그인하면 email,name은 세션에서 자동으로 입력되게 할겅미
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		String refStr = request.getParameter("ref");
		String levStr = request.getParameter("lev");
		String placeStr = request.getParameter("place");
		
		System.out.println(refStr+levStr+placeStr+email+subject+content);
		
		service.rewriteS(refStr, levStr, placeStr, email, subject, content);
		
		String view = "board_q.do";
		response.sendRedirect(view);
	}


	//////////////////////
	private int check(String seqStr){
		if(seqStr != null){
			seqStr = seqStr.trim();
			if(seqStr.length() != 0){
				try{
					int seq = Integer.parseInt(seqStr);
					return seq;
				}catch(NumberFormatException ne){	
				}
			}else{
			}
		}else{
		}
		return -1;
	}
}
