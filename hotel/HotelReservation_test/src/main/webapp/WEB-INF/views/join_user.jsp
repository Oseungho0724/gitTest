<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<!-- 유저 등록 페이지 -->

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</head>
<body>

<br>
<div style="text-align:center;">
	<a href="<%=request.getContextPath()%>/"> <img src="resources/logo/rightlogo.png" style="width: 180px; height: 150px; "></a> 
</div>
<hr>
<br>
<div style="position:absolute; top:25%; left:35.5%; text-align:center;">
<h3>회원가입</h3>
	<form action="join_user_save" method="post" id="join_user_save_form">
	<div class="input-group mb-3">
  		<span class="input-group-text" id="basic-addon1">아이디</span>
  		<input type="text" class="form-control" placeholder="아이디" aria-label="Username" aria-describedby="basic-addon1" name="id_user" id="id_user" onkeyPress="keypress()">
  		<input type="button" value="중복 확인" class="btn btn-outline-primary btn-sm" id="join_user_check">
	</div>
	<div class="input-group mb-3">
  		<span class="input-group-text" id="basic-addon1">비밀번호</span>
  		<input type="text" class="form-control" placeholder="비밀번호" aria-label="Username" aria-describedby="basic-addon1" name="pw_user">
	</div>
	<div class="input-group mb-3">
  		<span class="input-group-text" id="basic-addon1">이름</span>
  		<input type="text" class="form-control" placeholder="이름" aria-label="Username" aria-describedby="basic-addon1" name="name_user">
	</div>
	<div class="input-group mb-3">
  		<span class="input-group-text" id="basic-addon1">전화번호</span>
  		<input type="text" class="form-control" placeholder="전화번호" aria-label="Username" aria-describedby="basic-addon1" name="tel_user">
	</div>
	<div class="input-group mb-3">
  		<span class="input-group-text" id="basic-addon1">이메일</span>
  		<input type="text" class="form-control" placeholder="이메일" aria-label="Username" aria-describedby="basic-addon1" name="email_user">
	</div>
	<div class="input-group mb-3">
  		<span class="input-group-text" id="basic-addon1">나이</span>
  		<input type="text" class="form-control" placeholder="아이디" aria-label="Username" aria-describedby="basic-addon1" name="age_user">
	</div>
	<div class="input-group" style="margin-left:30px;">
  		<div class="input-group-text">
    		<span class="input-group-text" id="basic-addon1">일반회원</span>
    		<input class="form-check-input mt-0" type="radio" value="일반회원" name="type_user" checked="checked">
    	</div>
    	<div class="input-group-text">
    		<span class="input-group-text" id="basic-addon1">사업주</span>
    		<input class="form-check-input mt-0" type="radio" value="사업주" name="type_user">
  		</div>
  		
	</div>
		<input type="submit" value="회원가입" disabled style="margin-top:15px;" class="btn btn-outline-primary btn-lg" id="join_user_save">
	</form>
</div>
</body>
<script src ="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
const target = document.getElementById("join_user_save");
$('#join_user_check').click(function(){
    let ID = $('#id_user').val();
    
    $.ajax({
       url: "join_user_check.do",
       type:"post",
       data : {ID:ID},
       dataType:"JSON",
       success : function(result){
          if(result == 1){
        	  alert("사용 가능 합니다.");
        	  target.disabled = false;
          }
          else{
        	  alert("아이디를 사용할 수 없습니다.");
          }
       },
       error : function(){
          alert("서버요청 실패");
       }
    })
 })
function keypress(){
    target.disabled = true;
}
</script>
</html>