<%@ page contentType="text/html; charset=utf-8" import="amd.domain.Product"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<c:if test="${empty product}">
    <c:redirect url="product.do"/>
</c:if>


<!------ Include the above in your HEAD tag ---------->
<!DOCTYPE html>

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>HBAF</title>
  <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
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
      <a class="navbar-brand" href="product.do?cp=1&ps=16">전체 상품</a>
      <a class="navbar-brand" href="product.do?m=listC&cp=1&ps=16&p_type=1">아몬드</a>
      <a class="navbar-brand" href="product.do?m=listC&cp=1&ps=16&p_type=2">etc</a>
	  <a class="navbar-brand" href="index.html">공지게시판</a>
	  <a class="navbar-brand" href="index.html">Q&A게시판</a>
     </div>
  </header>
  
<div class="container">
<hr>

	
<div class="card">
	<div class="row">
		<aside class="col-sm-5 border-right">

<div class="img-container">
  <div> <a href="#"><img src="img/${product.p_img}" width="350" alt="" style="position:relative;top:5em;left:2em" ></a></div> 
  </div><!-- slider-product.// -->
		</aside>
		<aside class="col-sm-7">
<article class="card-body p-5">
	<h3 class="title mb-3">${product.p_name}</h3>

<p class="price-detail-wrap"> 
	<span class="price h3 text-warning"> 
		<span class="num">${product.p_price}원</span>
	</span> 
</p> <!-- price-detail-wrap .// -->
<dl class="item-property">
  <dt>제품 설명</dt>
  <dd><p>${product.p_info} </p></dd>
</dl>
<form name="input" method="post" action="../cart/cart.do?m=insert&p_code=${product.p_code}&p_img=${product.p_img}">
<hr>
	<div class="row">
		<div class="col-sm-5">
			<dl class="param param-inline">
			  <dt>수량: </dt>
			  <dd>
			  		
			  	<select name="c_amount" class="form-control form-control-sm" style="width:70px;">
			  		<option value="1"> 1 </option>
			  		<option value="2"> 2 </option>
			  		<option value="3"> 3 </option>
			  		<option value="4"> 4 </option>
			  		<option value="5"> 5 </option>
			  		<option value="6"> 6 </option>
			  		<option value="7"> 7 </option>
			  	</select>
			  	
			  </dd>
			</dl>  <!-- item-property .// -->
		</div> <!-- col.// -->
	</div> <!-- row.// -->
	<hr>
	  <a href="" class="btn btn-lg btn-primary text-uppercase"> 바로 구매 </a>
	  <input type="submit" value="장바구니" 
	  class="btn btn-lg btn-outline-primary text-uppercase"/>
	<!--<a href="../cart/cart.do?m=insert&p_code=${product.p_code}&p_img=${product.p_img}" class="btn btn-lg btn-outline-primary text-uppercase"> 
	<i class="fas fa-shopping-cart"></i> 장바구니 
	</a>-->
</form>
</article> <!-- card-body.// -->
		</aside> <!-- col.// -->
	</div> <!-- row.// -->
</div> <!-- card.// -->


</div>
<!--container.//-->


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