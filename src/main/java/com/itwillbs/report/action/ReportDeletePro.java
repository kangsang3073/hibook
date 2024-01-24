package com.itwillbs.report.action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.itwillbs.report.db.ReportDAO;
import com.itwillbs.report.db.ReportDTO;

public class ReportDeletePro implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ReportDeletePro execute()");
		
		request.setCharacterEncoding("utf-8");
		
		String num =  request.getParameter("num");
		
		ReportDAO dao=new ReportDAO();
		ReportDTO dto= dao.getreport(Integer.parseInt(num));
		
		ActionForward forward = null;
		if(dto != null) {
		 File file = new File (request.getRealPath("/upload") +"/"+ dto.getContent_img1());
		 if(file.exists() && file.isFile()) file.delete(); //파일삭제
		 
		}
		
		dao.deletereport(Integer.parseInt(num));
		
		forward = new ActionForward();
		forward.setPath("ReportList.re");
		System.out.println(forward.getPath());
		forward.setRedirect(true);
	
		
		return forward;
	}

}
