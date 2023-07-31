<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
<a href="mainPage"> <img src="resources/logo/rightlogo.png" style="width: 180px; height: 150px; "></a> 
</div>
<hr>
<br>


<div style="position:absolute; top:25%; left:38.5%; text-align:center;">
<h3>정보 수정</h3>
	<form action="update_user_save" method="post">
	<div class="input-group mb-3">
  		<span class="input-group-text" id="basic-addon1">아이디</span>
  		<input type="text" class="form-control" aria-label="Username" aria-describedby="basic-addon1" name="id_user" value="${uvo.id_user}" readonly>
	</div>
	<div class="input-group mb-3">
  		<span class="input-group-text" id="basic-addon1">비밀번호</span>
  		<input type="text" class="form-control" aria-label="Username" aria-describedby="basic-addon1" name="pw_user" value="${uvo.pw_user }">
	</div>
	<div class="input-group mb-3">
  		<span class="input-group-text" id="basic-addon1">이름</span>
  		<input type="text" class="form-control" aria-label="Username" aria-describedby="basic-addon1" name="name_user" value="${uvo.name_user }">
	</div>
	<div class="input-group mb-3">
  		<span class="input-group-text" id="basic-addon1">전화번호</span>
  		<input type="text" class="form-control" aria-label="Username" aria-describedby="basic-addon1" name="tel_user" value="${uvo.tel_user }">
	</div>
	<div class="input-group mb-3">
  		<span class="input-group-text" id="basic-addon1">이메일</span>
  		<input type="text" class="form-control" aria-label="Username" aria-describedby="basic-addon1" name="email_user" value="${uvo.email_user }">
	</div>
	
		<input type="submit" value="정보수정" style="margin-top:15px;" class="btn btn-outline-primary btn-lg">
		<a href="myPage?id_user=${nowUser }">
		<input type="button" value="돌아가기" style="margin-top:15px;" class="btn btn-outline-primary btn-lg">
		</a>
	</form>
</div>

</body>
</html>