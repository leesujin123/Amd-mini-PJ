package member.mvc.control;

import java.io.IOException;
import java.sql.Date;
import java.text.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import amd.domain.Member;
import member.mvc.model.MemberService;
import member.mvc.model.MemberSet;

@WebServlet("/member/member.do")
	public class MemberServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
	       
		private String m = "";
	
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		m = request.getParameter("m");
		if(m != null) {
			m = m.trim();
			switch(m) {
				case "form": form(request, response); break;
				case "check": check(request, response); break;
				case "join": join(request, response); break;
				case "edit": edit(request, response); break;
				case "goUpdate": goUpdate(request, response); break;
				case "update": update(request, response); break;
	
				default: response.sendRedirect("../index.jsp");
			}
		}else {
			response.sendRedirect("../index.do");
		}
	}
	private void form(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = "join.jsp";
		response.sendRedirect(view);
	}
	private void check(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		String email = request.getParameter("email");
	    if(email != null) email = email.trim();
	    
	    MemberService service = MemberService.getInstance();
	    int rCode = service.checkMember(email);
	    request.setAttribute("rCode", rCode);
	    if(rCode==MemberSet.PASS) {
	    	HttpSession session = request.getSession();
	    	session.setAttribute("email", email);
	    }
	    
	    String referer = request.getHeader("Referer");
		request.getSession().setAttribute("redirectURI", referer);
	}
	
	private void join(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		String email = request.getParameter("email");
	    String pwd = request.getParameter("pwd");
	    String name = request.getParameter("name");
	    String phone = request.getParameter("phone");
	    String addr = request.getParameter("addr");
	    String addr2 = request.getParameter("addr2");
	    if(email != null) email = email.trim();
	    if(pwd != null) pwd = pwd.trim();
	    if(name != null) name = name.trim();
	    if(phone != null) phone = phone.trim();
	    if(addr != null) addr = addr.trim();
	    if(addr2 != null) addr2 = addr2.trim();
	    
	    System.out.println(email+pwd+name+phone+addr+addr2);
	    MemberService service = MemberService.getInstance();
	    Member member = new Member(email, pwd, name, phone, addr, addr2, null);
	    Boolean rCode = service.join(member);
	    
	
	    if(rCode) {
	    	member.setM_pwd("");
	    	HttpSession session = request.getSession();
		    session.setAttribute("member", member);
		    String view = "../login/login.jsp";
			response.sendRedirect(view);
	    }else {
	    	System.out.println("회원가입 실패");
	    }
	}
	private void welcome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String view = "../login/login.jsp";
		response.sendRedirect(view);
	}
	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String email = null;
		Boolean uCode = false;
		Member m = (Member)session.getAttribute("loginUser");
		if(m!=null) {
			email = m.getM_email();
		}
		if(email!=null) {
			uCode = true;
			session.setAttribute("uCode", uCode);
			
		}else {
			session.setAttribute("uCode", uCode);
		}
		String view = "fail.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	
	private void goUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String view = "edit.jsp";
		response.sendRedirect(view);
	}
		

	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Boolean upCode = false; // update 성공여부
		
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
	    String newpwd = request.getParameter("newPwd"); // 비번확인란
	    String name = request.getParameter("name");
	    String phone = request.getParameter("phone");
	    String addr = request.getParameter("addr");
	    String addr2 = request.getParameter("addr2");
	    System.out.println(email+pwd);
	    if(email != null) email = email.trim();
	    if(pwd != null) pwd = pwd.trim();
	    if(name != null) name = name.trim();
	    if(phone != null) phone = phone.trim();
	    if(addr != null) addr = addr.trim();
	    if(addr2 != null) addr2 = addr2.trim();
	    
	    System.out.println(email+ "&"+pwd);
	    MemberService service = MemberService.getInstance();
	    if(service.checkPwd(email, pwd) == MemberSet.YES_ID) {
	    	System.out.println(1);
		    Member member = new Member(email, newpwd, name, phone, addr, addr2, null);
		    upCode = service.edit(member);
	
	    	HttpSession session = request.getSession();
	    	session.setAttribute("loginUser", member);
	    	session.setAttribute("upCode", upCode);
		    response.sendRedirect("edit_msg.jsp");
	    }else {
	    	HttpSession session = request.getSession();
	    	session.setAttribute("upCode", upCode);
		    response.sendRedirect("edit_msg.jsp");
	    }

	}
}
