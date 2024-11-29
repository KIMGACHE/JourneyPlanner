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
					
					<form action="${pageContext.request.contextPath}/user/login" method="post"  style="width:400px;">
						<div style="color:red;">${message}</div>
						<div class="m-3">
							<h1>로그인 페이지</h1>
						</div>
						<div class="m-3">
							<label for="">아이디 : </label>
							<input type="text" class="form-control" name="userid" />
						</div>
						<div class="m-3">
							<label for="">패스워드 : </label>
							<input type="password" class="form-control" name="password" />
						</div>

						<div class="m-3">
							<button  class="btn btn-success" >로그인</button>
						</div>											
					</form>
				</section>
				
		</main>
		
		
		<!-- footer -->
		<%@include file="/resources/static/jsp/footer.jsp" %>
	</div>

	
</body>
</html>