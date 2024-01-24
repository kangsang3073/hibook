package adminpage.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mypage.report.ReportDAO;

public class AdminMultidelReportPro implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 글목록에서 여러개 체크박스로 삭제
		// 체크박스 name값 chk를 받아와 배열 report_id 에 저장 
		String[] report_id = request.getParameterValues("chk");

		// 체크박스 삭제 메서드 호출 
		ReportDAO dao = new ReportDAO();
		dao.multiDelete(report_id); 

		ActionForward forward=new ActionForward();
		forward.setPath("AdminReportList.adminpage");
		forward.setRedirect(true);
		return forward;		
	}

}
