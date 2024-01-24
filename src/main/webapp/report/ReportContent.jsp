<%@page import="com.itwillbs.report.db.ReportDTO"%>
<%@page import="com.itwillbs.report.db.ReportDAO"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
//  http://localhost:8080/webProject/board/content.jsp?num=2
// request 에 저장된 num 파라미터값 가져오기
// int num=Integer.parseInt(request.getParameter("num"));
// BoardDAO 객체생성
// ReportDAO dao=new ReportDAO();
// 리턴할형 BoardDTO  getBoard(int num) 메서드 정의 
// BoardDTO  dto = dao.getBoard(num) 메서드 호출
// ReportDTO dto=dao.getreport(num);
// 세션값 가져오기

// 리턴할형 BoardDTO getBoard(int num) 메서드 정의
	// BoardDTO dto = dao.getBoard(num) 메서드 호출
	ReportDTO dto = (ReportDTO)request.getAttribute("dto");
	String id = (String) session.getAttribute("id");
%>
	<!-- 헤더파일들어가는 곳 -->
	<jsp:include page="/inc/header.jsp"/>
	<!-- 헤더파일들어가는 곳 -->
	<link href="resource/css/board.css" rel="stylesheet" type="text/css">
	<link href="resource/css/ReportContent.css " rel="stylesheet" type="text/css">
	<div class="boardContainer">
<!-- 내용 시작 -->
<form action="ReportWrite.re" method="post" enctype="multipart/form-data">

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
<h1>글내용📇</h1>
<table border="1">
<tr><td>글번호</td><td><%=dto.getReport_id() %></td></tr>
<tr><td>작성자</td><td><%=dto.getInsert_id() %> </td></tr>
<tr><td>글제목</td><td><%=dto.getTitle() %></td></tr>
<tr><td>글내용</td><td><%=dto.getContent() %></td></tr>
<tr><td>글쓴날짜</td><td><%=dto.getInsert_date() %></td></tr>
<tr><td>첨부파일</td><td><img src="upload/<%=dto.getContent_img1()%>" width="400"></td></tr>

<tr><td colspan="2">
<%
//  로그인 => 세션값 있음 
 if(id != null){ 
 	// 세션값  , 글쓴이  => 일치 => 자기자신 쓴 글(글수정, 글삭제 보이기) -->
 	if(id.equals("admin") || id.equals(dto.getInsert_id())){
 		%>
<input type="button" value="글수정"
 onclick="location.href='ReportUpdateForm.re?num=<%=dto.getReport_id()%>'">
 <input type="button" value="글삭제" onclick="location.href='ReportDeletePro.re?num=<%=dto.getReport_id()%>'">		
		<%
	} 
 } 
%> 


<input type="button" value="사기목록" onclick="location.href='ReportList.re'">
</td></tr>
</table>
</form>
<!-- 내용 끝 -->	



</div>
<!-- 푸터파일들어가는 곳 -->
<jsp:include page="/inc/footer.jsp"/>
<!-- 푸터파일들어가는 곳 -->