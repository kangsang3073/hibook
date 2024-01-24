
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="../inc/header.jsp"/>
<head>
<meta charset="UTF-8">
<link href="/resource/css/member.css" rel="stylesheet" type="text/css">

  <script type="text/javascript" src="../script/jquery-3.6.3.js"></script>
 <script type="text/javascript">
 	// jQuery 준비 => 대상.함수()
 	 	$(document).ready(function(){
 			alert("준비");
 	// submit 버튼을 클릭했을때 전송되어지면 이벤트 onsubmit()
 		// id="join" 폼태그 표시 => 전송	
	 	$('#join').submit(function(){
	 		alert("전송");
		//class="id"
			if($('.mem_id').val()==""){
				alert("아이디를 입력하세요");
				$('.mem_id').focus();
				// 대상 지정 * 전체, h1 태그, id=이름 #이름, class=이름 .이름
			//	$('*').css('color', 'red');
			//	$('form').css('color', 'blue');
				return false;
			}
			if($('.mem_pass').val()==""){
            	alert("비밀번호를 입력하세요");
				$('.mem_pass').focus();
				return false;
            }
            
            if($('.mem_nm').val()==""){
            	alert("이름을 입력하세요");
				$('.name').focus();
				return false;
            }
            
            //
            if($('.email').val()==""){
            	alert("이메일 입력하세요");
				$('.email').focus();
				return false;
            }
            
            if($('.email2').val()==""){
            	alert("이메일2 입력하세요");
				$('.email2').focus();
				return false;
            }
            
            if($('.email').val() != $('.email2').val()){
            	alert("이메일 틀림");
				$('.email2').focus();
				return false;
            }
            
	 	});
            
            
	 	//
	 	
//		class="dup" 클릭했을때
		$('.dup').click(function(){
//			alert("클릭");
			if($('.id').val()==""){
				alert("아이디 입력하세요");
				$('.id').focus();
				return false;
			}
			// 페이지 이동없이 디비에 가서 중복체크해서 결과를 가져와 출력 
			// idCheck.jsp
			$.ajax({
				url:'idCheck.jsp',
				data:{'id':$('.id').val()},
				success:function(result){
// 					alert(result);
					//result.trim() => 결과값 앞뒤로 공백제거
					if(result.trim()=="아이디 중복"){
						$('.divresult').html(result).css("color","red");
					}else{
						$('.divresult').html(result).css("color","blue");
					}
				}	
				
			});
			
		
			
		});



 	});//준비
 </script>
</head>



<body>
	
	<form action="MemberInsertPro.me" id="join" method="post" enctype = "multipart/form-data">
		<div class="" align="center">
			<table >
				<tr>
					<th colspan="2">회원 기본 정보</th>
				</tr>
				<tr>
					<td align="center">아이디 :</td>
					<td><input type="text" id="mem_id" name="mem_id" class="mem_id" ></td>
				</tr>
				<tr>
					<td align="center">비밀번호 :</td>
					<td><input type="password" id="mem_pass" name="mem_pass" class="mem_pass"></td>
				</tr>
				<tr>
					<td align="center">이름 :</td>
					<td><input type="text" id="mem_nm" name="mem_nm" class="mem_nm">
					</td>
				</tr>
				<tr>
					<td align="center">닉네임 :</td>
					<td><input type="text" id="nickname" name="nickname" ></td>
				</tr>

				<tr>	
					<td align="center">프로필이미지 :</td>
					<td><input type="file" id="mem_img" name="mem_img" >
					
					</td>
				</tr>
				
				<tr>
					<td align="center">생년월일 :</td>
					<td><input type="text" id="birth" name="birth" 
						style="width: 70px;">년-월-일</td>
				</tr>
				<tr>
					<td align="center">우편번호 :</td>
					<td><input type="text" id="zipcode" 
						name="zipcode" placeholder="우편번호"> <br>
				</td>
				</tr>
				<tr>
					<td align="center">주소 :</td>
					<td><input type="text" id="addr" 
						name="addr" placeholder="주소"> <br>
				</td>
				</tr>
				<tr>
					<td align="center">상세주소 :</td>
					<td><input type="text" id="addr_dtl" 
					name="addr_dtl" placeholder="상세주소">
					</td>
				</tr>
				<tr>
					<td align="center">메일주소 :</td>
					<td><input type="text" id="email" name="email" >예)id@naver.com</td>
				</tr>
				<tr>
					<td align="center">핸드폰번호 :</td>
					<td><input type="text" id="phone" name="phone" >
					</td>
				</tr>
				
					<input type="hidden" id="mem_st" value="default" name="mem_st" >
					
					<input type="hidden" id="admin_yn" value="N" name="admin_yn" >
					
					<input type="hidden" id="sns_type" name="sns_type" >
					
					<input type="hidden" id="sns_id" name="sns_id" >
					
					<input type="hidden" id="join_date" name="join_date" value="join_date" >
				
					<input type="hidden" id="delete_date" name="delete_date" value="join_date">
					
				
					
			</table>
			</div>
				<div align="center">
					<input type="submit" value="회원 가입">
					</div>
	</form>
	
			
	<br/>
	
	<footer>
<jsp:include page="/inc/footer.jsp"/>
</footer>
</body>

</html>