<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script language="javascript">
	if(${uCode}){
		location.href="member.do?m=goUpdate";
	}else{
		alert("로그인 후에 사용 가능합니다.");
		location.href="../login/login.do?m=form";
	}
</script>