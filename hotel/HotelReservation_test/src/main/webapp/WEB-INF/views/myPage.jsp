<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<!-- 마이 페이지 -->

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
	crossorigin="anonymous"></script>
</head>
<body>
	<br>
	<div style="text-align: center;">
		<a href="mainPage"> <img src="resources/logo/rightlogo.png"
			style="width: 180px; height: 150px;"></a>
	</div>


	<nav class="navbar navbar-expand-lg bg-light">
		<div class="container-fluid">
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-1">
					<li class="nav-link me-5"></li>
					<li class="nav-item dropdown me-5"><a
						class="nav-link dropdown-toggle" href="#" role="button"
						data-bs-toggle="dropdown" aria-expanded="false"> Menu </a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="#">공지사항</a></li>
							<li><a class="dropdown-item" href="#">이벤트</a></li>
							<li><hr class="dropdown-divider"></li>
							<li><a class="dropdown-item" href="#">고객센터</a></li>
						</ul></li>
					<c:forEach var="category" items="${cList}">
						<li class="nav-item"><a class="nav-link me-5"
							href="list_hotel?category_hotel=${category }"> ${category} </a></li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</nav>

	<hr>
	<br>

	<div class="container text-center">
		<!-- Stack the columns on mobile by making one full-width and the other half-width -->
		<div class="row">
			<div class="col-md-8">
				
				<table border=2 style="width: 768px"
					class="table-dark table table-striped">
					<tr>
						<td colspan=4>내 정보</td>
					</tr>
					
					<c:if test="${nowUser == '비회원' }">
						<tr>
						<td colspan=4>비회원은 내 정보 보기가 불가능 합니다</td>
						</tr>
					</c:if>
					<c:if test="${nowUser != '비회원' }">
							<tr class="table-success">
								<td>아이디</td>
								<td>${uservo.id_user }</td>
							</tr>
							<tr class="table-success">
								<td>이름</td>
								<td>${uservo.name_user}</td>
							</tr>
							<tr class="table-success">
								<td>휴대폰 번호</td>
								<td>${uservo.tel_user}</td>
							</tr>
							<tr class="table-success">
								<td>이메일</td>
								<td>${uservo.email_user}</td>
							</tr>
							<tr class="table-success">
								<td>회원 유형</td>
								<td>${uservo.type_user}</td>
							</tr>
							<tr class="table-success">
								<td><a href="update_user?id_user=${nowUser }"><input type="button" value="정보수정" style="margin-top:15px;" class="btn btn-outline-primary "></a></td>
								<td><a href="delete_user?id_user=${nowUser }"><input type="button" value="탈퇴하기" style="margin-top:15px;" class="btn btn-outline-primary "></a></td>
							</tr>
							<table border=2 style="width: 768px" class="table-dark table table-striped">
								<tr>
									<td colspan=4>예약 정보</td>
								</tr>
								<c:forEach var="resvo" items="${resList}">
									<tr class="table-success">
										<td>방 번호</td>
										<td><a href="detail_room_res?id_hotel=${id_hotel}&detailAddr_roomNum_room=${resvo.detailAddr_roomNum_res}">${resvo.hotelname}</a>
										</td>
									</tr>
									<tr class="table-success">
										<td>입실 시간</td>
										<td>${resvo.checkIn_res}
										</td>
									</tr>
									<tr class="table-success">
										<td>퇴실 시간</td>
										<td>${resvo.checkOut_res }
										</td>
									</tr>
									<tr class="table-success">
										<td colspan=4><a href="reservation_delete?id_res=${resvo.id_res }&detailAddr_roomNum_res=${resvo.detailAddr_roomNum_res}&checkInDate=${resvo.checkInDate}"><input type="button" value="예약 취소" style="margin-top:15px;" class="btn btn-outline-primary bt-sm"></a></td>
									</tr>
									<tr>
										<td colspan=4>&nbsp;</td>
									</tr>
								</c:forEach>
							</table>
					</c:if>
				</table>
			</div>
			<div class="col-6 col-md-4">
				<div class="card" style="width: 350px; margin-left: 13px">
					<img src="resources/logo/greenlogo.png" class="card-img-top"
						alt="..." style="height: 210px;"> <br>
					<div class="card-body">
						<h5 class="card-title">${nowUser }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
								href="logout" class="btn btn-outline-primary btn-sm">로그아웃</a>
						</h5>
						<br>
						<p class="card-text">
							<a href="myPage?id_user=${nowUser}"
								class="btn btn-outline-primary"> 내 정보 보기 </a>

							<c:if test="${nowUser == '비회원' }">
								<a href="join_user" class="btn btn-outline-primary"> 회원가입 </a>
							</c:if>
							<c:if test="${userType == '사업주'}">
								<a href="join_hotel" class="btn btn-outline-primary"> 숙소 등록
								</a>
								<a href="myHotel?id_user=${nowUser}"
									class="btn btn-outline-primary"> 내 호텔 보기 </a>
							</c:if>

						</p>
						<br>
					</div>
				</div>




				<!-- <div style="position:absolute; top:50%;">
					loginUser : ${nowUser } <a href="logout">[로그아웃]</a> <br>
					<hr>
					<div style="text-align: center;">
						<a href="myPage?id_user=${nowUser}">[ 내 정보 보기 ]</a>

						<c:if test="${nowUser == '비회원' }">
							<a href="join_user">[ 회원가입 ]</a>
						</c:if>
						<c:if test="${userType == '사업주'}">
							<a href="join_hotel">[ 숙소 등록 ]</a>
						</c:if>
					</div>
				</div> -->
			</div>
		</div>
	</div>
	
</body>
</html>