<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="Domain.Common.Dto.*"%>

<div class="top-header layout  bg-warning text-light">
	
	<!-- <div class="title">김병관 만세</div> -->
	
	<ul>
	<%
		UserDto permissionUserDto = (UserDto)session.getAttribute("userDto");
	
		if(permissionUserDto==null){
	%>
			<li><a href="${pageContext.request.contextPath}/user/login">로그인</a></li>
			<li><a href="${pageContext.request.contextPath}/user/join">회원가입</a></li>
			
	<%	
		} else{
	%>
			<li><a href="${pageContext.request.contextPath}/user/myinfo">나의정보</a></li>
			<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
	<%
		}
	%>
	
	</ul>
	
</div>