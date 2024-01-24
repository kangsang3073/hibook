<%@page import="com.itwillbs.board.db.BoardDAO"%>
<%@page import="com.itwillbs.market.db.MarketDAO"%>
<%@page import="com.itwillbs.market.db.MarketDTO"%>
<%@page import="com.itwillbs.util.ChangeTime"%>
<%@page import="com.itwillbs.board.db.BoardDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
// request 가져온 데이터 담기 

MarketDAO dao = new MarketDAO();
ArrayList<MarketDTO> latestList = dao.getMainMarketLatestList();
ArrayList<MarketDTO> viewList = dao.getMainMarketViewList();

ArrayList<BoardDTO> freeList = (ArrayList<BoardDTO>) request.getAttribute("freeList");
ArrayList<BoardDTO> reviewList = (ArrayList<BoardDTO>) request.getAttribute("reviewList");

//글쓴이를 닉네임으로 불러오기
	BoardDAO dao2 = new BoardDAO();

%>
<!-- 헤더파일들어가는 곳 -->
<jsp:include page="/inc/header.jsp"/>
<link href="/resource/css/board.css" rel="stylesheet" type="text/css">
<link href="/resource/css/front.css" rel="stylesheet" type="text/css">
<!-- 헤더파일들어가는 곳 -->

<section class="bannerBack">
	<div class="banner">
		<div class="bannerText">
			<p id="mainText">Hibook 중고책 서점</p>
			<p id="subText">잠자는 책들을 거래해보세요</p>
	    </div>
	</div>
</section>

<article class="board">
	<div class="boardContainer">
		<div class="board1">
			<p id="boardTag">추천 도서📚</p>
			<p >인기있는 책을 확인하세요!</p>
		 	<div class="cardContainer">
		 	
				<%for(int i = 0; i < viewList.size(); i++) {
		 		MarketDTO dto = viewList.get(i); 
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
		
		<div class="board1">
			<p id="boardTag">최신 도서📚</p>
			<p>관심있던 책을 찾아보세요!</p>
			<div class=cardContainer>
			
				<%for(int i = 0; i < latestList.size(); i++) {
		 		MarketDTO dto = latestList.get(i); 
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
			<p id="boardTag">자유게시판💬</p>
			<div class="tableBar">
				<table>
				<colgroup>
						<col width="80px;">
						<col width="*">
						<col width="150px;">
						<col width="80px;">
						<col width="100px;">
					</colgroup>
				  <thead>
				    <tr>
				      <th>글번호</th>
				      <th>글제목</th>
				      <th>글쓴이</th>
				      <th>조회수</th>
				      <th>등록일</th>
				    </tr>
				  </thead>
				  <tbody>
				    <tr>
				    <%
				    // list반복해서 list에 담긴 boardDTO 뿌려주기
				    for (int i = 0; i < freeList.size(); i++) {
						// 배열 한칸에 내용 가져오기 
						BoardDTO dto = freeList.get(i);
						
						// 시간계산해서 몇초전 몇분전 몇시간전 등 출력하는 함수사용.
						String changeTime = ChangeTime.calculateTime(dto.getInsertDate());
						%>
				      <td class="td2" ><%=dto.getBoardId() %></td>
				      <td>
				      	<a class="aa" href="BoardContent.bo?boardType=<%=dto.getBoardType() %>&boardId=<%=dto.getBoardId()%>">
							<%=dto.getTitle()%>
						</a>
					  </td>
				     <td class="td2"><span id="idSpan"><%=dao2.getNickname(dto.getBoardId()) %></span>
				      <td class="td2"><%=dto.getViewCnt() %></td>
				      <td class="td2"><%=changeTime%></td>
				    </tr>
				   <%} %>
				  </tbody>
				</table>
			</div>
		</div>
		
		<div class="board2">
			<p id="boardTag">책 리뷰💬</p>
			<div class="tableBar">
					<table>
					<colgroup>
						<col width="80px;">
						<col width="*">
						<col width="150px;">
						<col width="80px;">
						<col width="100px;">
					</colgroup>
					<thead>
			
				    <tr>
				 	  <th>글번호</th>
				      <th>글제목</th>
				      <th>글쓴이</th>
				      <th>조회수</th>
				      <th>등록일</th>
				    </tr>
				  </thead>
				  <tbody>
				   <tr>
				    <%
				    // list반복해서 list에 담긴 boardDTO 뿌려주기
				    for (int i = 0; i < reviewList.size(); i++) {
						// 배열 한칸에 내용 가져오기 
						BoardDTO dto = reviewList.get(i);
						
						// 시간계산해서 몇초전 몇분전 몇시간전 등 출력하는 함수사용.
						String changeTime = ChangeTime.calculateTime(dto.getInsertDate());
						%>
				      <td class="td2"><%=dto.getBoardId() %></td>
				      <td>
				      	<div class="skip">
				      	<a class="aa" href="BoardContent.bo?boardType=<%=dto.getBoardType() %>&boardId=<%=dto.getBoardId()%>">
							<%=dto.getTitle()%>
						</a>
						</div>
					  </td>
				      <td class="td2">
				      <span id="idSpan"><%=dao2.getNickname(dto.getBoardId()) %></span>
				      </td>
				      <td class="td2"><%=dto.getViewCnt() %></td>
				      <td class="td2"><%=changeTime%></td>
				    </tr>
				   <%} %>
				    
				  </tbody>
				</table>
			</div>
		</div>
	</div>
</article>



<!-- 푸터파일들어가는 곳 -->
<jsp:include page="/inc/footer.jsp"/>
<!-- 푸터파일들어가는 곳 -->   