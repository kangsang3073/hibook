<%@page import="com.itwillbs.market.db.MarketDTO"%>
<%@page import="com.itwillbs.util.ComCdDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.itwillbs.util.ComCdDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 헤더파일들어가는 곳 -->
<jsp:include page="/inc/header.jsp"/>
<!-- 헤더파일들어가는 곳 -->
<link href="resource/css/market.css" rel="stylesheet" type="text/css">
<!-- 자바 들어가는 곳 -->
<%
String id = (String) session.getAttribute("id");
if (id == null) {
	response.sendRedirect("MemberloginForm.me");
}
// DB com_code 테이블 값 가져오기
ComCdDAO cdao = new ComCdDAO();
ComCdDTO cdto = new ComCdDTO();
ArrayList<ComCdDTO> cdtoList0 = cdao.getComCdList(cdto.getCdGrpnms()[0]);
ArrayList<ComCdDTO> cdtoList1 = cdao.getComCdList(cdto.getCdGrpnms()[1]);
ArrayList<ComCdDTO> cdtoList2 = cdao.getComCdList(cdto.getCdGrpnms()[2]);
ArrayList<ComCdDTO> cdtoList3 = cdao.getComCdList(cdto.getCdGrpnms()[3]);
ArrayList<ComCdDTO> cdtoList4 = cdao.getComCdList(cdto.getCdGrpnms()[4]);
%>
<script type="text/javascript">
$(document).ready(function(){ // j쿼리 start
		//멀티이미지 업로드 시 미리보기 불러오기
		$("#img").on("change", function(event) {
			//파일 업로드 교체 시 동작
			if($(".preview-image").length > 0) { 
				 $(".preview-container").remove();   
				
				var files = event.target.files;
				var imageContainer = $("#imgWrap");
					
				for (var i = 0; i < files.length; i++) {
				 	var file = files[i];
				 	var reader = new FileReader();
				 	reader.onload = (function(theFile) {
				    	return function(e) {
				                var img = $('<img>', {
				                    src: e.target.result,
				                    class: 'preview-image'
				                });
				                var label = $('<div>', {
				                    text: theFile.name,
				                    class: 'preview-label'
				                });
				                var container = $('<div>', {
				                    class: 'preview-container'
				                });
				                container.append(label, img);
				                imageContainer.append(container);
				      };
					})(file);
					reader.readAsDataURL(file);
				}	 
			//첫 파일 업로드 동작
		  }else{
		     var files = event.target.files;
				 var imageContainer = $("#imgWrap");
					
				 for (var i = 0; i < files.length; i++) {
				 	var file = files[i];
				 	var reader = new FileReader();
				 	reader.onload = (function(theFile) {
				    	return function(e) {
				                var img = $('<img>', {
				                    src: e.target.result,
				                    class: 'preview-image'
				                });
				                var label = $('<div>', {
				                    text: theFile.name,
				                    class: 'preview-label'
				                });
				                var container = $('<div>', {
				                    class: 'preview-container'
				                });
				                container.append(label, img);
				                imageContainer.append(container);
				      };
					})(file);
					reader.readAsDataURL(file);
				}	 
			}
		});
		//미리보기 이미지클릭 시 해당 이미지 및 파일 삭제 동작 
		$("#imgWrap").on("click", ".preview-image", function() {
	 		var fileName = $(this).prev().text(); 
			$(this).parent().remove();
			var files = document.getElementById("img").files;
			var index = 0;
 	 		for (var i = 0; i < files.length; i++) {
				if (files[i].name === fileName) {
					index = i;
				    break;
				}	
			} 	
 	 		const deleteImgFile = (num) => {	
 	 		    const dataTransfer = new DataTransfer();
 	 		    let files = $('#img')[0].files;
 	 		    let fileArray = Array.from(files);
 	 		    fileArray.splice(num, 1);
 	 		    fileArray.forEach(file => { dataTransfer.items.add(file); });
 	 		    $('#img')[0].files = dataTransfer.files;
 	 		  }
 	 		  deleteImgFile(index);
		});
}); // j쿼리 end
  //가격 콤마
  function inputNumber(obj) {
        obj.value = comma(uncomma(obj.value));
  }
  function comma(str) {
      str = String(str);
      return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
  }

  function uncomma(str) {
      str = String(str);
      return str.replace(/[^\d]+/g, '');
  } 
  //제출 전 체크
	function checkWrite() {
		 //내용 제한 변수 값
		 var oldImgLength = document.getElementsByClassName("old-image").length;
		 var preImgLength = document.getElementsByClassName("preview-image").length;
		 var totalImgLength = oldImgLength + preImgLength;
		 var subjectLength = document.getElementsByName("title")[0].value.length;
		 var contentLength = document.getElementsByName("content")[0].value.length;
		 console.log(subjectLength);
		 //게시글 submit 전 제한 사항
		 if(totalImgLength < 1) {
			 alert("최소 1개의 이미지를 올려주세요.");
			 return false;
		 }
		 if(totalImgLength > <%=MarketDTO.getImgLengthMax()%>) {
			 alert("게시글 당 이미지는 5개까지 올릴 수 있습니다.");
			 return false;
		 }
		 if(subjectLength < 2) {
			 alert("제목을 2글자 이상 입력해주세요.");
			 return false;
		 }
		 if(contentLength < 2) {
			 alert("내용을 2글자 이상 입력해주세요.");
			 return false;
		 }
		 //내용 제한 넘길 시 클라우디너리 업로드 진행
		 var result = confirm("게시글을 등록하시겠습니까?");
		 if (result == true){     
				 const url = "https://api.cloudinary.com/v1_1/dsbicmz4i/image/upload";	
				 const files = document.getElementById("img").files;
				 var imgUrls = [];
				 
				 Promise.all(
					[...files].map(file => {
						const formData = new FormData();
						formData.append("file", file);
						formData.append("upload_preset", "Productimage");
						
						return fetch(url, {
							method: "POST",
							body: formData
						})
							.then(response => response.json())
							.then(data => { 
								imgUrls.push(data.url);	
							})
					})
				 ).then(() => {			    
					 	const imageElList = document.getElementsByName("imgUrls");
						 	if(imgUrls[0]!=null) {
							    for(let i = 0; i < imageElList.length; i++) {	
							    	if(imgUrls[i]==null) {break;}
							    	imageElList[i].value = imgUrls[i];
							    }
					    	}
				  }).then(() => {	 
					alert("게시글 등록");
					document.move.submit();  
				}) 
		 }else{   
		     return false;
	   } 
	}
