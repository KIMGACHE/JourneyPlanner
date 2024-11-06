<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<!-- link -->
	<%@include file="/resources/static/jsp/link.jsp" %>
	
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
		<main  class="layout" style="">
				<h1>도서 등록 페이지</h1>
				<form style="width:400px" action="${pageContext.request.contextPath}/book/add" method="post">
					<div class="mb-3">
						<label for="">도서코드 : </label>
						<input name="bookCode" class="form-control" />
					</div>
					<div class="mb-3">
						<label for="">도서명 : </label>
						<input name="bookName" class="form-control" />
					</div>	
					<div class="mb-3">
						<label for="">출판사 : </label>
						<input name="publisher" class="form-control" />
					</div>		
					<div class="mb-3">
						<label for="">ISBN : </label>
						<input name="isbn" class="form-control" />
					</div>
					<div>	
						<button class="btn btn-success">도서 등록</button>
					</div>		
				</form>
		</main>
		
		
		<!-- footer -->
		<%@include file="/resources/static/jsp/footer.jsp" %>
	</div>

	
</body>
</html>