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
				<h1>도서 수정 페이지</h1>
				<form style="width:400px" action="${pageContext.request.contextPath}/book/update" method="post">
					<div class="mb-3">
						<label for="">도서코드 : </label>
						<input name="bookCode" class="form-control" value="${bookDto.bookCode}" />
					</div>
					<div class="mb-3">
						<label for="">도서명 : </label>
						<input name="bookName" class="form-control" value="${bookDto.bookName}" />
					</div>	
					<div class="mb-3">
						<label for="">출판사 : </label>
						<input name="publisher" class="form-control" value="${bookDto.publisher}" />
					</div>		
					<div class="mb-3">
						<label for="">ISBN : </label>
						<input name="isbn" class="form-control" value="${bookDto.isbn}" />
					</div>
					<div>	
						<button class="btn btn-success me-2">수정요청</button>
						<button class="btn btn-secondary m-2">이전으로</button>
					</div>		
				</form>
		</main>
		
		
		<!-- footer -->
		<%@include file="/resources/static/jsp/footer.jsp" %>
	</div>

	
</body>
</html>