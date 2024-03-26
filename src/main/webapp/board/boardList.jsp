<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.itwillbs.util.ChangeTime"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.itwillbs.board.db.BoardDTO"%>
<%@page import="com.itwillbs.board.db.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%
	// request 가져온 데이터 담기
	String boardTypeCd = (String) request.getAttribute("boardTypeCd");
	String boardTypeCdNm = (String) request.getAttribute("boardTypeCdNm");
	// 검색
	String keyword = request.getParameter("keyword")==null ? "" : request.getParameter("keyword");
	ArrayList<BoardDTO> BoardList = (ArrayList<BoardDTO>) request.getAttribute("boardList");
	ArrayList<BoardDTO> NoticeList = (ArrayList<BoardDTO>) request.getAttribute("noticeList");
	
	int currentPage = (Integer) request.getAttribute("currentPage");
	int startPage = (Integer) request.getAttribute("startPage");
	int pageBlock = (Integer) request.getAttribute("pageBlock");
	int endPage = (Integer) request.getAttribute("endPage");
	int pageCount = (Integer) request.getAttribute("pageCount");
	int count = (Integer) request.getAttribute("count");
	int pageSize = (Integer) request.getAttribute("pageSize");
	
	String id = (String) session.getAttribute("id");
	String adminYn = (String)session.getAttribute("adminYn"); 
	BoardDTO dto2 = (BoardDTO)request.getAttribute("dto2");
	
	// 글쓴이를 닉네임으로 불러오기
	 	BoardDAO dao = new BoardDAO();
	%>

<!-- 헤더파일들어가는 곳 -->
<jsp:include page="/inc/header.jsp"/>
<!-- 헤더파일들어가는 곳 -->
<link href="resource/css/board.css" rel="stylesheet" type="text/css">
<link href="resource/css/market.css" rel="stylesheet" type="text/css">
<div class="boardContainer">

<!-- 내용 시작 -->
<!-- 스크립트 파일 들어가는곳 -->
<script type="text/javascript">

</script>
<!-- 스크립트 끝. -->
<div>
<p class="boardTag"><%=boardTypeCdNm %> 💬</p>

<form acion="BoardList.bo" method="get">
	<div class="board_search">
		<input type="text" id="keyword" name="keyword" value="<%=keyword %>" placeholder="검색어를 입력하세요">
		<input type="hidden" name="boardType" value="<%=boardTypeCd %>">
		<input type="submit" id="scrhBtn" value="검색">
	</div>
