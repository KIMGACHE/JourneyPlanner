<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="Domain.Common.Dto.*"%>
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
		<%
			PlannerDto plannerDto = (PlannerDto)request.getAttribute("plannerDto");
			System.out.println("plannerDto : " + plannerDto);
		%>
				<section>
					
					<form method="POST"  style="width:400px;" >
						<div class="m-3">
							<h1>Planner 수정</h1>
						</div>
						<div class="m-3">
							<label for="">지역</label>
							<select class="form-select" name="areacode">
								<option value="0"<%=plannerDto.getAreacode()==0 ? "selected" : "" %>>서울특별시</option>
								<option value="1"<%=plannerDto.getAreacode()==1 ? "selected" : "" %>>부산광역시</option>
								<option value="2"<%=plannerDto.getAreacode()==2 ? "selected" : "" %>>인천광역시</option>
								<option value="3"<%=plannerDto.getAreacode()==3 ? "selected" : "" %>>대구광역시</option>
								<option value="4"<%=plannerDto.getAreacode()==4 ? "selected" : "" %>>대전광역시</option>
								<option value="5"<%=plannerDto.getAreacode()==5 ? "selected" : "" %>>광주광역시</option>
								<option value="6"<%=plannerDto.getAreacode()==6 ? "selected" : "" %>>울산광역시</option>
								<option value="7"<%=plannerDto.getAreacode()==7 ? "selected" : "" %>>경기도</option>
								<option value="8"<%=plannerDto.getAreacode()==8 ? "selected" : "" %>>충청북도</option>
								<option value="9"<%=plannerDto.getAreacode()==9 ? "selected" : "" %>>충청남도</option>
								<option value="10"<%=plannerDto.getAreacode()==10 ? "selected" : "" %>>전라남도</option>
								<option value="11"<%=plannerDto.getAreacode()==11 ? "selected" : "" %>>전라북도</option>
								<option value="12"<%=plannerDto.getAreacode()==12 ? "selected" : "" %>>경상북도</option>
								<option value="13"<%=plannerDto.getAreacode()==13 ? "selected" : "" %>>경상남도</option>
								<option value="14"<%=plannerDto.getAreacode()==14 ? "selected" : "" %>>강원도</option>
								<option value="15"<%=plannerDto.getAreacode()==15 ? "selected" : "" %>>제주도</option>
							</select>
						</div>
						<div class="m-3">
							<select class="form-select" name="citycode">
								<option value="0"<%=plannerDto.getCitycode()==0 ? "selected" : "" %>>서울특별시</option>
								<option value="1"<%=plannerDto.getCitycode()==1 ? "selected" : "" %>>부산광역시</option>
								<option value="2"<%=plannerDto.getCitycode()==2 ? "selected" : "" %>>인천광역시</option>
								<option value="3"<%=plannerDto.getCitycode()==3 ? "selected" : "" %>>대구광역시</option>
								<option value="4"<%=plannerDto.getCitycode()==4 ? "selected" : "" %>>대전광역시</option>
								<option value="5"<%=plannerDto.getCitycode()==5 ? "selected" : "" %>>광주광역시</option>
								<option value="6"<%=plannerDto.getCitycode()==6 ? "selected" : "" %>>울산광역시</option>
								<option value="7"<%=plannerDto.getCitycode()==7 ? "selected" : "" %>>경기도</option>
								<option value="8"<%=plannerDto.getCitycode()==8 ? "selected" : "" %>>충청북도</option>
								<option value="9"<%=plannerDto.getCitycode()==9 ? "selected" : "" %>>충청남도</option>
								<option value="10"<%=plannerDto.getCitycode()==10 ? "selected" : "" %>>전라남도</option>
								<option value="11"<%=plannerDto.getCitycode()==11 ? "selectsed" : "" %>>전라북도</option>
								<option value="12"<%=plannerDto.getCitycode()==12 ? "selected" : "" %>>경상북도</option>
								<option value="13"<%=plannerDto.getCitycode()==13 ? "selected" : "" %>>경상남도</option>
								<option value="14"<%=plannerDto.getCitycode()==14 ? "selected" : "" %>>강원도</option>
								<option value="15"<%=plannerDto.getCitycode()==15 ? "selected" : "" %>>제주도</option>
							</select>
						</div>
						<div class="m-3">
							<label for="">출발일</label>
							<input type="date" class="form-control" name="startdate" value="<%=plannerDto.getStartdate()%>" />
						</div>
						<div class="m-3">
							<label for="">종료일</label>
							<input type="date" class="form-control" name="enddate" value="<%=plannerDto.getEnddate()%>"/>
						</div>
						<div class="m-3">
							<button  class="btn btn-success" formaction="${pageContext.request.contextPath}/planner/update?plannerid=<%=plannerDto.getPlannerid()%>">수정</button>
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