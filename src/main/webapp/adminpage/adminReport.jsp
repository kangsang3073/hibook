<%@page import="java.text.SimpleDateFormat"%>
<%@page import="mypage.report.ReportDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="mypage.report.ReportDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 헤더파일들어가는 곳 -->
	<jsp:include page="/inc/header.jsp"/>
	<link href="/resource/css/mypage.css" rel="stylesheet" type="text/css">	
	
	<div class="boardContainer">

<h1>1:1 신고 접수</h1>

<div class=content>
정지할 회원의 아이디를 입력해 주세요.
<form action="AdminReportPro.adminpage" method="post">
<input type="text" name="mem_id"><br>
<input type="submit" value="정지" class=button>
</form>
</div>

<%
SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy.MM.dd");

ArrayList<ReportDTO> reportList=(ArrayList<ReportDTO>)request.getAttribute("reportList"); 
		
int currentPage=(Integer)request.getAttribute("currentPage");
int startPage=(Integer)request.getAttribute("startPage");
int pageBlock=(Integer)request.getAttribute("pageBlock");
int endPage=(Integer)request.getAttribute("endPage");
int pageCount=(Integer)request.getAttribute("pageCount"); 
%>

<!-- 체크박스로 선택해 글 여러개 삭제가능  -->
<form action="AdminMultidelReportPro.adminpage" method="post">
<input type="submit" value="삭제" class=button>

<!-- 체크박스 모두 선택하는 함수  -->
<script type="text/javascript">
function allChk(obj){
    var chkObj = document.getElementsByName("chk");
    var rowCnt = chkObj.length - 1;
    var check = obj.checked;
    if (check) {﻿
        for (var i=0; i<=rowCnt; i++){
         if(chkObj[i].type == "checkbox")
             chkObj[i].checked = true;
        }
    } else {
        for (var i=0; i<=rowCnt; i++) {
         if(chkObj[i].type == "checkbox"){
             chkObj[i].checked = false;
         }
        }
    }
} 
</script> 

<div class="tableBar">
<table>
<colgroup>
<col width="80px;">
<col width="100px;">
<col width="*">
<col width="140">
<col width="150">
</colgroup>
<tr><th><input id="allCheck" type="checkbox" onclick="allChk(this);"/></th>
<th>글번호</th>
<th>글제목</th><th>글쓴이</th><th>등록일</th></tr>

<%
	for(int i=0; i<reportList.size(); i++){
		// 배열 한칸에 내용 가져오기 => ReportDTO 저장 => 출력 
		ReportDTO dto = reportList.get(i);
	%>
	<tr>
	<td><input type="checkbox" name="chk" value="<%=dto.getReport_id() %>"></td>
	<td><%=dto.getReport_id() %></td>
	<td><a href="ReportContent.re?id=<%=dto.getReport_id() %>"><%=dto.getTitle() %></a></td>
	<td><%=dto.getInsert_id() %></td>
	<td><%=dateFormat.format(dto.getInsert_date()) %></td><tr>
	<%
}
%>
</table>
</div>
</form>

<div class=pageNum>
<%
//1페이지 이전
if(currentPage > 1){
	%>
	<a href="AdminReportList.adminpage?pageNum=<%=currentPage-1%>">&lt;</a>
	<%
}

//페이지 번호 누르면 해당 페이지로 이동 
for(int i=startPage; i<=endPage; i++) {
	%>
	<a href="AdminReportList.adminpage?pageNum=<%=i%>"><%=i%></a>
	<% 
}

//1페이지 다음
if(currentPage < pageCount){
	%>
	<a href="AdminReportList.adminpage?pageNum=<%=currentPage+1%>">&gt;</a>
	<%
}
%>
</div>

</div>
<!-- 푸터파일들어가는 곳 -->
<jsp:include page="/inc/footer.jsp"/>