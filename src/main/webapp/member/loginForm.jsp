<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- 헤더파일들어가는 곳 -->
<jsp:include page="/inc/header.jsp"/>
<!-- 헤더파일들어가는 곳 -->
<link href="resource/css/member.css" rel="stylesheet" type="text/css">

	<div class= "loginContainer" >
	<!-- 내용 시작 -->
		<h2 class="h2">로그인</h2>
		<form  action="MemberLoginPro.me" method="post">
				
			    <input type="text"  placeholder="아이디" name="id" class="text-field" minlength="3" maxlength="12">
				<input type="password"  placeholder="비밀번호"	name="pass" class="text-field" minlength="3" maxlength="12"> 
				<input type="submit"  value="로그인" class="submit-btn">
				
			</form>
	</div>
	
	
	<jsp:include page="/inc/footer.jsp"/>
	



	

