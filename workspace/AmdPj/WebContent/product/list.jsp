<%@ page contentType="text/html; charset=utf-8"
    import="java.util.*, amd.domain.Product, product.mvc.vo.ListResult"%>
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
      <a class="navbar-brand" href="index.html">아몬드</a>
      <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        Menu
        <i class="fas fa-bars"></i>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item">
            <a class="nav-link" href="login/login.do">로그인</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="member/member.do">회원가입</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>

  <!-- Page Header -->
  <header class="masthead" style="background-image: url('img/hbaf-bg3.png')">
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
      <strong><a class="navbar-brand" href="product.do?cp=1&ps=16">전체 상품</a></strong>
      <a class="navbar-brand" href="product.do?m=listC&cp=1&ps=16&p_type=1">아몬드</a>
      <a class="navbar-brand" href="product.do?m=listC&cp=1&ps=16&p_type=2">etc</a>
	  <a class="navbar-brand" href="index.html">공지게시판</a>
	  <a class="navbar-brand" href="index.html">Q&A게시판</a>
     </div>
  </header>
  

  <!-- Main Content -->
  <div style="text-align:center;font-size:1.5em;font-family:'Nanum Gothic'">
	<strong>전체 상품</strong>
</div><br/>
	<div class="container">
        <div class="row">
 	 <c:forEach items="${listResult.list}" var="list">
          <div class="col-lg-3 col-md-4 mb-4">
            <div class="card" style="margin:0 auto;float: none;">
              <a href="product.do?m=detail&p_code=${list.p_code}"><img class="card-img-top" src="img/${list.p_img}" alt=""></a>
              <div class="card-body">
                <h4 class="card-title">
                  <a href="product.do?m=detail&p_code=${list.p_code}">${list.p_name}</a>
                </h4>
                <h5>${list.p_price}원</h5>
              </div>
            </div>
          </div>
          </c:forEach>
           </div>
          </div>
        <hr width='600' size='2' color='gray' noshade>
	<font color='gray' size='3' face='휴먼편지체'>
	<div style="text-align:center;font-size:1em;font-family:'Nanum Gothic'">
    (총 페이지수 : ${listResult.totalPageCount} )
    &nbsp;
    <c:forEach begin="1" end="${listResult.totalPageCount}" var="i">
        <a href="product.do?cp=${i}">
           <c:choose>
               <c:when test="${i==listResult.currentPage}">
                  <strong>${i}</strong>
               </c:when>
	           <c:otherwise>
	              ${i}
	           </c:otherwise>
           </c:choose>
        </a>&nbsp;
    </c:forEach>
    ( TOTAL : ${listResult.totalCount} )
    </div>
    
</font>
<hr width='600' size='2' color='gray' noshade>
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
