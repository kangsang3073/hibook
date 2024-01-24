<%@page import="com.itwillbs.report.db.ReportDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
String id =(String)session.getAttribute("id");
ReportDTO dto = (ReportDTO) request.getAttribute("dto");
%>
<!-- 헤더파일들어가는 곳 -->
<jsp:include page="/inc/header.jsp" />
<!-- 헤더파일들어가는 곳 -->
<link href="resource/css/board.css" rel="stylesheet" type="text/css">
<link href="resource/css/ReportContent.css " rel="stylesheet" type="text/css">
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
	<form name="UpdateContent"action="ReportUpdatePro.re" method="post" enctype="multipart/form-data" onsubmit="return writeCheck();">
		<input type="hidden" name="num" value="<%=dto.getReport_id()%>">
		<table border="1">
		<tbody>
			<tr>
				<td>작성자</td>
				<td><input type="text" name="insert_id" value="<%=dto.getInsert_id()%>" readonly></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="title" value="<%=dto.getTitle()%>">
				</td>
			</tr>
			<tr>
				<td>글내용</td>
				<td><textarea class="repo" name="content" style="width: 100%"><%=dto.getContent()%>"></textarea></td>
			</tr>
			<tr>
				<td>제보 날짜</td>
				<td><input type="text" name="date"  value="<%=dto.getInsert_date()%>" readonly></td>
			</tr>
			<tr>
				<td>첨부파일</td>
				<td><img src="upload/<%=dto.getContent_img1()%>" width="400">
				<input type="file" name="content_img1" ><%=dto.getContent_img1() == null? "" : dto.getContent_img1() %>
				<input type="hidden" name="oldfile1" value="<%=dto.getContent_img1() == null? "" : dto.getContent_img1()%>"></td>
			</tr>
				
			</tbody>
		</table>
		
		<input type = "submit"  name = "submit" value="등록">
		<input type = "reset"  name = "reset" value="초기화" >
		<input type = "reset"  name = "reset" value="리스트이동" onclick="location.href='ReportList.re'">
	</form>
	
			
</div>

<!-- 푸터파일들어가는 곳 -->
<jsp:include page="/inc/footer.jsp" />
<!-- 푸터파일들어가는 곳 -->
