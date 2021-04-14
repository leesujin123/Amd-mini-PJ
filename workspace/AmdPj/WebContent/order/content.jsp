<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, amd.domain.BoardQ, boardQ.mvc.vo.BoardQVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
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
            <a class="nav-link" href="../login/login.do?m=out">로그아웃</a>
          </li>
          <li class="nav-item">
			<a class="nav-link" href="../member/member.do?m=form">회원정보</a>
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
	  <a class="navbar-brand" href="board_q.do">QA게시판</a>
     </div>
  </header>
 
 <div class="container">
    <div class="row">	
		<div class="col-lg-8 col-md-10 mx-auto">
			주문확인<br/><br/>
			<table class="table table-striped table-hover">
            <thead>
                <tr>
                    <th width='40%'>상품</th>
                    <th width='20%'>상품가격</th>
                    <th width='20%'>수량</th>
					<th width='20%'>주문금액</th>
                </tr>
            </thead>
				<tbody>
				   <c:if test="${empty listC}">
					   <TR align='center' noshade>
						  <TD colspan="5">데이터가 없음</TD>
					   </TR>
				   </c:if>
				   <c:set var="total" value="0" scope="request"/>
				   <c:forEach items="${listC}" var="cart">
				   		<TR align='center' noshade>
							<c:set var = "total" value="${total + (cart.p_price * cart.c_amount)}"/>
							<td>
								<figure class="media">
									<div class="img-wrap"><img src="../img/${cart.p_img}" width="150" class="img-thumbnail img-sm"></div>
									<figcaption class="media-body">
										<h6 class="title text-truncate">${cart.p_name}</h6>
										<dl class="param param-inline small">
										  <dt></dt>
										  <dd></dd>
										</dl>
									</figcaption>
								</figure>
							</td>
							<td>${cart.p_price}</td>
							<td>${cart.c_amount}</td>
							<td>${cart.p_price * cart.c_amount}</td>
						 </TR>
					</c:forEach>
				</tbody>
			</table>
			<div align="right">주문금액 합계 : ${total}</div>
			
		</div>
	</div>
</div>

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
  <script src="../vendor/jquery/jquery.min.js"></script>
  <script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Custom scripts for this template -->
  <script src="../js/clean-blog.min.js"></script>

</body>

</html>