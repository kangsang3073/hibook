<%@page import="mypage.market.MarketDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 헤더파일들어가는 곳 -->
<jsp:include page="/inc/header.jsp" />
<link href="/resource/css/mypage.css" rel="stylesheet" type="text/css">	

	<div class="boardContainer">

<%
SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy.MM.dd");

ArrayList<MarketDTO> marketList=(ArrayList<MarketDTO>)request.getAttribute("marketList");
		
int currentPage=(Integer)request.getAttribute("currentPage");
int startPage=(Integer)request.getAttribute("startPage");
int pageBlock=(Integer)request.getAttribute("pageBlock");
int endPage=(Integer)request.getAttribute("endPage");
int pageCount=(Integer)request.getAttribute("pageCount");
String nickname = (String)request.getAttribute("nickname");
String insert_id = (String)request.getAttribute("insert_id");
%>

<h1><%=nickname %>님의 게시글</h1>

<div class="tableBar">
<table>
<tr>
<th>글번호</th><th>게시판유형</th><th>사진</th>
<th>글제목</th><th>가격</th><th>조회수</th><th>등록일</th></tr>

<%
	for(int i=0; i<marketList.size(); i++){ 
		// 배열 한칸에 내용 가져오기 => MarketDTO 저장 => 출력 
		MarketDTO dto = marketList.get(i);
	%>
	<tr>
	<td><%=dto.getMarket_id() %></td>
	<td><%=dto.getTrade_type()  %></td>
	<td><img src="<%=dto.getUrl() %>" width="100" height="100"></td>
	<td><a href="MarketContent.ma?market_id=<%=dto.getMarket_id() %>"><%=dto.getTitle() %></a></td>
	<td><%=dto.getBook_price() %></td>
	<td><%=dto.getView_cnt() %></td>
	<td><%=dateFormat.format(dto.getInsert_date()) %></td>
	<tr>
	<%
}
%>
</table>
</div>
</form>

<div class=pageNum>
<%
//1페이지 이전
if(currentPage > 1){
	%>
	<a href="MypageUserMarketList.mypage?insert_id=<%=insert_id %>&nickname=<%=nickname %>&pageNum=<%=currentPage-1%>">&lt;</a>
	<%
}

//페이지 번호 누르면 해당 페이지로 이동 
for(int i=startPage; i<=endPage; i++) {
	%>
	<a href="MypageUserMarketList.mypage?insert_id=<%=insert_id %>&nickname=<%=nickname %>&pageNum=<%=i%>"><%=i%></a>
	<% 
}

//1페이지 다음
if(currentPage < pageCount){
	%>
	<a href="MypageUserMarketList.mypage?insert_id=<%=insert_id %>&nickname=<%=nickname %>&pageNum=<%=currentPage+1%>">&gt;</a>
	<%
}
%>
</div>

</div>
<!-- 푸터파일들어가는 곳 -->
<jsp:include page="/inc/footer.jsp"/>
