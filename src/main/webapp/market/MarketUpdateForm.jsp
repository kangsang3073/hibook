<%@page import="java.util.ArrayList"%>
<%@page import="com.itwillbs.util.ComCdDTO"%>
<%@page import="com.itwillbs.util.ComCdDAO"%>
<%@page import="com.itwillbs.market.db.MarketDTO"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 헤더파일들어가는 곳 -->
<jsp:include page="/inc/header.jsp"/>
<!-- 헤더파일들어가는 곳 -->
<link href="resource/css/marketForm.css" rel="stylesheet" type="text/css">
<!-- 자바 들어가는 곳 -->
<%
MarketDTO dto = (MarketDTO) request.getAttribute("dto");
// DB 실제 url 개수 들고오기
int realUrlLength = 0;
for (int i = 0; i < dto.getImgUrls().length; i++) {
	if (!(dto.getImgUrls()[i].equals("url"))) {
		realUrlLength++;
	}
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
<!-- 자바스크립트 들어가는 곳 -->
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
		//기존 이미지 클릭 시 삭제 구동
		$(".old-image").on("click", function(event) {
			console.log("클릭");
			$(this).parent().remove();
		});	
		//해당 글에 대해 DB에 저장된 select option값 나타내기
		$('#book_st').val('<%=dto.getBook_st()%>').prop("selected",true);
		$('#book_type').val('<%=dto.getBook_type()%>').prop("selected", true);
		$('#trade_st').val('<%=dto.getTrade_st()%>').prop("selected",true);
		$('#trade_type').val('<%=dto.getTrade_type()%>').prop("selected",true);
		$('#trade_inperson').val('<%=dto.getTrade_inperson()%>').prop("selected",true);
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
		 //제한 사항 변수 값
		 var oldImgLength = document.getElementsByClassName("old-image").length;
		 console.log("oldimg개수 : "+oldImgLength);
		 var preImgLength = document.getElementsByClassName("preview-image").length;
		 console.log("preimg개수 : "+preImgLength);
		 var totalImgLength = oldImgLength + preImgLength;
		 
		 var titleLength = document.getElementsByName("title")[0].value.length;
		 var contentLength = document.getElementsByName("content")[0].value.length;
		 var priceLength = document.getElementsByName("book_price")[0].value.length;
		 var tradeTypeLength = document.getElementsByName("trade_type")[0].value.length;
		 //게시글 submit 전 제한 사항
		 console.log(titleLength);
		 if(titleLength < 2 || titleLength > 20) {
			 alert("제목은 2글자에서 20글자 사이로 입력해주세요.");
			 return false;
		 }
		 if(contentLength < 2) {
			 alert("내용을 2글자 이상 입력해주세요.");
			 return false;
		 }
		 if(priceLength < 1) {
			 alert("가격을 입력해주세요.");
			 return false;
		 }
		 if(tradeTypeLength < 1) {
			 alert("거래종류를 선택해주세요.");
			 return false;
		 }
		 if(totalImgLength < 1) {
			 alert("최소 1개의 이미지를 올려주세요.");
			 return false;
		 }
		 if(totalImgLength > <%=MarketDTO.getImgLengthMax()%>) {
			 alert("게시글 당 이미지는 5개까지 올릴 수 있습니다.");
			 return false;
		 }
		 //기존 이미지 및 추가 이미지 개수 변수 저장
		 var oldImgNum = document.getElementsByName("oldImgNum")[0];
		 var preImgNum = document.getElementsByName("preImgNum")[0];
		 oldImgNum.value = oldImgLength;
		 preImgNum.value = preImgLength;
		 //기존 이미지 배열 저장
		 var oldImgUrls = [];
		 var oldImages = document.getElementsByName("old-image");
		 for (let i = 0; i < oldImages.length; i++) {
  			oldImgUrls.push(oldImages[i].src);
  			console.log(oldImgUrls[i]);
		 }
		 const oldImageElList = document.getElementsByName("oldImgUrls");
		 	if(oldImgUrls[0]!=null) {
			    for(let i = 0; i < oldImageElList.length; i++) {	
			    	if(oldImgUrls[i]==null) {break;}
			    	oldImageElList[i].value = oldImgUrls[i];
			    }
	   }
		 //내용 제한 넘길 시 클라우디너리 업로드 진행
		 var result = confirm("게시글을 수정하시겠습니까?");
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
					alert("게시글 수정");
					document.move.submit();  
				}) 
		 }else {
		     return false;
	   } 
	} 
