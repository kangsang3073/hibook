<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%
String boardTypeCd = (String) request.getAttribute("boardTypeCd");
String boardTypeCdNm = (String) request.getAttribute("boardTypeCdNm");
String id =(String)session.getAttribute("id");
String adminYn = (String)session.getAttribute("adminYn"); 
if(id == null){
	response.sendRedirect("MemberLoginForm.me");
} 

String parentId = request.getParameter("parentId")==null ? "0" : request.getParameter("parentId");

%>
	
<!-- 헤더파일들어가는 곳 -->
<jsp:include page="/inc/header.jsp"/>
<!-- 헤더파일들어가는 곳 -->
<link href="/resource/css/board.css" rel="stylesheet" type="text/css">
<div class="boardContainer">
<!-- 내용 시작 -->
<script type="text/javascript">
function writeCheck() {

   var title = $("#title").val(); //id로 가져온것(textarea id="content") 
   var content = $("textarea[name=content]").val(); //name으로 가져오기 - val()/text();
   
   
	if (title == "") {
		alert('글제목을 입력하세요.');
		$("#title").focus();
		return false;
	}
	
	if (content == "") {
		alert('글내용을 입력하세요.');
		$("textarea[name=content]").focus();
 		return false;
	}

} // writeCheck()
</script>

<div>
<p id="boardTag"><%=boardTypeCdNm %> 💬</p>

<div class="tableBar boardView th:first-child">
<hr class="my-hr3">
	<form name="boardWriteForm" action="BoardWritePro.bo" method="post" enctype="multipart/form-data" onsubmit="return writeCheck();">
	
		<input type="hidden" name="boardType" value="<%=boardTypeCd %>">
		<input type="hidden" name="insertId" value="<%=id %>">
		<input type="hidden" name="parentId" value="<%=parentId %>">
		
		<div class="tableBar">
		<table class="boardWrite">
	<tbody>
	
		<% if("Y".equals(adminYn)){%>
			<tr>
				<td>
					<input type="radio" class=""name="noticeYn" value="Y" > 공지
					<input type="radio" name="noticeYn" value="N" checked> 일반
				</td>
			</tr>
		<%} %>
			
			<tr>
				<td><input type="text" class="board_title" id="title" name="title" placeholder="제목을 입력하세요"></td>
			</tr>
			
			<tr>
				<td colspan="3" >
					<textarea id="content" class="writeContent cl " name="content" 
						rows="15" placeholder="내용을 입력하세요" style="width: 100%"></textarea>
				</td>
			</tr>
			
			<tr>
				<td><input type="file" name="contentImg1"></td>
			</tr>
			
			<tr>
				<td><input type="file" name="contentImg2"></td>
			</tr>
			
			<tr>
				<td><input type="file" name="contentImg3"></td>
			</tr>
			</tbody>
		</table>
		</div>
		<div class="btfl"
		       onclick="location.href='BoardList.bo?boardType=<%=boardTypeCd %>'">
		<input type = "submit" class="smallButtonSubmit smallButtonBlueGray" name = "submit" value="등록">
		<input type = "reset" class="smallButtonCancle smallButtonBlueGray " name = "reset" value="취소">
		</div>
</form>
</div>
</div>
<!-- 내용 끝 -->   
</div>
<!-- 푸터파일들어가는 곳 -->
<jsp:include page="/inc/footer.jsp"/>
<!-- 푸터파일들어가는 곳 -->   
