<%@page import="com.itwillbs.market.db.MarketDAO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.itwillbs.wish.WishDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.itwillbs.wish.WishDAO"%>
<%@page import="com.itwillbs.util.ComCdDTO"%>
<%@page import="com.itwillbs.market.db.MarketDTO"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 자바 변수들어가는 곳 -->
<%
String id = (String)session.getAttribute("id");

MarketDTO dto = (MarketDTO)request.getAttribute("dto");
ComCdDTO cdto = new ComCdDTO();
WishDAO dao = new WishDAO();
MarketDAO dao2 = new MarketDAO();

int length = 0;
for(int i = 0; i < dto.getImgUrls().length; i++) {
	if(!(dto.getImgUrls()[i].equals("url"))) {length++;}
}
%>

<!-- 헤더파일들어가는 곳 -->
<jsp:include page="/inc/header.jsp" />
<!-- 헤더파일들어가는 곳 -->
<link href="resource/css/market.css" rel="stylesheet" type="text/css">

<!-- 자바스크립트 들어가는 곳 -->
<script type="text/javascript">

$(document).ready(function(){ // j쿼리 시작
	//처음으로 가져온 찜개수 저장
	var count = <%= dao.getMarketWishCount(dto.getMarket_id())%>
	document.getElementById("wishCount").innerHTML=count;
	if($('.wish-btn').val() === "찜하기") {
		$('#heart').hide(); 
	} 
	// 페이지 이동 없이 MarketWishPro 동작
	$(document).on('click', '.wish-btn', function(){
			var button = $(this);
	        $.ajax({ 
	            url:'MarketWishPro.ma',
	            data:{'market_id':<%=dto.getMarket_id() %>}, 
	            success:function(result) { 
	                count = result;
	                $('#wishCount').html(count);          
	                if(button.val() === "찜하기"){
	                    button.val("찜취소");
	                    $('#heart').show();  
	                }else{
	                    button.val("찜하기");
	                    $('#heart').hide();         
	                }
	            }   
	        });
    });
	
});// j쿼리 끝 
	//마우스 우클릭 시 프로필메뉴 동작
		document.addEventListener("DOMContentLoaded", () => {	
		  var id ='<%=(String)session.getAttribute("id")%>';
		  console.log(id);
		  if(id!=="null") {
		 	  var idSpan = document.getElementById("idSpan");
			  var miniMenu = document.getElementById("miniMenu");
			  var mmenu = document.getElementById("mmenu");
			
			  const mouse_end = (event) => {
			
			    const is_right_click = (event.which == 3) || (event.button == 2);
				    if (is_right_click) {
				      miniMenu.style.display = "flex";
				    } 
			  };
			  
			  idSpan.addEventListener("mouseup", mouse_end);
			  
			  const hideMiniMenu = (event) => {
				  
				    if (!miniMenu.contains(event.target)) {
				      miniMenu.style.display = "none";
				    }
			  };
			  document.addEventListener("click", hideMiniMenu); 
			  
			  window.oncontextmenu = function () {
				  	return false;
			  };
		  }			
		});
	//리뷰작성 팝업 열기
	function popup(){
		var link = "ReviewWrite.pr?insert_id=<%=dto.getInsert_id()%>";     
		var popupWidth = 600;
		var popupHeight = 400;
		var popupX = (window.screen.width/2) - (popupWidth/2);
		var popupY= (window.screen.height/2) - (popupHeight/2);
		
	  	window.open(link,'_blank','status=no height='+popupHeight+', width='+popupWidth +',left='+popupX+',top='+popupY);
	}
	// 채팅방 팝업 열기
	function openchat() {
	 	window.open("Chat.hi?to_id=<%=dto.getInsert_id()%>", "vv", "width=500, height=700");
	}
	//게시글 삭제 동작
	function checkDelete() {
		var result = confirm("게시글을 삭제하시겠습니까?");
		if (result == true){    
			alert("게시글 삭제");
			window.location.href = "MarketDeletePro.ma?market_id=<%=dto.getMarket_id()%>";
		}else{   
		     return false;
	  } 
	}
</script>


