<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.itwillbs.util.ChangeTime"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.itwillbs.boardComment.db.BoardCmmtDTO"%>
<%@page import="com.itwillbs.board.db.BoardDTO"%>
<%@page import="com.itwillbs.board.db.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
 <%
 String id = (String)session.getAttribute("id");
  	// 게시판 유형을 공통코드로 구분.
  	String boardTypeCd = (String) request.getAttribute("boardTypeCd");
  	String boardTypeCdNm = (String) request.getAttribute("boardTypeCdNm");
   
  	// 댓글 페이징 데이터
  	int currentPage = (Integer) request.getAttribute("currentPage");
	int startPage = (Integer) request.getAttribute("startPage");
	int pageBlock = (Integer) request.getAttribute("pageBlock");
	int endPage = (Integer) request.getAttribute("endPage");
	int pageCount = (Integer) request.getAttribute("pageCount");
	int count = (Integer) request.getAttribute("count");
	int pageSize = (Integer) request.getAttribute("pageSize");
   
 	BoardDTO dto = (BoardDTO)request.getAttribute("dto");
 	String adminYn = (String)session.getAttribute("adminYn");
   
 	// 댓글 
 	ArrayList<BoardCmmtDTO> boardList = (ArrayList<BoardCmmtDTO>) request.getAttribute("boardList");
 	
 	// 글쓴이를 닉네임으로 불러오기
 	BoardDAO dao = new BoardDAO();
   
 %>
   
<!-- 헤더파일들어가는 곳 -->
<jsp:include page="/inc/header.jsp"/>
<!-- 헤더파일들어가는 곳 -->
<link href="/resource/css/board.css" rel="stylesheet" type="text/css">
<link href="resource/css/market.css" rel="stylesheet" type="text/css">
<div class="boardContainer">
<!-- 내용 시작 -->


<script type="text/javascript">

// 댓글등록
function cmmtWrite() {

   var content = $("#content").val(); 
   var secretYn = $("#secretYn:checked").val(); 
   if(secretYn != "Y") secretYn = "N";
   var boardId = $("#boardId").val();

// 댓글유효성체크   
   if (content == "") { 
		alert('댓글이 입력되지 않았습니다.');
		$("textarea[name=content]").focus();
		return false;
	}
// 댓글제어   
   $.ajax({
          url: "/BoardCmmtWritePro.cmmt" // Http 요청보낼 서버Url
          , method: "POST" // http 요청 메소드(GET, POST 등)
          , async: true // 비동기여부
          , data: {content: content, secretYn: secretYn, boardId: boardId}
          , dataType: "json" // 서버에서 응답하여 보내줄 데이터타입
          , beforeSend: function() { // 요청보내기전 실행할 함수
          }
          , success: function(data) { // 요청성공하면 실행될 함수
             if(data.result == 1){
                alert("댓글이 등록되었습니다.");
                 location.reload(); // 새로고침
             }else{
                alert("댓글 등록에 실패하였습니다.");
             }
          }
          , error: function(jqXhr, status, errorThrown) { // 요청실패하면 실행될 함수
              alert("시스템에러. (error : " + errorThrown + " )");
          }
          , complete: function(jqXhr, status) { // 성공,실패와 상관없이 항상 실행되는 함수, done/fail 함수 먼저 실행됩니다.
             // $("#loading").hide();
          }
      }); // ajxx
    
}// cmmtWrite()

 // 댓글수정
function cmmtEdit(cmmtId){
	$("#view_"+cmmtId).hide(); // 댓글조회<tr>숨기기 _js
	$("#edit_"+cmmtId).show(); // 댓글조회<tr>숨기기 _js
	
} //cmmtEdit

// 댓글취소
function cmmtCancle(cmmtId){
	$("#view_"+cmmtId).show(); // 댓글조회<tr>숨기기 _js
	$("#edit_"+cmmtId).hide(); // 댓글조회<tr>숨기기 _js
	
} //cmmtCancle

