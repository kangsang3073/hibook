<%@page import="com.itwillbs.board.db.BoardDTO"%>
<%@page import="com.itwillbs.board.db.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
String boardTypeCd = (String) request.getAttribute("boardTypeCd");
String boardTypeCdNm = (String) request.getAttribute("boardTypeCdNm");
String id =(String)session.getAttribute("id");
BoardDTO dto = (BoardDTO)request.getAttribute("dto");
%>

<!-- 헤더파일들어가는 곳 -->
<jsp:include page="/inc/header.jsp"/>
<!-- 헤더파일들어가는 곳 -->
<link href="/resource/css/board.css" rel="stylesheet" type="text/css">
<div class="boardContainer">
<!-- 내용 시작 -->

<script type="text/javascript">

function writeUpdateCheck() { // 글수정시 유효성체크

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

}// writeUpdateCheck()


</script>

<div>
<p id="boardTag"><%=boardTypeCdNm %> 💬</p>

<div class="tableBar boardView th:first-child">
<hr class="my-hr3">
	<form name="boardUpdateForm" action="BoardUpdatePro.bo" method="post" enctype="multipart/form-data" onsubmit="return writeUpdateCheck();">
	
		<input type="hidden" name="boardId" value="<%=dto.getBoardId() %>">
		<input type="hidden" name="boardType" value="<%=boardTypeCd %>">
		
		
			<table class="boardWrite">
			<tbody>
				
				<tr>
					<td><input type="text" class="board_title" id="title" name="title" value="<%=dto.getTitle() %>"></td>
				</tr>
			
				<tr>
					<td><textarea id="content"  class="writeContent cl " name="content"
								  rows="15" style="width: 100%"><%=dto.getContent() %></textarea>
					</td>
				</tr>
				
				<tr>
				<td>
					<input type="file" name="contentImg1" ><%=dto.getContentImg1() == null? "" : dto.getContentImg1()%>
    				<input type="hidden" name="oldfile1" value="<%=dto.getContentImg1() == null? "" : dto.getContentImg1()%>">
				</td>
				</tr>
				
				<tr>
					<td>
					<input type="file" name="contentImg2" ><%=dto.getContentImg2() == null? "" : dto.getContentImg2()%>
    				<input type="hidden" name="oldfile2" value="<%=dto.getContentImg2() == null? "" : dto.getContentImg2()%>">
				</td>
				</tr>
				
				<tr>
					<td>
					<input type="file" name="contentImg3" ><%=dto.getContentImg3() == null? "" : dto.getContentImg3()%>
    				<input type="hidden" name="oldfile3" value="<%=dto.getContentImg3() == null? "" : dto.getContentImg3()%>">
				</td>
				</tr>
			</tbody>
			</table>
		<div class="btfl">	
		<input type = "submit" class="smallButtonSubmit smallButtonBlueGray" name = "submit" value="등록">
		<input type = "reset"  class="smallButtonCancle smallButtonBlueGray" name = "reset" value="취소">
		</div>
</form>

</div>
</div>
<!-- 내용 끝 -->   
</div>
<!-- 푸터파일들어가는 곳 -->
<jsp:include page="/inc/footer.jsp"/>
<!-- 푸터파일들어가는 곳 -->   