</form>	
	<div class="tableBar">
	<table class="board_list">
		<colgroup>
			<col width="60px;">
			<col width="*">
			<col width="140px;">
			<col width="120px;">
			<col width="60px;">
		</colgroup>
		
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>글쓴이</th>
				<th>작성일</th>
				<th>조회</th>
			</tr>
		</thead>
		
		<tbody>
			<%
			// 공지사항 for
			for (int i = 0; i < NoticeList.size(); i++) {
				// 배열 한칸에 내용 가져오기 
				BoardDTO dto = NoticeList.get(i);
				
				// 시간계산해서 몇초전 몇분전 몇시간전 등 출력하는 함수사용.
				String changeTime = ChangeTime.calculateTime(dto.getInsertDate());
			%>
	
			<tr>
				<td class="back2">공지</td>
				<td class="tl back2">
					<a href="BoardContent.bo?boardType=<%=boardTypeCd %>&boardId=<%=dto.getBoardId()%>">
						<%=dto.getTitle()%>
					</a>
				</td>
				<td class="back2">
				<span id="idSpan"><%=dao.getNickname(dto.getBoardId()) %></span>
					<div id="miniMenu" style="display: none;">
						<div id="mmenu" onclick="location.href='main.do'">menu1</div>	
							<div id="mmenu" onclick="location.href='main.do'">menu2</div>
							<div id="mmenu" onclick="location.href='main.do'">menu3</div>
							<div id="mmenu" onclick="location.href='main.do'">menu4</div>
					</div>
				      	
				</td>
				<td class="back2"><% SimpleDateFormat dateformat = new SimpleDateFormat("yyyy년 MM월 dd일 HH:mm:ss");%><%=changeTime%></td>
				<td class="back2"><%=dto.getViewCnt()%></td>
			</tr>
	
			<%
			} // 공지사항 for 끝
			%>
			
			<%
			// 일반글 for
			for (int i = 0; i < BoardList.size(); i++) {
				// 배열 한칸에 내용 가져오기 
				BoardDTO dto = BoardList.get(i);
				int num = (count - (currentPage -1) * pageSize) - i; 
				
				// 시간계산해서 몇초전 몇분전 몇시간전 등 출력하는 함수사용.
				String changeTime = ChangeTime.calculateTime(dto.getInsertDate());
			%>
	
			<tr>
				<td><%=num%></td>
				<td class="tl">
					<a href="BoardContent.bo?boardType=<%=boardTypeCd %>&boardId=<%=dto.getBoardId()%>">
						<%if(dto.getParentId() != 0){%> &nbsp;&nbsp;ㄴ<%}%>
						<%=dto.getTitle()%>
						<% 
						  if(dto.getCmmtCount() != 0){ %> 
						  <span class= "cmmtnew cmmtbackground">(<%=dto.getCmmtCount()%>)</span>
						  <%}%>
						
						
						
						
						
					</a>
				</td>
				<td>
				<span id="idSpan"><%=dao.getNickname(dto.getBoardId()) %></span>
					<div id="miniMenu" style="display: none;">
						<div id="mmenu" onclick="location.href='main.do'">menu1</div>	
							<div id="mmenu" onclick="location.href='main.do'">menu2</div>
							<div id="mmenu" onclick="location.href='main.do'">menu3</div>
							<div id="mmenu" onclick="location.href='main.do'">menu4</div>
					</div>
				</td>
				<td><%=changeTime%></td>
				<td><%=dto.getViewCnt()%></td>
			</tr>
	
			<%
			} // 일반글 for
			%>
		</tbody>
	</table>
	</div>
		<%
		if ("notice".equals(boardTypeCd)) {
			
			if ("Y".equals(adminYn)) {%>
			
				<input type="button" class="button buttonBlueGray" value="글쓰기"
					   onclick="location.href='BoardWriteForm.bo?boardType=<%=boardTypeCd%>'">
		
			<% }}
			else { %>
				<input type="button" class="button buttonBlueGray" value="글쓰기"
					   onclick="location.href='BoardWriteForm.bo?boardType=<%=boardTypeCd%>'">
		<%}%>

		<div class="ssgap page">	
	
	<%
			// 10페이지 이전
			if (startPage > pageBlock) {
			%>
	<a href="BoardList.bo?boardType=<%=boardTypeCd %>&keyword=<%=keyword%>&pageNum=<%=startPage - pageBlock%>">&lt;&lt;</a>
	<%
	}
	
	for (int i = startPage; i <= endPage; i++) {
	%>
	<a href="BoardList.bo?boardType=<%=boardTypeCd %>&keyword=<%=keyword%>&pageNum=<%=i%>" <%if(currentPage == i){%> class="active" <%}%>><%=i%></a>
	<%
	}
	// 10페이지 다음
	if (endPage < pageCount) {
		%>
	<a href="BoardList.bo?boardType=<%=boardTypeCd %>&keyword=<%=keyword%>&pageNum=<%=startPage + pageBlock%>">&gt;&gt;</a>
	<%
	}%>
	
	
	</div>
</div>
<!-- 내용 끝 -->	
</div>
<!-- 푸터파일들어가는 곳 -->
<jsp:include page="/inc/footer.jsp"/>
<!-- 푸터파일들어가는 곳 -->