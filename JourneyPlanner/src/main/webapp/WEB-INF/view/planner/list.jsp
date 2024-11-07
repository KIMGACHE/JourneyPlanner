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
		<main  class="layout">
			<!-- 여긴 검색창 아직미구현 -->
			<%-- <section style="display:flex;justify-content:right;align-items:center;">
				
				<form class="d-flex" action="${pageContext.request.contextPath}/planner/list" method="get">	
			      	<select name="type" id="">
			      		<option value="all" selected>전체</option>
			      		<option value="bookCode">도서코드</option>
			      		<option value="bookName">도서명</option>
			      		<option value="publisher">출판사</option>
			      	</select>  	
			        <input name="keyword" style="width:200px;" class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
			        <input type="hidden" name="pageno" value="1" />
			        
			        <button class="btn btn-outline-success" type="submit">Search</button>
				</form>
			      
			</section> --%>
		
				
			<section  class="show-book">
				<h1>플래너 조회 페이지</h1>

				<%-- <div>
					페이지 : <%=pageDto.getCriteria().getPageno() %> / <%=pageDto.getTotalpage() %> (현재페이지 / 전체페이지)
				</div> --%>
				<table class="table w-100" style="margin:20px 0;">
					<thead>
						<tr class="bg-dark text-light">
							<th>PLANNER ID</th>
							<th>AREA</th>
							<th>CITY</th>
							<th>START DAY</th>
							<th>END DAY</th>
						</tr>
					</thead>
					<tbody>
						<%@page import="java.util.*,Domain.Common.Dto.*" %>
						<% 
							List<PlannerDto> list = (List<PlannerDto>)request.getAttribute("list");
							for(PlannerDto dto : list)
							{
						%>
						<tr>
							<td>
								<a href="${pageContext.request.contextPath}/planner/read?plannerid=<%=dto.getPlannerid() %>">
									<%=dto.getPlannerid() %>
								</a>
							</td>
							<td><%=dto.getAreacode() %></td>
							<td><%=dto.getCitycode() %></td>
							<td><%=dto.getStartdate() %></td>
							<td><%=dto.getEnddate() %></td>
						</tr>
						<%
							}
						%>
					</tbody>
					<%-- <tfoot>
						<tr>
							<td colspan="3">
								<!-- 페이지네이션 -->
								<nav aria-label="Page navigation example">
								  <ul class="pagination">
								    <!-- 이전버튼(PREV)  -->
								    <% 
								    	String type = pageDto.getCriteria().getType();
								    	String keyword = pageDto.getCriteria().getKeyword();
								    	
								    	if(pageDto.isPrev())
								    	{
								    		if(type==null)
								    		{
								    %>
											    <li class="page-item">
											      <a class="page-link" href="${pageContext.request.contextPath}/book/list?pageno=<%=pageDto.getStartPage() - 1%>" aria-label="Previous">
											        <span aria-hidden="true">&laquo;</span>
											      </a>
											    </li>
								    <%
								    		}
								    		else
								    		{
								    			%>
										    	<li class="page-item">
											      <a class="page-link" href="${pageContext.request.contextPath}/book/list?pageno=<%=pageDto.getStartPage() - 1%>&type=<%=type%>&keyword=<%=keyword%>" aria-label="Previous">
											        <span aria-hidden="true">&laquo;</span>
											      </a>
											    </li>			
							
								    			<%
								    			
								    		}
								    	}
								    %>
								    
								    
								    <%
								    	int startPage = pageDto.getStartPage();
								    	int endPage = pageDto.getEndPage();
								    	

								    	
								    	for(int i=startPage;i<=endPage;i++)
								    	{
								    %>
								    	<li class="page-item">
								    		<%
								    		if(type==null)
								    		{
								    		%>
								    			<a class="page-link" href="${pageContext.request.contextPath}/book/list?pageno=<%=i%>"><%=i%></a>
								    		<%
								    		}
								    		else
								    		{
								    		%>	
								    			<a class="page-link" href="${pageContext.request.contextPath}/book/list?pageno=<%=i%>&type=<%=type%>&keyword=<%=keyword%>"><%=i%></a>
								    		<%
								    		}
								    		%>
								    	</li>
								    <%
								    	}
								    %>
								    
								    <!-- 다음버튼(NEXT) -->
								    
								    <%
								    if(pageDto.isNext())
								    {
								    	if(type==null)
								    	{
								    		%>
											    <li class="page-item">
											      <a class="page-link" href="${pageContext.request.contextPath}/book/list?pageno=<%=pageDto.getEndPage()+1 %>" aria-label="Next">
											        <span aria-hidden="true">&raquo;</span>
											      </a>
											    </li>
								    		<%
								    	}
								    	else
								    	{
								    		%>
											    <li class="page-item">
											      <a class="page-link" href="${pageContext.request.contextPath}/book/list?pageno=<%=pageDto.getEndPage()+1 %>&type=<%=type%>&keyword=<%=keyword%>" aria-label="Next">
											        <span aria-hidden="true">&raquo;</span>
											      </a>
											    </li>								    		
		
								    		<%
								    	}
								    }
								    %>
								    
								  </ul>
								  
								</nav>
							
							</td>
							<td style="text-align:right">
								<!-- 글쓰기/처음으로 버튼 -->
								<a class="btn btn-success m-2" href="">글쓰기</a>
								<a class="btn btn-secondary m-2" href="">처음으로</a>
							</td>
						</tr>
					</tfoot> --%>
					
				</table>
					<a class="btn btn-success m-2" href="${pageContext.request.contextPath}/planner/add">플래너 작성하기</a>
					<a class="btn btn-secondary m-2" href="${pageContext.request.contextPath}/">처음으로</a>
			</section>
		</main>
		
		
		<!-- footer -->
		<%@include file="/resources/static/jsp/footer.jsp" %>
	</div>

	
</body>
</html>