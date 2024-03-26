<%@page import="com.itwillbs.review.db.StarReviewDTO"%>
<%@page import="com.itwillbs.review.db.StarReviewDAO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
// 로그인 ID
//String sid = request.getParameter("id");
String id = (String) session.getAttribute("id");

// 타겟 ID
String tgt_id = (String) request.getAttribute("tgt_id");

// 마이페이지 리뷰가 없는 회원의 NICKNMAE
String nickname = "";
if (request.getParameter("nickname") != null) {
	nickname = request.getParameter("nickname");
}

StarReviewDAO sDao = new StarReviewDAO();
StarReviewDTO dto = sDao.getMemberImg(id);;
StarReviewDTO sdto = sDao.getMemberImg(tgt_id);

// 페이징 처리 
ArrayList<StarReviewDTO> boardList = (ArrayList<StarReviewDTO>) request.getAttribute("boardList");

SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
int currentPage = (Integer) request.getAttribute("currentPage");
int startPage = (Integer) request.getAttribute("startPage");
int pageBlock = (Integer) request.getAttribute("pageBlock");
int endPage = (Integer) request.getAttribute("endPage");
int pageCount = (Integer) request.getAttribute("pageCount");

StarReviewDTO sDto = sDao.ReviewStar(tgt_id);
%>
<link href="resource/css/profile.css" rel="stylesheet" />
</head>
<body>
	<div class="reviewcontainer">
		<div class="main-box">
			<div class="image-container">
				<div class="image-box">
					<div class="profile-chat">
						<%
						if (sdto.getMemImg() == null || sdto.getMemImg().equals("")) {
						%>
						<img src="resource/image/image.png" width="100" height="100">
						<%
						} else {
						%>
						<img src="upload/<%=sdto.getMemImg()%>" width="100" height="100">
						<%
						}
						%>
						<h4 class="profileInfo">
							아이디 :
							<%
						if (sDto.getMemId() == null) {
						%>
							<%=tgt_id%>
							<%
							} else {
							%>
							<%=sDto.getMemId()%>
							<%
							}
							%>
						</h4>
						<h4 class="profileInfo">
							닉네임 :
							<%
						if (sDto.getNickname() == null && !(nickname.equals(""))) {
						%>
							<%=nickname%>
							<%
							} else {
							%>
							<%=sdto.getNickname()%>
							<%
							}
							%>
						</h4>
						<h4 class="profileInfo">
							⭐ :
							<%=Double.toString(sDto.getScore()).substring(0, 3)%>
						</h4>
						<input type="button" class="probtn" onclick="maketlist()"
							value="중고거래 게시글">
						<P>
			
						<%-- 							onclick="location.href='Chat.hi?to_id=<%=dto.getTgt_id()%>'">1:1채팅</button> --%>
						<%if(id != tgt_id) { %>
						<button value="1:1채팅" class="probtn" onclick="openchat();">1:1채팅</button>
						<%
							}
						%> 
						
					</div>
					<!-- 					<hr class="board-box"> -->
				</div>
			</div>
			<div class="reviewbox">
				<hr class="pr_hr1">
				<%
				//배열 접근 => for => //배열 한칸에 내용 가져오기 => BoardDTO 저장 => 출력
				for (int i = 0; i < boardList.size(); i++) {
					StarReviewDTO Sdto = boardList.get(i);
				%>
				<div class="reviewbox2">
					<table class="reviewTable">
						<tr class="re_tr">
							<td class="re_td1">⭐별점</td>
							<td class="re_td2">작성자</td>
							<td class="re_td3">리뷰내용</td>
						</tr>
						<tr class="re_tr1">
							<td class="re_td4"><%=Sdto.getScore()%></td>
							<td class="re_td5"><%=Sdto.getNickname()%></td>
							<td class="re_td6"><%=Sdto.getReview_content()%></td>
						</tr>
					</table>
				</div>
				<div class="page1">
					<%
					}
					%>
					<%
					if (currentPage > 1) {
					%>
					<a
						href="profile.pr?insert_id=<%=tgt_id%>&&pageNum=<%=currentPage - 1%>">🔙</a>
					<%
					}
					%>
					<%
					for (int i = startPage; i <= endPage; i++) {
					%>
					<a href="profile.pr?insert_id=<%=tgt_id%>&&pageNum=<%=i%>"><%=i%></a>
					<%
					}
					%>
					<%
					if (currentPage < pageCount) {
					%>
					<a
						href="profile.pr?insert_id=<%=tgt_id%>&&pageNum=<%=currentPage + 1%>">🔜</a>
					<%
					}
					%>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			var urlParams = new URL(location.href).searchParams;
			var id = urlParams.get('insert_id');
			console.log(id);
			document.getElementById("tgt_id").value = id;

			function maketlist() {
				//팝업창에서 부모창을 다른페이지로 이동합니다.
				window.opener.location.href = "MypageUserMarketList.mypage?insert_id=<%=tgt_id%>&nickname=<%=nickname%>";
			}
			function boardlist() {
				//팝업창에서 부모창을 다른페이지로 이동합니다.
				window.opener.location.href = "http://localhost:8080/MypageBoardList.mypage?insert_id=<%=tgt_id%>";
			}
			function openchat() {
				window.open("<%=request.getContextPath()%>/Chat.hi?to_id=<%=tgt_id%>", "a", "width=500, height=700"); 
				<%-- 채팅 버튼 open 함수 --%>
			}
		</script>
</body>
</html>