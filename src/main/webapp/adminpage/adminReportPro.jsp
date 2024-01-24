<%@page import="mypage.member.MemberDTO"%>
<%@page import="mypage.member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
//request 파라미터 => 변수저장 
request.setCharacterEncoding("utf-8");
String id = request.getParameter("mem_id");

//회원 아이디 db에 있는지 체크
MemberDAO dao = new MemberDAO();
MemberDTO dto = dao.userCheck(id);

if(dto != null) {
	// id 일치하면 MemberDTO 바구니에 데이터 담아서 가져오기 
	// 회원상태 '정지'로 업데이트하는 메서드
	dao.updateMemberSt(id); %>
	<script type="text/javascript">
 	 	alert("회원 정지처리 완료되었습니다.");
 	 	history.back();
	</script>
	<%
}else {
	// id가 db에 없으면 빈(null) 바구니 가져오기 
	%>
	<script type="text/javascript">
 	 	alert("해당 아이디는 존재하지 않습니다.");
 	 	history.back();
	</script>
	<%
 }
%>