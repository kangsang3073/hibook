<%@page import="com.itwillbs.member.db.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 헤더파일들어가는 곳 -->
<jsp:include page="/inc/header.jsp"/>	
	
	<h1>member/info.jsp</h1>
	<%-- <%
// 회원정보 기준값 id => 세션에 "id"값 저장 되어있음
// 세션에서 "id" 값을 가져오기 => 변수저장
String id=(String)session.getAttribute("id");
// MemberDAO 객체생성 => 기억장소 할당
MemberDAO dao=new MemberDAO();
// MemberDTO 리턴할형 getMember(String id) 메서드 정의
// MemberDTO dto = dao.getMember(id)메서드 호출
MemberDTO dto=dao.getMember(id);
// dto 주소를 찾아가서 id,pass,name, date 가져오기=> 출력
	%> --%>
	<%
	//request에 담긴 값을 가져오려면 값을 MemberDTO형으로 다운캐스팅
	MemberDTO dto = (MemberDTO) request.getAttribute("dto");
	%>
	아이디 :
	<%=dto.getMemId()%><br> 비밀번호 :
	<%=dto.getMemPass()%><br> 이름 :
	<%=dto.getMemNm()%><br> 날짜 :
	<%=dto.getJoinDate()%><br> 회원상태 :
	<%=dto.getMemType()%>

	<a href="main.do">메인으로 이동</a>
<!-- 푸터파일들어가는 곳 -->
<jsp:include page="/inc/footer.jsp"/>
<!-- 푸터파일들어가는 곳 -->   