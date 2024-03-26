package com.itwillbs.report.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.report.db.ReportDTO;
import com.itwillbs.report.db.ReportDAO;




public class ReportList  implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("reportList execute()");
		
		HttpSession session=request.getSession();
		String id = (String)session.getAttribute("id");
		
		String adminYn = (String)session.getAttribute("adminYn");
		System.err.println(adminYn);
		
		ReportDAO dao=new ReportDAO();
		int pageSize=10;
		String pageNum=request.getParameter("pageNum");
		if(pageNum==null){
			pageNum="1";		
		}
		
		int currentPage=Integer.parseInt(pageNum);
		int startRow=(currentPage-1)*pageSize+1;
		int endRow = startRow+pageSize-1;
		
		ArrayList<ReportDTO> reportList = null;
		
		if("Y".equals(adminYn)){ 
			reportList=dao.getAdminReportList(startRow,pageSize);
		} else {
			reportList=dao.getUserReportList(id, startRow,pageSize);
		}
		
		int pageBlock=5;
		int startPage=(currentPage-1)/pageBlock*pageBlock+1;
		int endPage=startPage+pageBlock-1;
		
		int count = 0;
		
		if("Y".equals(adminYn)){ 
			count = dao.getAdminReportCount();
		} else {
			count = dao.getUserReportCount(id);
		}
		
		int pageCount=count/pageSize+(count%pageSize==0?0:1);
		if(endPage > pageCount){
			endPage = pageCount;
		}
		// request 가져온 데이터 담기
		request.setAttribute("reportList", reportList);
		
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		
//		System.out.println("-------------------------3");
		// 이동
		ActionForward forward=new ActionForward();
		
		forward.setPath("report/ReportList.jsp");
//		System.out.println("-------------------------4");
		forward.setRedirect(false);
//		System.out.println("-------------------------5");
		return forward;
		
	
	}

}
