<%@page contentType="text/html;charset=utf-8" import="login.mvc.model.LoginSet, amd.domain.Member"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script language="javascript">
	if(${rCode} == <%=LoginSet.NO_EMAIL%>){
		alert("로그인 실패 ( 존재하지않는 아이디 )");
		location.href="login.jsp";
	}else if(${rCode} == <%=LoginSet.NO_PWD%>){
		alert("로그인 실패 ( 잘못된 비번 )");
		location.href="login.do";
	}else{
		alert("${loginUser.m_name}님 하이~! 로그인 성공");
		location.href="../index.jsp";
	}
</script>