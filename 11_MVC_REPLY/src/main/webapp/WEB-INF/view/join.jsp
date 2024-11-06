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
<title>Insert title here</title>
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
					
					<form action="${pageContext.request.contextPath}/join" method="post"  style="width:400px;">
						<div class="m-3">
							<h1>회원가입 페이지</h1>
						</div>
						<div class="m-3">
							<label for="">아이디 : </label>
							<input type="text" class="form-control" name="username" />
						</div>
						<div class="m-3">
							<label for="">패스워드 : </label>
							<input type="text" class="form-control" name="password" />
						</div>
						<div class="m-3">
							<label for="">역할 : </label>
							<select class="form-select"  name="role">
								<option value="ROLE_USER" selected>일반유저</option>
								<option value="ROLE_MEMBER">사서계정</option>
								<option value="ROLE_ADMIN">관리자</option>
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