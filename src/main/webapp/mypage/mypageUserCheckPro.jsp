<%@page import="mypage.member.MemberDTO"%>
<%@page import="mypage.member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- 헤더파일들어가는 곳 -->
<jsp:include page="/inc/header.jsp" />	
<link href="/resource/css/mypage.css" rel="stylesheet" type="text/css">	

	
	<div class="boardContainer">

<h1>회원탈퇴</h1>

<%
request.setCharacterEncoding("utf-8");
String id = (String)session.getAttribute("id");
String pass = request.getParameter("mem_pass");

// 유저 비밀번호 맞는지 체크하는 메서드 
MemberDAO dao = new MemberDAO();
MemberDTO dto = dao.userCheck(id, pass);
if(dto != null) {
	// 비밀번호 일치
	%>
	
<div class=content>

<p>
하이북에서 정말 탈퇴하시겠습니까?
<form action="MypageDeletePro.mypage" method="post">
<input type="submit" value="탈퇴" class=button>
<a href="MypageMain.mypage"><input type="button" value="취소" class=button></a>
</form>	
</p>

</div>
	<%
}else {
	// 비밀번호 틀림
	%>
	<script type="text/javascript">
 	 	alert("비밀번호가 틀렸습니다.");
 	 	history.back();
	</script>
	<%
 }
%>

</div>
<!-- 푸터파일들어가는 곳 -->
<jsp:include page="/inc/footer.jsp"/>
