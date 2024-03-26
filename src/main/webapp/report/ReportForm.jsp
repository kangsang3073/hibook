<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%
String id = (String) session.getAttribute("id");
if (id == null) {
	response.sendRedirect("MemberLoginForm.me");
}
%>
<!-- 헤더파일들어가는 곳 -->
<jsp:include page="/inc/header.jsp" />
<!-- 헤더파일들어가는 곳 -->
<link href="resource/css/Reportcss.css " rel="stylesheet"
	type="text/css">
<div>
	<h3 class="call">고객센터</h3>
				<br>
				<br>
				<div class=Left>
					<br> <br>
					<div>
						<div>
							<a href="ReportForm.re"> 신고하기 </a><br> <br> <a
								href="ReportList.re">신고목록</a><br> <br> <a
								href="List.re">자주하는 질문</a>

						</div>
					</div>
				</div>
	<div>

		<div class="boardContainer">
			<!-- 내용 시작 -->
			<script type="text/javascript">
				// 글작성 없을시
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
			<form action="ReportWritePro.re" method="post"
				enctype="multipart/form-data" onsubmit="return writeCheck();">

				<h1 class="T">사기신고하기📢</h1>
				<input type="hidden" name="id" value="<%=id%>">
				
				<div>
					<input class="m" type="text" name="title" placeholder="제목을입력해주세요">
				</div>
				<div>
					<table class="te" border="1">
						<tr>
							<th class="a">사유(구체적으로)</th>
				<!-- <td width=700px height=500px> -->
							
								<td style=background-color:#42444e  width=700px height=500px >
								<textarea class="repo" name="content"></textarea>
								</td>
							
						</tr>

						<tr>
							<th>첨부파일 등록</th>
							<td class="f"><input type="file" name="content_img1"> </td>
						</tr>

					</table>
				</div>
				
			 <div>
					<input type="reset"  class="smallButtonCancle" value="취소">
					<input type="submit" class="smallButtonComment" value="등록">
			</div>
			</form>
		</div>
	</div>
</div>
<!-- 푸터파일들어가는 곳 -->
<jsp:include page="/inc/footer.jsp" />
<!-- 푸터파일들어가는 곳 -->
