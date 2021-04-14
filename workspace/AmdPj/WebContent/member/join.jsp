<%@ page language="java" contentType="text/html; charset=utf-8"%>

<!DOCTYPE html>
<html lang="en">

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
  
  <script src="../js/trim.js"></script>
<script language="javascript"> 

	function check(){
		var emailval = f.email.value;
		emailval = trim(emailval);
		if(emailval.length == 0){
			alert("ID를 입력하세요.");
			f.email.value = "";
			f.email.focus();
			return false;
		}else{
			pass = checkByteLen(emailval, 50);
			if(!pass){
				alert("이메일을 정확히 입력해주세요.");
				f.email.focus();
				return false;
			}
		}

		var pwdval = f.pwd.value;
		pwdval = trim(pwdval);
		if(pwdval.length == 0){
			alert("비밀번호를 입력하세요.");
			f.pwd.value = "";
			f.pwd.focus();
			return false;
		}else{
			pass = checkByteLen(pwdval, 30);
			if(!pass){
				alert("비밀번호가 너무 깁니다. (영어 소문자/숫자 15자이내)");
				f.pwd.focus();
				return false;
			}
		}
		
		var pwdcheck = f.pwdcheck.value;
		pwdcheck = trim(pwdcheck);
		if(pwdcheck.length == 0){
			alert("비밀번호 확인란을 입력하세요.");
			f.pwdcheck.value = "";
			f.pwdcheck.focus();
			return false;
		}
		
		if(pwdcheck!=pwdval){
			alert("비밀번호 입력이 일치하지 않습니다.");
			return false;
		}
		
		var nameval = f.name.value;
		nameval = trim(nameval);
		if(nameval.length == 0){
			alert("이름을 입력하세요.");
			f.name.value = "";
			f.name.focus();
			return false;
		}else{
			pass = checkByteLen(nameval, 20);
			if(!pass){
				alert("이름이 너무 깁니다. (5자이내)");
				f.name.focus();
				return false;
			}
		}

		var addrval = f.addr.value;
		addrval = trim(addrval);
		if(addrval.length == 0){
			alert("주소를 입력하세요.");
			f.addr.value = "";
			f.addr.focus();
			return false;
		}
		f.submit();
	}
	function checkByteLen(str, size){
	    var byteLen = getByteLen(str);
		if(byteLen <= size){
			return true;
		}else{
			return false;
		}
	}
	function getByteLen(str){
	   return str.replace(/[\0-\x7f]|([0-\u07ff]|(.))/g,"$&$1$2").length;
	}
	function enterCheck(elm){
		if(event.keyCode == 13){
			if(elm == f.id){
				f.pwd.focus();
			}else{
				check();
			}
		}
	}
	function idCheck(){ //새창 만들기 
		window.open("idCheckForm.jsp", "idwin", "width=500, height=500");
		
		var f=document.getElementById("email");
		//f.readOnly = false;
	}
</script>

	<script>
	
	function readOnly() {
	   var f=document.getElementById("addr2");
	   f.readOnly = false;
	}
	</script>
	
</head>

<body>
  <!-- Navigation -->
  <nav class="navbar navbar-expand-lg navbar-light fixed-top" id="mainNav">
    <div class="container">
      <a class="navbar-brand" href="../index.jsp">아몬드</a>
      <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        Menu
        <i class="fas fa-bars"></i>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item">
            <a class="nav-link" href="../login/login.jsp">로그인</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="member/member.do?m=form">회원가입</a>
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
      <a class="navbar-brand" href="index.html">상품</a>
	  <a class="navbar-brand" href="index.html">공지게시판</a>
	  <a class="navbar-brand" href="index.html">Q&A게시판</a>
     </div>
  </header>
  

  <!-- Main Content -->
  <div class="site-wrap">
	s<div class="site-section bg-light">
  <div class="container">
    <div class="row">
      <div class="col-md-6 mb-5 mb-md-0">
      <h2 class="h3 mb-3 text-black font-heading-serif">JOIN</h2>
						<div class="p-3 p-lg-5 ">
<body>
    <!-- div 왼쪽, 오른쪽 바깥여백을 auto로 주면 중앙정렬된다.  -->
    <div id="wrap">
        <br><br>
        <b><font size="6" color="gray">회원가입</font></b>
        <!-- 입력한 값을 전송하기 위해 form 태그를 사용한다 -->
        <!-- 값(파라미터) 전송은 POST 방식, 전송할 페이지는 JoinPro.jsp -->
        <form method="post" action="member.do?m=join" name="f" onsubmit="return checkValue()">
        <div class="form-group row">
		<div class="col-md-12">
		<table>
               <label for="id" class="text-black">아이디<span
				class="text-danger">*</span></label> <input readOnly
				class="form-control" id="email" name="email"
				placeholder="exemple@exemple.com">
				</div>
				<div class="form-group">
					<input type="button" value="ID 중복확인" onclick="idCheck()">
				</div>
			</div>     
                <div class="form-group row">
				<div class="col-md-12">
					<label for="pwd" class="text-black">비밀번호 <span
						class="text-danger">*</span></label> <input class="form-control"
						id="pwd" name="pwd" type="password"
						placeholder="****">
				</div>
			</div>
				
                <div class="form-group row">
				<div class="col-md-12">
					<label for="pwdcheck" class="text-black">비밀번호 확인 <span
						class="text-danger">*</span></label> <input class="form-control"
						id="pwdcheck" name="pwdcheck" type="password">
				</div>
			</div>
                    
                <div class="form-group row">
					<div class="col-md-6">
						<label for="name" class="text-black">이름 <span
							class="text-danger">*</span></label> <input class="form-control"
							id="name" name="name">
					</div>
                    
                <div class="col-md-6">
					<label for="phone" class="text-black">전화번호 </label> <input
						type="text" class="form-control" name="phone"
						placeholder="- 없이">
				</div>
			</div>
                <div class="form-group row">
					<div class="col-md-12">
						<label for="addr" class="text-black">주소 </label> <input
						type="text" class="form-control" name="addr"
						placeholder="도로명"> 
						<input type="text" class="form-control" name="addr2"
						placeholder="상세주소">
					</div>
				</div>

					<div class="form-group row">
						<div class="col-md-12">
							<input type="button" value="Join"
								class="btn btn-primary py-3 px-5" onkeydown="check()"
								onclick="check()"> 
                    </td>
                </tr>
            </table>
            <br>
        </form>
    </div>
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
  <script src="../vendor/jquery/jquery.min.js"></script>
  <script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Custom scripts for this template -->
  <script src="../js/clean-blog.min.js"></script>

</body>

</html>