</script>
<div class="boardContainer">
<!-- 내용 시작 -->
	<h1>거래글 작성</h1>
	
	<form name="move" action="MarketWritePro.ma" method="post">
	<table>
			<tr>
			<td>글쓴이</td>
			<td><input type="text" name="insert_id" value="<%=id%>" readonly></td>
			</tr>
	
			<tr>
			<td>글제목</td>
			<td><input type="text" name="title" value=""></td>
			</tr>

			<tr>
			<td>글내용</td>
			<td><textarea name="content" rows="10" cols="20"></textarea></td>
			</tr>
			
			<tr>
			<td>가격</td>
			<td><input type="text" name="book_price" value="" onkeyup="inputNumber(this);">원</td>
			</tr>
			
			<!-- select box start -->
			<tr>
			<td><%=cdto.getCdGrpnms()[0]%></td>
			<td><select name="<%=cdtoList0.get(0).getCdGrp()%>">
						<%
						for (int i = 0; i < cdtoList0.size(); i++) {
						%>
						<option value="<%=cdtoList0.get(i).getCdNm()%>"><%=cdtoList0.get(i).getCdNm()%>
						</option>
						<%
						}
						%>
					 </select></td>
			</tr>
			
			<tr>
				<td><%=cdto.getCdGrpnms()[1]%></td>
				<td><select name="<%=cdtoList1.get(0).getCdGrp()%>">
						<%
						for (int i = 0; i < cdtoList1.size(); i++) {
						%>
						<option value="<%=cdtoList1.get(i).getCdNm()%>"><%=cdtoList1.get(i).getCdNm()%>
						</option>
						<%
						}
						%>
					 </select></td>
			</tr>
			
			<tr>
			<td><%=cdto.getCdGrpnms()[2]%></td>
		  <td><select name="<%=cdtoList2.get(0).getCdGrp()%>">
						<%
						for (int i = 0; i < cdtoList2.size(); i++) {
						%>
						<option value="<%=cdtoList2.get(i).getCdNm()%>"><%=cdtoList2.get(i).getCdNm()%>
						</option>
						<%
						}
						%>
					 </select></td>
			</tr>
			
			<tr>
			<td><%=cdto.getCdGrpnms()[3]%></td>
			<td><select name="<%=cdtoList3.get(0).getCdGrp()%>">
						<%
						for (int i = 0; i < cdtoList3.size(); i++) {
						%>
						<option value="<%=cdtoList3.get(i).getCdNm()%>"><%=cdtoList3.get(i).getCdNm()%>
						</option>
						<%
						}
						%>
					 </select></td>
			</tr>
			
			<tr>
			<td><%=cdto.getCdGrpnms()[4]%></td>
			<td><select name="<%=cdtoList4.get(0).getCdGrp()%>">
						<%
						for (int i = 0; i < cdtoList4.size(); i++) {
						%>
						<option value="<%=cdtoList4.get(i).getCdNm()%>"><%=cdtoList4.get(i).getCdNm()%>
						</option>
						<%
						}
						%>
					 </select></td>
			</tr>
			<!-- select box end -->

			<tr>
			<td>첨부파일</td>
			<td><input id="img" type="file" name="files[]" accept="image/*" multiple></td>
		</table>
		
		<div id="imgWrap">
			이미지 미리보기<br>
		</div>
		<input type="button" value="글쓰기" name="sub" id="sub" onclick="checkWrite();"> 
		<input type="button" value="돌아가기" onclick="location.href='MarketList.ma'">

		<!-- 클라우디너리 배열값 저장 -->
		<%
		for(int i = 0; i < MarketDTO.getImgLengthMax(); i++) {
		%>
			<input type="hidden" value="url" name="imgUrls"><br> 
		<% } %> 
	</form>
<!-- 내용 끝 -->
</div>

<!-- 푸터 들어가는 곳 -->
<jsp:include page="/inc/footer.jsp" />
<!-- 푸터 들어가는 곳 -->
</body>
</html>