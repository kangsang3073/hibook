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
MarketDAO mdao = new MarketDAO();
SimpleDateFormat dateformat = new SimpleDateFormat("yyyy년 MM월 dd일 HH:mm:ss");

int length = 0;
for(int i = 0; i < dto.getImgUrls().length; i++) {
	if(!(dto.getImgUrls()[i].equals("url"))) {length++;}
}
%>

<!-- 헤더파일들어가는 곳 -->
<jsp:include page="/inc/header.jsp" />
<!-- 헤더파일들어가는 곳 -->
<link href="resource/css/marketContent.css" rel="stylesheet" type="text/css">

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
		//캐러셀 
		  var interval = 3000; // Change image every 3 seconds
		  var slides = $('.carousel-item');
		  var i = 0;

		  setInterval(function() {
		    slides.eq(i).removeClass('active');
		    i = (i + 1) % slides.length;
		    slides.eq(i).addClass('active');
		  }, interval);
		  $('.carousel-control-prev').click(function() {
		    slides.eq(i).removeClass('active');
		    i = (i - 1 + slides.length) % slides.length;
		    slides.eq(i).addClass('active');
		  });
		  $('.carousel-control-next').click(function() {
		    slides.eq(i).removeClass('active');
		    i = (i + 1) % slides.length;
		    slides.eq(i).addClass('active');
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
	//프로필 팝업 열기
		function popup1(){
			var link = "profile.pr?insert_id=<%=dto.getInsert_id()%>&nickname=<%=mdao.getNickname(dto.getMarket_id())%>";     
			var popupWidth = 500;
			var popupHeight = 700;
			var popupX = (window.screen.width/2) - (popupWidth/2);
			var popupY= (window.screen.height/2) - (popupHeight/2);
			
		  	window.open(link,'_blank','status=no height='+popupHeight+', width='+popupWidth +',left='+popupX+',top='+popupY);
		}
	//리뷰작성 팝업 열기
	function popup2(){
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

<div class="boardText">
	<p class="boardTag">중고책 상세 <span class="marketNum">글번호 <%=dto.getMarket_id() %></span> 
</div>

<article class="board">
		<div class="ContentContainer">
			
			<div class="conBox">
					<div class="carousel" id="myCarousel" data-ride="carousel">
	  				<div class="carousel-inner">
	  						<div class="carousel-item active">
			     				<img class="conImg" src="<%= dto.getImgUrls()[0]%>" alt="Image 1">
			     				<span class="imgCount">1/<%=length %></span>
			   				</div>
	  						<% if(length > 1) {
										for(int i = 1; i < length; i++) { %>
			   		 		<div class="carousel-item">
			      			<img class="conImg" src="<%= dto.getImgUrls()[i]%>" alt="Image 2">
			      			<span class="imgCount"><%=i+1 %>/<%=length %></span>
			   		 		</div>
			   		 		<%  }
								}%>
	 				 </div>
	 				 <%if(length > 1) { %>
	  			<a class="carousel-control-prev" role="button">
	   				<span class="carousel-control-icon" aria-hidden="true">&#129152;</span>
				  </a>
	 			  <a class="carousel-control-next" role="button">
	  			  <span class="carousel-control-icon" aria-hidden="true">&#129154;</span>
				  </a>
				  <% } %>
				</div>
			</div>
		
		<div class="conBox">
			
			<div class="conMain"><span class="trade_type"><%=dto.getTrade_type() %></span>&nbsp;&nbsp;<%=dto.getTitle() %></div>
						
			<div class="price"><%=dto.getBook_price() %> 원</div>

			
			<div class="conCount">찜개수 <span id="wishCount"></span>
			<% if(id!=null && !(id.equals(dto.getInsert_id()))) { %>
			<span id="heart">💖</span>  
			<% } %>
			&nbsp;&#108;&nbsp; 조회수 <%=dto.getView_cnt() %>
			</div>
			
			<div><span class="conSt">등록일</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=dateformat.format(dto.getInsert_date()) %></div>
			
			<div class="conWriter"><span class="conSt">작성자</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id="idSpan"><%=mdao.getNickname(dto.getMarket_id()) %></span>
				<div id="miniMenu">
						<div id="profile">
							<%if(mdao.getImg(dto.getMarket_id())==null || mdao.getImg(dto.getMarket_id()).equals("")) { %>
								<img id="profilImg" src="resource/image/image.png">
							<% } else{ %>
								<img id="profilImg" src="upload/<%=mdao.getImg(dto.getMarket_id())%>">
							<% } %> </div>
						<div id="notmmenu"><%=dto.getInsert_id() %> 님</div>	
						<div id="mmenu" onclick="popup1();">프로필 보기</div>	
						<div id="mmenu" onclick="location.href='MypageUserMarketList.mypage?insert_id=<%=dto.getInsert_id()%>&nickname=<%=mdao.getNickname(dto.getMarket_id())%>'">작성글 보기</div>
						<% if(id!=null) {
								if(!(id.equals(dto.getInsert_id()))) { %> 
									<div id="mmenu" onclick="popup2();">거래후기 쓰기</div>
						<% }
						}%>
				</div>
			</div>
			
			<div><span class="conSt"><%=cdto.getCdGrpnms()[0] %></span>&nbsp;&nbsp;&nbsp;&nbsp;<%=dto.getBook_type() %></div>
			<div><span class="conSt"><%=cdto.getCdGrpnms()[1] %></span>&nbsp;&nbsp;&nbsp;&nbsp;<%=dto.getBook_st() %></div>
			<div><span class="conSt"><%=cdto.getCdGrpnms()[3] %></span>&nbsp;&nbsp;&nbsp;&nbsp;<%=dto.getTrade_st() %></div>
			<div><span class="conSt"><%=cdto.getCdGrpnms()[4] %></span>&nbsp;&nbsp;&nbsp;&nbsp;<%=dto.getTrade_inperson() %></div>
			
			<div class="conCon"><%=dto.getContent() %></div>
			
			<div class="conBtn">
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
			    
			    <% if(id!=null) {
						if(!(id.equals(dto.getInsert_id()))) { %> 
							<input type="button" class="chat-btn" value="1:1 채팅" onclick="openchat();">
					<%	} 
					} %>
	    </div>
	    
	  </div>
	 </div>
	 
</article>

		<div class="conBottom">
		<% 
				if(id != null) {
					if(id.equals(dto.getInsert_id())) { %>
						<input class="basic-btn" type="button" value="게시글삭제" onclick="checkDelete();">
						<input class="basic-btn" type="button" value="게시글수정" onclick="location.href='MarketUpdateForm.ma?market_id=<%=dto.getMarket_id()%>'">
				<%
					}
				}
				%>
				
				<input class="basic-btn" type="button" value="게시글목록" onclick="location.href='MarketList.ma'">
				
				<input class="basic-btn" type="button" value="신고하기" onclick="location.href='ReportForm.re?insert_id=<%=dto.getInsert_id()%>'">
		</div>
		
<!-- 내용 끝 -->
</div>

<!-- 푸터 들어가는 곳 -->
<jsp:include page="/inc/footer.jsp" />
<!-- 푸터 들어가는 곳 -->