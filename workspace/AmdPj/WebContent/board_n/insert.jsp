<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="amd.domain.Admin"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<script language='javascript'>
	if(<${flag}){
		alert("입력 성공 by MV");
	}else{
		alert("입력 실패 by MV");
	}
		location.href='board.do?m=inserS';
</script>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
    <title>HBAF</title>

  <!-- Bootstrap core CSS -->
  <link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom fonts for this template -->
  <link href="../vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href='https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
  <link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>

  <!-- Custom styles for this template -->
  <link href="../css/clean-blog.min.css" rel="stylesheet">

</head>
<body>

  <!-- Navigation -->
  <nav class="navbar navbar-expand-lg navbar-light fixed-top" id="mainNav">
    <div class="container">
      <a class="navbar-brand" href="../index.do">아몬드</a>
      <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        Menu
        <i class="fas fa-bars"></i>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item">
            <c:choose>
          	<c:when test="${empty loginUser}"><a class="nav-link" href="../login/login.do?m=form">로그인</a></c:when>
          	<c:otherwise><a class="nav-link" href="../login/login.do?m=out">로그아웃</a></c:otherwise>
          	</c:choose>
          </li>
          <li class="nav-item">
            <c:choose>
          	<c:when test="${empty loginUser}"> <a class="nav-link" href="../member/member.do?m=form">회원가입</a></c:when>
          	<c:otherwise> <a class="nav-link" href="../member/member.do?m=form">회원정보</a></c:otherwise>
          	</c:choose>
          </li>
        </ul>
      </div>
    </div>
  </nav>

  <!-- Page Header -->
  <header class="masthead" style="background-image: url('../img/hbaf-bg3.png')">
    <div class="container">
      <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">
          <div class="site-heading">
            <h1></h1>
          </div>
        </div>
      </div>
    </div>
	<div class="container">
      <a class="navbar-brand" href="../product/product.do?cp=1&ps=16">전체 상품</a>
      <a class="navbar-brand" href="../product/product.do?m=listC&cp=1&ps=16&p_type=1">아몬드</a>
      <a class="navbar-brand" href="../product/product.do?m=listC&cp=1&ps=16&p_type=2">etc</a>
	  <a class="navbar-brand" href="">공지게시판</a>
	  <strong><a class="navbar-brand" href="board_q.do">Q&A게시판</a></strong>
     </div>
  </header>
  
  <script>
	$(document).on('click', '#btnSave', function(e){
		e.preventDefault();
		
		$("#form").submit();
	});
	
	$(document).on('click', '#btnList', function(e){
		e.preventDefault();
		
		location.href="${pageContext.request.contextPath}/board/getBoardList";
	});
</script>

<style>
body {
  padding-top: 70px;
  padding-bottom: 30px;
}
</style>

</head>

<body>

	<article>

		<div class="container" role="main">

			<h2>공지사항</h2>

			<form name="form" id="form" role="form" method="post" action="${pageContext.request.contextPath}"board.do?m=inserS">
				<div class="mb-3">
					<label for="title">제목</label>
					<input type="text" class="form-control" name="title" id="title" placeholder="제목">
				</div>
				
				<div class="mb-3">
					<label for="reg_id">작성자</label>
					<input type="text" class="form-control" name="reg_id" id="reg_id" placeholder="관리자">
				</div>
				
				<div class="mb-3">
					<label for="content">내용</label>
					<textarea class="form-control" rows="5" name="content" id="content" placeholder="내용을 입력해주세요" ></textarea>
				</div>
				
				<div class="mb-3">
					<label for="tag">작성날짜</label>
					<input type="text" class="form-control" name="tag" id="tag" placeholder="${board.b_date}">
				</div>
			
			</form>
			<div >
				<c:if test="${sessionScope.loginUser eq sessionScope.Admin}">
				<button  type="button" class="btn btn-sm btn-primary" id="btnSave">저장</button>
				
				</c:if>
				<a href="board.do" class="btn btn-sm btn-primary id="btnList">목록</button></a>
				
			</div>

		</div>

	</article>

</body>

</html>