<%@page import="java.text.SimpleDateFormat"%>
<%@page import="mypage.member.MemberDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="mypage.member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 헤더파일들어가는 곳 -->
	<jsp:include page="/inc/header.jsp"/>
	<link href="/resource/css/mypage.css" rel="stylesheet" type="text/css">	
	
	<div class="boardContainer">

<h1>회원목록 보기</h1>
<%
SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy.MM.dd");

ArrayList<MemberDTO> memberList=(ArrayList<MemberDTO>)request.getAttribute("memberList"); 
		
int currentPage=(Integer)request.getAttribute("currentPage");
int startPage=(Integer)request.getAttribute("startPage");
int pageBlock=(Integer)request.getAttribute("pageBlock");
int endPage=(Integer)request.getAttribute("endPage");
int pageCount=(Integer)request.getAttribute("pageCount"); 
%>

<div class="tableBar">
<table>
<tr><th>아이디</th><th>이름</th><th>닉네임</th><th>이메일</th>
<th>핸드폰번호</th><th>회원상태</th><th>회원가입일자</th></tr>
<%
	for(int i=0; i<memberList.size(); i++){
		// 배열 한칸에 내용 가져오기 => MemberDTO 저장 => 출력 
		MemberDTO dto = memberList.get(i);
	%>
	<tr><td><%=dto.getMem_id() %></td>
	<td><%=dto.getMem_nm()  %></td>
	<td><%=dto.getNickname() %></td>
	<td><%=dto.getEmail() %></td>
	<td><%=dto.getPhone() %></td>
	<td><%=dto.getMem_st() %></td>
	<td><%=dateFormat.format(dto.getJoin_date()) %></td>
	<tr>
	<%
}
%>
</table>
</div>

<div class=pageNum>
<%
// 1페이지 이전
if(currentPage > 1){
	%>
	<a href="AdminMemberList.adminpage?pageNum=<%=currentPage-1%>">&lt;</a>
	<%
}

//페이지 번호 누르면 해당 페이지로 이동 
for(int i=startPage; i<=endPage; i++) {
	%>
	<a href="AdminMemberList.adminpage?pageNum=<%=i%>"><%=i%></a>
	<% 
}

//1페이지 다음
if(currentPage < pageCount){
	%>
	<a href="AdminMemberList.adminpage?pageNum=<%=currentPage+1%>">&gt;</a>
	<%
}
%>
</div>

</div>
<!-- 푸터파일들어가는 곳 -->
<jsp:include page="/inc/footer.jsp"/>