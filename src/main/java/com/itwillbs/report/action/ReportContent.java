package com.itwillbs.report.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.report.db.ReportDAO;
import com.itwillbs.report.db.ReportDTO;


public class ReportContent implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ReportContent execute()");
		
		// 세션에서 "id"값을 가져오기 -> 변수저장( 다운캐스팅 이루어짐.)
		HttpSession session = request.getSession(); // - 세션 객체생성
		String id = request.getParameter("id");
		
		// BoardDAO 객체생성 -> 기억장소 할당
		ReportDAO dao = new ReportDAO();
		

		// BoardDTO dto = dao.getMember(id)메서드 호출
		ReportDTO dto = dao.getreport(Integer.parseInt(id)); 
		
		// dto 정보를 들고 가기(request 정보를 담아가기)
		request.setAttribute("dto", dto); // "id" ="dto"
		
		ActionForward forward = new ActionForward();
		forward.setPath("report/ReportContent.jsp"); // 이동경로
		forward.setRedirect(false); // 주소 변경없이 이동해야해서 false
		return forward; // 이동정보 리턴 : forward 가 이동경로 를 나타내서 return forward
	}

}
