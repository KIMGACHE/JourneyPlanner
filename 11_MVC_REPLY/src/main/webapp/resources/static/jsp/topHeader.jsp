<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="top-header layout  bg-success text-light">
	
	<ul>
	
		<%
			String role = (String)session.getAttribute("role");
			if(role == null)
			{
		%>
				<li><a href="${pageContext.request.contextPath}/login">로그인</a></li>
				<li><a href="${pageContext.request.contextPath}/join">회원가입</a></li>
		<%
			}
			else
			{
		%>
				<li><a href="${pageContext.request.contextPath}/user/myinfo">나의정보</a></li>
				<li><a href="${pageContext.request.contextPath}/logout">로그아웃</a></li>
		<% 
			}
		%>
		
		
		
	</ul>
	
</div>