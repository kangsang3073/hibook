<%@page import="com.itwillbs.util.ChangeTime"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.itwillbs.market.db.MarketDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 자바 들어가는 곳 -->
<%
ArrayList<MarketDTO> dtolist = (ArrayList<MarketDTO>)request.getAttribute("dtolist");
String trade_type = (String)request.getAttribute("trade_type");

int currentPage = (Integer)request.getAttribute("currentPage");
int pageBlock = (Integer)request.getAttribute("pageBlock");
int startPage = (Integer)request.getAttribute("startPage");
int endPage= (Integer)request.getAttribute("endPage");
int pageCount = (Integer)request.getAttribute("pageCount");
%>

<!-- 헤더파일들어가는 곳 -->
<jsp:include page="/inc/header.jsp" />
<!-- 헤더파일들어가는 곳 -->
<link href="resource/css/market.css" rel="stylesheet" type="text/css">

<!-- 자바스크립트 들어가는 곳 -->
<script type="text/javascript"></script>

<div class="boardContainer">
<!-- 내용 시작 -->
<article class="board">
	<div class="MarketContainer">
	
		<div class="board1">
			<p id="boardTag">중고책 <%=trade_type %> 게시판📚</p>
			<p>새로운 책들을 확인해보세요!</p>
		 	<div class="cardContainer">
		 	
		 	<%for(int i = 0; i < dtolist.size(); i++) {
		 		MarketDTO dto = dtolist.get(i); 
		 		String changeTime = ChangeTime.calculateTime(dto.getInsert_date());
		 		%>	
				<div class="card" onclick="location.href='MarketContent.ma?market_id=<%=dto.getMarket_id()%>'">
					<div class="innerCard">
						<div class="innerTop">
							<img src="<%=dto.getUrl() %>" class="books">
						</div>
						<div class="innerBottom">
							<div class="cardText">
								<p id="book_price"><%=dto.getBook_price() %> 원</p>
								<p id="title"><span id=""><%=dto.getTrade_type() %></span> &#5; <%=dto.getTitle() %></p>
								<p id="insert_date"><%=changeTime %></p>
							</div>
						</div>
					</div>
				</div>
			<% }%>
			
			</div>
		</div>
		
		<div class="board2">
			<input type="button" value="메인으로" onclick="location.href='main.do'">
			<input type="button" value="거래글 쓰기" onclick="location.href='MarketWriteForm.ma'">
		</div>
		
		<div class="board3">
			<% 
			if(startPage > pageBlock) { %>
				<a href="MarketList.ma?pageNum=<%= startPage-pageBlock%>">🔙</a>
			<% 
			}
			for(int i = startPage; i <= endPage; i++) {	
			%>
				<a href="MarketList.ma?pageNum=<%= i%>"><%= i%> </a> 
			<% 
			}
			if(endPage < pageCount) {
			%>
				<a href="MarketList.ma?pageNum=<%= startPage+pageBlock%>">🔜</a> 
			<%
			}
			%>
		</div>
		
	</div>
</article>

<!-- 내용 끝 -->
</div>

<!-- 푸터 들어가는 곳 -->
<jsp:include page="/inc/footer.jsp" />
<!-- 푸터 들어가는 곳 -->