//댓글수정처리
function cmmtUpdate(cmmtId) { 

	   var content = $("#content_"+cmmtId).val(); 
	   var secretYn = $("#secretYn_"+cmmtId+":checked").val(); 
	   if(secretYn != "Y") secretYn = "N";
	   
	// 댓글유효성체크	   
	   if (content == "") { 
			alert('댓글을 입력하세요');
			$("#content_"+cmmtId).focus();
			return false;
			
		} //if 
		// 댓글제어    
	   $.ajax({
	          url: "/BoardCmmtUpdatePro.cmmt" // Http 요청보낼 서버Url
	          , method: "POST" // http 요청 메소드(GET, POST 등)
	          , async: true // 비동기여부
	          , data: {content: content, secretYn: secretYn, cmmtId: cmmtId}
	          , dataType: "json" // 서버에서 응답하여 보내줄 데이터타입
	          , beforeSend: function() { // 요청보내기전 실행할 함수
	          }
	          , success: function(data) { // 요청성공하면 실행될 함수
	             if(data.result == 1){
	                alert("댓글이 수정 되었습니다.");
	                 location.reload(); // 새로고침
	             }else{
	                alert("댓글 수정에 실패하였습니다.");
	             }
	          }
	          , error: function(jqXhr, status, errorThrown) { // 요청실패하면 실행될 함수
	              alert("시스템에러. (error : " + errorThrown + " )");
	          }
	          , complete: function(jqXhr, status) { // 성공,실패와 상관없이 항상 실행되는 함수, done/fail 함수 먼저 실행됩니다.
	             // $("#loading").hide();
	          }
	      }); // ajxx
	    
	}// cmmtUpdate()
	
// 댓글삭제	
function cmmtDelete(cmmtId) { 
	//댓글제어
	   $.ajax({
	          url: "/BoardCmmtDeletePro.cmmt" // Http 요청보낼 서버Url
	          , method: "POST" // http 요청 메소드(GET, POST 등)
	          , async: true // 비동기여부
	          , data: {cmmtId: cmmtId}
	          , dataType: "json" // 서버에서 응답하여 보내줄 데이터타입
	          , beforeSend: function() { // 요청보내기전 실행할 함수
	          }
	          , success: function(data) { // 요청성공하면 실행될 함수
	             if(data.result == 1){
	                alert("댓글이 삭제 되었습니다.");
	                location.reload(); // 새로고침
	             }else{
	                alert("댓글 삭제에 실패하였습니다.");
	             }
	          }
	          , error: function(jqXhr, status, errorThrown) { // 요청실패하면 실행될 함수
	              alert("시스템에러. (error : " + errorThrown + " )");
	          }
	          , complete: function(jqXhr, status) { // 성공,실패와 상관없이 항상 실행되는 함수, done/fail 함수 먼저 실행됩니다.
	             // $("#loading").hide();
	          }
	      }); // ajxx
	    
	}// cmmtUpdate()	

// 글쓰기 유효성체크
function writeCheck() { 

   var title = $("#title").val(); //id로 가져온것(textarea id="content") 
   var content = $("textarea[name=content]").val(); //name으로 가져오기 - val()/text();
   
   
	if (title == "") {
		alert('글제목이 입력되지 않았습니다.');
		$("#title").focus();
		return false;
	}
	
	if (content == "") {
		alert('글내용이 입력되지 않았습니다.');
		$("textarea[name=content]").focus();
 		return false;
	}

}// writeCheck()


</script>


<div>
<p id="boardTag"><%=boardTypeCdNm %>  💬</p>


<div class="tableBar">
<hr class="my-hr3">
   <table class="boardView">
   		<colgroup>
			<col width="100px;">
			<col width="*">
			<col width="100px;">
		</colgroup>
		<% // 시간계산해서 몇초전 몇분전 몇시간전 등 출력하는 함수사용.
		String changeTime = ChangeTime.calculateTime(dto.getInsertDate()); %>
		
      <tr>
      	<td colspan="3"><%=dto.getTitle()%></td>
      </tr>
      
      <tr>
