<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<!-- link -->
	<%@include file="/resources/static/jsp/link.jsp" %>
	
	<!-- myinfo.css -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/user/myinfo.css" />
	
	<script>
		const path = "<%=request.getContextPath() %>";
		console.log("Path : " + path);
	</script>
	<!-- myinfo.js -->
	<script src="${pageContext.request.contextPath}/resources/static/js/user/myinfo.js" defer></script>
	
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
				<form action="" style="width:400px;margin:50px auto;">
					<div class="m-3" style="position:relative;">
						
						<div class="profileBox" style="margin:15px auto;width:150px;height:150px;border:1px solid lightgray;border-radius:50%;">
							<img src="${pageContext.request.contextPath}/profile/image?username=${username}"  style="width:100%;height:100%;border : 1px solid;border-radius:50%;"/>
						</div>
						<div class="profileUploadBtn" style="padding:5px;position:absolute;left:67%;top:80%;cursor:pointer">
							<span class="material-symbols-outlined">image</span>
						</div>
					</div>
					<div class="m-3">
						<label for="">USERNAME : </label>
						<input type="text" class="form-control"  />
					</div>
					<div class="m-3">
						<label for="">PASSWORD : </label>
						<input type="text" class="form-control" />
					</div>	
					<div class="m-3">
						<label for="">ROLE : </label>
						<input type="text" class="form-control" />
					</div>				
					<div class="m-3">
						<a href="" class="btn btn-success me-2">수정요청</a>
						<a href="" class="btn btn-secondary">이전으로</a>
					</div>				
				</form>
		</main>
		
		
		<!-- footer -->
		<%@include file="/resources/static/jsp/footer.jsp" %>
	</div>


	
</body>
</html>