<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<!-- link -->
	<%@include file="/resources/static/jsp/link.jsp" %>
	
	<!-- join.css -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/user/join.css" />
	
<meta charset="UTF-8">
<title>플래너 작성</title>
</head>
<body>

	<div class="wrapper">
		<header>
			<!-- topHeader -->
			<%@include file="/resources/static/jsp/topHeader.jsp" %>
			<!-- nav -->
			<%@include file="/resources/static/jsp/nav.jsp" %>
		</header>
		<main  class="layout">
				
				<section>
					
					<form method="post"  style="width:400px;">
						<div class="m-3">
							<h1>Planner 작성</h1>
						</div>
						<div class="m-3">
							<label for="">지역</label>
							<select class="form-select" name="areacode">
								<option value="0">서울특별시</option>
								<option value="1">부산광역시</option>
								<option value="2">인천광역시</option>
								<option value="3">대구광역시</option>
								<option value="4">대전광역시</option>
								<option value="5">광주광역시</option>
								<option value="6">울산광역시</option>
								<option value="7">경기도</option>
								<option value="8">충청북도</option>
								<option value="9">충청남도</option>
								<option value="10">전라남도</option>
								<option value="11">전라북도</option>
								<option value="12">경상북도</option>
								<option value="13">경상남도</option>
								<option value="14">강원도</option>
								<option value="15">제주도</option>
							</select>
						</div>
						<div class="m-3">
							<select class="form-select" name="citycode">
									<option value="0">서울특별시</option>
									<option value="1">부산광역시</option>
									<option value="2">인천광역시</option>
									<option value="3">대구광역시</option>
									<option value="4">대전광역시</option>
									<option value="5">광주광역시</option>
									<option value="6">울산광역시</option>
									<option value="7">경기도</option>
									<option value="8">충청북도</option>
									<option value="9">충청남도</option>
									<option value="10">전라남도</option>
									<option value="11">전라북도</option>
									<option value="12">경상북도</option>
									<option value="13">경상남도</option>
									<option value="14">강원도</option>
									<option value="15">제주도</option>
								</select>
						</div>
						<div class="m-3">
							<label for="">출발일</label>
							<input type="date" class="form-control" name="startdate" />
						</div>
						<div class="m-3">
							<label for="">종료일</label>
							<input type="date" class="form-control" name="enddate" />
						</div>
						<div class="m-3">
							<button  class="btn btn-success" formaction="${pageContext.request.contextPath}/planner/add">추가</button>
							<button class="btn btn-secondary" formaction="${pageContext.request.contextPath}/">이전으로</button>
						</div>											
					</form>
					
				</section>
				
		</main>
		
		
		<!-- footer -->
		<%@include file="/resources/static/jsp/footer.jsp" %>
	</div>

	
</body>
</html>