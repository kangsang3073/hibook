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
	%>
	<!-- 헤더파일들어가는 곳 -->
	<jsp:include page="/inc/header.jsp"/>
	<!-- 헤더파일들어가는 곳 -->
	<link href="resource/css/board.css" rel="stylesheet" type="text/css">
	<link href="resource/css/ReportList.css " rel="stylesheet" type="text/css">




<div class="boardContainer">
<!-- 내용 시작 -->
<div class=Left>
	<h3>고객센터</h3>
	<br>
		
		<div>
			<div>
				<a href="ReportForm.re"> 신고하기 </a><br>
				<br>
				<a href="ReportList.re">신고목록</a><br>
				<br>	
				<a href="List.re">자주하는 질문</a>					
		</div>
	</div>
	</div>

	<h1>📮신고접수 현황📮</h1>
	<table border="1">
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
		
		<% 
			//  로그인 => 세션값 있음 
 if(id != null){ 
 	// 세션값  , 글쓴이  => 일치 => 자기자신 쓴 글(글수정, 글삭제 보이기) -->
 	if(id.equals("admin") || id.equals(dto.getInsert_id())){
 		%><tr>
			<td><%=dto.getReport_id()%></td>
			<td><%=dto.getInsert_id() %></td>
			<td><a href="ReportContent.re?id=<%=dto.getReport_id()%>"><%=dto.getTitle()%>
			</td>
			<td><%=dto.getInsert_date()%></td>
		<%
 	}
	} 
 } 
%> 

	</table>
<div class="page">
	<%

	if (currentPage > 1) {
	%>
		<a href="BoardList.bo?pageNum=<%=currentPage-1%>">[1페이지 이전]</a>
	<%
	}

	// 10페이지 이전
	if (startPage > pageBlock) {
	%>
	<a href="ReportList.re?pageNum=<%=startPage - pageBlock%>">[10페이지 이전]</a>
	<%
	}

	for (int i =startPage;  i <= endPage; i++) {
	%>
	<a href="ReportList.re?pageNum=<%=i%>"><%=i%></a>
	<%
	}

	//1페이지 다음
	if (currentPage < pageCount) {
	%>
		<a href="BoardList.bo?pageNum=<%=currentPage+1%>">[1페이지 다음]</a>
	<%
	}
	%>
	<% 
	//10페이지 다음
	if (endPage < pageCount) {
	 %>
	<a href="ReportList.re?pageNum=<%=startPage + pageBlock%>">[10페이지 다음]</a>
	<%
	}
	
	%>

<!-- 내용 끝 -->	


</div>
</div>
<!-- 푸터파일들어가는 곳 -->
<jsp:include page="/inc/footer.jsp"/>
<!-- 푸터파일들어가는 곳 -->