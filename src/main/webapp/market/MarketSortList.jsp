<%@page import="com.itwillbs.util.ChangeTime"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.itwillbs.market.db.MarketDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 자바 들어가는 곳 -->
<%
ArrayList<MarketDTO> dtolist = (ArrayList<MarketDTO>)request.getAttribute("dtolist");
String trade_type = (String)request.getAttribute("trade_type");
String arr = (String)request.getAttribute("arr");

int currentPage = (Integer)request.getAttribute("currentPage");
int pageBlock = (Integer)request.getAttribute("pageBlock");
int startPage = (Integer)request.getAttribute("startPage");
int endPage= (Integer)request.getAttribute("endPage");
int pageCount = (Integer)request.getAttribute("pageCount");
%>

<!-- 헤더파일들어가는 곳 -->
<jsp:include page="/inc/header.jsp" />
<!-- 헤더파일들어가는 곳 -->
<link href="resource/css/marketList.css" rel="stylesheet" type="text/css">

<!-- 자바스크립트 들어가는 곳 -->
<script type="text/javascript">
</script>

<div class="boardContainer">
<!-- 내용 시작 -->
<article class="board">
	<div class="MarketContainer">
	
		<div class="board1">
			<div class="boardText">
				<p class="boardTag">중고책 
				<%if(trade_type.equals("")) { %>
				종합<% } else { %>
				<%=trade_type %><% }%> 게시판📚</p>
			</div>
			
			<div class="boardArr">
				<div class="boardList" onclick="location.href='MarketSortList.ma?trade_type=<%=trade_type%>&arr=Latest'">최신순</div> 
				<div class="boardList" onclick="location.href='MarketSortList.ma?trade_type=<%=trade_type%>&arr=View'">조회순</div> 
				<div class="boardList" onclick="location.href='MarketSortList.ma?trade_type=<%=trade_type%>&arr=Att'">인기순</div>
			</div>
			
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
								<p class="book_price"><%=dto.getBook_price() %> 원</p>
								<div class="book_title">
								<p><span class="trade_type"><%=dto.getTrade_type() %></span>&nbsp;&nbsp;<%=dto.getTitle() %></p>
								</div>
								<p class="insert_date"><%=changeTime %>&nbsp;&nbsp;<span class="view_cnt">조회수 <%=dto.getView_cnt() %> </span></p>
							</div>
						</div>
					</div>
				</div>
			<% }%>
			
			</div>
			
		</div>
		
		<div class="board2">
			<input type="button" value="메인으로" onclick="location.href='main.do'">
			<input type="button" value="거래글 쓰기" onclick="location.href='MarketWriteForm.ma?trade_type=<%=trade_type%>'">
		</div>
		
		<div class="board3">
			<% 
			if(startPage > pageBlock) { %>
				<a href="MarketSortList.ma?pageNum=<%= startPage-pageBlock%>&arr=<%=arr%>">🔙</a>
			<% 
			}
			for(int i = startPage; i <= endPage; i++) {	
			%>
				<a href="MarketSortList.ma?pageNum=<%= i%>&arr=<%=arr%>"><span class="mpageNum"><%= i%>&nbsp;</span> </a> 
			<% 
			}
			if(endPage < pageCount) {
			%>
				<a href="MarketSortList.ma?pageNum=<%= startPage+pageBlock%>&arr=<%=arr%>">🔜</a> 
			<%
			}
			%>
		</div>
		
	</div>
</article>
	<form>
		<input type="hidden" value="none" name="trade_type">
	</form>
	
<!-- 내용 끝 -->
</div>

<!-- 푸터 들어가는 곳 -->
<jsp:include page="/inc/footer.jsp" />
<!-- 푸터 들어가는 곳 -->