</script>

<div class="boardContainer">
<!-- 내용 시작 -->
	<p class="boardTag">중고거래 게시글 수정</p>
	
	<div class="tableContainer">
	
		<form name="move" action="MarketUpdatePro.ma" method="post">
			<table>
						<thead>
					  <tr>
					  <th class="tc"></th>
					  <th class="tc"></th>	
					  </tr>
					  </thead>
					  
						<tr>
						<td>글제목</td>
						<td><input type="text" class="formText" name="title" value="<%=dto.getTitle()%>" placeholder="제목을 입력하세요"></td>
						</tr>
			
						<tr>
						<td>글내용</td>
						<td><textarea name="content" class="formText" rows="10" cols="20" placeholder="내용을 입력하세요"><%=dto.getContent()%></textarea></td>
						</tr>
						
						<tr>
						<td>가격</td>
						<td><input type="text" class="formMiniText" name="book_price" value="<%=dto.getBook_price()%>" onkeyup="inputNumber(this);" placeholder="숫자만 입력하세요"> 원</td>
						</tr>
						
						<!-- select box start -->
						<tr>
						<td><%=cdto.getCdGrpnms()[0]%></td>
						<td><select class="selectText" name="<%=cdtoList0.get(0).getCdGrp()%>" id="<%=cdtoList0.get(0).getCdGrp()%>">
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
							<td><select class="selectText" name="<%=cdtoList1.get(0).getCdGrp()%>" id="<%=cdtoList1.get(0).getCdGrp()%>">
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
					  <td><select class="selectText" name="<%=cdtoList2.get(0).getCdGrp()%>" id="<%=cdtoList2.get(0).getCdGrp()%>">
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
						<td><select class="selectText" name="<%=cdtoList3.get(0).getCdGrp()%>" id="<%=cdtoList3.get(0).getCdGrp()%>"> 
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
						<td><select class="selectText" name="<%=cdtoList4.get(0).getCdGrp()%>" id="<%=cdtoList4.get(0).getCdGrp()%>">
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
						<td>이미지 업로드</td>
						<td><input id="img" type="file" name="files[]" accept="image/*" multiple></td>
						</tr>
						
					</table>
		
					<!-- 이미지 수 저장 -->
					<input type="hidden" value="0" name="oldImgNum"> 
					<input type="hidden" value="0" name="preImgNum"> 
					<!-- 게시글번호 저장 -->
					<input type="hidden" name="market_id" value="<%=dto.getMarket_id()%>">
					<!-- 기존 이미지 배열값 저장 -->
					<%
					for(int i = 0; i < MarketDTO.getImgLengthMax(); i++) {
					%>
						<input type="hidden" value="url" name="oldImgUrls">
					<%
			 		}
			 		%> 
					<!-- 클라우디너리의 새로운 배열값 저장 -->
					<%
					for(int i = 0; i < MarketDTO.getImgLengthMax(); i++) {
					%>
						<input type="hidden" value="url" name="imgUrls">
					<% } %>  
		</form>
	</div>
	
	<div class="imgContainer">
		<p>저장된 이미지</p>
		<div id="oldImgWrap">
			<%
			for (int i = 0; i < realUrlLength; i++) {
			%>
			<div class="oldview-container">
				<div class="oldview-label">이미지<%=i + 1%></div>
				<img class="old-image" name="old-image" src="<%=dto.getImgUrls()[i]%>" width=260px>
			</div>
			<%
			}
			%>
		</div>
	</div>
	
	<div class="imgContainer">
		<p>이미지 미리보기</p>
		<div id="imgWrap">
		</div>
	</div>
	
	<div class="conBottom">
		<input type="button" class="basic-btn" value="게시글수정" name="sub" id="sub" onclick="checkWrite();"> 
		<input type="button" class="basic-btn" value="게시글목록" onclick="location.href='MarketList.ma'">
	</div>
		
<!-- 내용 끝 -->
</div>

<!-- 푸터 들어가는 곳 -->
<jsp:include page="/inc/footer.jsp" />
<!-- 푸터 들어가는 곳 -->
