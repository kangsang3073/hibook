package com.itwillbs.report.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.itwillbs.report.db.ReportDAO;
import com.itwillbs.report.db.ReportDTO;

public class ReportUpdateForm implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	System.out.println("ReportUpdateForm execute()");
//	int num =Integer.parseInt(request.getParameter("report_id"));
	String num = request.getParameter("num");
	// MemberDAO 객체생성 -> 기억장소 할당
	ReportDAO dao = new ReportDAO();
	
	// MemberDTO dto = dao.getMember(id)메서드 호출
	ReportDTO dto  = dao.getreport(Integer.parseInt(num)); //- Object형
	
	// dto 정보를 들고 가기(request 정보를 담아가기)
	request.setAttribute("dto", dto); // "id" ="dto"

	//member/updateForm.jsp 주소 변경없이(false) 이동
	ActionForward forward = new ActionForward();
	forward.setPath("report/UpdateContent.jsp"); // 이동경로
	forward.setRedirect(false); // 주소 변경없이 이동해야해서 false
	return forward; // 이동정보 리턴 : forward 가 이동경로 를 나타내서 return forward
	}

}