<div class="boardContainer">
<!-- 내용 시작 -->

	<h1>제품상세</h1>
	
	<table>
		<tr>
		<td>글번호</td>
		<td><%=dto.getMarket_id() %></td>
		</tr>
		
		<tr>
		<td>작성자</td>
		<td><span id="idSpan"><%=dao2.getNickname(dto.getMarket_id()) %></span>
		<div id="miniMenu">
			<div id="profile"><img id="profilImg" src="resource/image/image.png"></div>
			<div id="mmenu" onclick="location.href='profile.pr?insert_id=<%=dto.getInsert_id()%>&nickname=<%=dao2.getNickname(dto.getMarket_id())%>'">프로필 보기</div>	
			<div id="mmenu" onclick="location.href='MypageUserMarketList.mypage?insert_id=<%=dto.getInsert_id()%>&nickname=<%=dao2.getNickname(dto.getMarket_id())%>'">작성글 보기</div>
			<div id="mmenu" onclick="popup();">거래후기 쓰기</div>
		</div></td>
		</tr>
			
		<tr>
		<td>등록일</td>
		<% SimpleDateFormat dateformat = new SimpleDateFormat("yyyy년 MM월 dd일 HH:mm:ss");%>
		<td><%=dateformat.format(dto.getInsert_date()) %></td>
		</tr>
		
		<tr>
		<td>조회수</td>
		<td><%=dto.getView_cnt() %></td>
		</tr>
		
		<tr>
		<td>찜개수</td>
		<td>
		<span id="wishCount"></span>
		<% if(id!=null && !(id.equals(dto.getInsert_id()))) { %>
			<span id="heart">💖</span>
			<% } %>
		<% if(id!=null && !(id.equals(dto.getInsert_id()))){
				ArrayList<WishDTO> wishCheck = dao.wishCheck(dto.getMarket_id(), id);
				if(wishCheck.isEmpty()){ %>
					<input type="button" class="wish-btn" value="찜하기">
				<%} else{ %>
					<input type="button" class="wish-btn" value="찜취소">
				<%
			  }
			}
	    %>
	    </td>
		</tr>
		
		
		<tr>
		<td>글제목</td>
		<td><%=dto.getTitle() %></td>
		</tr>
		
		<tr>
		<td>글내용</td>
		<td><%=dto.getContent() %></td>
		</tr>
		
		<tr>
		<td>가격</td>
		<td><%=dto.getBook_price() %> 원</td>
		</tr>
		
		<% if(length > 0) {
				for(int i = 0; i < length; i++) { %>
				<tr><td>첨부이미지<%=i+1%></td><td><img src="<%= dto.getImgUrls()[i]%>" width=260px ></td></tr>
		 	<% }%>
		<% } else { %>
				<tr><td>첨부이미지</td><td>없음</td></tr>	
		<% }%>
		
		<tr>
		<td><%=cdto.getCdGrpnms()[0] %></td>
		<td><%=dto.getBook_type() %></td>
		</tr>	
		
		<tr>
		<td><%=cdto.getCdGrpnms()[1] %></td>
	  <td><%=dto.getBook_st() %></td>
	  </tr>
	  
		<tr>
		<td><%=cdto.getCdGrpnms()[2] %></td>
		<td><%=dto.getTrade_type() %></td>
		</tr>
			  
		<tr>
		<td><%=cdto.getCdGrpnms()[3] %></td>
		<td><%=dto.getTrade_st() %></td>
		</tr>
			  
		<tr>
		<td><%=cdto.getCdGrpnms()[4] %></td>
		<td><%=dto.getTrade_inperson() %></td>
		</tr>
			  
	</table>
		<input type="button" value="게시글목록" onclick="location.href='MarketList.ma'">
		<% 
		if(id != null) {
			if(id.equals(dto.getInsert_id())) { %>
				<input type="button" value="게시글삭제" onclick="checkDelete();">
				<input type="button" value="게시글수정" onclick="location.href='MarketUpdateForm.ma?market_id=<%=dto.getMarket_id()%>'">
		<%
			}
		}
		%>
		<input type="button" value="1:1 채팅" onclick="openchat();">
		<input type="button" value="신고하기" onclick="location.href='신고가상주소?insert_id=<%=dto.getInsert_id()%>'">
<!-- 내용 끝 -->
</div>

<!-- 푸터 들어가는 곳 -->
<jsp:include page="/inc/footer.jsp" />
<!-- 푸터 들어가는 곳 -->