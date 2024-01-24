<%@page import="mypage.member.MemberDTO"%>
<%@page import="mypage.member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 헤더파일들어가는 곳 -->
<jsp:include page="/inc/header.jsp" />
<link href="/resource/css/mypage.css" rel="stylesheet" type="text/css">	
	
	<div class="boardContainer">

<h1>프로필사진 및 닉네임 수정</h1>

<%
MemberDTO dto=(MemberDTO)request.getAttribute("dto");
%>

<div class=content>
<form id="fr" action="MypageImgUpdatePro.mypage" method="post" enctype="multipart/form-data">

<p>
		<%if(dto.getMem_img().equals("url")) { %>
<!-- 		기본 프로필사진 뿌려줌  -->
		<img src="resource/image/image.png" width="100" height="100"><%
		}else { %>
<!-- 		프로필사진, 닉네임 뿌려줌  -->
		<img src="upload/<%=dto.getMem_img() %>" width="100" height="100">
		<%
		}%>
<ul>

<li>
<label>프로필사진</label> 
<input type="file" name="mem_img">
<input type="hidden" name="oldfile" value="<%=dto.getMem_img()%>"><br>
</li>

<li>
<label>닉네임</label> 
<input type="text" name="nickname" value="<%=dto.getNickname()%>"><br>
</li>
</ul>
</p>

<input type="submit" value="수정" class=button>
</form>
</div>

</div>
<!-- 푸터파일들어가는 곳 -->
<jsp:include page="/inc/footer.jsp"/>
