<%@ page language="java" contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8" import="member.mvc.model.MemberService, member.mvc.model.MemberSet"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>idCheckProc</title>
<style>
@import url(//fonts.googleapis.com/earlyaccess/hanna.css);
@import url(//fonts.googleapis.com/earlyaccess/jejumyeongjo.css);
</style>
</head>
<body>
	<div style="text-align: center"></div>
	<h3 style="font-family: 'Jeju Myeongjo', serif;"> 아이디 중복 확인 결과 </h3>
	<% //1) 사용가능한 아이디일 경우, 아이디 입력 폼에 넣기 위함 
		String email = request.getParameter("email");
		MemberService service = MemberService.getInstance();
		int rCode = service.checkMember(email);
		out.println("입력 ID : <strong>" + email + "</stong>");
		if(rCode== MemberSet.PASS){ 
			out.println("<p>사용 가능한 아이디입니다.</p>"); 
			out.println("<a href='javascript:apply(\"" + email + "\")'>[적용]</a>"); 
	%>
	<script> 
		function apply(email){ //2) 중복확인 id를 부모창에 적용 
			//부모창 opener
			opener.document.getElementById("email").value = email;
			window.close(); //창닫기 
			}//apply () end 
	</script>
	<% }else{ 
		out.println("<p style='color: red'>해당 아이디는 사용하실 수 없습니다.</p>"); 
		}//if end 
	%>
	<hr>
	<a href="javascript:history.back()">[다시시도]</a> &nbsp; &nbsp;
	<a href="javascript:window.close()">[창닫기]</a>
</body>
</html>