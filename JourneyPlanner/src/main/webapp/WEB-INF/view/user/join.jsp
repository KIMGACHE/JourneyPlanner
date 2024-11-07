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
<title>회원가입</title>
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
					
					<form action="${pageContext.request.contextPath}/user/join" method="post"  style="width:400px;">
						<div class="m-3">
							<h1>회원가입 페이지</h1>
						</div>
						<div class="m-3">
							<label for="">아이디 : </label>
							<input type="text" class="form-control" name="username" />
						</div>
						<div class="m-3">
							<label for="">패스워드 : </label>
							<input type="password" class="form-control" name="password" />
						</div>
						<div class="m-3">
							<label for="">나이 : </label>
							<input type="text" class="form-control" name="age" />
						</div>
						<div class="m-3">
							<label for="">성별 : </label>
							<select class="form-select"  name="gender">
								<option value="ROLE_USER" selected>Male</option>
								<option value="ROLE_MEMBER">Female</option>
							</select>
						</div>
						<div class="m-3">
							<button  class="btn btn-success" >회원가입</button>
							<button class="btn btn-secondary" >이전으로</button>
						</div>											
					</form>
					
				</section>
				
		</main>
		
		
		<!-- footer -->
		<%@include file="/resources/static/jsp/footer.jsp" %>
	</div>

	
</body>
</html>