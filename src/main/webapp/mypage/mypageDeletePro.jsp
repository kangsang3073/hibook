<%@page import="mypage.member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
// 회원테이블 회원상태=탈퇴, 회원탈퇴일자=now() update
String id = (String) session.getAttribute("id");
MemberDAO dao = new MemberDAO();

dao.updateMember(id);
session.invalidate();
%>

<script type="text/javascript">
	alert("탈퇴 완료되었습니다.");
	location.href = "/main.do";
</script>
