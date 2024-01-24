<%@page import="com.itwillbs.market.db.MarketDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 헤더파일들어가는 곳 -->
<jsp:include page="/inc/header.jsp"/>
<link href="./resource/css/star.css" rel="stylesheet" />
	<%
	String insert_id = (String) session.getAttribute("id");
	%>
	<form action="ReviewWritePro.pr" class="mb-3" id="myform" name="myform"
		method="get">
		<fieldset>
			<input type="hidden" name="insert_id" value=<%=insert_id%>>
			<span class="text-bold">별점을 선택해주세요</span> <input type="radio"
				name="score" value="5.0" id="rate1"><label for="rate1">★</label>
			<input type="radio" name="score" value="4.0" id="rate2"><label
				for="rate2">★</label> <input type="radio" name="score" value="3.0"
				id="rate3"><label for="rate3">★</label> <input type="radio"
				name="score" value="2.0" id="rate4"><label for="rate4">★</label>
			<input type="radio" name="score" value="1.0" id="rate5"><label
				for="rate5">★</label>
			<p>
				<input type="hidden" name="tgt_id" id="tgt_id" value="">
		</fieldset>

		<div>
			<textarea class="col-auto form-control" type="text"
				id="reviewContents" name="review_content"
				placeholder="좋은 수강평을 남겨주시면 Cocolo에 큰 힘이 됩니다! 포인트 5000p도 지급!!"></textarea>
		</div>
		<input type="submit" value="전송" onclick="TabClose()">
		<input type="reset" value="취소" onclick="TabClose()">
		<script type="text/javascript">
			var urlParams = new URL(location.href).searchParams;
			var id = urlParams.get('insert_id');
			console.log(id);
			document.getElementById("tgt_id").value = id;
			function TabClose() {
				self.opener=self;
	          setTime('close()',3000);
	        }
			
			function close(){
				self.close();
			}
		</script>
	</form>
<!-- 푸터파일들어가는 곳 -->
<jsp:include page="/inc/footer.jsp"/>
<!-- 푸터파일들어가는 곳 -->   