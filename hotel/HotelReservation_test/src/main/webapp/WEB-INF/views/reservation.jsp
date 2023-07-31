<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<!-- 방 상세보기 페이지 -->

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

	<div class="container text-center">
		<!-- Stack the columns on mobile by making one full-width and the other half-width -->
		<div class="row">
			<div class="col-md-8">
				<!-- 이벤트 사진 캐러셀 -->

				<table border=1 style="width: 768px"
					class="table-dark table table-striped">
					<tr class="table-dark">
						<td colspan=4>방 정보</td>
					</tr>
					<tr class="table-success">
						<td colspan=2>숙소 이름</td>
						<td colspan=2>${roomvo.detailAddr_room }</td>
					</tr>
					<tr class="table-success">
						<td colspan=2>방 이름</td>
						<td colspan=2>${roomvo.roomNum_room}</td>
					</tr>
					<tr class="table-success">
						<td colspan=2>방 설명</td>
						<td colspan=2>${roomvo.info_room}</td>
					</tr>
					<tr class="table-success">
						<td colspan=2>방 가격</td>
						<td colspan=2 id="price_room">${roomvo.price_room * roomvo.days }\</td>
					</tr>
					<tr class="table-success">
						<td colspan=2>
							<div class="input-group mb-3">
								<span class="input-group-text" id="basic-addon1">입실 시간</span> <input
									type="time" class="form-control"
									value="${roomvo.checkIn_room }" aria-label="Username"
									aria-describedby="basic-addon1" name="checkIn_res" readonly>
							</div>
						</td>
						<td colspan=2>
							<div class="input-group mb-3">
								<span class="input-group-text" id="basic-addon1">퇴실 시간</span> <input
									type="time" class="form-control"
									value="${roomvo.checkOut_room }" aria-label="Username"
									aria-describedby="basic-addon1" name="checkOut_res" readonly>
							</div>
						</td>
					</tr>
					<form action="reservation_save" method="get" id="reservationForm">
						<tr class="table-success">
							<td colspan=2>
								<div class="input-group mb-3">

									<span class="input-group-text" id="basic-addon1">입실</span> <input
										type="date" class="form-control" aria-label="Username"
										aria-describedby="basic-addon1" name="checkIn_res"
										id="checkIn_res" value = ${checkIn } readonly>
								</div>
							</td>
							<td colspan=2>
								<div class="input-group mb-3">
									<span class="input-group-text" id="basic-addon1">퇴실</span> <input
										type="date" class="form-control" aria-label="Username"
										aria-describedby="basic-addon1" name="checkOut_res"
										id="checkOut_res" value = ${checkOut } readonly> 
										<input type="hidden" name="id_res" value="${nowUser }"> 
										<input type="hidden" name="detailAddr_roomNum_res" value="${roomvo.detailAddr_roomNum_room }" id="detailAddr_roomNum_res" >
										<input type="hidden" id="price_res" name="price_res" value="${roomvo.price_room * roomvo.days }">
								</div>
							</td>
						</tr>

						<tr class="table-success">
							<td colspan=4><input type="submit"
								class="btn btn-outline-primary " value="방 예약"
								id="sendCheck"></td>
						</tr>
					</form>
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
						<p class="card-text" style="margin-top: 10px;">
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
			</div>
		</div>
	</div>




</body>
</html>