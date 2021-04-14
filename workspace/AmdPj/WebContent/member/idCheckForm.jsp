
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>idCheck</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
</head>
<body>
	<div style="text-align: center; padding-top:60px">
		
		<form method="post" action="idCheckProc.jsp"
			onsubmit="return blankCheck(this)">
			아이디  <input type="text" id="email" name="email" maxlength="30" autofocus>
			<p>
			</p>
			<button type="submit" class="btn btn-outline-dark btn-sm">중복 확인</button>
		</form>
	</div>
	<script> 
		function blankCheck(f){ 
			var email=f.email.value; email=email.trim(); 
			if(email.length<4){ 
				alert("이메일을 다시 확인해주세요."); 
				return false; 
			}//if end 
			return true; 
		}//blankCheck() end 
		</script>
</body>
</html>