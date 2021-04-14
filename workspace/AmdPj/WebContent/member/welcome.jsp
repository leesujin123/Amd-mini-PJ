<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script language="javascript">
	if(${uCode}){
		location.href="member.do?m=welcome";
	}else{
		alert("회원가입을 축하드립니다.");
		location.href="../login/login.do?m=form";
	}
</script>