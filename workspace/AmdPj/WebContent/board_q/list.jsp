<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, amd.domain.BoardQ, boardQ.mvc.vo.BoardQVO"%>
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
  

  <!-- Main Content -->
  <div class="container">
    <div class="row">
      
        <table class="table table-striped table-hover">
            <thead>
                <tr>
                    <th width='5%'>NO</th>
                    <th width='10%'>작성자</th>
					<th width='20%'>이메일</th>
                    <th width='45%'>제목</th>
                    <th width='10%'>날짜</th>
                    <th width='10%'>조회수</th>
                </tr>
            </thead>
            <tbody>
               <c:if test="${empty boardQVO.list}">
			       <TR align='center' noshade>
			          <TD colspan="6">데이터가 없음</TD>
			       </TR>
			   </c:if> 
			   <c:forEach items="${boardQVO.list}" var="boardQ">
                    <tr>
                        <td>${boardQ.bq_seq}</td>
                        <td>${boardQ.m_name}</td>
                        <td>${boardQ.m_email}</td>
                        <td>
                        	<c:forEach begin="1" end="${boardQ.bq_lev}">
									&nbsp;&nbsp;&nbsp;
							</c:forEach>
							<a href="board_q.do?m=content&seq=${boardQ.bq_seq}" >
								<c:if test="${boardQ.bq_lev > 0}"> <img src="img/icon_bbs_answer.gif">Re:</c:if>
								${boardQ.bq_subject}
							</a>
						</td>
						<td>${boardQ.bq_rdate}</td>
						<td>${boardQ.bq_count}</td>
                    </tr>
                 </c:forEach>
                 	<tr align='center'>
                 		<TD  colspan="6">
				            <c:forEach begin="1" end="${boardQVO.totalPageCount}" var="i">
						        <a id='aa' href="board_q.do?cp=${i}">
						           <c:choose>
						               <c:when test="${i==boardQVO.currentPage}">
						                  <strong>${i}</strong>
						               </c:when>
							           <c:otherwise>
							              ${i}
							           </c:otherwise>
						           </c:choose>
						        </a>&nbsp;
						    </c:forEach>
                 		</TD>
                 	</tr>
            </tbody>
        </table>
		<div align='center'>
       		<c:choose>
          		<c:when test="${empty loginUser}"></c:when>
          		<c:otherwise>
					<a href="board_q.do?m=write" class="btn btn-success">글쓰기</a>
				</c:otherwise>
          	</c:choose>
		    
		    
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
