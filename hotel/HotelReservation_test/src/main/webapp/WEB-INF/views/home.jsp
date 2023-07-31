<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>Home</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>

</head>
<body>
<br>
	<div style="text-align: center;">
		<a href="mainPage"> <img src="resources/logo/rightlogo.png"
			style="width: 180px; height: 150px;"></a>
	</div>
<hr>
<div style="position:absolute; top:25%; left:40%; text-align:center;">
	<form action = "login" method = "post" id="loginform">
		<div class="input-group mb-3">
  			<span class="input-group-text" id="basic-addon1">아이디</span>
  			<input type="text" class="form-control" placeholder="아이디" name="id_user" id="id_user">
		</div>
		<div class="input-group mb-3">
  			<span class="input-group-text" id="basic-addon1">비밀번호</span>
  			<input type="password" class="form-control" placeholder="비밀번호" name="pw_user" id="pw_user">
		</div>
		<button type="button" value="회원 로그인" style="margin-top:15px;" class="btn btn-outline-primary" id="user_chk">회원 로그인</button>
		<button type="button" value="비회원 로그인" style="margin-top:15px;" class="btn btn-outline-primary" onclick="unuser_chk()">비회원 로그인</button>
	</form>
	<a href="join_user" style="margin-top:15px;" class="btn btn-outline-primary btn-lg"> 회원가입 </a> <br>
	</div>
</body>

<script src ="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>

function user_chk(){
	document.getElementById('loginform').submit();
}

function unuser_chk(){
	document.getElementById('id_user').value = "비회원";
	document.getElementById('loginform').submit();
}

$('#user_chk').click(function(){
    let ID = $('#id_user').val();
    let PW = $('#pw_user').val();
    console.log(ID)
    $.ajax({
       url: "LoginCheck.do",
       type:"post",
       data : {ID:ID,PW:PW},
       dataType:"JSON",
       success : function(result){
          if(result == 1){
        	  document.getElementById('loginform').submit();
          }
          else{
        	  alert("아이디와 비밀번호를 확인해 주세요.");
          }
       },
       error : function(){
          alert("서버요청 실패");
       }
    })
 })
 
</script>
</html>
