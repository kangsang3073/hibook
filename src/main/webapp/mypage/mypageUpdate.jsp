<%@page import="mypage.member.MemberDTO"%>
<%@page import="mypage.member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 헤더파일들어가는 곳 -->

<jsp:include page="/inc/header.jsp" />
<link href="/resource/css/mypage.css" rel="stylesheet" type="text/css">	
	
	<div class="boardContainer">

<!-- 입력 제어 -->
<script type="text/javascript">

function fun2() {
	
	if(document.fr.mem_pass.value.length < 10 ||
			document.fr.mem_pass.value.length >14) {
		alert("비밀번호 10~14자 입력하세요.");
		document.fr.mem_pass.focus();
		return false;
	}
	
	if(document.fr.mem_pass.value != document.fr.mem_pass2.value) {
		alert("비밀번호가 일치하지 않습니다.");
		document.fr.mem_pass2.focus();
		return false;
		}		
	
	if(document.fr.phone.value == "") {
		alert("휴대폰번호를 입력하세요.");
		document.fr.phone.focus();
		return false;
	}
	
	if(document.fr.zipcode.value == "") {
		alert("주소를 입력하세요.");
		document.fr.zipcode.focus();
		return false;
	}
}

function fun3() {
	location.href="MypageMain.mypage";
}
</script>

<h1>회원정보 수정</h1>

<%
MemberDTO dto=(MemberDTO)request.getAttribute("dto");
%>

<form id="fr2" name="fr" action="MypageUpdatePro.mypage" method="post" onsubmit="return fun2()">
    <fieldset>
      <ul>
        <li>
          <label>아이디</label>
          <input type="text" name="mem_id" value="<%=dto.getMem_id()%>" readonly>
        </li>
        <li>
          <label>비밀번호</label>
          <input type="password" name="mem_pass" placeholder="10~14자, 특수문자 포함">
        </li>
        <li>
          <label>비밀번호 확인</label>
          <input type="password" name="mem_pass2">
        </li>
        <li>
          <label>이름</label>
          <input type="text" name="mem_nm" value="<%=dto.getMem_nm()%>" readonly>
        </li>
        <li>
          <label>휴대폰번호</label>
          <input type="tel" name="phone" value="<%=dto.getPhone()%>">
        </li>                  
        <li>
          <label>생년월일</label>
          <input type="date" name="birth" value="<%=dto.getBirth()%>" readonly>   
        </li>
        
<!--         주소 카카오API 사용  -->
        <li>
          <label>주소</label>
<input type="text" id="zipcode" name="zipcode" value="<%=dto.getZipcode()%>" placeholder="우편번호">
<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
<label>&nbsp;</label><input type="text" id="addr" name="addr" value="<%=dto.getAddr()%>" placeholder="주소"><br>
<label>&nbsp;</label><input type="text" id="addr_dtl" name="addr_dtl" value="<%=dto.getAddr_dtl()%>" placeholder="상세주소">
<input type="text" id="sample6_extraAddress" placeholder="참고항목">

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("sample6_extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("sample6_extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('zipcode').value = data.zonecode;
                document.getElementById("addr").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("addr_dtl").focus();
            }
        }).open();
    }
</script>
        </li>
        <!--         주소 카카오API 사용  -->
        
        
        <li>
          <label>이메일</label>
		<input type="email" name="email" value="<%=dto.getEmail()%>">
        </li>     
                               
      </ul>
    </fieldset>
    <fieldset>
    <div class=submit>
    	<input type="submit" value="회원정보 수정" class=button>
    	<input type="button" value="취소" onclick="fun3()" class=button> 
    </fieldset>
    </div>
  </form>

</div>
<!-- 푸터파일들어가는 곳 -->
<jsp:include page="/inc/footer.jsp"/>
