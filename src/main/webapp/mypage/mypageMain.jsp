<%@page import="mypage.member.MemberDTO"%>
<%@page import="mypage.member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 헤더파일들어가는 곳 -->
<jsp:include page="/inc/header.jsp" />
<link href="/resource/css/mypage.css" rel="stylesheet" type="text/css">	

	<div class="boardContainer">

<%
//로그인 id 세션 받아오기 
String id = (String)session.getAttribute("id");

//관리자만 공지사항 쓸 수있음 
String adminYn = (String)session.getAttribute("adminYn");

//세션값이 없으면 => 로그인페이지 
if(id == null){
	response.sendRedirect("MemberLoginForm.me");
}

//일반유저 마이페이지- 프로필사진, 닉네임 보여줘야함 
//MemberDAO 객체생성 
MemberDAO dao = new MemberDAO();
//메서드 호출 
MemberDTO dto = dao.getMemberImg(id);

// 로그인 했을때 
if(id != null){
	// 관리자id일때 관리자페이지, 일반유저 마이페이지  
	if("Y".equals(adminYn)){
		%>
		<h1>관리자 페이지</h1>
		<div class=menu1><a href="AdminMemberList.adminpage">회원목록 보기</a></div>
		<div class=menu2><a href="AdminMarketList.adminpage">게시글 관리</a></div>
		<div class=menu3><a href="AdminCommentList.adminpage">댓글 관리</a></div>
<!-- 		공지사항 글목록으로  -->
		<div class=menu4><a href="BoardList.bo?boardType=notice">공지사항 관리</a></div>
<!-- 		사이트소개 페이지로  -->
		<div class=menu5><a href="introduce.jsp">사이트 소개 관리</a></div>
		<div class=menu6><a href="AdminReportList.adminpage">1:1 신고 접수</a></div>
		<br>
		<%
	}else {
		if(dto.getMem_img().equals("url")) { %>
<!-- 		기본 프로필사진 뿌려줌  -->
		<img src="resource/image/image.png" width="100" height="100">
		<%=dto.getNickname()%>&nbsp;
		<a href="MypageImgUpdateForm.mypage"><input type="button" class=button2 value="프로필사진 및 닉네임 수정"></a><br>
		<div class=menu1><a href="profile.pr?nickname=<%=dto.getNickname()%>">내 프로필</a></div>		
		<div class=menu2><a href="MypageUpdateForm.mypage">회원정보 수정</a></div>
		<div class=menu3><a href="MypageMarketList.mypage">내가 쓴 글</a></div>
		<div class=menu4><a href="MypageCommentList.mypage">내가 쓴 댓글</a></div>
		<div class=menu5><a href="MypageWishList.mypage">찜목록</a></div>
		<div class=menu6><a href="MypageDelete.mypage">회원탈퇴</a></div> <%
		}else { %>
		<h1>마이페이지</h1>
<!-- 		프로필사진, 닉네임 뿌려줌  -->
		<img src="upload/<%=dto.getMem_img() %>" width="100" height="100">
		<%=dto.getNickname()%>&nbsp;
		<a href="MypageImgUpdateForm.mypage"><input type="button" class=button2 value="프로필사진 및 닉네임 수정"></a><br>
		<div class=menu1><a href="profile.pr?nickname=<%=dto.getNickname()%>">내 프로필</a></div>			
		<div class=menu2><a href="MypageUpdateForm.mypage">회원정보 수정</a></div>
		<div class=menu3><a href="MypageMarketList.mypage">내가 쓴 글</a></div>
		<div class=menu4><a href="MypageCommentList.mypage">내가 쓴 댓글</a></div>
		<div class=menu5><a href="MypageWishList.mypage">찜목록</a></div>
		<div class=menu6><a href="MypageDelete.mypage">회원탈퇴</a></div>
		<%
		}
	}
}
%>

</div>
<!-- 푸터파일들어가는 곳 -->
<jsp:include page="/inc/footer.jsp"/>
