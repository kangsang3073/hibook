<%@page import="java.text.SimpleDateFormat"%>
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
	<link href="resource/css/ReportContent.css " rel="stylesheet" type="text/css">
	
		<h3 class="call">고객센터</h3>
				<br><br>
				<div class="left">
					<br> <br>
					<div>
						<div>
							<a href="ReportForm.re"> 신고하기 </a><br> <br> <a
								href="ReportList.re">신고목록</a><br> <br> <a
								href="List.re">자주하는 질문</a>

						</div>
					</div>
				</div>
	
	<div class="boardContainer">
<!-- 내용 시작 -->
<form action="ReportWrite.re" method="post" enctype="multipart/form-data">


<h1 class="T">글내용📇</h1>
<table  class="te" border="1">
<tr><td class="t">글번호</td><td><%=dto.getReport_id() %></td></tr>
<tr><td class="t">작성자</td><td><%=dto.getInsert_id() %> </td></tr>
<tr><td class="t">글제목</td><td><%=dto.getTitle() %></td></tr>
<tr><td class="t">글내용</td><td><%=dto.getContent() %></td></tr>
<tr><td class="t">글쓴날짜</td><td><%SimpleDateFormat dateformat = new SimpleDateFormat("yyyy년 MM월 dd일 HH:mm:ss");%>
         <%=dateformat.format(dto.getInsert_date()) %></td></tr>
<tr><td class="t">첨부파일</td><td><img src="upload/<%=dto.getContent_img1()%>" width="600"></td></tr>
</table>

<div>
<input type="button"  class="smallButtonComment" value="사기목록" onclick="location.href='ReportList.re'">
<%
//  로그인 => 세션값 있음 
 if(id != null){ 
 	// 세션값  , 글쓴이  => 일치 => 자기자신 쓴 글(글수정, 글삭제 보이기) -->
 	if(id.equals("admin") || id.equals(dto.getInsert_id())){
 		%>
 <input type="button" class="smallButtonComment" value="글삭제" onclick="location.href='ReportDeletePro.re?num=<%=dto.getReport_id()%>'">
 <input type="button" class="smallButtonComment" value="글수정"
 onclick="location.href='ReportUpdateForm.re?num=<%=dto.getReport_id()%>'">		
		<%
	} 
 } 
%> 



</div>
</form>
<!-- 내용 끝 -->	



</div>
<!-- 푸터파일들어가는 곳 -->
<jsp:include page="/inc/footer.jsp"/>
<!-- 푸터파일들어가는 곳 -->