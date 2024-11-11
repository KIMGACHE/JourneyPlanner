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
	<%
		String userid = (String)request.getAttribute("userid");
	%>
	<div class="wrapper">
		<header>
			<!-- topHeader -->
			<%@include file="/resources/static/jsp/topHeader.jsp" %>
			<!-- nav -->
			<%@include file="/resources/static/jsp/nav.jsp" %>
		</header>
		<main  class="layout">
				<form style="width:400px;margin:50px auto;">
					<div class="m-3">
						<label for="">USERNAME : </label>
						<input type="text" class="form-control" name="userid" readonly/>
					</div>
					<div class="m-3">
						<label for="">PASSWORD : </label>
						<input type="password" class="form-control" name="password"/>
					</div>	
					<div class="m-3">
						<label for="">ROLE : </label>
						<input type="text" class="form-control" name="role" readonly/>
					</div>
					<div class="m-3">
							<label for="">성별 : </label>
							<select class="form-select"  name="gender">
								<option value="M" selected>Male</option>
								<option value="F">Female</option>
							</select>
						</div>
					<div class="m-3">
						<button formaction="${pageContext.request.contextPath}/user/myinfo?userid=${userid}}" class="btn btn-success me-2">수정요청</button>
						<button formaction="${pageContext.request.contextPath}/" class="btn btn-secondary">이전으로</button>
					</div>				
				</form>
		</main>
		
		
		<!-- footer -->
		<%@include file="/resources/static/jsp/footer.jsp" %>
	</div>


	
</body>
</html>