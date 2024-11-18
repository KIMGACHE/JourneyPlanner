<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="Domain.Common.Dto.*"%>
<%@page import = "java.util.*" %>
<!DOCTYPE html>
<html>
<head>
	<!-- link -->
	<%@include file="/resources/static/jsp/link.jsp" %>
	

	
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
		UserDto userDto = (UserDto)request.getAttribute("userDto");
	%>
	<div class="wrapper">
		<header>
			<!-- topHeader -->
			<%@include file="/resources/static/jsp/topHeader.jsp" %>
			<!-- nav -->
			<%@include file="/resources/static/jsp/nav.jsp" %>
		</header>
		<main  class="layout">
				<form style="width:400px;margin:30px auto;" method="POST">
					<div class="m-3">
						<label for="">USERID : </label>
						<input type="text" class="form-control" name="userid" readonly value="${userDto.getUserid()}"/>
					</div>
					<div class="m-3">
						<label for="">PASSWORD : </label>
						<input type="password" class="form-control" name="password" value="${userDto.getPassword()}"/>
					</div>	
					<div class="m-3">
						<label for="">ROLE : </label>
						<input type="text" class="form-control" name="role" readonly value="${userDto.getRole()}"/>
					</div>
					<div class="m-3">
						<label for="">age : </label>
						<input type="text" class="form-control" name="age" value="${userDto.getAge()}"/>
					</div>
					<div class="m-3">
							<label for="">성별 : </label>
							<select class="form-select"  name="gender">
								<option value="M" <%= userDto.getGender().equals("M") ? "selected" : "" %> >Male </option>
								<option value="F" <%= userDto.getGender().equals("F") ? "selected" : "" %> >Female</option>
							</select>
						</div>
					<div class="m-3">
						<button formaction="${pageContext.request.contextPath}/user/myinfo?userid=<%=userDto.getUserid()%>" class="btn btn-success me-2">수정요청</button>
						<button formaction="${pageContext.request.contextPath}/" class="btn btn-secondary">이전으로</button>
					</div>
				</form>
				<div class="myPlan">
					<table>
						<thead>
							<tr>
							<th scope="col">정보1</th>
							<th scope="col">정보2</th>
							<th scope="col">정보3</th>
						</tr>
						</thead>
						<tbody>
						<%List<PlannerDto> plannerDto = (ArrayList<PlannerDto>)request.getAttribute("plannerDto"); %>
						<%int totalCells = 9; // 3x3 테이블 고정
			              int plannerCount = plannerDto != null && !plannerDto.isEmpty() ? plannerDto.size() : 0;	//Dto가 비어있다면 0
			              int index = 0;	//각 칸에 들어갈 plannerID INDEX
			              
			              for(int row=0;row<3;row++){
			            	  %>
			            <tr>
			            	<%for(int col=0;col<3;col++){
			            		%>
			            	<td>
			            		<%
			            		if(index < plannerCount){
			            			PlannerDto planner = plannerDto.get(index);
			            			index++;	
			            		%>
			          	  		<a href="${pageContext.request.contextPath }/user/detailPlan?plannerId=<%=planner.getPlannerid() %>">
			            			Planner ID : <%=planner.getPlannerid() %><br>
			            		</a>
			            	
			            	</td>	
			            		<% }else{  //planner 개수가 부족할 경우 빈 셀
			            		%>
			            		<!-- 빈 셀 --> &nbsp;
			            		<% } %>
			            	<% } %>
			            	
			            </tr>
						<% } %>

						</tbody>

					</table>
				</div>
				
		</main>
		
		
		<!-- footer -->
		<%@include file="/resources/static/jsp/footer.jsp" %>
	</div>


	
</body>
</html>