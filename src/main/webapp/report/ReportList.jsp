<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.itwillbs.report.db.ReportDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

	<%
	
	ArrayList<ReportDTO> reportlist = 
			(ArrayList<ReportDTO>) request.getAttribute("reportList");

	int currentPage = (Integer) request.getAttribute("currentPage");
	int startPage = (Integer) request.getAttribute("startPage");
	int pageBlock = (Integer) request.getAttribute("pageBlock");
	int endPage = (Integer) request.getAttribute("endPage");
	int pageCount = (Integer) request.getAttribute("pageCount");
	
	ReportDTO dto1= new ReportDTO(); 
	String id = (String) session.getAttribute("id");
	
	if(id == null){
		response.sendRedirect("MemberLoginForm.me");
	}
	%>
	<!-- 헤더파일들어가는 곳 -->
	<jsp:include page="/inc/header.jsp"/>
	<!-- 헤더파일들어가는 곳 -->
	<link href="resource/css/ReportList.css " rel="stylesheet" type="text/css">

	<h3 class="call">고객센터</h3>
				<br><br>
				<div class="left">
					<br> <br>
					<div>
						<div>
							<a href="ReportForm.re"> 신고하기 </a><br> <br> 
							<a href="ReportList.re">신고목록</a><br> <br> 
							<a	href="List.re">자주하는 질문</a>

						</div>
					</div>
				</div>


<div class="boardContainer">
<!-- 내용 시작 -->

	<h1 class="T">신고접수 현황📮</h1>
	<table  class="te" border="1">
		<tr class="title">
			<td>글번호</td>
			<td>글쓴이</td>
			<td>글제목</td>
			<td>글쓴날짜</td>
		</tr>
		<%
		//배열 접근 => for => //배열 한칸에 내용 가져오기 => BoardDTO 저장 => 출력
		for (int i = 0; i < reportlist.size(); i++) {
			ReportDTO dto = reportlist.get(i);
		%>
		
			<tr>
			<td><%=dto.getReport_id()%></td>
			<td><%=dto.getInsert_id() %></td>
			<td><a href="ReportContent.re?id=<%=dto.getReport_id()%>"><%=dto.getTitle()%></a></td>
			<td><%SimpleDateFormat dateformat = new SimpleDateFormat("yyyy년 MM월 dd일 HH:mm:ss");%>
			<%=dateformat.format(dto.getInsert_date()) %> </td>
			</tr>
		<%
		 } 
		%> 

	</table>
<div class="page">

	<% 
	//5페이지 이전
	if (startPage > pageBlock) {
	%>
	<a href="ReportList.re?pageNum=<%=startPage - pageBlock%>">[5페이지 이전]</a>
	<%
	}
	%>
	
	<% 
	//현재페이지
	for (int i =startPage;  i <= endPage; i++) {
	%>
	<a href="ReportList.re?pageNum=<%=i%>"><%=i%></a>
	<%
	}
	%>
	
	<% 
	//5페이지 다음
	if (endPage < pageCount) {
	 %>
	<a href="ReportList.re?pageNum=<%=startPage + pageBlock%>">[5페이지 다음]</a>
	<%
	}
	%>

<!-- 내용 끝 -->	


</div>
</div>
<!-- 푸터파일들어가는 곳 -->
<jsp:include page="/inc/footer.jsp"/>
<!-- 푸터파일들어가는 곳 -->