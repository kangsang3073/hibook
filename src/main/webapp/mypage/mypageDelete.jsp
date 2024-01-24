<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 헤더파일들어가는 곳 -->
<jsp:include page="/inc/header.jsp"/>
<!-- 헤더파일들어가는 곳 -->
<link href="/resource/css/mypage.css" rel="stylesheet" type="text/css">	
	
	<div class="boardContainer">

<h1>회원탈퇴</h1>

<div class=content>

<p>
탈퇴를 위해 현재 비밀번호를 확인해 주세요.
<form action="MypageDeleteUserCheckPro.mypage" method="post">
<input type="password" name="mem_pass"><br>
<input type="submit" value="확인" class=button>
</form>
</p>
</div>

</div>
<!-- 푸터파일들어가는 곳 -->
<jsp:include page="/inc/footer.jsp"/>
