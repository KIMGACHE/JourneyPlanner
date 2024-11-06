<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<!-- link -->
	<%@include file="/resources/static/jsp/link.jsp" %>
		
		
	

	<style>
		.material-symbols-outlined {
		  font-variation-settings:
		  'FILL' 0,
		  'wght' 300,
		  'GRAD' 0,
		  'opsz' 12;
		  font-size : 1.2rem;
		}
	</style>
	
	<!-- read.css -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/book/read.css" />
	
	<script>
		const path = '${pageContext.request.contextPath}';
		const bookCode ='${bookDto.bookCode}';
		const profilePath = '${profilePath}';
	</script>
	
	<!-- read.js -->
	<script src="${pageContext.request.contextPath}/resources/static/js/book/read.js" defer></script>
	
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
		
		
				<h1>도서 조회 페이지</h1>
				<form style="width:400px" action="${pageContext.request.contextPath}/book/add" method="post">
					<div class="mb-3">
						<label for="">도서코드 : </label>
						<input name="bookCode" class="form-control" value="${bookDto.bookCode}" readonly/>
					</div>
					<div class="mb-3">
						<label for="">도서명 : </label>
						<input name="bookName" class="form-control" value="${bookDto.bookName}" readonly/>
					</div>	
					<div class="mb-3">
						<label for="">출판사 : </label>
						<input name="publisher" class="form-control" value="${bookDto.publisher}" readonly/>
					</div>		
					<div class="mb-3">
						<label for="">ISBN : </label>
						<input name="isbn" class="form-control" value="${bookDto.isbn}" readonly/>
					</div>
					<div>	
						<a href="${pageContext.request.contextPath}/book/update?bookCode=${bookDto.bookCode}" class="btn btn-success me-2">도서수정</a>
						<a href="${pageContext.request.contextPath}/book/delete?bookCode=${bookDto.bookCode}" class="btn btn-success me-2">도서삭제</a>
						<button class="btn btn-secondary m-2">이전으로</button>
					</div>		
				</form>
				
				
				<div class="mt-3">
					<div class="mb-2">
						<h3>댓글 수  <span>00</span> </h3>
					</div>
					<hr style="width:50%;min-width:400px;" >
					<div  class="mb-2 reply-header" >
						<textarea class="form-control" "   ></textarea>
						<a href="javascript:void(0)" class="reply-add-btn btn btn-primary">입력</a>
					</div>
					<div class="reply-box mb-2 reply-items">
						<!-- 
						<div class="item" style="">
							<div class="left" >
								<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRWzhx02ZkzK3F3tnKUFR9aodUUVVz7cESLtw&s" alt="profileImage"  />
								<div>USERNAME</div>
							</div>
							<div class="right" style="">
								<div class="date">2024-01-01</div>
								<div class="content">
									<textarea name="" id="" cols="10" rows="2" readonly>템플릿 - !</textarea>
								</div>
								<div class="buttongroup">
									<span class="material-symbols-outlined">thumb_up</span>
									<span class="material-symbols-outlined">thumb_down</span>
								</div>
							</div>
						</div>
						 -->
					
					</div>
					
				</div>
		</main>
		
		
		<!-- footer -->
		<%@include file="/resources/static/jsp/footer.jsp" %>
	</div>

	
</body>
</html>