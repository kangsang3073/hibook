<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- 헤더파일들어가는 곳 -->
<jsp:include page="/inc/header.jsp"/>
<!-- 헤더파일들어가는 곳 -->

<link href="/resource/css/member.css" rel="stylesheet" type="text/css">

	<div class="boardContainer">
	<div>
		<h2 style="text-align: center;">Login</h2>
		<br>
		<br>
		<br>
		<br>
		<br>
		<form  action="MemberLoginPro.me" method="post" >
			<div style="text-align: center;">
				<label>아이디: <input type="text"  placeholder="Enter id" name="id"></label>
				<br>
				<label>비밀번호:<input type="password"  placeholder="Enter password"	name="pass"></label> 
				<br>
			</div>

<!-- 			<div style="text-align: center;"> -->
<!-- 				<label><input type="checkbox" name="remember"> -->
<!-- 					Remember me</label> -->
<!-- 			</div> -->
			<br>
			<div style="text-align: center;">
			<label>	<button type="submit" value="Submit">Submit</button></label>
			</div>

			<!-- <button type="text" value="text" class="btn btn-default" href="/findIdForm.me">아이디찾기</button>  -->


			<!-- <div> -->
			<!-- <a href="javascript:kakaoLogin();"><img src="https://www.gb.go.kr/Main/Images/ko/member/certi_kakao_login.png" alt="카카오계정 로그인" style="height: 40px;"/></a> -->

			<!--     <script src="https://developers.kakao.com/sdk/js/kakao.js"></script> -->
			<!--     <script type='text/javascript'> -->
			<!-- // Kakao.init('721340b02834b32872e15949f680030d'); -->
			<!-- // console.log(Kakao.isInitialized()); -->

			<!-- // function kakaoLogin() { -->
			<!-- //     Kakao.Auth.login({ -->
			<!-- //       success: function (response) { -->
			<!-- //         Kakao.API.request({ -->
			<!-- //           url: '/v2/user/me', -->
			<!-- //           success: function (response) { -->
			<!-- //         	  console.log(response) -->
			<!-- //           }, -->
			<!-- //           fail: function (error) { -->
			<!-- //             console.log(error) -->
			<!-- //           }, -->
			<!-- //         }) -->
			<!-- //       }, -->
			<!-- //       fail: function (error) { -->
			<!-- //         console.log(error) -->
			<!-- //       }, -->
			<!-- //     }) -->
			<!-- //   } -->


			<!-- </script> -->


			<!--      <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script> -->
			<!--      <script src="js/bootstrap.min.js"></script> -->
		</form>
	
	</div>
	</div>

<!-- 푸터파일들어가는 곳 -->
<jsp:include page="/inc/footer.jsp"/>
<!-- 푸터파일들어가는 곳 -->