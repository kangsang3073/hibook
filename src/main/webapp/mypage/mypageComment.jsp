<%@page import="java.text.SimpleDateFormat"%>
<%@page import="mypage.comment.CommentDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="mypage.comment.CommentDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <!-- 헤더파일들어가는 곳 -->
<jsp:include page="/inc/header.jsp"/>
<!-- 헤더파일들어가는 곳 -->
<link href="/resource/css/mypage.css" rel="stylesheet" type="text/css">	
	
	<div class="boardContainer">

<h1>내가 쓴 댓글</h1>

<%
SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy.MM.dd");

ArrayList<CommentDTO> commentList=(ArrayList<CommentDTO>)request.getAttribute("commentList"); 
		
int currentPage=(Integer)request.getAttribute("currentPage");
int startPage=(Integer)request.getAttribute("startPage");
int pageBlock=(Integer)request.getAttribute("pageBlock");
int endPage=(Integer)request.getAttribute("endPage");
int pageCount=(Integer)request.getAttribute("pageCount");
%>

<!-- 체크박스로 선택해 글 여러개 삭제가능  -->
<form action="MypageMultidelCommentPro.mypage" method="post">
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
<col width="50px;">
<col width="*">
<col width="100px;">
<col width="*">
<col width="110px;">
</colgroup>
<tr><th><input id="allCheck" type="checkbox" onclick="allChk(this);"/></th>
<th>원문</th><th>비밀댓글</th><th>작성댓글</th><th>등록일</th></tr>

<%
	for(int i=0; i<commentList.size(); i++){
		// 배열 한칸에 내용 가져오기 => CommentDTO 저장 => 출력 
		CommentDTO dto = commentList.get(i);
	%>
	<tr>
	<td><input type="checkbox" name="chk" value="<%=dto.getCmmt_id() %>"></td>
	<td><a href="BoardContent.bo?boardType=<%=dto.getBoard_type() %>&boardId=<%=dto.getBoard_id() %>"><%=dto.getTitle() %></td>
	<td><%=dto.getSecret_yn() %></td>
	<td><%=dto.getContent() %></td>
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
	<a href="MypageCommentList.mypage?pageNum=<%=currentPage-1%>">&lt;</a>
	<%
}

//페이지 번호 누르면 해당 페이지로 이동 
for(int i=startPage; i<=endPage; i++) {
	%>
	<a href="MypageCommentList.mypage?pageNum=<%=i%>"><%=i%></a>
	<% 
}

//1페이지 다음
if(currentPage < pageCount){
	%>
	<a href="MypageCommentList.mypage?pageNum=<%=currentPage+1%>">&gt;</a>
	<%
}
%>
</div>

</div>
<!-- 푸터파일들어가는 곳 -->
<jsp:include page="/inc/footer.jsp"/>