<%--          <td><%=dto.getInsertId()%></td> --%>
<!-- 		<span id="idSpan"> -->
<%-- 		<input type="hidden" name=<%=dto.getInsertId() %>></span> --%>
         <td><span id="idSpan"><%=dao.getNickname(dto.getBoardId()) %></span>
         <% SimpleDateFormat dateformat = new SimpleDateFormat("yyyy년 MM월 dd일 HH:mm:ss");%>
		<td><%=dateformat.format(dto.getInsertDate()) %></td>
         <td>조회 : <%=dto.getViewCnt()%></td>
      </tr>
      
      <tr>
         <td colspan="3" class="boardContent pb40 ">
         	<div style="min-height:300px;">
        		 <%=dto.getContent().replace("\r\n","<br>")%>
       		</div>
         </td>
      </tr>
      
      <tr>
         <td colspan="3" class="contentImg pt40">
         <%
            if(dto.getContentImg1() != null && !"".equals(dto.getContentImg1())){
         %>
            <img src="upload/<%=dto.getContentImg1()%>" width="400">
            <a href="upload/<%=dto.getContentImg1()%>" download><br>
            <span class="download">Download</span></a> <%}%>
         </td>
      </tr>
      
      <tr>
         <td colspan="3" class="contentImg">
         <% if(dto.getContentImg2() != null && !"".equals(dto.getContentImg2())){ %>
            <img src="upload/<%=dto.getContentImg2() %>" width="400">
            <a href="upload/<%=dto.getContentImg2()%>" download><br>
            <span class="download">Download</span></a> <% } %>
         </td>
      </tr>
      
      <tr>
         <td colspan="3" class="contentImg">
         <% if(dto.getContentImg3() != null && !"".equals(dto.getContentImg3())){ %>
            <img src="upload/<%=dto.getContentImg3() %>" width="400">
            <a href="upload/<%=dto.getContentImg3()%>" download><br>
            <span class="download">Download</span></a><% } %>
         </td>
      </tr>
   </table>
 
 <table class="bCmmtContent1">
 <!-- 공지사항게시판 모든글에 댓글작성불가 -->
    	  <%if("N".equals(dto.getNoticeyn())) {%>
       <!-- 댓글 시작 -->	 
         <tr>
            <td>
             <textarea id="content" name="content" rows="8" placeholder="댓글을 입력하세요" style="width: 100%"></textarea>
             <input type="checkbox" id="secretYn" name="secretYn" value="Y" >비밀댓글
             <input type="hidden" id="boardId" name="boardId" class="testClass" value="<%=dto.getBoardId()%>">
             <input type="button" class="smallButtonComment smallButtonBlueGray  "value="댓글등록" onclick="cmmtWrite();">
            </td>
         </tr>
 		<%} %>
         <% 
            // 댓글 반복- 배열저장.
            for (int i = 0; i < boardList.size(); i++) {
               // 배열 한칸에 내용 가져오기 
               BoardCmmtDTO cmmtDto = boardList.get(i);
               
               
            %> 
            
        <!-- 댓글조회  -->
            <tr id ="view_<%=cmmtDto.getCmmtId() %>">
               <td> 
               		<!-- 작성자아이디, 작성일 , 댓글내용, 비밀댓글여부 -->
<%--                 	  <%=cmmtDto.getInsertId()%> --%>
                	<span id="idSpan"><%=dao.getNickname(dto.getBoardId()) %></span>
                	  &nbsp;&nbsp; <%=changeTime%><br> 
                  
                  	<!-- 일반글 or 로그인아이디=댓글작성자아이디 or 로그인아이디=원글작성자아이디 or 관리자'Y' -->
               	  	 <% if( "N".equals(cmmtDto.getSecretYn()) || id.equals(cmmtDto.getInsertId()) 
                		  || id.equals(dto.getInsertId()) || "Y".equals(adminYn)){%>
                		  
                		<!-- 비밀글 -->  
                      <% if("Y".equals(cmmtDto.getSecretYn())){%>
                    	  🔒비밀댓글
                      <%}%>	
                     
                      <%=cmmtDto.getContent()%> 
                     
                  <%} else {%>
                  		 🔒 비공개 댓글입니다.
                  <%}%>
                  <br><br>
                  
       <!-- 세션값 = 글쓴이 -> 일치 -> 자기자신 쓴 글(글수정, 글삭제 ,비밀댓글 보이기)  -->     
                  <%   
                     if (id.equals(cmmtDto.getInsertId())) {%> 
                 		<input type="button" value="댓글수정"  class="smallButtonsubmit2 smallButtonBlueGray" onclick="cmmtEdit('<%=cmmtDto.getCmmtId() %>');" >
                  		<input type="button" value="댓글삭제" class="smallButtonsubmit2 smallButtonBlueGray" onclick="cmmtDelete('<%=cmmtDto.getCmmtId() %>');"> 
                  		
                  <%} // if끝 %>
               </td>
            </tr>
            
       <!-- 댓글수정 -->
             <tr id ="edit_<%=cmmtDto.getCmmtId() %>" style="display:none">
               <td> 
                 <textarea id="content_<%=cmmtDto.getCmmtId() %>" name="content" rows="8" placeholder="댓글을 입력하세요" style="width: 80%"><%=cmmtDto.getContent()%> </textarea>
	             <input type="checkbox" id="secretYn_<%=cmmtDto.getCmmtId() %>" name="secretYn" value="Y" <%if("Y".equals(cmmtDto.getSecretYn())){%> checked <%}%> >비밀댓글
	             <input type="button"  value="댓글수정" onclick="cmmtUpdate('<%=cmmtDto.getCmmtId() %>');">
	             <input type="button" value="댓글취소" onclick="cmmtCancle('<%=cmmtDto.getCmmtId() %>');">
               </td>
            </tr>
            
         <%} // 댓글for문 끝%>
      </table>
		</div>
	<div class="Page">
	<% // 댓글 페이징
	// 10페이지 이전
		if (startPage > pageBlock) {
	%>
	<a
		href="BoardContent.bo?boardId=<%=
		
		dto.getBoardId()%>&boardType=<%=boardTypeCd%>&pageNum=<%=startPage - pageBlock%>">&lt;</a>
	<%
	}

	for (int i = startPage; i <= endPage; i++) {
	%>
	<a href="BoardContent.bo?boardId=<%=dto.getBoardId()%>&boardType=<%=boardTypeCd%>&pageNum=<%=i%>" <%if(currentPage == i){%>class="active"<%}%>><%=i%></a>
	<%
	}
	// 10페이지 다음
	if (endPage < pageCount) {
	%>
	<a
		href="BoardContent.bo?boardId=<%=dto.getBoardId()%>&boardType=<%=boardTypeCd%>&pageNum=<%=startPage + pageBlock%>">&gt;</a>
	<%
	}
	%>
	</div>	
	<!-- 댓글 끝 -->

      <br>
      <span class="flote"> 
   <% // 로그인 -> 세션값 있음
         if (id != null) {
            // 세션값 = 글쓴이 -> 일치 -> 자기자신 쓴 글(글수정, 글삭제 보이기)
            if (id.equals(dto.getInsertId())) { %> 
           
               <input type="button" value="글수정" class="smallButtonSubmit smallButtonBlueGray"
                     onclick="location.href='BoardUpdateForm.bo?boardType=<%=boardTypeCd %>&boardId=<%=dto.getBoardId()%>'">

               <input type="button" value="글삭제" class="smallButtonSubmit smallButtonBlueGray"
                     onclick="location.href='BoardDeletePro.bo?boardType=<%=boardTypeCd %>&boardId=<%=dto.getBoardId()%>'"> <%}}%>
           
   <input type="button" value="글목록"  class="smallButtonSubmit smallButtonBlueGray"
   		  onclick="location.href='BoardList.bo?boardType=<%=boardTypeCd %>'">
   
   <!-- 원글이면서 일반글인경우 답글쓰기 보이게 -->
   
   <%
   	 if(!"notice".equals(dto.getBoardType())){
   		 
   		if(dto.getParentId() == 0 && "N".equals(dto.getNoticeyn())) {%> 
  			 <input type="button" value="답글쓰기"  class="smallButtonSubmit smallButtonBlueGray"
  		 			onclick="location.href='BoardWriteForm.bo?boardType=<%=boardTypeCd %>&parentId=<%=dto.getBoardId()%>'">
  		</span>
			<%}%>
	<%} %> 
    
  
    	
</div>
<!-- 내용 끝 -->   
</div>
<!-- 푸터파일들어가는 곳 -->
<jsp:include page="/inc/footer.jsp"/>
<!-- 푸터파일들어가는 곳 -->   

       
       
       

