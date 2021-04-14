<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

	<script src="../js/check.js"></script>
	<script language="javascript">
	   function check()
	   {
	       for(var i=0; i<document.input.elements.length; i++)
		   {
		      var inputElem = document.input.elements[i]
			  if(inputElem.value == "")
			  {
			     alert("모든 값을 입력 하셔야 합니다. ");
				 return false;
			  }
		   }
			var writerval = input.writer.value;
			writerval = trim(writerval);
			pass = checkByteLen(writerval, 80);
			if(!pass){
				alert("제한");
				input.writer.focus();
				return false;
			}
			var emailval = input.email.value;
			emailval = trim(emailval);
			pass = checkByteLen(emailval, 80);
			if(!pass){
				alert("제한");
				input.email.focus();
				return false;
			}
			var subjectval = input.subject.value;
			subjectval = trim(subjectval);
			pass = checkByteLen(subjectval, 60);
			if(!pass){
				alert("제목 ");
				input.subject.focus();
				return false;
			}
			var contentval = input.content.value;
			contentval = trim(contentval);
			pass = checkByteLen(contentval, 500);
			if(!pass){
				alert("글 ");
				input.writer.focus();
				return false;
			}
		   document.input.submit();
       }
	</script>


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
	  <strong><a class="navbar-brand" href="board_q.do">Q&A게시판</a></strong>
     </div>
  </header>
  

  <!-- Main Content -->
  <div class="container">
    <div class="row">
      <div class="col-lg-8 col-md-10 mx-auto">
        <form name="input" method="post" action="board_q.do?m=insert">
			글작성<br/><br/>
			<table class="table table-striped table-hover">
			  <tr>
				 <td width="10%" align="center">WRITER</td>
				 <td><input type="text" name="writer" class="form-control" value="${loginUser.m_name}" readonly></td>
			  </tr>
			  <tr>
				 <td align="center">EMAIL</td>
				 <td><input type="text" name="email" class="form-control" value="${loginUser.m_email}" readonly></td>
			  </tr>
			  <tr>
				 <td align="center">SUBJECT</td>
				 <td><input type="text" name="subject" class="form-control"></td>
			  </tr>
			  <tr>
				 <td align="center">CONTENT</td>
				 <td><textarea  name="content" rows="5" class="form-control"></textarea></td>
			  </tr>
			</table>
			<div align='right'>            
				<a href="board_q.do" class="btn btn-success">목록</a>
				<input type="button" value="작성" class="btn btn-success" onclick="check()">
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
