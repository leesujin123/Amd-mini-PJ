<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, amd.domain.BoardN , boardN.mvc.vo.BoardNVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>


<head>

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
          	<c:when test="${empty loginUser}"><a class="nav-link" href="login/login.do?m=form">로그인</a></c:when>
          	<c:otherwise><a class="nav-link" href="login/login.do?m=out">로그아웃</a></c:otherwise>
          	</c:choose>
          </li>
          <li class="nav-item">
          	<c:choose>
          	<c:when test="${empty loginUser}"> <a class="nav-link" href="member/member.do?m=form">회원가입</a></c:when>
          	<c:otherwise> <a class="nav-link" href="member/member.do?m=goUpdate">회원정보</a></c:otherwise>
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
	  <a class="navbar-brand" href="../board_n/board_n.do">공지게시판</a>
	  <a class="navbar-brand" href="../board_q/board_q.do">Q&A게시판</a>
     </div>
  </header>

  <!-- Main Content -->
  <div class="container">
    <div class="row">
      <div class="col-lg-8 col-md-10 mx-auto">
        <form name="input" method="post" action="board_n.do?m=updateOk">
			글수정<br/><br/>
			<input type="hidden" name="bn_seq" value="${boardN.bn_seq}">
			<table class="table table-striped table-hover">
			  <tr>
				 <td width="10%" align="center">WRITER</td>
				 <td><input type="text" name="writer" value="관리자" class="form-control" readonly> </td>
			  </tr>
			  <tr>
				 <td align="center">EMAIL</td>
				 <td><input type="text" name="bn_email" value="${boardN.m_email}"class="form-control" readonly> </td>
			  </tr>
			  <tr>
				 <td align="center">SUBJECT</td>
				 <td><input type="text" name="bn_subject" value="${boardN.bn_subject}" class="form-control"> </td>
			  </tr>
			  <tr>
				 <td align="center">CONTENT</td>
				 <td><textarea  name="bn_content" rows="5" class="form-control">${boardN.bn_content} </textarea></td>
			  </tr>
			</table>
			<div align='right'>            
				<a href="board_n.do" class="btn btn-success">목록</a>
				<input type="submit" value="작성" class="btn btn-success">
				<input type="reset" value="다시작성" class="btn btn-success" onclick="input.subject.focus()">
			</div>
		</form>
	  </div>
    </div>
  </div>

  <hr>

  <!-- Footer -->
  <footer>
    <div class="container">
      <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">
          <ul class="list-inline text-center">
            <li class="list-inline-item">
              <a href="#">
                <span class="fa-stack fa-lg">
                  <i class="fas fa-circle fa-stack-2x"></i>
                  <i class="fab fa-twitter fa-stack-1x fa-inverse"></i>
                </span>
              </a>
            </li>
            <li class="list-inline-item">
              <a href="#">
                <span class="fa-stack fa-lg">
                  <i class="fas fa-circle fa-stack-2x"></i>
                  <i class="fab fa-facebook-f fa-stack-1x fa-inverse"></i>
                </span>
              </a>
            </li>
            <li class="list-inline-item">
              <a href="#">
                <span class="fa-stack fa-lg">
                  <i class="fas fa-circle fa-stack-2x"></i>
                  <i class="fab fa-github fa-stack-1x fa-inverse"></i>
                </span>
              </a>
            </li>
          </ul>
          <p class="copyright text-muted">Copyright &copy; Your Website 2020</p>
        </div>
      </div>
    </div>
  </footer>

  <!-- Bootstrap core JavaScript -->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Custom scripts for this template -->
  <script src="js/clean-blog.min.js"></script>

</body>

</html>